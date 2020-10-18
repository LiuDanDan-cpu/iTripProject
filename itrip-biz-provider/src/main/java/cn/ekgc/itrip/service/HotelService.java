package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Hotel;

import java.util.List;

/**
 * <b>酒店业务层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HotelService {
    /**
     * <b>根据对象查询集合</b>
     * @param query
     * @return
     */
    List<Hotel> getListByQuery(Hotel query)throws Exception;
}
