package cn.ekgc.itrip.service.impl;
/***
 *本代码中所有的被注起来的代码都是 原来的代码
 */
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.dao.UserDao;
import cn.ekgc.itrip.enums.ActivatedEnum;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.service.UserService;
import cn.ekgc.itrip.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("userService")
//下边是事务处理注解
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    //这是redis 的工具类
    private RedisUtil redisUtil;
    /**
     * 没使用工具类时 应该需要的 在有了工具类之后不需要的原因是 在工具类中引入了
     * private StringRedisTemplate template;
     **/
    @Autowired
    private EmailUtils emailUtils;
    @Autowired
    private SmsUtil smsUtil;

    /**
     * <b>根据用户名校验该用户名被使用</b>
     *
     * @param name
     * @return true 则可以添加
     */
    @Override
    public boolean queryUserCodeIsCanUsed(String name) throws Exception {
        User entity = new User();
        entity.setUserCode(name);
        List<User> userList = userDao.getListByEntity(entity);
        if (userList != null && userList.size() > 0) {
            return false;
        }
        return true;
    }

    /**
     * <b>根据所给对象添加用户</b>
     *
     * @param user
     * @return
     * @throws Exception
     */
    @Override
    public boolean saveUser(User user) throws Exception {
        Integer entity = userDao.save(user);
        if (entity > 0) {
            //添加成功
            //再次将用户信息进行查询
            User query = userDao.getListByEntity(user).get(0);
            //获得用户id将用户id 作为flatId 进行设定
            System.out.println(query.getId());
            user.setId(query.getId());
            user.setFlatID(query.getId());
            //修改用户信息
            userDao.update(user);
            //生成随机验证码
            String activationCode = ActivetionCodeUtil.generate();
/***************************************************************************************************************//*
将生成的随机验证码保存到Redis 中 ，使用userCode作为key
            template.opsForValue().set(user.getUserCode(), activationCode);
设置保存时间 保存时间为5分钟
            template.expire(user.getUserCode(), Long.parseLong(ConstantUtils.MAIL_EXPIRE), TimeUnit.MINUTES);
*//***************************************************************************************************************/
            redisUtil.saveToken(user.getUserCode(),activationCode,Long.parseLong(ConstantUtils.MAIL_EXPIRE));
            if (user.getUserCode().indexOf("@") > -1) {
                //说明此处为邮箱的验证
                //将激活码发送到邮箱
                String context="<div style='width: 600px; margin: 0 auto;'>" +
                "<h3>亲爱的：<strong>" + user.getUserCode() + "</strong></h3>" +
                        "<p>您已经成功的注册成为<strong>爱旅行</strong>会员！</p>" +
                        "<p>只剩下最后一步，激活您的账号，您的激活码是：<strong style='color: red;'>" + activationCode + "</strong></p>" +
                        "<p>请在<strong>" + ConstantUtils.MAIL_EXPIRE + "</strong>分钟之内进行账号激活！</p></div>";
                emailUtils.sendMail(user.getUserCode(),"爱旅行用户激活",context);
            } else {
                //则此处为手机的验证
                smsUtil.sendActivationCodeByCloopen(user.getUserCode(),activationCode);
            }
            return true;
        }
        return false;
    }

    /**
     * <b>根据所给的手机号或者邮箱进行激活用户</b>
     *
     * @param userCode
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public boolean activateUser(String userCode, String code)
            throws Exception {
        User user = new User();
        user.setUserCode(userCode);
        user = userDao.getListByEntity(user).get(0);
            //将将激活 改为1  已激活状态
        user.setActivated(ActivatedEnum.ACTIVATED_TRUE.getCode());
            //调取数据持久
        int qruey = userDao.update(user);
        if (qruey > 0) {
            return true;
        }
        return false;
    }

    /**
     * <b>根据所给用户信息查询用户</b>
     * @param entity
     * @return
     * @throws Exception
     */
    @Override
    public User getUser(User entity) throws Exception {
        List<User> userList=userDao.getListByEntity(entity);
        if (userList!=null&&userList.size()>0){
            return userList.get(0);
        }
        return null;
    }

    @Override
    public String getActivated(String userCode) throws Exception {
/**************************************************************************************
        String code=template.opsForValue().get(userCode);
***************************************************************************************/
        String code= (String) redisUtil.getFromRedis(userCode,String.class);
        if (code!=null){
            return code;
        }
        return null;
    }

    @Override
    public void send(String userCode) throws Exception {
        //生成随机验证码
        String activationCode = ActivetionCodeUtil.generate();
/**************************************************************************************
        //将生成的随机验证码保存到Redis 中 ，使用userCode作为key
        template.opsForValue().set(userCode, activationCode);
        //设置保存时间 保存时间为5分钟
        template.expire(userCode, Long.parseLong(ConstantUtils.MAIL_EXPIRE), TimeUnit.MINUTES);
 ***************************************************************************************/
        redisUtil.saveToken(userCode,activationCode, Long.valueOf(ConstantUtils.MAIL_EXPIRE));
        if (userCode.indexOf("@")!=-1){
            //邮箱
            //说明此处为邮箱的验证
            //将激活码发送到邮箱
            String context="<div style='width: 600px; margin: 0 auto;'>" +
                    "<h3>亲爱的：<strong>" + userCode + "</strong></h3>" +
                    "<p>您已经成功的注册成为<strong>爱旅行</strong>会员！</p>" +
                    "<p>只剩下最后一步，激活您的账号，您的激活码是：<strong style='color: red;'>" + activationCode + "</strong></p>" +
                    "<p>请在<strong>" + ConstantUtils.MAIL_EXPIRE + "</strong>分钟之内进行账号激活！</p></div>";
            emailUtils.sendMail(userCode,"爱旅行用户激活",context);
        }else {
            //手机
            //则此处为手机的验证
            smsUtil.sendActivationCodeByCloopen(userCode,activationCode);
        }
    }

    /**
     * <b>用户登录</b>
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO loginUser(String name, String password) throws Exception {
        //进行用户查询
        User user=new User();
        user.setUserCode(name);
        List<User> list =userDao.getListByEntity(user);
        System.out.println(list.get(0).getId());
        user=list.get(0);
        if (user!=null){
//            说明此时有用户信息

            //判断其是否为已激活状态
            if (user.getActivated().equals(ActivatedEnum.ACTIVATED_TRUE.getCode())){
                //说明用户为激活状态
                //判断密码是否为正确的
                if (MD5Util.encrypt(password).equals(user.getUserPassword())){
//                    生成token，并且将其用户绑定到redis 中
                    String toKen=MD5Util.encrypt(name);
                    /**
                    template.opsForValue().set(token,user);
                     **/
                    //此处传的值为是为了登录 所以保存的时间会久一点
                    redisUtil.saveToken(toKen,user,ConstantUtils.TOKEN_EXPRIE);
                    Map map=new HashMap();
                    map.put("token",toKen);
                    map.put("seting",ConstantUtils.TOKEN_EXPRIE);
                    return ResultVO.success(map);
                }else {
                    return ResultVO.failure("请输入正确的用户信息");
                }
            }else {
                //该用户没有激活
                return ResultVO.failure("该用户还未激活，请先进行激活");
            }
        }else {
//            说明输入的信息有误
            return ResultVO.failure("请输入123456");
        }
    }
    /**
     * <b>用户退出</b>
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public ResultVO userOut(String token) throws Exception {
        System.out.println(token);
        if (token!=null){
            User user= (User) redisUtil.getFromRedis(token,User.class);
            if (user!=null){
                redisUtil.saveToken(token,null, 0L);
            }
        }
        return ResultVO.success("退出成功");
    }
}
