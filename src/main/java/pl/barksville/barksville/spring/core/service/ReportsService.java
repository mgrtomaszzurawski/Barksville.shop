package pl.barksville.barksville.spring.core.service;

import jdk.vm.ci.meta.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.*;
import pl.barksville.barksville.spring.model.entities.data.Invoice;
import pl.barksville.barksville.spring.model.entities.data.ShopReport;
import pl.barksville.barksville.spring.model.entities.reports.DayReport;
import pl.barksville.barksville.spring.model.entities.reports.MonthReport;
import pl.barksville.barksville.spring.model.entities.reports.WeekReport;
import pl.barksville.barksville.spring.model.entities.reports.YearReport;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.List;


@Service
public class ReportsService {

    final private DayReportRepository dayReportRepository;
    final private WeekReportRepository weekReportRepository;
    final private MonthReportRepository monthReportRepository;
    final private YearReportRepository yearReportRepository;
    final private SoldItemReportRepository soldItemReportRepository;
    final private InvoiceRepository invoiceRepository;
    final private ShopReportRepository shopReportRepository;

    public ReportsService(DayReportRepository dayReportRepository, WeekReportRepository weekReportRepository, MonthReportRepository monthReportRepository, YearReportRepository yearReportRepository, SoldItemReportRepository soldItemReportRepository, InvoiceRepository invoiceRepository, ShopReport shopReport, ShopReportRepository shopReportRepository) {
        this.dayReportRepository = dayReportRepository;
        this.weekReportRepository = weekReportRepository;
        this.monthReportRepository = monthReportRepository;
        this.yearReportRepository = yearReportRepository;
        this.soldItemReportRepository = soldItemReportRepository;
        this.invoiceRepository = invoiceRepository;
        this.shopReportRepository = shopReportRepository;
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

        return weekReportRepository.findAll();
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

        return monthReportRepository.findAll();
    }

    public MonthReport getMonthReport(LocalDate reportDate) {
        return monthReportRepository.findByReportName(
                "Month Report - "
                        + reportDate.getMonthValue() + "."
                        + reportDate.getYear());
    }

    }

    public List<YearReport> getYearReportsList() {

    return yearReportRepository.findAll();
    }

    public YearReport getYearReport(LocalDate reportDate) {

        return yearReportRepository.findByReportName(
                "Year Report - "
                        + reportDate.getYear());
    }

    public void createDayReport(LocalDate reportDate) {
    DayReport dayReport = new DayReport();

    Invoice invoice= invoiceRepository.getInvoiceByInvoiceDate(reportDate);

    ShopReport shopReport= shopReportRepository.getShopReportByDate(reportDate);





    }

    public void createWeekReport(LocalDate reportDate) {
    }

    public void createMonthReport(LocalDate reportDate) {
    }

    public void createYearReport(LocalDate reportDate) {
    }
}
