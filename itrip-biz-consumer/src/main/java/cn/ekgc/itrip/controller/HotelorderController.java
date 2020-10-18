package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.vo.ItripSearchOrderVO;
import cn.ekgc.itrip.pojo.vo.StoreVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.pojo.vo.itripAddHotelOrderVO;
import cn.ekgc.itrip.transport.biz.HotelorderTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * <b>生成订单前,获取预订信息控制层</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController("hotelorderController")
@RequestMapping("/biz/api/hotelorder")
public class HotelorderController extends BaseController {
    @Autowired
    private HotelorderTransport transport;
    /**
     * <b>获取房间信息</b>
     * @param roomStoreVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getpreorderinfo")
    public ResultVO getPreorderInfo(@RequestBody ValidateRoomStoreVO roomStoreVO)throws Exception{
        String token=request.getHeader("token");
        roomStoreVO.setToken(token);
        StoreVO storeVO=transport.getPreorderInfo(roomStoreVO);
        if (storeVO!=null){
            return ResultVO.success(storeVO);
        }
        return ResultVO.failure("您的身份信息有误");
    }

    /**
     * <b>生成订单</b>
     * @param orderVO
     * @return
     * @throws Exception
     */
    @PostMapping("/addhotelorder")
    public ResultVO addHotelOrder(@RequestBody itripAddHotelOrderVO orderVO)throws Exception{
        orderVO.setToken(request.getHeader("token"));
        ResultVO resultVO=transport.addHotelOrder(orderVO);
        return resultVO;
    }

    /**
     * <b>根据所给id查询订单</b>
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/getpersonalorderinfo/{orderId}")
    public ResultVO getPersonalOrderInfo(@PathVariable Long orderId)throws Exception{
        String token=request.getHeader("token");
        return transport.getPersonalOrderInfo(orderId,token);
    }

    /**
     * <b>根据订单ID查看个人订单详情-房型相关信息</b>
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/getpersonalorderroominfo/{orderId}")
    public ResultVO getPersonalOrderRoomInfo(@PathVariable Long orderId)throws Exception{
        String token = request.getHeader("token");
        return transport.getPersonalOrderRoomInfo(token,orderId);
    }

    /**
     * <b>根据订单ID获取订单信息</b>
     * @param orderId
     * @return
     * @throws Exception
     */
    @GetMapping("/queryOrderById/{orderId}")
    public ResultVO queryOrderById(@PathVariable Long orderId)throws Exception{
        return transport.queryOrderById(orderId);
    }

    /**
     * <b>根据所给对象分页查询订单</b>
     * @param orderVO
     * @return
     * @throws Exception
     */
    @PostMapping("/getpersonalorderlist")
    public ResultVO getPersonalOrderList(@RequestBody ItripSearchOrderVO orderVO) throws Exception{
        orderVO.setToken(request.getHeader("token"));
        return transport.getPersonalOrderList(orderVO);
    }
}
