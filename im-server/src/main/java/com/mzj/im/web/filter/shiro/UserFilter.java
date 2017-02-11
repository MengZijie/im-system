package com.mzj.im.web.filter.shiro;

import com.mzj.im.service.UserService;
import com.mzj.im.util.dic.UserConstants;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by OB on 2017/2/5.
 */
public class UserFilter extends PathMatchingFilter {

    @Autowired
    private UserService userService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(UserConstants.CURRENT_USER.getValue(), userService.getUserByUsername(username));
        return true;
    }
}
