package cn.ekgc.itrip.transport.user;


import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "itrip-user-provider")
@RequestMapping("/user/transport")
public interface UserTransport {
    /**
     * <b>根据用户名校验该用户名被使用</b>
     *
     * @param name
     * @return true 则可以添加
     */
    @PostMapping("/ckusr")
    boolean queryUserCodeIsCanUsed(@RequestParam String name) throws Exception;

    /**
     * <b>根据对象添加 用户信息</b>
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/doregister")
    boolean saveUser(@RequestBody User user) throws Exception;

    /**
     * <b>激活用户</b>
     *
     * @param userCode
     * @param code
     * @return
     */
    @PostMapping("/validate")
    boolean activateUser(@RequestParam String userCode, @RequestParam String code) throws Exception;

    /**
     * <b></b>
     *
     * @param user
     * @return
     */
    @PostMapping("/dologin")
    String userLogin(@RequestBody User user) throws Exception;

    /**
     * <b>查询获得用户信息</b>
     *
     * @param entity
     * @return
     */
    @PostMapping("/getUser")
    User getUser(@RequestBody User entity) throws Exception;

    /**
     * <b>查询redis中存储的激活码</b>
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/getActivated")
    String getActivated(@RequestParam String user) throws Exception;

    /**
     * <b>通过所给的地址 发送短信或者邮件</b>
     *
     * @param userCode
     * @return
     * @throws Exception
     */
    @PostMapping("/send")
    void send(@RequestParam String userCode) throws Exception;

    /**
     * <b>用户登录</b>
     *
     * @param name
     * @param password
     * @return
     */
    @PostMapping("/loginUser")
    ResultVO loginUser(@RequestParam String name, @RequestParam String password) throws Exception;

    /**
     * <b>用户退出</b>
     * @param token
     * @return
     * @throws Exception
     */
    @PostMapping("/Out")
    ResultVO userOut(@RequestParam String token)throws Exception;
}
