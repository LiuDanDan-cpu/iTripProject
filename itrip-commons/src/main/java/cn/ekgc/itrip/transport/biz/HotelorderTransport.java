package cn.ekgc.itrip.transport.biz;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.RoomIHO;
import cn.ekgc.itrip.pojo.entity.Store;
import cn.ekgc.itrip.pojo.vo.ItripSearchOrderVO;
import cn.ekgc.itrip.pojo.vo.StoreVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.pojo.vo.itripAddHotelOrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <b>生成订单前,获取预订信息传输层接口</b>
 *
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/hotelorderTransport")
public interface HotelorderTransport {
    /**
     * <b>根据所给对象查询所剩余酒店</b>
     *
     * @param roomStoreVO
     * @return
     */
    @PostMapping("/getpreorderinfo")
    StoreVO getPreorderInfo(@RequestBody ValidateRoomStoreVO roomStoreVO) throws Exception;

    /**
     * <b>生成订单</b>
     *
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/addHotelOrder")
    ResultVO addHotelOrder(@RequestBody itripAddHotelOrderVO query) throws Exception;

    /**
     * <b>根据所给订单id查询订单</b>
     *
     * @param orderId
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/getPersonalOrderInfo")
    ResultVO getPersonalOrderInfo(@RequestParam Long orderId, @RequestParam String token) throws Exception;

    /**
     * <b>根据订单ID查看个人订单详情-房型相关信息</b>
     *
     * @param token
     * @param orderId
     * @return
     */
    @PostMapping("getPersonalOrderRoomInfo")
    ResultVO getPersonalOrderRoomInfo(@RequestParam String token, @RequestParam Long orderId) throws Exception;

    /**
     * <b>根据订单ID获取订单信息</b>
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    @PostMapping("/queryOrderById")
    ResultVO queryOrderById(@RequestParam Long orderId) throws Exception;

    /**
     * <b>根据所给对象查询订单</b>
     *
     * @param roomIHO
     * @return
     * @throws Exception
     */
    @PostMapping("/getListByQuery")
    List<RoomIHO> getLisetByQuery(@RequestBody RoomIHO roomIHO) throws Exception;

    /**
     * <b>根据所给对象修改订单信息</b>
     *
     * @param query
     */
    @PostMapping("/updateRoomIHO")
    void updateRoomIHO(@RequestBody RoomIHO query) throws Exception;

    /**
     * <b>根据所给对象查询订房前的库存</b>
     * @param store
     * @return
     * @throws Exception
     */
    @PostMapping("/getStore")
    Store getStore(@RequestBody Store store)throws Exception;

    /**
     * <b>根据所给对象添加新的库存信息</b>
     * @param store
     * @throws Exception
     */
    @PostMapping("/addStore")
    void addStore(@RequestBody Store store)throws Exception;

    /**
     * <b>根据个人订单列表，并分页显示</b>
     * @param orderVO
     * @return
     */
    @PostMapping("/getPersonalOrderList")
    ResultVO getPersonalOrderList(@RequestBody ItripSearchOrderVO orderVO)throws Exception;
}
