package com.mzj.im.dao;

import com.mzj.im.model.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * Created by OB on 2017/2/3.
 */
@Repository
public interface UserDAO {
    HashMap<Long, UserPO> selectUser(UserPO user);

    UserPO selectUserByUsername(@Param("username") String username);

    int insertOneUser(UserPO user);

    int updateOneUserByUsername(UserPO user);

}
