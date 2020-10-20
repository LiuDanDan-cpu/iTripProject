package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.Comment;
import cn.ekgc.itrip.pojo.vo.ItripAddCommentVO;
import cn.ekgc.itrip.pojo.vo.Page;
import cn.ekgc.itrip.pojo.vo.ScoreCommentVO;
import cn.ekgc.itrip.pojo.vo.SearchCommentVO;
import cn.ekgc.itrip.service.CommentService;
import cn.ekgc.itrip.transport.comment.CommentTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;
import java.util.Map;

/**
 * <b>评论传输层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("commentTransport")
@RequestMapping("/comment")
public class CommentTransportImpl implements CommentTransport {
    @Autowired
    private CommentService commentService;
    /**
     * <b>据酒店id查询酒店平均分</b>
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getScoreCommentVO")
    @Override
    public ScoreCommentVO getScoreCommentVO(Comment query) throws Exception {
        return commentService.getScoreCommentVO(query);
    }
    /**
     * <b>根据对象查询集合</b>
     * @param query
     * @return
     */
    @PostMapping("/getListByQuery")
    @Override
    public List<Comment> getListByQuery(@RequestBody Comment query) throws Exception {
        return commentService.getListByQuery(query);
    }

    /**
     * <b>查询列表</b>
     * @param searchCommentVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getList")
    @Override
    public Page<Comment> getList(@RequestBody SearchCommentVO searchCommentVO) throws Exception {
        return commentService.getList(searchCommentVO);
    }
    /**
     * <b>根据所给信息添加评论</b>
     * @param itripAddCommentVO
     * @return
     * @throws Exception
     */
    @PostMapping("/saveComment")
    @Override
    public ResultVO saveComment(@RequestBody ItripAddCommentVO itripAddCommentVO) throws Exception {
        return commentService.saveComment(itripAddCommentVO);
    }
}
