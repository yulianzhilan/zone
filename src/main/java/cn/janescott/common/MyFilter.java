package cn.janescott.common;

import javax.servlet.*;
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
        System.out.println("my filter do filter");
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        System.out.println("my filter destroy");
    }
}
