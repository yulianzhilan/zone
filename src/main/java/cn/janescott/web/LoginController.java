package cn.janescott.web;

import cn.janescott.domain.system.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
