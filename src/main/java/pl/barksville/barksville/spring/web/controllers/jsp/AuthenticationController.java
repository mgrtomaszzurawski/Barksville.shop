package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.UserEntityService;
import pl.barksville.barksville.spring.dto.LoggedUserEntityDTO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
@Controller
@RequestMapping("/login")
public class AuthenticationController {

    @GetMapping
    public String prepareRegistrationPage() {
        return "/elements/login";
    }
}
