package pl.barksville.barksville.spring.core.service;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.*;
import pl.barksville.barksville.spring.model.entities.reports.DayReport;
import pl.barksville.barksville.spring.model.entities.reports.MonthReport;
import pl.barksville.barksville.spring.model.entities.reports.WeekReport;
import pl.barksville.barksville.spring.model.entities.reports.YearReport;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


@Service
public class ReportService {

    final private DayReportRepository dayReportRepository;
    final private WeekReportRepository weekReportRepository;
    final private MonthReportRepository monthReportRepository;
    final private YearReportRepository yearReportRepository;
    final private SoldItemReportRepository soldItemReportRepository;

    public ReportService(DayReportRepository dayReportRepository, WeekReportRepository weekReportRepository, MonthReportRepository monthReportRepository, YearReportRepository yearReportRepository, SoldItemReportRepository soldItemReportRepository) {
        this.dayReportRepository = dayReportRepository;
        this.weekReportRepository = weekReportRepository;
        this.monthReportRepository = monthReportRepository;
        this.yearReportRepository = yearReportRepository;
        this.soldItemReportRepository = soldItemReportRepository;
    }

    public List<DayReport> getDayReportsList() {

        return dayReportRepository.findAll(Sort.by(Sort.Direction.ASC, "reportName"));
    }

    public DayReport getDayReport(LocalDate reportDate) {

        return dayReportRepository.findByReportName(
                "Day Report - "
                        + reportDate.getDayOfMonth() + "."
                        + reportDate.getMonthValue() + "."
                        + reportDate.getYear());
    }

    public List<WeekReport> getWeekReportsList() {
        return new ArrayList<>();
    }

    public WeekReport getWeekReport(LocalDate reportDate) {

        WeekFields weekFields = WeekFields.ISO;

        return weekReportRepository.findByReportName(
                "Week Report - "
                        + reportDate.getMonthValue() + "."
                        + reportDate.getYear() + " -it is "
                        + reportDate.get(weekFields.weekOfWeekBasedYear()));


    }

    public List<MonthReport> getMonthReportsList() {
        return new ArrayList<>();
    }

    public MonthReport getMonthReport(LocalDate reportDate) {
        return monthReportRepository.findByReportName(
                "Month Report - "
                        + reportDate.getMonthValue() + "."
                        + reportDate.getYear());
    }

    }

    public List<YearReport> getYearReportsList() {
        return new ArrayList<>();
    }

    public YearReport getYearReport(LocalDate reportDate) {

        return yearReportRepository.findByReportName(
                "Year Report - "
                        + reportDate.getYear());
    }
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
