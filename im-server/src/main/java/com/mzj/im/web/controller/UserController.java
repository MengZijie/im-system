package com.mzj.im.web.controller;

import com.mzj.im.model.po.UserPO;
import com.mzj.im.model.po.UserRelationPo;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.service.UserService;
import com.mzj.im.util.ObjectUtil;
import com.mzj.im.util.ResponseObject;
import com.mzj.im.util.dic.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by OB on 2017/2/5.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage(){
        return "login";
    }
    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(HttpSession session,
                          @RequestBody UserVO user) {
        user = userService.doLogin(user.getUsername(), user.getPassword());
        ResponseObject<UserVO> result = new ResponseObject<UserVO>();
        if (ObjectUtil.isNotNull(user)) {
            result.setSuccess().setData(user);
            session.setAttribute("user", user);
        }
        else {
            result.setFaild();
        }
        return result.toJSONString();
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String doRegister(HttpSession session, @RequestBody UserPO user) {
        OperateResult result = userService.addOneUser(user);
        JSONObject jsonObject = new JSONObject();
        if(result.getCode().equals(OperateResult.SUCCESS.getCode())) {
            session.setAttribute("user", user);
            jsonObject.put("status", "success");
        }
        else
            jsonObject.put("status", "fail");
        return jsonObject.toString();
    }

    @RequestMapping(value = "get", method = RequestMethod.POST)
    @ResponseBody
    public String getUser(UserPO user) {
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(userService.getUserListByInfo(user));
        return jsonArray.toString();
    }

    @RequestMapping(value = "getsession", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        return session.getId();
    }

    @RequestMapping(value = "friend/get", method = RequestMethod.GET)
    @ResponseBody
    public String getFriendList(HttpSession session) {
        UserVO user = (UserVO) session.getAttribute("user");
        long userId = user.getId();
        List<UserRelationPo> userRelationList = userService.getFriendList(userId);
        ResponseObject<List> responseObject = new ResponseObject<>();
        if (ObjectUtil.isNotNull(userRelationList)) {
            responseObject.setSuccess().setData(userRelationList);
            return responseObject.toJSONString();
//            JSONArray jsonArray = new JSONArray();
//            for (UserRelationPo relation : userRelationList) {
//                jsonArray.add(relation.toJSON());
//            }
//            return jsonArray.toString();
        }
        return responseObject.setFaild().toJSONString();
    }
}
