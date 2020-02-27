package pl.barksville.barksville.spring.web.controllers.jsp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthenticationController {

    @GetMapping
    public String prepareRegistrationPage() {
        return "/elements/login";
    }
}
