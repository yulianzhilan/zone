package cn.janescott.web;

import cn.janescott.common.LoggerManage;
import cn.janescott.domain.dto.SidebarDTO;
import cn.janescott.domain.system.Role;
import cn.janescott.domain.system.User;
import cn.janescott.repository.system.UserRepository;
import cn.janescott.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * Created by scott on 2017/6/14.
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("user", new User());
        return "index";
    }

//    @RequestMapping("/logout")
//    public String logout(HttpServletRequest request, HttpServletResponse response){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        if(auth != null){
//            new SecurityContextLogoutHandler().logout(request,response,auth);
//        }
//        return "redirect:/login?logout";
//    }

}
