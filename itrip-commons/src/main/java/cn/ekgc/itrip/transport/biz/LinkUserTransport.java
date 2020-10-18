package cn.ekgc.itrip.transport.biz;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.LinkUser;
import cn.ekgc.itrip.pojo.vo.LinkUserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>常用联系人传输层</b>
 */
@FeignClient(name = "itrip-biz-provider")
@RequestMapping("/LinkUserTransport")
public interface LinkUserTransport {
    /**
     * <b>校验用户信息  获取常用联系人</b>
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getListByToken")
    List<LinkUser> getListByQuery(@RequestBody LinkUser query)throws Exception;

    /**
     * <b>根据所给对象添加常用联系人</b>
     * @param linkUser
     * @return
     * @throws Exception
     */
    @RequestMapping("/addLinkUser")
    boolean addLinkUser(@RequestBody LinkUser linkUser)throws Exception;

    /**
     * <b>根据所给集合删除对应的常用联系人</b>
     * @param arrayList
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteLinkUser")
    ResultVO deleteLinkUser(@RequestBody ArrayList<String> arrayList)throws Exception;

    /**
     * <b>根据所给对象修改身份信息</b>
     * @param linkUserVO
     * @return
     */
    @PostMapping("/UpdateLinkUser")
    ResultVO updateLinkUser(@RequestBody LinkUserVO linkUserVO)throws Exception;
}
