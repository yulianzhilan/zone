package cn.janescott.common;

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
        System.out.println("--------WebMVCInterceptor preHandle start--------");
        System.out.println(request.getRequestURL());
        System.out.println("--------WebMVCInterceptor preHandle end--------");

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("--------WebMVCInterceptor postHandle start--------");
        System.out.println(request.getRequestURL());
        System.out.println("--------WebMVCInterceptor postHandle end--------");
    }
}
