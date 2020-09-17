package cn.ekgc.itrip.controller;

/***
 * 1、@controller 控制器（注入服务）
 *
 * 2、@service 服务（注入dao）
 *
 * 3、@repository dao（实现dao访问）
 *
 * 4、@component （把普通pojo实例化到spring容器中，相当于配置文件中的<bean id="" class=""/>）
 */

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.enums.UserTypeEnum;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.pojo.vo.UserVO;
import cn.ekgc.itrip.transport.user.UserTransport;
import cn.ekgc.itrip.enums.ActivatedEnum;
import cn.ekgc.itrip.util.MD5Util;
import cn.ekgc.itrip.util.ValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController("userController")
@RequestMapping("/auth/api")
public class UserController extends BaseController {
    @Autowired
    private UserTransport userTransport;


    /**
     * <b>验证用户名是否被占用</b>
     *
     * @param name
     * @return
     * @throws Exception
     */
    @GetMapping("/ckusr")
    public ResultVO checkName(String name) throws Exception {
        boolean isCanUsed = userTransport.queryUserCodeIsCanUsed(name);
        if (isCanUsed) {
            return ResultVO.success();
        }
        return ResultVO.failure("该邮箱或手机号已经被注册");
    }

    /**
     * <b>添加用户信息</b>
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/doregister")
    public ResultVO saveUser(@RequestBody UserVO userVO) throws Exception{
       //校验用户所提交的邮箱是否有效
        System.out.println(userVO.getUserCode()+"====="+userVO.getUserPassword());
        if (ValidateUtil.checkEmail(userVO.getUserCode())
                &&ValidateUtil.checkPassword(userVO.getUserPassword())){
            //校验邮箱唯一性
            if (userTransport.queryUserCodeIsCanUsed(userVO.getUserCode())){
                //进行用户保存
                //将UserVO 转换为User
                User user=new User();
                user.setUserCode(userVO.getUserCode());
                user.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
                user.setUserName(userVO.getUserName());
                //用户类型 也就是用户来源
                user.setUserType(UserTypeEnum.USER_TYPE_SELF.getCode());
                user.setCreationDate(new Date());
                user.setModifyDate(new Date());
                user.setActivated(ActivatedEnum.ACTIVATED_FALSE.getCode());
                boolean saveFlag=userTransport.saveUser(user);
                if (saveFlag){
                    return ResultVO.success();
                }else {
                    return ResultVO.failure("保存失败");
                }
            }else {
                return ResultVO.failure("该邮箱地址已被注册");
            }
        }else {
            return ResultVO.failure("请填写正确的邮箱地址和登录密码");
        }
    }
    /**
     * <b>添加用户手机号信息</b>
     * @param userVO
     * @return
     * @throws Exception
     */
    @PostMapping("/registerbyphone")
    public ResultVO saveUserByCellphone(@RequestBody UserVO userVO) throws Exception{
        //校验用户所提交的邮箱是否有效
        if (ValidateUtil.checkCellphone(userVO.getUserCode())
                &&ValidateUtil.checkPassword(userVO.getUserPassword())){
            //校验邮箱唯一性
            if (userTransport.queryUserCodeIsCanUsed(userVO.getUserCode())){
                //进行用户保存
                //将UserVO 转换为User
                User user=new User();
                user.setUserCode(userVO.getUserCode());
                user.setUserPassword(MD5Util.encrypt(userVO.getUserPassword()));
                user.setUserName(userVO.getUserName());
                //用户类型 也就是用户来源
                user.setUserType(UserTypeEnum.USER_TYPE_SELF.getCode());
                user.setCreationDate(new Date());
                user.setModifyDate(new Date());
                user.setActivated(ActivatedEnum.ACTIVATED_FALSE.getCode());
                boolean saveFlag=userTransport.saveUser(user);
                if (saveFlag){
                    return ResultVO.success();
                }else {
                    return ResultVO.failure("保存失败");
                }
            }else {
                return ResultVO.failure("该手机号已被注册");
            }
        }else {
            return ResultVO.failure("请填写正确的手机号码和登录密码");
        }
    }

