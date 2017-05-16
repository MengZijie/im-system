package com.mzj.im.model.po;

/**
 * Created by ob on 17-5-2.
 */
public class UserRelationPo {
    private Long    id;
    private Long    primaryUserId;
    private Long    relatedUserId;
    private Integer userCategoryId;
    private UserPO  friend;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrimaryUserId() {
        return primaryUserId;
    }

    public void setPrimaryUserId(Long primaryUserId) {
        this.primaryUserId = primaryUserId;
    }

    public Long getRelatedUserId() {
        return relatedUserId;
    }

    public void setRelatedUserId(Long relatedUserId) {
        this.relatedUserId = relatedUserId;
    }

    public Integer getUserCategoryId() {
        return userCategoryId;
    }

    public void setUserCategoryId(Integer userCategoryId) {
        this.userCategoryId = userCategoryId;
    }

    public UserPO getFriend() {
        return friend;
    }

    public void setFriend(UserPO friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "UserRelationPo{" +
                "id=" + id +
                ", primaryUserId=" + primaryUserId +
                ", relatedUserId=" + relatedUserId +
                ", userCategoryId=" + userCategoryId +
                ", friend=" + friend + "{" +
                        friend.toString() +
                    "}" +
                '}';
    }
}