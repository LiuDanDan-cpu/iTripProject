package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.RoomDao;
import cn.ekgc.itrip.pojo.entity.Room;
import cn.ekgc.itrip.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <b>房间综合信息业务层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("roomService")
@Transactional
public class RoomServiceImpl implements RoomService {
    @Autowired
    private RoomDao roomDao;
    /**
     * <b>根据对象查询房间列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<Room> getListByQuery(Room query) throws Exception {
        return roomDao.finListByQuery(query);
    }
}
