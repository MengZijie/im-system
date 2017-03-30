package com.mzj.im.web.controller;

import com.mzj.im.model.po.UserPO;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.service.UserService;
import com.mzj.im.util.ObjectUtil;
import com.mzj.im.util.dic.*;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by OB on 2017/2/5.
 */
@Controller
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String getLoginPage() {
        return "login";
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    public String doLogin(HttpSession session,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password) {
        UserVO user = userService.doLogin(username,password);
        if (ObjectUtil.isNotNull(user)) {
            session.setAttribute("user", user);
            String uri = (String) session.getAttribute("requestUri");
            if (StringUtils.isEmpty(uri)) {
                uri = "/home";
            }
            return uri;
        }
        return "login";
    }

    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String getRegisterPage() {
        return "register";
    }

    @RequestMapping(value = "register", method = RequestMethod.POST)
    @ResponseBody
    public String doRegister(UserPO user) {
        OperateResult result = userService.addOneUser(user);
        String data = result.getCode().equals(OperateResult.SUCCESS.getCode()) ? "login" : "register";
        return data;
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
    public String getSession(HttpSession session){
        return ((UserVO)session.getAttribute("user")).toJSON().toString();
    }
}
