package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ReportService;

import java.time.LocalDate;


@Controller
@RequestMapping("/admin/report-create")
public class AdminReportCreateController {

    private final ReportService reportService;

    public AdminReportCreateController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping
    public String createReport() {
        return "adminPanel/report-create";
    }

    @GetMapping(value = "day")
    public String getDayForReport() {
        return "adminPanel/report-create/day";
    }

    @PostMapping(value = "day",params = {"create"})
    public String createDayReport(String reportDate) {

        reportService.createDayReport(LocalDate.parse(reportDate));

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "week")
    public String getWeekForReport() {
        return "adminPanel/report-create/week";
    }

    @PostMapping(value = "week",params = {"create"})
    public String createWeekReport(String reportDate) {

        reportService.createWeekReport(LocalDate.parse(reportDate));

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "month")
    public String getMonthForReport() {
        return "adminPanel/report-create/month";
    }

    @PostMapping(value = "month",params = {"create"})
    public String createMonthReport(String reportDate) {

        reportService.createMonthReport(LocalDate.parse(reportDate));

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "year")
    public String getYearForReport() {
        return "adminPanel/report-create/month";
    }

    @PostMapping(value = "year",params = {"create"})
    public String createYearReport(String reportDate) {

        reportService.createYearReport(LocalDate.parse(reportDate));

        return "redirect:/admin/report-create";
    }
}
