package com.mzj.im.model.po;

import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by OB on 2017/2/3.
 */
public class UserPO implements Serializable {
    private long id;
    private String username;
    private String nickname;
    private String password;
    private String salt;
    private String photo;
    private String sign;
    private String email;
    private Timestamp createTime;
    private Timestamp updateTime;
    private Character isDelete;

    public JSONObject toJSON() {
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("username", username);
        jsonObject.put("nickname", nickname);
        jsonObject.put("password", null);
        jsonObject.put("salt", null);
        jsonObject.put("photo", photo);
        jsonObject.put("sign", sign);
        jsonObject.put("email", email);
        jsonObject.put("createTime", createTime);
        jsonObject.put("updateTime", updateTime);
        return jsonObject;
    }

    public long getId() {
        return id;
    }


    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        if(StringUtils.isEmpty(nickname)) {
            nickname = username;
        }
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public Character getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Character isDelete) {
        this.isDelete = Character.toUpperCase(isDelete == null ? 'N' : isDelete);
    }

    @Override
    public String toString() {
        return "UserPO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", photo='" + photo + '\'' +
                ", sign='" + sign + '\'' +
                ", email='" + email + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDelete=" + isDelete +
                '}';
    }
}
