package com.mzj.im.biz;

import com.mzj.im.dao.UserRelationDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * Created by ob on 17-3-16.
 */
@Component
public class UserRelationBiz {
    @Autowired
    private UserRelationDAO userRelationDAO;

    public Set<Long> getUserRelationId(long userId) {
        Set<Long> set = userRelationDAO.selectUserId1ByUserId2(userId);
        set.addAll(userRelationDAO.selectUserId2ByUserId1(userId));
        return set;
    }

    public boolean addUserRelation(long userId1, long userId2) {
        return userRelationDAO.insertUserRelation(userId1, userId2);
    }
}
