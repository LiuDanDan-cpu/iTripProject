package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.Img;
import cn.ekgc.itrip.service.ImgService;
import cn.ekgc.itrip.transport.comment.ImgTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>图片传输层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("imgTransport")
@RequestMapping("/getImg")
public class ImgTransportImpl implements ImgTransport {
    @Autowired
    private ImgService imgService;
    /**
     * <b>根据所给对象查询评论图片</b>
     * @param img
     * @return
     * @throws Exception
     */
    @PostMapping("/getCommentImg")
    @Override
    public List<Img> getCommentImg(@RequestBody Img img) throws Exception {
        return imgService.getCommentImg(img);
    }
}
