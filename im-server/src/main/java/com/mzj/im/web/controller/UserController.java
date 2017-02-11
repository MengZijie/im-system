package com.mzj.im.web.controller;

import com.mzj.im.model.po.UserPO;
import com.mzj.im.model.vo.UserVO;
import com.mzj.im.service.UserService;
import com.mzj.im.util.PasswordUtil;
import com.mzj.im.util.ResponseObject;
import com.mzj.im.util.dic.OperateResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String doLogin(HttpSession Session,
                          @RequestParam("username") String username,
                          @RequestParam("password") String password) {
        UserVO user = userService.getUserByUsername(username);
//        if (user.getPassword().equals(PasswordUtil.encodePassword(password.getBytes(), user.getSalt().getBytes()))) {
        if (user.getPassword().equals(password)) {
            Session.setAttribute("user", user);
            String uri = (String) Session.getAttribute("requestUri");
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
    public String register(UserPO user) {
        OperateResult result = userService.addOneUser(user);
        String data = result.getCode() == OperateResult.SUCCESS.getCode() ? "login" : "register";
        return new ResponseObject<String>(result, data).toJSON().toJSONString();
    }
}
