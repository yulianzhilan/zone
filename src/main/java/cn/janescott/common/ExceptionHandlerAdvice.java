package cn.janescott.common;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by scott on 2017/6/7.
 * 异常全局处理建言
 * 组合了@Component会自动注册为bean
 */
@ControllerAdvice
public class ExceptionHandlerAdvice {
    /**
     * 通过@Exception的value属性过滤拦截条件
     * @param exception
     * @param request
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public ModelAndView exception(Exception exception, WebRequest request){
        ModelAndView modelAndView = new ModelAndView("error");//error页面
        modelAndView.addObject("errorMsg", exception.getMessage());
        return modelAndView;
    }

    /**
     * 添加全局键值对——只要注解了@ModelAndAttribute都可获得此键值对
     * @param model
     */
    @ModelAttribute
    public void addAttributes(Model model){
        model.addAttribute("msg", "额外信息");
    }

    /**
     * 使用@InitBinder定制WebDataBinder
     * 详细参考WebDataBinder API
     * @param webDataBinder
     */
    @InitBinder
    public void initBinder(WebDataBinder webDataBinder){
     // 此处忽略id属性
//        webDataBinder.setDisallowedFields("id");
    }
}
