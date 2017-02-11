package com.mzj.im.web.filter;

import com.mzj.im.util.ObjectUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SystemUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashSet;

public class UserFilter implements Filter {

    private static final HashSet<String> staticUri;

    static {
        staticUri = new HashSet<>();
        staticUri.add("/user/login");
        staticUri.add("/user/register");
        staticUri.add("/home");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpSession session = ((HttpServletRequest) request).getSession();
        String uri = ((HttpServletRequest) request).getRequestURI();
        if (!staticUri.contains(uri) && ObjectUtil.isNotNull(session.getAttribute("user"))) {
            session.setAttribute("requestUrl", uri);
            System.out.println(((HttpServletRequest) request).getRequestURI());
            ((HttpServletResponse) response).sendRedirect("/user/login");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

}