package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.pojo.entity.Hotel;
import cn.ekgc.itrip.service.HotelService;
import cn.ekgc.itrip.transport.biz.HotelTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <b>酒店传输层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelTransport")
@RequestMapping("/Hotel")
public class HotelTransportImpl implements HotelTransport {
    @Autowired
    private HotelService hotelService;
    /**
     * <b>根据对象查询集合</b>
     * @param query
     * @return
     */
    @PostMapping("/getQuery")
    @Override
    public List<Hotel> getListByQuery(@RequestBody Hotel query) throws Exception{
        return hotelService.getListByQuery(query);
    }
}
