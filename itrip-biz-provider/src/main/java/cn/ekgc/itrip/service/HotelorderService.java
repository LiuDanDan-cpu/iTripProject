package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.RoomIHO;
import cn.ekgc.itrip.pojo.entity.Store;
import cn.ekgc.itrip.pojo.vo.ItripSearchOrderVO;
import cn.ekgc.itrip.pojo.vo.StoreVO;
import cn.ekgc.itrip.pojo.vo.ValidateRoomStoreVO;
import cn.ekgc.itrip.pojo.vo.itripAddHotelOrderVO;

import java.util.List;

/**
 * <b>生成订单前,获取预订信息业务层接口</b>
 *
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
public interface HotelorderService {
    /**
     * <b>根据所给对象查询所剩余酒店</b>
     *
     * @param roomStoreVO
     * @return
     */
    StoreVO getPreorderInfo(ValidateRoomStoreVO roomStoreVO) throws Exception;

    /**
     * <b>生成订单</b>
     *
     * @param query
     * @return
     * @throws Exception
     */
    ResultVO addHotelOrder(itripAddHotelOrderVO query) throws Exception;

    /**
     * <b>根据所给订单id查询订单</b>
     *
     * @param orderId
     * @param token
     * @return
     * @throws Exception
     */
    ResultVO getPersonalOrderInfo(Long orderId, String token) throws Exception;

    /**
     * <b>根据订单ID查看个人订单详情-房型相关信息</b>
     *
     * @param token
     * @param orderId
     * @return
     */
    ResultVO getPersonalOrderRoomInfo(String token, Long orderId) throws Exception;

    /**
     * <b>根据订单ID获取订单信息</b>
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    ResultVO queryOrderById(Long orderId) throws Exception;
    /**
     * <b>根据所给对象查询订单</b>
     * @param roomIHO
     * @return
     * @throws Exception
     */
    List<RoomIHO> getLisetByQuery(RoomIHO roomIHO)throws Exception;

    /**
     * <b>修改订单</b>
     * @param query
     * @throws Exception
     */
    void updateRoomIHO(RoomIHO query)throws Exception;

    /**
     * <b>根据所给对象查询库存</b>
     * @param store
     * @return
     * @throws Exception
     */
    Store getStore(Store store) throws Exception;

    /**
     * <b>根据所给对象添加新的库存信息</b>
     * @param store
     * @throws Exception
     */
    void addStore(Store store)throws Exception;
    /**
     * <b>根据个人订单列表，并分页显示</b>
     * @param orderVO
     * @return
     */
    ResultVO getPersonalOrderList(ItripSearchOrderVO orderVO)throws Exception;
}
