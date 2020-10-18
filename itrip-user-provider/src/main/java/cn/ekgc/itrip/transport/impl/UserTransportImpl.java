package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.service.UserService;
import cn.ekgc.itrip.transport.user.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("userTransport")
@RequestMapping("/user/transport")
public class UserTransportImpl implements UserTransport {
    @Autowired
    private UserService userService;
    /**
     * <b>根据用户名校验该用户名被使用</b>
     * @param name
     * @return true 则可以添加
     */
    @Override
    @PostMapping("/ckusr")
    public boolean queryUserCodeIsCanUsed(@RequestParam String name) throws Exception{
        return userService.queryUserCodeIsCanUsed(name);
    }

    @Override
    @PostMapping("/doregister")
    public boolean saveUser(@RequestBody User user) throws Exception {
        return userService.saveUser(user);
    }
    /**
     * <b>激活用户</b>
     * @param userCode
     * @param code
     * @return
     */
    @PostMapping("/validate")
    @Override
    public boolean activateUser(@RequestParam String userCode,@RequestParam  String code) throws Exception {
        return userService.activateUser(userCode,code);
    }

    /**
     * <b></b>
     * @param user
     * @return
     */
    @PostMapping("/dologin")
    @Override
    public String userLogin(@RequestBody User user) throws Exception {
        return "false";
    }

    /**
     * <b>查询获得用户信息</b>
     * @param entity
     * @return
     */
    @PostMapping("/getUser")
    @Override
    public User getUser(@RequestBody User entity) throws Exception {
        return userService.getUser(entity);
    }

    /**
     * <b>查询redis中存储的激活码</b>
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/getActivated")
    @Override
    public String getActivated(@RequestParam String user) throws Exception {
        return userService.getActivated(user);
    }
    /**
     * <b>通过所给的地址 发送短信或者邮件</b>
     * @param userCode
     * @return
     * @throws Exception
     */
    @PostMapping("/send")
    @Override
    public void send(@RequestParam String userCode) throws Exception {
        userService.send(userCode);
    }
    /**
     * <b>用户登录</b>
     *
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/loginUser")
    @Override
    public ResultVO loginUser(@RequestParam String name, @RequestParam String password) throws Exception {
        return userService.loginUser(name,password);
    }
    /**
     * <b>用户退出</b>
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/Out")
    @Override
    public ResultVO userOut(@RequestParam String token) throws Exception {
        return userService.userOut(token);
    }
}