    /**
     * <b>邮箱激活</b>
     * @param user
     * @param code
     * @return
     * @throws Exception
     */
    @PutMapping("/activate")
    public ResultVO verifyCodeByMail(String user,String code)throws Exception{
        if (ValidateUtil.checkEmail(user)
                &&code!=null&&!"".equals(code)){
            User entity=new User();
            entity.setUserCode(user);
            //通过user查询是否注册
            entity = userTransport.getUser(entity);
            if(entity.getId()!=null){
                //则此时有该用户
                if (entity.getActivated().equals(ActivatedEnum.ACTIVATED_FALSE.getCode())){
                    //说明此时没有激活
                    //获取redis中的真激活码
                    String isCode = userTransport.getActivated(user);
                    if (isCode!=null){
                        //可以进行对比
                        if (code.equals(isCode)){
                            //可以激活
                            //邮箱验证成功 可以向后传递
                            boolean qurey=userTransport.activateUser(user,code);
                            if (qurey){
                                //修改成功
                                return ResultVO.success("激活成功");
                            }else {
                                return ResultVO.failure("激活失败 请重新激活");
                            }
                        }else {
                            //激活码错误
                            //重新发送激活码

                            return ResultVO.failure("激活码错误");
                        }
                    }else {
                        //说明激活码已经失效
                        //此时应该去service层 重新发送验证码
                        userTransport.send(user);
                        return ResultVO.failure("激活码失效");
                    }
                }else {
                    //说明已经激活
                    return ResultVO.failure(user+"已激活");
                }
            }else {
                //没有该用户
                return ResultVO.failure(user+"邮箱没有注册,请先进行注册");
            }
        }
        return ResultVO.failure("请输入正确邮箱和验证码");
    }

    /**
     * <b>手机激活码激活</b>
     * @param user
     * @param code
     * @return
     * @throws Exception
     */
    @PutMapping("/validatephone")
    public ResultVO verifyCodeByCellphone(String user,String code)throws Exception {
        if (ValidateUtil.checkCellphone(user)
                && code != null && !"".equals(code)) {
            User entity = new User();
            entity.setUserCode(user);
            //通过user查询是否注册
            entity = userTransport.getUser(entity);
            if (entity!= null) {
                //则此时有该用户
                if (entity.getActivated().equals(ActivatedEnum.ACTIVATED_FALSE.getCode())) {
                    //说明此时没有激活
                    //获取redis中的真激活码
                    String isCode = userTransport.getActivated(user);
                    if (isCode != null) {
                        //可以进行对比
                        if (code.equals(isCode)) {
                            //可以激活
                            //邮箱验证成功 可以向后传递
                            boolean qurey = userTransport.activateUser(user, code);
                            if (qurey) {
                                //修改成功
                                return ResultVO.success("激活成功");
                            } else {
                                return ResultVO.failure("激活失败 请重新激活");
                            }
                        } else {
                            //激活码错误
                            //重新发送激活码

                            return ResultVO.failure("激活码错误");
                        }
                    } else {
                        //说明激活码已经失效
                        //此时应该去service层 重新发送验证码
                        userTransport.send(user);
                        return ResultVO.failure("激活码失效");
                    }
                } else {
                    //说明已经激活
                    return ResultVO.failure(user + "已激活");
                }
            } else {
                //没有该用户
                return ResultVO.failure(user + "没有注册,请先进行注册");
            }
        }
        return ResultVO.failure("请输入正确手机号和验证码");
    }

    /**
     *<b>进行用户登录</b>
     * @param name
     * @param password
     * @return
     * @throws Exception
     */
    @PostMapping("/dologin")
    public ResultVO userLogin(String name,String password)throws Exception{
//        验证所给的name和password的有效性
        if (name!= null&&!"".equals(name.trim())
                &&password!=null&&!"".equals(password.trim())){
            //说明此时的信息都是有效的 可以将该信息向后传递
            ResultVO type=userTransport.loginUser(name,password);
            return type;
        }
        return ResultVO.failure("请输入有效的信息");
    }
}
