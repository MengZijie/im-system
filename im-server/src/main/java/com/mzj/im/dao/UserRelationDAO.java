package com.mzj.im.dao;

import org.springframework.stereotype.Repository;

import java.util.Set;

/**
 * Created by ob on 17-3-16.
 */
@Repository
public interface UserRelationDAO {
    Set<Long> selectUserId2ByUserId1(Long userId);
    Set<Long> selectUserId1ByUserId2(Long userId);
    boolean insertUserRelation(Long userId1, Long userId2);
}
