package cn.ekgc.itrip.service;


import cn.ekgc.itrip.pojo.entity.Img;

import java.util.List;

/**
 * <b>图片业务层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface ImgService {
    /**
     * <b>查询图片list集合</b>
     * @param img
     * @return
     * @throws Exception
     */
    List<Img> getCommentImg(Img img)throws Exception;
}
