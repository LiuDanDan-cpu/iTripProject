package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.Room;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>房间综合信息数据持久层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface RoomDao {
    /**
     * <b>根据对象查询房间列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    List<Room> finListByQuery(Room query)throws Exception;
}
