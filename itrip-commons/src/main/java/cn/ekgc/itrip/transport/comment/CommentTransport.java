package cn.ekgc.itrip.transport.comment;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.Comment;
import cn.ekgc.itrip.pojo.vo.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * <b>评论传输层接口</b>
 *
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/comment")
public interface CommentTransport {
    /**
     * <b>据酒店id查询酒店平均分</b>
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getScoreCommentVO")
    ScoreCommentVO getScoreCommentVO(@RequestBody Comment query) throws Exception;

    /**
     * <b>根据对象查询集合</b>
     *
     * @param query
     * @return
     */
    @PostMapping("/getListByQuery")
    List<Comment> getListByQuery(@RequestBody Comment query) throws Exception;


    /**
     * <b>查询列表</b>
     *
     * @param searchCommentVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getList")
    Page<Comment> getList(@RequestBody SearchCommentVO searchCommentVO) throws Exception;

    /**
     * <b>根据所给信息添加评论</b>
     * @param itripAddCommentVO
     * @return
     * @throws Exception
     */
    @PostMapping("/saveComment")
    ResultVO saveComment(@RequestBody ItripAddCommentVO itripAddCommentVO) throws Exception;
}
