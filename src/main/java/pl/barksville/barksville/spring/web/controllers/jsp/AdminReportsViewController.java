package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ReportsService;
import pl.barksville.barksville.spring.model.entities.reports.DayReport;
import pl.barksville.barksville.spring.model.entities.reports.MonthReport;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/report-view")
public class AdminReportsViewController {

private final ReportsService reportsService;

    public AdminReportsViewController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping
    public String getReports() {
        return "adminPanel/reportView/reportViewPanel";
    }

    @GetMapping(value = "/day-report-list")
    public String getDayReportsList(Model model) {
        model.addAttribute("dayReports", reportsService.getDayReportsList());
        return "adminPanel/reportView/dayReportsList";
    }

    @GetMapping(value = "/day-report-list-of-month")
    public String getDayReportsListOfMonth(Model model, List<DayReport> dayReportListOfMonth) {
        model.addAttribute("dayReports", dayReportListOfMonth);
        return "adminPanel/reportView/dayReportsList";
    }

    @GetMapping(value = "/day-report-list-of-week")
    public String getDayReportsListOfWeek(Model model, List<DayReport> dayReportListOfWeek) {
        model.addAttribute("dayReports", dayReportListOfWeek);
        return "adminPanel/reportView/dayReportsList";
    }

    @PostMapping(value = "/day-report")
    public String getDayReport(Model model, DayReport dayReport) {
        model.addAttribute("dayReport", dayReport);

        return "adminPanel/reportView/dayReport";
    }

    @GetMapping(value = "/week-report-list")
    public String getWeekReportsList(Model model) {
        model.addAttribute("weekReports", reportsService.getWeekReportsList());
        return "adminPanel/reportView/weekReportsList";
    }

    @PostMapping(value = "/week-report", params = {"get"})
    public String getWeekReport(Model model, String reportDate) {
        model.addAttribute("weekReport", reportsService.getWeekReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/weekReport";
    }
    @GetMapping(value = "/month-report-list")
    public String getMonthReportsList(Model model) {
        model.addAttribute("monthReports", reportsService.getMonthReportsList());
        return "adminPanel/reportView/monthReportsList";
    }

    @GetMapping(value = "/month-report-list-of-year")
    public String getMonthReportsList(Model model, List<MonthReport> monthReportList) {
        model.addAttribute("monthReports",monthReportList);
        return "adminPanel/reportView/monthReportsList";
    }

    @PostMapping(value = "/month-report", params = {"get"})
    public String getMonthReport(Model model, String reportDate) {
        model.addAttribute("monthReport", reportsService.getMonthReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/monthReport";
    }
    @GetMapping(value = "/year-report-list")
    public String getYearReportsList(Model model) {
        model.addAttribute("yearReports", reportsService.getYearReportsList());
        return "adminPanel/reportView/yearReportsList";
    }

    @PostMapping(value = "/year-report", params = {"get"})
    public String getYearReport(Model model, String reportDate) {
        model.addAttribute("yearReport", reportsService.getYearReport(LocalDate.parse(reportDate)));

        return "adminPanel/reportView/yearReport";
    }



}
