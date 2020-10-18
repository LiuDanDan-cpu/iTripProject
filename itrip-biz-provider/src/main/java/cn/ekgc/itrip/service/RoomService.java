package cn.ekgc.itrip.service;

import cn.ekgc.itrip.pojo.entity.Room;

import java.util.List;

/**
 * <b>房间综合信息业务层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface RoomService {
    /**
     * <b>根据对象查询房间列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    List<Room> getListByQuery(Room query)throws Exception;
}
