package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.system.User;
import cn.janescott.repository.system.UserRepository;
import cn.janescott.service.SidebarService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/14.
 */
@RequestMapping("/mysql")
@RestController
public class LoginController {
    private ObjectMapper mapper = new ObjectMapper();

    @Resource
    private UserRepository userRepository;

    @Resource
    private SidebarService sidebarService;

    @RequestMapping("/user/{msg}")
    @LoggerManage(description = "get user")
    public String getUser(@PathVariable("msg")String msg) throws Exception{
        User user = userRepository.findByAccount(msg);
        return mapper.writeValueAsString(user);
    }

    @RequestMapping("/sidebar/{id}")
    public String getSidebar(@PathVariable("id")Integer id) throws Exception{
        return mapper.writeValueAsString(sidebarService.getSidebar(id));
    }

}
