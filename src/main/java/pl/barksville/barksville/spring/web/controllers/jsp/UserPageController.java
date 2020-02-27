package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserPageController {
    @GetMapping
    public String getUserPage(Model model) {

        return "/elements/user";
    }

    @PostMapping
    public String postUserPage() {
        return "redirect:/";
    }
}
