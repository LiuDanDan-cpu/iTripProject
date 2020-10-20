package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>评论数据持久层接口</b>
 *
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface CommentDao {
    /**
     * <b>据酒店id查询酒店平均分</b>
     *
     * @param query
     * @return
     * @throws Exception
     */
    List<Comment> getListByQuery(Comment query) throws Exception;

    /**
     * <b>添加评论信息</b>
     * @param comment
     * @return
     * @throws Exception
     */
        int addComment(Comment comment) throws Exception;
}
