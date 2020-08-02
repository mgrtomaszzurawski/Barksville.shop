package pl.barksville.barksville.spring.core.service;

import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.DayReportRepository;
import pl.barksville.barksville.spring.model.dal.repositories.MonthReportRepository;
import pl.barksville.barksville.spring.model.dal.repositories.WeekReportRepository;
import pl.barksville.barksville.spring.model.dal.repositories.YearReportRepository;
import pl.barksville.barksville.spring.model.entities.reports.DayReport;
import pl.barksville.barksville.spring.model.entities.reports.MonthReport;
import pl.barksville.barksville.spring.model.entities.reports.WeekReport;
import pl.barksville.barksville.spring.model.entities.reports.YearReport;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

//todo
@Service
public class ReportService {

    final private DayReportRepository dayReportRepository;
    final private WeekReportRepository weekReportRepository;
    final private MonthReportRepository monthReportRepository;
    final private YearReportRepository yearReportRepository;

    public ReportService(DayReportRepository dayReportRepository, WeekReportRepository weekReportRepository, MonthReportRepository monthReportRepository, YearReportRepository yearReportRepository) {
        this.dayReportRepository = dayReportRepository;
        this.weekReportRepository = weekReportRepository;
        this.monthReportRepository = monthReportRepository;
        this.yearReportRepository = yearReportRepository;
    }


    public static List<DayReport> getDayReportsList() {
        return new ArrayList<>();
    }

    public static DayReport getDayReport(LocalDate reportDate) {
        return  new DayReport();
    }

    public static List<WeekReport> getWeekReportsList() {
        return new ArrayList<>();
    }

    public static WeekReport getWeekReport(LocalDate reportDate) {
        return  new WeekReport();
    }

    public static List<MonthReport> getMonthReportsList() {
        return new ArrayList<>();
    }

    public static MonthReport getMonthReport(LocalDate reportDate) {
        return  new MonthReport();
    }

    public static List<YearReport> getYearReportsList() {
        return new ArrayList<>();
    }

    public static YearReport getYearReport(LocalDate reportDate) {

        return  new YearReport();
    }


    public void createDayReport(LocalDate reportDate) {
    }

    public void createWeekReport(LocalDate reportDate) {
    }

    public void createMonthReport(LocalDate reportDate) {
    }

    public void createYearReport(LocalDate reportDate) {
    }
}
