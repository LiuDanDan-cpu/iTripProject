package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <b>用户数据持久层</b>
 * @author LiuDanDan
 * @version 1.0.0
 * @since 1.0.0
 */
@Repository
public interface UserDao {
    /**
     * <b>根据对象查询所有的user</b>
     * @param entity
     * @return
     */
    List<User> getListByEntity(User entity)throws Exception;
    /**
     * <b>根据所给对象添加对象</b>
     * @param entity
     * @return
     */
    int save(User entity)throws Exception;

    /**
     * <b>通过所给对象修改</b>
     * @param user
     * @return
     * @throws Exception
     */
    int update(User user)throws Exception;
}
