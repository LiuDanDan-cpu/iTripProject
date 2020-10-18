package cn.ekgc.itrip.transport.impl;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.LinkUser;
import cn.ekgc.itrip.pojo.vo.LinkUserVO;
import cn.ekgc.itrip.service.LinkUserService;
import cn.ekgc.itrip.transport.biz.LinkUserTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("linkUserTransport")
@RequestMapping("/LinkUserTransport")
public class LinkUserTransportImpl implements LinkUserTransport {
    @Autowired
    private LinkUserService service;
    /**
     * <b>校验用户信息  获取常用联系人</b>
     * @param query
     * @return
     * @throws Exception
     */
    @PostMapping("/getListByToken")
    @Override
    public List<LinkUser> getListByQuery(@RequestBody LinkUser query) throws Exception {
        return service.getListByQuery(query);
    }
    /**
     * <b>根据所给对象添加常用联系人</b>
     * @param linkUser
     * @return
     * @throws Exception
     */
    @RequestMapping("/addLinkUser")
    @Override
    public boolean addLinkUser(@RequestBody LinkUser linkUser) throws Exception {
        return service.addLinkUser(linkUser);
    }
    /**
     * <b>根据所给集合删除对应的常用联系人</b>
     * @param arrayList
     * @return
     * @throws Exception
     */
    @PostMapping("/deleteLinkUser")
    @Override
    public ResultVO deleteLinkUser(@RequestBody ArrayList<String> arrayList) throws Exception {
        return service.deleteLinkUser(arrayList);
    }
    /**
     * <b>根据所给对象修改身份信息</b>
     * @param linkUserVO
     * @return
     */
    @PostMapping("/UpdateLinkUser")
    @Override
    public ResultVO updateLinkUser(@RequestBody LinkUserVO linkUserVO) throws Exception {
        return service.updateLinkUser(linkUserVO);
    }
}
