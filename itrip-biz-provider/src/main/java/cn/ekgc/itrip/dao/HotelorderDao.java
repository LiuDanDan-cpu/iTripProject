package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.RoomIHO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>生成订单前,获取预订信息数据持久层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface HotelorderDao {
    /**
     * <b>查询订单</b>
     * @param roomIHO
     * @return
     * @throws Exception
     */
    List<RoomIHO> getListByQuery(RoomIHO roomIHO)throws Exception;

    /**
     * <b>根据所给订单添加订单信息</b>
     * @param roomIHO
     * @return
     * @throws Exception
     */
    int addHotelOrder(RoomIHO roomIHO) throws Exception;

    /**
     * <b>根据所给订单信息修改订单</b>
     * @param query
     * @throws Exception
     */
    void updateRoomIHO(RoomIHO query)throws Exception;
}
