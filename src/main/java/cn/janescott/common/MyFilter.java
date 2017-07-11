package cn.janescott.common;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by scott on 2017/6/7.
 * 拦截器定义位置
 */
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("my filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 对于登录失败，并且是密码错误的情况，在这里单独处理。
        if ("true".equals(request.getParameter("error")) && "/zone/login".equals(((HttpServletRequest) request).getRequestURI())) {
            Object obj = ((HttpServletRequest) request).getSession().getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
            if (null != obj && obj instanceof BadCredentialsException) {
                if ("Bad credentials".equals(((BadCredentialsException) obj).getMessage())) {
                    ((HttpServletRequest) request).getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new BadCredentialsException(Constants.ERROR_L03));
                }
            }

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        System.out.println("my filter destroy");
    }
}
