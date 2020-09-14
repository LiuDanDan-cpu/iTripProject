package cn.ekgc.itrip.transport.user;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "itrip-user-provider")
@RequestMapping("/user/transport")
public interface UserTransport {
    /**
     * <b>根据用户名校验该用户名被使用</b>
     * @param name
     * @return true 则可以添加
     */
    @PostMapping("/ckusr")
    boolean queryUserCodeIsCanUsed(@RequestParam String name);
}
