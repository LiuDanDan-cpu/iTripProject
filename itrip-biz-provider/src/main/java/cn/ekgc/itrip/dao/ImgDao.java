package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Img;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImgDao {
    /**
     * <b>根据对象查询集合</b>
     * @param img
     * @return
     * @throws Exception
     */
    List<Img> getListByQuery(Img img)throws Exception;
}
