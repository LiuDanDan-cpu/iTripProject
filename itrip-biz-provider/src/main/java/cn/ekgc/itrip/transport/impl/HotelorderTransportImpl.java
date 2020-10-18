package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.RoomIHO;
import cn.ekgc.itrip.pojo.entity.Store;
import cn.ekgc.itrip.pojo.vo.ItripSearchOrderVO;
import cn.ekgc.itrip.pojo.vo.StoreVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.pojo.vo.itripAddHotelOrderVO;
import cn.ekgc.itrip.service.HotelorderService;
import cn.ekgc.itrip.transport.biz.HotelorderTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <b>生成订单前,获取预订信息传输层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelorderTransport")
@RequestMapping("/hotelorderTransport")
public class HotelorderTransportImpl implements HotelorderTransport {
    @Autowired
    private HotelorderService service;
    /**
     * <b>根据所给对象查询所剩余酒店</b>
     * @param roomStoreVO
     * @return
     */
    @PostMapping("/getpreorderinfo")
    @Override
    public StoreVO getPreorderInfo(@RequestBody ValidateRoomStoreVO roomStoreVO) throws Exception {
        return service.getPreorderInfo(roomStoreVO);
    }
    /**
     * <b>生成订单</b>
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/addHotelOrder")
    @Override
    public ResultVO addHotelOrder(@RequestBody itripAddHotelOrderVO query) throws Exception {
        return service.addHotelOrder(query);
    }
    /**
     * <b>根据所给订单id查询订单</b>
     * @param orderId
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/getPersonalOrderInfo")
    @Override
    public ResultVO getPersonalOrderInfo(@RequestParam Long orderId,@RequestParam String token) throws Exception {
        return service.getPersonalOrderInfo(orderId,token);
    }
    /**
     * <b>根据订单ID查看个人订单详情-房型相关信息</b>
     * @param token
     * @param orderId
     * @return
     */
    @PostMapping("getPersonalOrderRoomInfo")
    @Override
    public ResultVO getPersonalOrderRoomInfo(@RequestParam String token,@RequestParam Long orderId) throws Exception {
        return service.getPersonalOrderRoomInfo(token,orderId);
    }
    /**
     * <b>根据订单ID获取订单信息</b>
     * @param orderId
     * @return
     * @throws Exception
     */
    @PostMapping("/queryOrderById")
    @Override
    public ResultVO queryOrderById(@RequestParam Long orderId) throws Exception {
        return service.queryOrderById(orderId);
    }
    /**
     * <b>根据所给对象查询订单</b>
     * @param roomIHO
     * @return
     * @throws Exception
     */
    @PostMapping("/getListByQuery")
    @Override
    public List<RoomIHO> getLisetByQuery(@RequestBody RoomIHO roomIHO) throws Exception {
        return service.getLisetByQuery(roomIHO);
    }
    /**
     * <b>根据所给对象修改订单信息</b>
     *
     * @param query
     */
    @PostMapping("/updateRoomIHO")
    @Override
    public void updateRoomIHO(@RequestBody RoomIHO query) throws Exception {
        service.updateRoomIHO(query);
    }
    /**
     * <b>根据所给对象查询订房前的库存</b>
     * @param store
     * @return
     * @throws Exception
     */
    @PostMapping("/getStore")
    @Override
    public Store getStore(@RequestBody Store store) throws Exception {
        return service.getStore(store);
    }
    /**
     * <b>根据所给对象添加新的库存信息</b>
     * @param store
     * @throws Exception
     */
    @PostMapping("/addStore")
    @Override
    public void addStore(@RequestBody Store store) throws Exception {
        service.addStore(store);
    }
    /**
     * <b>根据个人订单列表，并分页显示</b>
     * @param orderVO
     * @return
     */
    @PostMapping("/getPersonalOrderList")
    @Override
    public ResultVO getPersonalOrderList(@RequestBody ItripSearchOrderVO orderVO) throws Exception {
        return service.getPersonalOrderList(orderVO);
    }
}
