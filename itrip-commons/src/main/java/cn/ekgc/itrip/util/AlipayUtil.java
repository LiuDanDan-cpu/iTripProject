package cn.ekgc.itrip.util;

import cn.ekgc.itrip.pojo.vo.TradeVO;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * <b>支付宝 接口工具类</b>
 */
@Component("alipayUtil")
public class AlipayUtil {
    @Autowired
    protected static HttpServletRequest request;
    @Autowired
    protected static HttpServletResponse response;
    @Autowired
    protected static HttpSession session;
    @Async
    //                          订单号             标题              总价钱       成功之后跳转地址    公共参数回调通知地址
    public void alipayUtil(String OrderNo, String hotelName, String payAmount,String returnUrl,String notifyUrl) throws Exception {
        AlipayClient alipayClient = new DefaultAlipayClient(TradeConstant.URL, TradeConstant.APPID,
                TradeConstant.APP_PRIVATE_KEY, TradeConstant.FORMAT, TradeConstant.CHARSET,
                TradeConstant.ALIPAY_PUBLIC_KEY, TradeConstant.SIGN_TYPE);  //获得初始化的AlipayClient
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest(); //创建API对应的request
        alipayRequest.setReturnUrl(returnUrl);//成功之后跳转地址
        alipayRequest.setNotifyUrl(notifyUrl); //在公共参数中设置回跳和通知地址
        TradeVO tradeVO = new TradeVO();
        tradeVO.setOut_trade_no(OrderNo);//订单号
        tradeVO.setProduct_code(TradeConstant.PRODUCT_CODE);//销售产品码
        tradeVO.setSubject(hotelName);//订单标题
        tradeVO.setTotal_amount(payAmount);//价钱
        JsonMapper jsonMapper = new JsonMapper();
        alipayRequest.setBizContent(jsonMapper.writeValueAsString(tradeVO)); //填充业务参数
        String form = "";
        try {
            form = alipayClient.pageExecute(alipayRequest).getBody();  //调用SDK生成表单
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }
        response.setContentType("text/html;charset=" + TradeConstant.CHARSET);
        response.getWriter().write(form); //直接将完整的表单html输出到页面
        response.getWriter().flush();
        response.getWriter().close();
    }
}
