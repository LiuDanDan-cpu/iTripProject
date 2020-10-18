package cn.ekgc.itrip.service;

import cn.ekgc.itrip.base.pojo.vo.ResultVO;
import cn.ekgc.itrip.pojo.entity.LinkUser;
import cn.ekgc.itrip.pojo.vo.LinkUserVO;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>常用旅客信息业务处理层</b>
 */
public interface LinkUserService {
    /**
     * <b>校验用户信息  获取常用联系人</b>
     * @param query
     * @return
     * @throws Exception
     */
    List<LinkUser> getListByQuery(LinkUser query)throws Exception;

    /**
     * <b>根据所给信息添加常用联系人信息</b>
     * @param linkUser
     * @return
     * @throws Exception
     */
    boolean addLinkUser(LinkUser linkUser)throws Exception;
    /**
     * <b>根据所给集合删除对应的常用联系人</b>
     * @param arrayList
     * @return
     * @throws Exception
     */
    ResultVO deleteLinkUser(ArrayList<String> arrayList)throws Exception;
    /**
     * <b>根据所给对象修改身份信息</b>
     * @param linkUserVO
     * @return
     */
    ResultVO updateLinkUser(LinkUserVO linkUserVO)throws Exception;
}
