package cn.janescott.common;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by scott on 2017/6/6.
 * 拦截器
 * FIXME 这里只是先写好类的位置，以后有需求再增加具体拦截内容 page 106
 */
public class WebMVCInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // 对于验证权限不足的（403），使用重定向到首页。
        if(HttpStatus.FORBIDDEN.value() == response.getStatus()){
            // 这里添加错误信息
            request.getSession().setAttribute("SPRING_SECURITY_LAST_EXCEPTION", new BadCredentialsException(Constants.ERROR_L04));
            response.sendRedirect("/zone/login");
        } else if(HttpStatus.NOT_FOUND.value() == response.getStatus()){
            response.sendRedirect("/zone/error");
        }
    }
}
