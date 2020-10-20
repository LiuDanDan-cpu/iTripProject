package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.dao.CommentDao;
import cn.ekgc.itrip.dao.HotelorderDao;
import cn.ekgc.itrip.dao.ImgDao;
import cn.ekgc.itrip.enums.ImgTypeEnum;
import cn.ekgc.itrip.enums.IsHavingImgEnum;
import cn.ekgc.itrip.enums.IsOkEnum;
import cn.ekgc.itrip.enums.OrderStatusEnum;
import cn.ekgc.itrip.pojo.entity.Comment;
import cn.ekgc.itrip.pojo.entity.Img;
import cn.ekgc.itrip.pojo.entity.RoomIHO;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.ItripAddCommentVO;
import cn.ekgc.itrip.pojo.vo.Page;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import cn.ekgc.itrip.service.CommentService;
import cn.ekgc.itrip.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * <b>评论业务层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("commentService")
@Transactional
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private HotelorderDao hotelorderDao;
    @Autowired
    private ImgDao imgDao;
    /**
     * <b>据酒店id查询酒店平均分</b>
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public ScoreCommentVO getScoreCommentVO(Comment query) throws Exception {
//        根据所给对象查询
        Double avgPositionScore = 0.0;
        Double avgFacilitiesScore = 0.0;
        Double avgServiceScore = 0.0;
        Double avgHygieneScore = 0.0;
        Double avgScore = 0.0;
        List<Comment> commentList = commentDao.getListByQuery(query);
        if (commentList != null) {
            for (Comment comment : commentList) {
                if (comment.getPositionScore() != null) {
                    avgPositionScore = avgPositionScore + comment.getPositionScore();
                }
                if (comment.getFacilitiesScore()!=null){
                    avgFacilitiesScore=avgFacilitiesScore+comment.getFacilitiesScore();
                }
                if (comment.getServiceScore()!=null){
                    avgServiceScore=avgServiceScore+comment.getServiceScore();
                }
                if(comment.getHygieneScore()!=null){
                    avgHygieneScore=avgHygieneScore+comment.getHygieneScore();
                }
                if (comment.getScore()!=null){
                    avgScore=avgScore+comment.getScore();
                }
            }
            return new ScoreCommentVO(
                    new BigDecimal(avgPositionScore/commentList.size()).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgFacilitiesScore/commentList.size()).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgServiceScore/commentList.size()).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgHygieneScore/commentList.size()).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgScore/commentList.size()).setScale(1,BigDecimal.ROUND_DOWN)
            );
        } else {
            return new ScoreCommentVO(
                    new BigDecimal(avgPositionScore).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgFacilitiesScore).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgServiceScore).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgHygieneScore).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(avgScore).setScale(1,BigDecimal.ROUND_DOWN)
            );
        }

    }
    /**
     * <b>根据对象查询集合接口实现类</b>
     * @param query
     * @return
     */
    @Override
    public List<Comment> getListByQuery(Comment query) throws Exception {
        return commentDao.getListByQuery(query);
    }

    /**
     * <b>查询列表</b>
     * @param searchCommentVO
     * @return
     * @throws Exception
     */
    @Override
    public Page<Comment> getList(SearchCommentVO searchCommentVO) throws Exception {
        Comment query=new Comment();
        Page<Comment> page=new Page<Comment>();
        page.setCurPage(searchCommentVO.getPageNo());
        page.setPageSize(searchCommentVO.getPageSize());
        query.setHotelId(searchCommentVO.getHotelId());
        if (searchCommentVO.getIsHavingImg()==1&&searchCommentVO.getIsOk()==-1){
            //有图不推荐
            query.setIsHavingImg(IsHavingImgEnum.IS_HAVING_IMG_YES.getCode());
        }else if (searchCommentVO.getIsHavingImg()==-1&&searchCommentVO.getIsOk()==1){
            //值得推荐但是没图
            query.setIsOk(IsOkEnum.IS_OK_YES.getCode());
        }else if (searchCommentVO.getIsHavingImg()==-1&&searchCommentVO.getIsOk()==0){
            //有待改善
            query.setIsOk(IsOkEnum.IS_OK_NO.getCode());
        }
        PageHelper.startPage(searchCommentVO.getPageNo(),searchCommentVO.getPageSize());
        List<Comment> list=commentDao.getListByQuery(query);
        PageInfo pageInfo=new PageInfo(list);
        page.pageToPage(pageInfo);
        return page;
    }
    /**
     * <b>根据所给信息添加评论</b>
     * @param itripAddCommentVO
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO saveComment(ItripAddCommentVO itripAddCommentVO) throws Exception {
        //校验token是否有效
        User user= (User) redisUtil.getFromRedis(itripAddCommentVO.getToken(),User.class);
        if (user != null) {
            //封装评论对象
            Comment comment=new Comment();
            comment.setHotelId(itripAddCommentVO.getHotelId());
            comment.setProductId(itripAddCommentVO.getProductId());
            comment.setOrderId(itripAddCommentVO.getOrderId());
            comment.setProductType(itripAddCommentVO.getProductType());
            comment.setContent(itripAddCommentVO.getContent());
            comment.setUserId(user.getId());
            comment.setIsHavingImg(itripAddCommentVO.getIsHavingImg());

            comment.setPositionScore(itripAddCommentVO.getPositionScore());
            comment.setFacilitiesScore(itripAddCommentVO.getFacilitiesScore());
            comment.setServiceScore(itripAddCommentVO.getServiceScore());
            comment.setHygieneScore(itripAddCommentVO.getHygieneScore());
            //综合评分
            comment.setScore((itripAddCommentVO.getPositionScore()+
                                itripAddCommentVO.getFacilitiesScore()+
                                itripAddCommentVO.getServiceScore()+
                                itripAddCommentVO.getHygieneScore())/4);
            comment.setTripMode(Long.parseLong(itripAddCommentVO.getTripMode()));
            comment.setIsOk(itripAddCommentVO.getIsOk());
            comment.setCreationDate(new Date());
            comment.setCreatedBy(user.getId());
            //添加评论信息
            int query=commentDao.addComment(comment);
            if (query>0){
                //评论成功
                //修改订单中的评论信息
                RoomIHO roomIHO=new RoomIHO();
                //订单id
                roomIHO.setOrderStatus(OrderStatusEnum.ORDER_STATUS_FOUY.getCode());
                roomIHO.setId(itripAddCommentVO.getOrderId());
                hotelorderDao.updateRoomIHO(roomIHO);
//                根据订单id查询出评论id
                Comment a=new Comment();
                a.setOrderId(itripAddCommentVO.getOrderId());
                List<Comment> commentList=commentDao.getListByQuery(a);
                //下边是存图片
                Img[] imgs=itripAddCommentVO.getItripImages();
                //如果图片可以加图片的话 此时的照片是一个img的数组
                //循环数组将图片添加到数据库
                //然后再查找出照片的id 添加到评论中。
                Img img=new Img();
                for (int i=0;i<imgs.length;i++){
                    img.setType(ImgTypeEnum.IMG_TYPE_COMMENT.getCode());
                    img.setImgUrl(String.valueOf(imgs[i]));
                    img.setTargetId(commentList.get(0).getId());
                    img.setPosition(i);
                    img.setCreationDate(new Date());
                    img.setCreatedBy(user.getId());
                    imgDao.saveImg(img);
                }
                return ResultVO.success("评论添加成功");
            }else {
                //评论失败
                return ResultVO.failure("评论添加失败");
            }
        }
        return ResultVO.failure("你的身份信息已过期，请重新登录");
    }
}
