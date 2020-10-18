package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Comment;
import cn.ekgc.itrip.pojo.vo.Page;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;

import java.util.List;
import java.util.Map;

/**
 * <b>评论业务层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface CommentService {
    /**
     * <b>据酒店id查询酒店平均分</b>
     * @param query
     * @return
     * @throws Exception
     */
    ScoreCommentVO getScoreCommentVO(Comment query)throws Exception;
    /**
     * <b>根据对象查询集合接口</b>
     * @param query
     * @return
     */
    List<Comment> getListByQuery(Comment query)throws Exception;


    /**
     * <b>查询列表</b>
     * @param searchCommentVO
     * @return
     * @throws Exception
     */
    Page<Comment> getList(SearchCommentVO searchCommentVO)throws Exception;
}
