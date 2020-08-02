package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ReportService;

import java.time.LocalDate;

@Controller
@RequestMapping("/admin/report-view")
public class AdminReportsViewController {

private final ReportService reportService;

    public AdminReportsViewController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String getReports() {
        return "adminPanel/reportView/reports";
    }

    @GetMapping(value = "/day-report-list")
    public String getDayReportsList(Model model) {
        model.addAttribute("dayReports", ReportService.getDayReportsList());
        return "adminPanel/reportView/dayReportsList";
    }

    @PostMapping(value = "/day-report", params = {"get"})
    public String getDayReport(Model model, String reportDate) {
        model.addAttribute("dayReport", ReportService.getDayReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/dayReport";
    }

    @GetMapping(value = "/week-report-list")
    public String getWeekReportsList(Model model) {
        model.addAttribute("weekReports", ReportService.getWeekReportsList());
        return "adminPanel/reportView/weekReportsList";
    }

    @PostMapping(value = "/week-report", params = {"get"})
    public String getWeekReport(Model model, String reportDate) {
        model.addAttribute("weekReport", ReportService.getWeekReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/weekReport";
    }
    @GetMapping(value = "/month-report-list")
    public String getMonthReportsList(Model model) {
        model.addAttribute("monthReports", ReportService.getMonthReportsList());
        return "adminPanel/reportView/monthReportsList";
    }

    @PostMapping(value = "/month-report", params = {"get"})
    public String getMonthReport(Model model, String reportDate) {
        model.addAttribute("monthReport", ReportService.getMonthReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/monthReport";
    }
    @GetMapping(value = "/year-report-list")
    public String getYearReportsList(Model model) {
        model.addAttribute("yearReports", ReportService.getYearReportsList());
        return "adminPanel/reportView/yearReportsList";
    }

    @PostMapping(value = "/year-report", params = {"get"})
    public String getYearReport(Model model, String reportDate) {
        model.addAttribute("yearReport", ReportService.getYearReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/yearReport";
    }



}
