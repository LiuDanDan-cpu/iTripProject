package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.Room;
import cn.ekgc.itrip.service.RoomService;
import cn.ekgc.itrip.transport.biz.RoomTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>房间综合信息传输层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("roomTransport")
@RequestMapping("/roomTransport")
public class RoomTransportImpl implements RoomTransport {
    @Autowired
    private RoomService roomService;
    /**
     * <b>根据对象查询房间列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getList")
    @Override
    public List<Room> getListByQuery(@RequestBody Room query) throws Exception {
        return roomService.getListByQuery(query);
    }
}
