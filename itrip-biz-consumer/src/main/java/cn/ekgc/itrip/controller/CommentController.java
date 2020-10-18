package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.enums.ImgTypeEnum;
import cn.ekgc.itrip.enums.IsHavingImgEnum;
import cn.ekgc.itrip.enums.IsOkEnum;
import cn.ekgc.itrip.pojo.entity.*;
import cn.ekgc.itrip.pojo.vo.*;
import cn.ekgc.itrip.transport.biz.AreaTransport;
import cn.ekgc.itrip.transport.biz.HotelTransport;
import cn.ekgc.itrip.transport.biz.LabelDicTransport;
import cn.ekgc.itrip.transport.comment.CommentTransport;
import cn.ekgc.itrip.transport.comment.ImgTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>评价综合控制层</b>
 *
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("commentController")
@RequestMapping("/biz/api/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentTransport commentTransport;
    @Autowired
    private ImgTransport imgTransport;
    @Autowired
    private AreaTransport areaTransport;
    @Autowired
    private HotelTransport hotelTransport;
    @Autowired
    private LabelDicTransport labelDicTransport;
    /**
     * <b>据酒店id查询酒店平均分</b>
     *
     * @param hotelId
     * @return
     * @throws Exception
     */
    @GetMapping("/gethotelscore/{hotelId}")
    public ResultVO getHotelScore(@PathVariable("hotelId") Long hotelId) throws Exception {
//        创建对象
        Comment comment = new Comment();
        comment.setHotelId(hotelId);
        ScoreCommentVO scoreCommentVO = commentTransport.getScoreCommentVO(comment);
        return ResultVO.success(scoreCommentVO);
    }

    /**
     * <b>根据酒店id查询各类评论数量</b>
     *
     * @param hotelId
     * @return
     * @throws Exception
     */
    @GetMapping("/getcount/{hotelId}")
    public ResultVO getCount(@PathVariable("hotelId") Long hotelId) throws Exception {
        Comment query = new Comment();
        query.setHotelId(hotelId);
        CommentCountVO commentCountVO = new CommentCountVO();
        //查询酒店评论集合
        List<Comment> commentList = commentTransport.getListByQuery(query);
        Integer isOk = 0;       //推荐
        Integer improve = 0;    //有待改善
        Integer havingImg = 0;  //有图片
//        防止list集合为空
        if (commentList != null) {
            //循环集合 获取各个评论的总数量
            for (Comment comment : commentList) {
                if (comment.getIsOk() != null && comment.getIsOk().equals(IsOkEnum.IS_OK_YES.getCode())) {
                    //值得推荐
                    isOk++;
                }
                if (comment.getIsOk() != null && comment.getIsOk().equals(IsOkEnum.IS_OK_NO.getCode())) {
                    //有待改善
                    improve++;
                }
                if (comment.getIsHavingImg() != null && comment.getIsHavingImg().equals(IsHavingImgEnum.IS_HAVING_IMG_YES.getCode())) {
                    //有图片
                    havingImg++;
                }
            }
            //将其放入到vo对象中去
            commentCountVO.setAllcomment(commentList.size());
            commentCountVO.setHavingimg(havingImg);
            commentCountVO.setImprove(improve);
            commentCountVO.setIsok(isOk);
        } else {
            commentCountVO.setAllcomment(0);
            commentCountVO.setHavingimg(0);
            commentCountVO.setImprove(0);
            commentCountVO.setIsok(0);
        }
        return ResultVO.success(commentCountVO);
    }

    /**
     * <b>分页查询酒店评论列表</b>
     *
     * @param searchCommentVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getcommentlist")
    public ResultVO getCommentList(@RequestBody SearchCommentVO searchCommentVO) throws Exception {
        Page<Comment> pageInfo = commentTransport.getList(searchCommentVO);
        return ResultVO.success(pageInfo);
    }

    /**
     * <b>查询酒店评论图片</b>
     *
     * @param targetId
     * @return
     * @throws Exception
     */
    @GetMapping("/getimg/{targetId}")
    public ResultVO getImg(@PathVariable Long targetId) throws Exception {
        Img img = new Img();
        //按照其对应的要求封装其对象
        img.setTargetId(targetId);
        img.setType(ImgTypeEnum.IMG_TYPE_COMMENT.getCode());
        List<Img> imgList = imgTransport.getCommentImg(img);
        return ResultVO.success(imgList);
    }

    /**
     * <b> 获取酒店相关信息（酒店名称、酒店星级）</b>
     *
     * @param hotelId
     * @return
     * @throws Exception
     */
    @GetMapping("/gethoteldesc/{hotelId}")
    public ResultVO getHotelDesc(@PathVariable Long hotelId) throws Exception {
        Area area = new Area();
        area.setHotelId(hotelId);
        ItripHotelDescVO itripHotelDescVO = new ItripHotelDescVO();
        Hotel hotel = new Hotel();
        hotel.setId(hotelId);
        itripHotelDescVO.setHotelId(hotelId);
        itripHotelDescVO.setHotelName(hotelTransport.getListByQuery(hotel).get(0).getHotelName());
        itripHotelDescVO.setHotelLevel(areaTransport.getAreaByQuery(area).get(0).getLevel());
        return ResultVO.success(itripHotelDescVO);
    }

    /**
     * <b> 查询出游类型列表</b>
     * @return
     * @throws Exception
     */
    @GetMapping("/gettraveltype")
    public ResultVO getTravelType() throws Exception {
        LabelDic labelDic=new LabelDic();
        labelDic.setId(107L);
        LabelDic query=new LabelDic();
        query.setParent(labelDic);
        List<LabelDic> labelDicList=labelDicTransport.getLabelDicListByQuery(query);
        return ResultVO.success(labelDicList);
    }

    /**
     * <b>图片保存</b>
     * @return
     * @throws Exception
     */
    @PostMapping("/upload")
    public ResultVO upload()throws Exception{
        return ResultVO.success();
    }
}
