package com.mzj.im.service;

import com.mzj.im.biz.UserBiz;
import com.mzj.im.model.po.UserPO;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.util.dic.OperateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by OB on 2017/2/3.
 */
@Service
public class UserService {

    @Autowired
    private UserBiz userBiz;

    public UserVO getUserByUsername(String username) {
        return new UserVO(userBiz.getUserByUsername(username));
    }

    public OperateResult addOneUser(UserPO user) {
        return userBiz.addOneUser(user);
    }

    public OperateResult updateOneUser(UserPO user) {
        return userBiz.updateOneUserByUsername(user);
    }
}
