package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/report-view")
public class AdminReportsViewController {


    @GetMapping
    public String getInvoiceList(Model model) {
        model.addAttribute("invoices", ReportService.getInvoices());
        return "adminPanel/invoiceList";
    }

}
