package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Area;

import java.util.List;

public interface AreaService {
    /**
     * <b>根据是否是国内信息，查询热门城市</b>
     * @param area
     * @return
     */
    List<Area> getAreaByQuery(Area area)throws Exception;
}
