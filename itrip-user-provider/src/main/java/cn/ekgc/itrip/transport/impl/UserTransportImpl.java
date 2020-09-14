package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.service.UserService;
import cn.ekgc.itrip.transport.user.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public boolean queryUserCodeIsCanUsed(String name) {
        return userService.queryUserCodeIsCanUsed(name);
    }
}
