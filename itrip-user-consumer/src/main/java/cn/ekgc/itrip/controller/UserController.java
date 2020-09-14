package cn.ekgc.itrip.controller;

import cn.ekgc.itrip.base.BaseController;
import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.transport.user.UserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userController")
@RequestMapping("/auth/api")
public class UserController extends BaseController {
    @Autowired
    private UserTransport userTransport;
    /**
     * <b>验证用户名是否被占用</b>
     * @param name
     * @return
     * @throws Exception
     */
    @GetMapping("/ckusr")
    public ResultVO checkName(String name) throws Exception{
        boolean isCanUsed=userTransport.queryUserCodeIsCanUsed(name);
        if (isCanUsed){
            return new ResultVO(true);
        }
        return new ResultVO(true ,"已经被注册使用");
    }

}
