package cn.ekgc.itrip.transport.biz;

import cn.ekgc.itrip.pojo.entity.Hotel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * <b>酒店传输层接口</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/Hotel")
public interface HotelTransport {
    /**
     * <b>根据对象查询集合</b>
     * @param query
     * @return
     */
    @PostMapping("/getQuery")
    List<Hotel> getListByQuery(@RequestBody Hotel query)throws Exception;
}
