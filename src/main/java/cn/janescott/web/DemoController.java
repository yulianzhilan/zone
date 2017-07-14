package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.dto.UserDTO;
import cn.janescott.repository.mapper.UserMapper;
import cn.janescott.service.SendEmailService;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/7.
 */

@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private Environment env;

    @Resource
    private SendEmailService sendEmailService;

    @Resource
    private UserMapper userMapper;

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

    @LoggerManage(description = "dataSource")
    @RequestMapping("/dataSource")
    public String dataSource(){
        return env.getProperty("spring.datasource.driver-class-name");
    }

    @RequestMapping("/mapper")
    @LoggerManage(description = "mapper")
    @ResponseBody
    public UserDTO mapper(String username){
        return userMapper.getOne(username);
    }

}
