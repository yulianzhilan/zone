package cn.janescott.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by scott on 2017/7/14.
 */
@Controller
@RequestMapping("/article")
public class ArticleController extends BaseController {
    @RequestMapping(method = {RequestMethod.POST,RequestMethod.GET})
    public String execute(Model model){
        model.addAttribute("title", "article");
        return "views/article/article";
    }
}
