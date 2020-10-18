package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.enums.OrderStatusEnum;
import cn.ekgc.itrip.pojo.entity.RoomIHO;
import cn.ekgc.itrip.pojo.entity.Store;
import cn.ekgc.itrip.pojo.vo.TradeVO;
import cn.ekgc.itrip.transport.biz.HotelorderTransport;
import cn.ekgc.itrip.util.OrderNoUtil;
import cn.ekgc.itrip.util.TradeConstant;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * <b>直接调取支付宝的接口</b>
 */
@RestController("tradeController")
@RequestMapping("/trade/api")
public class TradeController extends BaseController {
    @Autowired
    private HotelorderTransport transport;

    @GetMapping("/prepay/{number}")
    public void playAli(@PathVariable String number) throws Exception {
        RoomIHO roomIHO=new RoomIHO();
        roomIHO.setOrderNo(number);
        List<RoomIHO> roomIHOList=transport.getLisetByQuery(roomIHO);
        if (roomIHOList!=null){
            //设置订单信息
            roomIHO=roomIHOList.get(0);
            AlipayClient alipayClient =  new  DefaultAlipayClient(TradeConstant.URL, TradeConstant.APPID,
                    TradeConstant.APP_PRIVATE_KEY, TradeConstant.FORMAT, TradeConstant.CHARSET,
                    TradeConstant.ALIPAY_PUBLIC_KEY, TradeConstant.SIGN_TYPE);  //获得初始化的AlipayClient
            AlipayTradePagePayRequest alipayRequest =  new  AlipayTradePagePayRequest(); //创建API对应的request
            alipayRequest.setReturnUrl("http://localhost/itrip");//成功之后跳转地址
            alipayRequest.setNotifyUrl("http://domain.com/CallBack/notify_url.jsp"); //在公共参数中设置回跳和通知地址
            TradeVO tradeVO=new TradeVO();
            tradeVO.setOut_trade_no(roomIHO.getOrderNo());//订单号
            tradeVO.setProduct_code(TradeConstant.PRODUCT_CODE);//销售产品码
            tradeVO.setSubject(roomIHO.getHotelName());//订单标题
            tradeVO.setTotal_amount(String.valueOf(roomIHO.getPayAmount()));//价钱
            JsonMapper jsonMapper=new JsonMapper();
            alipayRequest.setBizContent(jsonMapper.writeValueAsString(tradeVO)); //填充业务参数
            String form= "" ;
            try  {
                form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
            }  catch  (AlipayApiException e) {
                e.printStackTrace();
            }
            response.setContentType( "text/html;charset="  + TradeConstant.CHARSET);
            response.getWriter().write(form); //直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
            //修改订单状态加入支付订单完成时间 以及支付人 以及库存的改变
            RoomIHO query=new RoomIHO();
            query.setOrderNo(roomIHO.getOrderNo());
            query.setOrderStatus(OrderStatusEnum.ORDER_STATUS_TWO.getCode());
            query.setModifyDate(new Date());
            query.setModifiedBy(roomIHO.getUserId());
            transport.updateRoomIHO(query);
            Store store=new Store();
            store.setRoomId(roomIHO.getRoomId());
            store.setHotelId(roomIHO.getHotelId());
            //查出之前的库存数量
            store=transport.getStore(store);
            System.out.println(store.getRecordDate());
            System.out.println(store.getHotelId());
            System.out.println(store.getId());
            System.out.println(store.getRoomId());
            System.out.println(store.getStore());
            Store store1=new Store();

            store1.setRecordDate(new Date());
//            //用之前的数量减去本次消耗的数量 就是剩余的库存
            store1.setHotelId(store.getHotelId());
            store1.setStore(store.getStore()-roomIHO.getCount());
            store1.setCreationDate(store.getCreationDate());
//            store.setId(null);
            store1.setRoomId(store.getRoomId());
            store1.setCreationDate(store.getCreationDate());
//            response.getHeader("outBizNo");
//            System.out.println("requers"+request.getHeader("outBizNo"));
//            System.out.println( response.getHeader("outBizNo"));
//            //将新的库存数量添加进去
            transport.addStore(store1);
        }
    }
}
