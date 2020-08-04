package pl.barksville.barksville.spring.web.controllers.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ReportsService;

import java.time.LocalDate;


@Controller
@RequestMapping("/admin/report-create")
public class AdminReportCreateController {

    private final ReportsService reportsService;

    public AdminReportCreateController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping
    public String createReport() {
        return "adminPanel/reportCreate/reportPanel";
    }

    @GetMapping(value = "day")
    public String getDayForReport() {
        return "adminPanel/reportCreate/dayReportForm";
    }

    @PostMapping(value = "day")
    public String createDayReport(LocalDate reportDate) {


        reportsService.createDayReport(reportDate);

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "week")
    public String getWeekForReport() {
        return "adminPanel/reportCreate/weekReportForm";
    }

    @PostMapping(value = "week")
    public String createWeekReport(LocalDate reportDate) {

        reportsService.createWeekReport(reportDate);

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "month")
    public String getMonthForReport() {
        return "adminPanel/reportCreate/monthReportForm";
    }

    @PostMapping(value = "month")
    public String createMonthReport(LocalDate reportDate) {

        reportsService.createMonthReport(reportDate);

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "year")
    public String getYearForReport() {
        return "adminPanel/reportCreate/yearReportForm";
    }

    @PostMapping(value = "year")
    public String createYearReport(LocalDate reportDate) {

        reportsService.createYearReport(reportDate);

        return "redirect:/admin/report-create";
    }
}
