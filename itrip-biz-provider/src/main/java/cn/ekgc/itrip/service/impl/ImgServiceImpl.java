package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.ImgDao;
import cn.ekgc.itrip.pojo.entity.Img;
import cn.ekgc.itrip.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>图片业务层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("imgService")
@Transactional
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgDao imgDao;

    /**
     * <b>根据对象查询集合</b>
     * @param img
     * @return
     * @throws Exception
     */
    @Override
    public List<Img> getCommentImg(Img img) throws Exception {
        return imgDao.getListByQuery(img);
    }
}
