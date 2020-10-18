package cn.ekgc.itrip.transport.biz;

import cn.ekgc.itrip.pojo.entity.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * <b>房间综合信息传输层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/roomTransport")
public interface RoomTransport {
    /**
     * <b>根据对象查询房间列表</b>
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getList")
    List<Room> getListByQuery(@RequestBody Room query)throws Exception;
}
