package cn.ekgc.itrip.dao;

import cn.ekgc.itrip.pojo.entity.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
    /**
     * <b>根据对象查询所有的user</b>
     * @param entity
     * @return
     */
    List<User> getListByEntity(User entity);
}
