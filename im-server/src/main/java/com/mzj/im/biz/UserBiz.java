package com.mzj.im.biz;

import com.mzj.im.dao.UserDAO;
import com.mzj.im.model.po.UserPO;
import com.mzj.im.util.ObjectUtil;
import com.mzj.im.util.PasswordUtil;
import com.mzj.im.util.dic.IsDelete;
import com.mzj.im.util.dic.OperateResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by OB on 2017/2/3.
 */
@Component
public class UserBiz {
    @Autowired
    private UserDAO userDAO;

    public Map<Long, UserPO> getUserMap(UserPO user) {
        if (ObjectUtil.isNull(user)) {
            user = new UserPO();
        }
        return userDAO.selectUser(user);
    }

    public UserPO getUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }
        return userDAO.selectUserByUsername(username);
    }

    public List<UserPO> getUserList(UserPO user){
        if(ObjectUtil.isNull(user)){
            return null;
        }
        return userDAO.selectUserList(user);
    }
    /**
     * 添加用户
     *
     * @param user 新增用户
     * @return 添加成功数，-1为失败
     */
    public OperateResult addOneUser(UserPO user) {
        if (ObjectUtil.isNull(user) || StringUtils.isEmpty(user.getUsername())) {
            return OperateResult.INPUT_ILLIGAL_ERROR;
        }
//        PasswordUtil.setPassword(user);
        return userDAO.insertOneUser(user) > 0 ? OperateResult.SUCCESS : OperateResult.FAILD;
    }

    /**
     * 更新用户信息
     *
     * @param user 用户信息
     * @return 更新成功数，-1为失败
     */
    public OperateResult updateOneUserByUsername(UserPO user) {
        if (ObjectUtil.isNull(user) || StringUtils.isEmpty(user.getUsername())) {
            return OperateResult.INPUT_ILLIGAL_ERROR;
        }
        PasswordUtil.setPassword(user);
        return userDAO.updateOneUserByUsername(user) > 0 ? OperateResult.SUCCESS : OperateResult.FAILD;
    }

    public OperateResult lockOneUser(String username) {
        if (StringUtils.isEmpty(username)) {
            return OperateResult.INPUT_ILLIGAL_ERROR;
        }
        UserPO user = new UserPO();
        user.setUsername(username);
        user.setIsDelete(IsDelete.YES.getValue());
        return userDAO.updateOneUserByUsername(user) > 0 ? OperateResult.SUCCESS : OperateResult.FAILD;
    }

    public OperateResult unlockOneUser(String username) {
        if (StringUtils.isNotEmpty(username)) {
            return OperateResult.INPUT_ILLIGAL_ERROR;
        }
        UserPO user = new UserPO();
        user.setUsername(username);
        user.setIsDelete(IsDelete.NO.getValue());
        return userDAO.updateOneUserByUsername(user) > 0 ? OperateResult.SUCCESS : OperateResult.FAILD;
    }
}
