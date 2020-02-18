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

    private UserEntityService userEntityService;
    @Autowired
    public AuthenticationController(UserEntityService userEntityService) {
        this.userEntityService = userEntityService;
    }
    @GetMapping
    public String prepareRegistrationPage() {
        return "/elements/login";
    }
    @PostMapping
    public void login(String username, String password, HttpServletResponse response, HttpSession session) throws IOException {
        boolean validCredentials = userEntityService.checkCredentials(username,password);
        if(!validCredentials){
            response.sendError(401, "Błędne logowanie");
            return;
        }
        LoggedUserEntityDTO userEntity = userEntityService.getUserEntity(username);
        session.setAttribute("user",userEntity);
        response.sendRedirect("/");

    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }
}
