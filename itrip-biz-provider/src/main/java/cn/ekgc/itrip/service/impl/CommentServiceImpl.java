package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.CommentDao;
import cn.ekgc.itrip.enums.IsHavingImgEnum;
import cn.ekgc.itrip.enums.IsOkEnum;
import cn.ekgc.itrip.pojo.entity.Comment;
import cn.ekgc.itrip.pojo.vo.Page;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import cn.ekgc.itrip.service.CommentService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
                    new BigDecimal(avgScore/commentList.size()).setScale(1,BigDecimal.ROUND_DOWN));
        } else {
            return new ScoreCommentVO(
                    new BigDecimal(0.0).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(0.0).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(0.0).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(0.0).setScale(1,BigDecimal.ROUND_DOWN),
                    new BigDecimal(0.0).setScale(1,BigDecimal.ROUND_DOWN));
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
}
