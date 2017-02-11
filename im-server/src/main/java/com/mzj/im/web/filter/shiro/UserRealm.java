package com.mzj.im.web.filter.shiro;

import com.mzj.im.model.vo.UserVO;
import com.mzj.im.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by OB on 2017/2/2.
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        UserVO user = userService.getUserByUsername(username);
        if (user.isEmpty()) {
            throw new UnknownAccountException();
        }
        if (user.isLock()) {
            throw new LockedAccountException();
        }
        Object principal = username;
        Object credentails = user.getPassword();

        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(principal, credentails, getName());
        authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes(user.getSalt()));
        return authenticationInfo;
    }
}

