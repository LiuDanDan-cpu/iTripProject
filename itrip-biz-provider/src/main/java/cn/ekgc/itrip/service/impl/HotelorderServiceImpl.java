package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.dao.*;
import cn.ekgc.itrip.enums.OrderStatusEnum;
import cn.ekgc.itrip.enums.ProductTypeEnum;
import cn.ekgc.itrip.pojo.entity.*;
import cn.ekgc.itrip.pojo.vo.*;
import cn.ekgc.itrip.service.HotelorderService;
import cn.ekgc.itrip.util.Number;
import cn.ekgc.itrip.util.RedisUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;


/**
 * <b>生成订单前,获取预订信息业务层接口实现类</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Service("hotelorderService")
@Transactional
public class HotelorderServiceImpl implements HotelorderService {
    @Autowired
    private RoomDao roomDao;//房间数据持久层
    @Autowired
    private HotelDao hotelDao;//酒店数据持久层
    @Autowired
    private HotelorderDao dao;//订单信息数据持久层
    @Autowired
    private StoreDao storeDao;//库存数据持久层
    @Autowired
    private LabelDicDao labelDicDao;//特色信息数据持久层
    @Autowired
    private RedisUtil redisUtil;//redis工具类

    /**
     * <b>根据所给对象查询所剩余酒店</b>
     * @param roomStoreVO
     * @return
     * @throws Exception
     */
    @Override
    public StoreVO getPreorderInfo(ValidateRoomStoreVO roomStoreVO) throws Exception {
        StoreVO storeVO=new StoreVO();
        //取出redis中的user对象
        User user=(User) redisUtil.getFromRedis(roomStoreVO.getToken(), User.class);
        if (user!=null) {
 /************************查询酒店 BEGIN**************************************/
            Hotel hotel=new Hotel();
            hotel.setId(roomStoreVO.getHotelId());
            List<Hotel> hotelList=hotelDao.getListByQuery(hotel);
            if (hotelList!=null){
                //将酒店id和酒店名称添加到返回对象
                storeVO.setHotelId(hotelList.get(0).getId());
                storeVO.setHotelName(hotelList.get(0).getHotelName());
            }
/************************查询酒店 END**************************************/
/************************查询库存 BEGIN**************************************/
            Store store=new Store();
            store.setHotelId(roomStoreVO.getHotelId());
            store.setRoomId(roomStoreVO.getRoomId());
            //查询出库存表中最新库存
            Store stores=storeDao.getListByQuery(store);
            //查询订单表中为状态为0 的数据
            RoomIHO roomIHO=new RoomIHO();
            roomIHO.setHotelId(roomStoreVO.getHotelId());
            roomIHO.setRoomId(roomStoreVO.getRoomId());
            //设置订单的类型
            roomIHO.setOrderType(ProductTypeEnum.PRODUCT_TYPE_HOTEL.getCode());
            //设置酒店订单的状态
            roomIHO.setOrderStatus(OrderStatusEnum.ORDER_STATUS_ZERO.getCode());
            List<RoomIHO> roomIHOList=dao.getListByQuery(roomIHO);
            if (roomIHOList!=null){
                //说明此时订单中有未支付的订单
                int roomCount=0;
                //循环集合 拿出所有已经在未支付的订单 计算出总数
                for (RoomIHO roomIHO1:roomIHOList) {
                    roomCount=roomCount+roomIHO1.getCount();
                }
                System.out.println(roomCount);
                //使用库存 减去未支付
                storeVO.setStore(stores.getStore()-roomCount);
            }else {
                //说明没有
                storeVO.setStore(stores.getStore());
            }
/************************查询库存 END**************************************/
/************************查询酒店房间 BEGIN**************************************/
            //根据所给的对象查询酒店房间的价格
            Room room=new Room();
            room.setId(roomStoreVO.getRoomId());
            room.setHotelId(roomStoreVO.getHotelId());
            List<Room> roomList=roomDao.finListByQuery(room);
            if (roomList!=null){
                //将酒店房间的价格传入要返回的对象中
                storeVO.setPrice(roomList.get(0).getRoomPrice());
            }
/************************查询酒店房间 END**************************************/
            return storeVO;
        }
        return null;
    }

    /**
     * <b>生成订单</b>
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO addHotelOrder(itripAddHotelOrderVO query) throws Exception {
        User user=(User) redisUtil.getFromRedis(query.getToken(), User.class);
        if (user != null) {
            //封装订单对象
            RoomIHO roomIHO=new RoomIHO();
            Room room=new Room();
            room.setHotelId(query.getHotelId());
            room.setId(query.getRoomId());

            roomIHO.setOrderType(query.getOrderType());
            //生成订单编号
            roomIHO.setOrderNo(Number.getNumber(query.getOrderType()));
            roomIHO.setUserId(user.getId());
            roomIHO.setHotelId(query.getHotelId());
            roomIHO.setHotelName(query.getHotelName());
            roomIHO.setRoomId(query.getRoomId());
            roomIHO.setCount(query.getCount());
//            预定天数
            //通过所给的入住时间和退房时间计算天数
            Long time=query.getCheckOutDate().getTime()-query.getCheckInDate().getTime();
            Long days=time/(24*3600*1000);
            System.out.println("天数是"+new Integer(Math.toIntExact(days)));
            roomIHO.setBookingDays(new Integer(Math.toIntExact(days)));
            roomIHO.setCheckInDate(query.getCheckInDate());
            roomIHO.setCheckOutDate(query.getCheckOutDate());
            roomIHO.setOrderStatus(OrderStatusEnum.ORDER_STATUS_ZERO.getCode());
            roomIHO.setNoticePhone(query.getNoticePhone());
            roomIHO.setNoticeEmail(query.getNoticeEmail());
            roomIHO.setSpecialRequirement(query.getSpecialRequirement());
            roomIHO.setIsNeedInvoice(query.getIsNeedInvoice());
            roomIHO.setInvoiceType(query.getInvoiceType());
            roomIHO.setInvoiceHead(query.getInvoiceHead());
            System.out.println(query.getLinkUser());
            String linkUserName="";
            for (LinkUser linkUser:query.getLinkUser()) {
                linkUserName=linkUserName+linkUser.getLinkUserName()+",";
            }
            roomIHO.setLinkUserName(linkUserName.substring(0,linkUserName.length()-1));
            roomIHO.setBookType(0);
            roomIHO.setCreationDate(new Date());
            roomIHO.setCreatedBy(user.getId());
            //计算总钱数
            Long q=days*(roomDao.finListByQuery(room).get(0).getRoomPrice().longValue());
            roomIHO.setPayAmount(new BigDecimal(q));
            int sum=dao.addHotelOrder(roomIHO);
            if (sum > 0) {
                System.out.println("roomIho"+roomIHO.getOrderNo());
                RoomIHO roomIHO1=new RoomIHO();
                roomIHO1.setOrderNo(roomIHO.getOrderNo());
                List<RoomIHO> roomIHOList=dao.getListByQuery(roomIHO1);
                System.out.println("长度是"+roomIHOList.size());
                if (roomIHOList != null) {
                    Map<String,Object> map=new HashMap<>();
                    System.out.println(roomIHOList.get(0).getId());
                    System.out.println(roomIHOList.get(0).getOrderNo());
                    map.put("id",roomIHOList.get(0).getId());
                    map.put("orderNo",roomIHOList.get(0).getOrderNo());
                    //订单信息添加成功
                    return ResultVO.success(map);
                }
            }
        }
        return ResultVO.failure("您的验证信息已失效，请重新登录");
    }

    /**
     * <b>根据所给id查询订单信息</b>
     * @param orderId
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO getPersonalOrderInfo(Long orderId, String token) throws Exception {
        //根据所给token校验登录用户
        User user= (User) redisUtil.getFromRedis(token,User.class);
        if (user!=null){
            //根据订单id查询订单信息
            RoomIHO roomIHO=new RoomIHO();
            roomIHO.setId(orderId);
            List<RoomIHO> roomIHOList=dao.getListByQuery(roomIHO);
            System.out.println("wozai ");
            if (roomIHOList!=null){
                return ResultVO.success(roomIHOList.get(0));
            }else {
                return ResultVO.failure("没有有效订单");
            }
        }
        return ResultVO.failure("用户信息失效，请重新登录");
    }
    /**
     * <b>根据订单ID查看个人订单详情-房型相关信息</b>
     * @param token
     * @param orderId
     * @return
     */
    @Override
    public ResultVO getPersonalOrderRoomInfo(String token, Long orderId) throws Exception {
        User user= (User) redisUtil.getFromRedis(token,User.class);
        ItripPersonalOrderRoomVO orderRoomVO=new ItripPersonalOrderRoomVO();
        if (user != null) {
            //根据订单id 查询订单信息
            RoomIHO iho=new RoomIHO();
            iho.setId(orderId);
            //查询出酒店订单信息
            List<RoomIHO> roomIHOList=dao.getListByQuery(iho);
            if (roomIHOList != null) {
                if (roomIHOList.size()>0) {
                    iho = roomIHOList.get(0);
                    //根据订单信息获取对应酒店星级和位置
                    Hotel hotel = new Hotel();
                    hotel.setId(iho.getHotelId());
                    List<Hotel> hotelList = hotelDao.getListByQuery(hotel);
                    //酒店信息
                    hotel = hotelList.get(0);
                    //查询房间的各个属性
                    Room room = new Room();
                    room.setId(iho.getRoomId());
                    room.setHotelId(iho.getHotelId());
                    List<Room> roomList = roomDao.finListByQuery(room);
                    room = roomList.get(0);
                    orderRoomVO.setId(iho.getId());
                    orderRoomVO.setHotelId(hotel.getId());
                    orderRoomVO.setHotelName(hotel.getHotelName());
                    orderRoomVO.setHotelLevel(hotel.getHotelLevel());
                    orderRoomVO.setAddress(hotel.getAddress());
                    orderRoomVO.setRoomId(room.getId());
                    orderRoomVO.setRoomTitle(room.getRoomTitle());
                    orderRoomVO.setRoomBedTypeId(room.getRoomBedTypeId());
                    orderRoomVO.setCheckInDate(iho.getCheckInDate());
                    orderRoomVO.setCheckOutDate(iho.getCheckOutDate());
                    orderRoomVO.setCount(iho.getCount());
                    orderRoomVO.setBookingDays(iho.getBookingDays());
                    orderRoomVO.setLinkUserName(iho.getLinkUserName());
                    orderRoomVO.setSpecialRequirement(iho.getSpecialRequirement());
                    orderRoomVO.setPayAmount(iho.getPayAmount());
                    orderRoomVO.setRoomPayType(room.getPayType());
                    orderRoomVO.setIsHavingBreakfast(room.getIsHavingBreakfast());
                    //查询床型
                    //设置父类信息
                    LabelDic labelDic = new LabelDic();
                    labelDic.setId(1L);
                    //设置床型id
                    LabelDic query = new LabelDic();
                    query.setParent(labelDic);
                    query.setId(room.getRoomBedTypeId());
                    List<LabelDic> labelDicList = labelDicDao.findListByQuery(query);
                    orderRoomVO.setRoomBedTypeName(labelDicList.get(0).getName());
                    return ResultVO.success(orderRoomVO);
                }else {
                    return ResultVO.failure("订单信息有误");
                }
            }
            return ResultVO.failure("没有对应订单");
        }else {
          return ResultVO.failure("您的身份信息失效，请重新登录");
        }
    }

    /**
     * <b></b>
     * @param orderId
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO queryOrderById(Long orderId) throws Exception {
        RoomIHO query=new RoomIHO();
        query.setId(orderId);
        List<RoomIHO> roomIHO=dao.getListByQuery(query);
        if (roomIHO!=null){
            return ResultVO.success(roomIHO.get(0));
        }
        return ResultVO.failure("没有查询到相应订单");
    }

    @Override
    public List<RoomIHO> getLisetByQuery(RoomIHO roomIHO) throws Exception {
        List<RoomIHO> roomIHOList=dao.getListByQuery(roomIHO);
        if (roomIHOList!=null){
            return roomIHOList;
        }
        return null;
    }
    /**
     * <b>修改订单</b>
     * @param query
     * @throws Exception
     */
    @Override
    public void updateRoomIHO(RoomIHO query) throws Exception {
        dao.updateRoomIHO(query);
    }

    /**
     * <b>根据所给对象查询最新库存</b>
     * @param store
     * @return
     * @throws Exception
     */
    @Override
    public Store getStore(Store store) throws Exception {
        return storeDao.getListByQuery(store);
    }
    /**
     * <b>根据所给对象添加新的库存信息</b>
     * @param store
     * @throws Exception
     */
    @Override
    public void addStore(Store store) throws Exception {
        storeDao.addStore(store);
    }
    /**
     * <b>根据个人订单列表，并分页显示</b>
     * @param orderVO
     * @return
     */
    @Override
    public ResultVO getPersonalOrderList(ItripSearchOrderVO orderVO) throws Exception {
        User user= (User) redisUtil.getFromRedis(orderVO.getToken(),User.class);
        if (user != null) {
            if (orderVO.getPageNo()==null){
                orderVO.setPageNo(1);
            }
            Page<RoomIHO> page=new Page<>();
            page.setPageSize(orderVO.getPageSize());
            page.setCurPage(orderVO.getPageNo());
            RoomIHO roomIHO=new RoomIHO();
            roomIHO.setUserId(user.getId());
            if (orderVO.getOrderType() != -1) {
                roomIHO.setOrderType(orderVO.getOrderType());
            }
            /*
            订单状态（0：待支付 1:已取消 2:支付成功 3:已消费 4：已点评）
             */
            if (orderVO.getOrderStatus()!=-1) {
                roomIHO.setOrderStatus(orderVO.getOrderStatus());
            }
            if (orderVO.getOrderNo() != null) {
                roomIHO.setOrderNo(orderVO.getOrderNo());
            }
            if (orderVO.getLinkUserName() != null) {
                roomIHO.setLinkUserName(orderVO.getLinkUserName());
            }
           if (orderVO.getStartDate()!=null){
               roomIHO.setCreationDate(orderVO.getStartDate());
           }
            if (orderVO.getEndDate()!=null){
                roomIHO.setModifyDate(orderVO.getEndDate());
            }
            PageHelper.startPage(orderVO.getPageNo(),orderVO.getPageSize());
            List<RoomIHO> roomIHOList= dao.getListByQuery(roomIHO);
            PageInfo pageInfo=new PageInfo(roomIHOList);
            page.pageToPage(pageInfo);
            return ResultVO.success(page);
        }
        return ResultVO.failure("您的身份信息错误请重新登录");
    }
}
