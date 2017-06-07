package cn.janescott.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by scott on 2017/6/7.
 */
@Controller
public class DemoController {

    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }
}
