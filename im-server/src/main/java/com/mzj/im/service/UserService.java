package com.mzj.im.service;

import com.mzj.im.biz.UserBiz;
import com.mzj.im.dao.redis.RedisUserDAO;
import com.mzj.im.model.po.UserPO;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.util.dic.OperateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by OB on 2017/2/3.
 */
@Service
public class UserService {

    @Autowired
    private UserBiz userBiz;

    @Autowired
    private RedisUserDAO redisUserDAO;

    public UserVO doLogin(String username, String password){
        UserVO user = getUserByUsername(username);
        if(!user.isEmpty() && password.equals(user.getPassword())){
            user.setOnline(true);
//            redisUserDAO.put(user);
            return user;
        }
        return null;
    }

    public UserVO getUserByUsername(String username) {
        return new UserVO(userBiz.getUserByUsername(username));
    }

    public List<UserPO> getUserListByInfo(UserPO user) {
        return userBiz.getUserList(user);
    }

    public OperateResult addOneUser(UserPO user) {
        return userBiz.addOneUser(user);
    }

    public OperateResult updateOneUser(UserPO user) {
        return userBiz.updateOneUserByUsername(user);
    }

}
