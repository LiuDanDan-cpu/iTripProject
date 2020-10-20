package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaDao {
    /**
     * <b>查询列表</b>
     * @param area
     * @return
     * @throws Exception
     */
    List<Area> findListByQuery(Area area)throws Exception;

}
