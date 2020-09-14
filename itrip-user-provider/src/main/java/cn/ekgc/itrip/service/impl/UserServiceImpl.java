package cn.ekgc.itrip.service.impl;

import cn.ekgc.itrip.dao.UserDao;
import cn.ekgc.itrip.pojo.entity.User;
import cn.ekgc.itrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    /**
     * <b>根据用户名校验该用户名被使用</b>
     * @param name
     * @return true 则可以添加
     */
    @Override
    public boolean queryUserCodeIsCanUsed(String name) {
        User entity=new User();
        entity.setUserCode(name);
        List<User> userList = userDao.getListByEntity(entity);
        if (userList!=null&&userList.size()>0){
            return false;
        }
        return true;
    }
}
