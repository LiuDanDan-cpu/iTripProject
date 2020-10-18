package cn.ekgc.itrip.transport.comment;

import cn.ekgc.itrip.pojo.entity.Img;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>图片传输层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/getImg")
public interface ImgTransport {
    /**
     * <b>根据所给对象查询评论图片</b>
     * @param img
     * @return
     * @throws Exception
     */
    @PostMapping("/getCommentImg")
    List<Img> getCommentImg(@RequestBody Img img)throws Exception;
}
