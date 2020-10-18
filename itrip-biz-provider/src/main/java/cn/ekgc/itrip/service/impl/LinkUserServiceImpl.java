package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.dao.HotelorderDao;
import cn.ekgc.itrip.dao.LinkUserDao;
import cn.ekgc.itrip.enums.OrderStatusEnum;
import cn.ekgc.itrip.pojo.entity.LinkUser;
import cn.ekgc.itrip.pojo.entity.RoomIHO;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.LinkUserVO;
import cn.ekgc.itrip.service.LinkUserService;
import cn.ekgc.itrip.util.RedisUtil;
import cn.ekgc.itrip.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <b>常用旅客信息业务处理层</b>
 */
@Service("linkUserService")
@Transactional
public class LinkUserServiceImpl implements LinkUserService {
    @Autowired
    private LinkUserDao dao;
    @Autowired
    private RedisUtil redisUtil;
    //订单数据持久层
    @Autowired
    private HotelorderDao hotelorderDao;
    /**
     * <b>校验用户信息  获取常用联系人</b>
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public List<LinkUser> getListByQuery(LinkUser query) throws Exception {
        User user= (User) redisUtil.getFromRedis(query.getToken(), User.class);
        if (user!=null){
            //则存在该用户
            //通过该用户id查询其常用联系人
            LinkUser linkUser=new LinkUser();
            linkUser.setUserId(user.getId());
            return dao.getListByQuery(linkUser);
        }else {
            return null;
        }
    }
    /**
     * <b>根据所给信息添加常用联系人信息</b>
     * @param linkUser
     * @return
     * @throws Exception
     */
    @Override
    public boolean addLinkUser(LinkUser linkUser) throws Exception {
        User user= (User) redisUtil.getFromRedis(linkUser.getToken(), User.class);
        if (user!=null){
            linkUser.setUserId(user.getId());
            linkUser.setCreatedBy(user.getId());
            linkUser.setCreationDate(new Date());
            if (ValidateUtil.checkCellphone(linkUser.getLinkPhone())){
                int query=dao.saveLinkUser(linkUser);
                if (query > 0) {
                    return true;
                }
            }
        }
        return false;
    }
    /**
     * <b>根据所给集合删除对应的常用联系人</b>
     * @param arrayList
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO deleteLinkUser(ArrayList<String> arrayList) throws Exception {
        //取出登录对象的token
        String token=arrayList.get(arrayList.size()-1);
        User user= (User) redisUtil.getFromRedis(token,User.class);
        if (user!=null) {
            int k=0;
            System.out.println("一次循环前");
            for (int i=0;i<arrayList.size();i++){
                System.out.println("一次循环后");
                if (i==arrayList.size()-1){
                    //则是token
                    //退出循环
                    continue;
                }else {
                   //此时是需要删除的人员
                    LinkUser linkUser=dao.getLinkUser(Long.parseLong(arrayList.get(i)));
                    System.out.println("linkUser判断上"+linkUser.getLinkUserName());
                        if (linkUser!=null) {
                            System.out.println("linkUser判断下");
                            String name = linkUser.getLinkUserName();
                            System.out.println(name);
                            //通过名字在订单表中查询，如果查询出待支付的就不可以删除 ，直接返回修改失败
                            //如果都没有的话  就可以直接删除
                            RoomIHO roomIHO = new RoomIHO();
                            roomIHO.setLinkUserName(name);
                            System.out.println("list集合前");
                            List<RoomIHO> roomIHOList=hotelorderDao.getListByQuery(roomIHO);
                            System.out.println("list集合后");
                            System.out.println(roomIHOList.size());
                            for (RoomIHO iHO:roomIHOList) {
                                System.out.println("for循环后");
                                System.out.println(iHO.getId());
                                if (iHO.getOrderStatus().equals(OrderStatusEnum.ORDER_STATUS_ZERO.getCode())) {
                                    //有关联 不可以删除
                                    return ResultVO.failure("您所删除的常用联系人中有人有订单未支付。");
                                } else {
                                    //则没有关联可以删除
                                    System.out.println("没关联");
                                    k++;
                                }
                            }

                        }
                }
            }
            System.out.println("k是"+k);
            //////////////////////////////
            int a=0;
            if (k>0){
                //循环集合 删除信息
                System.out.println("2此循环前");
                for (int i=0;i<arrayList.size();i++) {
                    System.out.println("2此循环"+arrayList.get(i));
                   if (i==arrayList.size()-1){
                       //是token
                      continue;
                   }else {
                       dao.deleteLinkUser(Long.parseLong(arrayList.get(i)));
                       a++;
                   }
                }
            }
            System.out.println("a是"+a);
            if (a > 0) {
                //删除成功
                return ResultVO.success();
            }else {
                return ResultVO.failure("删除失败");
            }
        }
        return ResultVO.failure("您的身份信息已过期");
    }
    /**
     * <b>根据所给对象修改身份信息</b>
     * @param linkUserVO
     * @return
     */
    @Override
    public ResultVO updateLinkUser(LinkUserVO linkUserVO) throws Exception {
        User user= (User) redisUtil.getFromRedis(linkUserVO.getToken(),User.class);
        if (user != null) {
            LinkUser linkUser=new LinkUser();
            linkUser.setId(linkUserVO.getId());
            linkUser.setLinkUserName(linkUserVO.getLinkUserName());
            linkUser.setLinkIdCard(linkUserVO.getLinkIdCard());
            linkUser.setLinkPhone(linkUserVO.getLinkPhone());
            linkUser.setUserId(user.getId());
            linkUser.setLinkIdCardType(linkUserVO.getLinkIdCardType());
            linkUser.setModifiedBy(user.getId());
            linkUser.setModifyDate(new Date());
            int query = dao.updateLinkUser(linkUser);
            if (query > 0) {
                return ResultVO.success();
            }
        }
        return ResultVO.failure("你的身份信息已过期");
    }
}
