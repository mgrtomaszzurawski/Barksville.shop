package pl.barksville.barksville.spring.web.controllers.jsp;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomePageController {

   /*private UserService userService;
    private SkillService skillService;*/

    /* @Autowired
     public HomePageController(UserService userService, SkillService skillService) {
         this.userService = userService;
         this.skillService = skillService;*//*
    }
*/
    @GetMapping
    public String getHomePage(Model model) {

        return "home";
    }

    @PostMapping
    public String postHomePage() {
        return "redirect:/";
    }
}
