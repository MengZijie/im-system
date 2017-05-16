package com.mzj.im.dao;

import com.mzj.im.model.po.UserRelationPo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Created by ob on 17-3-16.
 */
@Repository
public interface UserRelationDAO {
    List<UserRelationPo> selectFriendList(@Param("userId") Long userId);
    boolean insertUserRelation(Long userId1, Long userId2);
}
