package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.system.User;
import cn.janescott.repository.system.UserRepository;
import cn.janescott.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/14.
 */
@Controller
public class LoginController {
    private ObjectMapper mapper = new ObjectMapper();

    @Resource
    private UserRepository userRepository;

    @Resource
    private UserService sidebarService;

    @RequestMapping("/user/{msg}")
    @LoggerManage(description = "get user")
    public String getUser(@PathVariable("msg")String msg) throws Exception{
        User user = userRepository.findByUsername(msg);
        return mapper.writeValueAsString(user);
    }

    @RequestMapping("/sidebar/{id}")
    public String getSidebar(@PathVariable("id")Integer id) throws Exception{
        return mapper.writeValueAsString(sidebarService.getSidebar(id));
    }

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

}
