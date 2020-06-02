package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminPanelContoller {

    @GetMapping("/panel")
    public String getAdminPanelPage() {

        return "adminPanel/adminPanel";
    }
}
