package com.mzj.im.dao;

import com.mzj.im.model.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by OB on 2017/2/3.
 */
@Repository
public interface UserDAO {
    Map<Long, UserPO> selectUser(UserPO user);

    List<UserPO> selectUserList(UserPO user);

    UserPO selectUserByUsername(@Param("username") String username);

    int insertOneUser(UserPO user);

    int updateOneUserByUsername(UserPO user);
}
