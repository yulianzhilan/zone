package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.service.SendEmailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/7.
 */
@Controller
public class DemoController {

    @Resource
    private SendEmailService sendEmailService;

    @RequestMapping("/demo")
    public String demo(){
        return "demo";
    }

    @RequestMapping("/send/{content}")
    @ResponseBody
    @LoggerManage(description = "发送邮件")
    public void send(@PathVariable("content")String content) {
        sendEmailService.send("发送邮件", content);
    }

    @RequestMapping("/default")
    public String layout(){
        return "definition/default";
    }

}
