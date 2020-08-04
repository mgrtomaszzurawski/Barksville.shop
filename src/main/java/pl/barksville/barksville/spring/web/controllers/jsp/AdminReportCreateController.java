package pl.barksville.barksville.spring.web.controllers.jsp;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.barksville.barksville.spring.core.service.ReportsService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;


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
    public String createDayReport(String dayReportDate) {


        reportsService.createDayReport(LocalDate.parse(dayReportDate));

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "week")
    public String getWeekForReport() {
        return "adminPanel/reportCreate/weekReportForm";
    }

    @PostMapping(value = "week")
    public String createWeekReport(String weekReportDate) {

        String date = weekReportDate+"-1";

        reportsService.createWeekReport(LocalDate.parse(date, DateTimeFormatter.ISO_WEEK_DATE));

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "month")
    public String getMonthForReport() {
        return "adminPanel/reportCreate/monthReportForm";
    }

    @PostMapping(value = "month")
    public String createMonthReport(String monthReportDate) {

        LocalDate reportDate= LocalDate.parse(monthReportDate+"-01");

        reportsService.createMonthReport(reportDate);

        return "redirect:/admin/report-create";
    }

    @GetMapping(value = "year")
    public String getYearForReport() {
        return "adminPanel/reportCreate/yearReportForm";
    }

    @PostMapping(value = "year")
    public String createYearReport(String yearReportDate) {
    LocalDate reportDate= LocalDate.of(Integer.parseInt(yearReportDate),1,1);

        reportsService.createYearReport(reportDate);

        return "redirect:/admin/report-create";
    }
}
