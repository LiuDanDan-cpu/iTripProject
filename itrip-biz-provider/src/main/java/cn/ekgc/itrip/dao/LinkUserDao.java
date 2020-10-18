package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.LinkUser;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>常用旅客信息数据持久层接口</b>
 */
@Repository
public interface LinkUserDao {
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
    int saveLinkUser(LinkUser linkUser)throws Exception;

    /**
     * <b>根据所给主键删除信息</b>
     * @param query
     * @throws Exception
     */
//    @Delete("DELETE FROM itrip_user_link_user WHERE id=#{query}")
    void deleteLinkUser(Long query)throws Exception;

    /**
     * <b>根据所给主键查询常用人员</b>
     * @param parseLong
     * @return
     * @throws Exception
     */
    @Select("SELECT * FROM itrip_user_link_user where id=#{parseLong}")
    LinkUser getLinkUser(Long parseLong)throws Exception;

    /**
     * <b>根据所给对象修改信息</b>
     * @param linkUser
     * @return
     */
    int updateLinkUser(LinkUser linkUser)throws Exception;
}
