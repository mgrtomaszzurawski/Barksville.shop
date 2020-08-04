package pl.barksville.barksville.spring.core.service;

import jdk.vm.ci.meta.Local;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.*;
import pl.barksville.barksville.spring.model.entities.data.Invoice;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.ShopReport;
import pl.barksville.barksville.spring.model.entities.reports.*;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;


@Service
public class ReportsService {

    final private DayReportRepository dayReportRepository;
    final private WeekReportRepository weekReportRepository;
    final private MonthReportRepository monthReportRepository;
    final private YearReportRepository yearReportRepository;
    final private SoldItemReportRepository soldItemReportRepository;
    final private InvoiceRepository invoiceRepository;
    final private ShopReportRepository shopReportRepository;

    public ReportsService(DayReportRepository dayReportRepository, WeekReportRepository weekReportRepository, MonthReportRepository monthReportRepository, YearReportRepository yearReportRepository, SoldItemReportRepository soldItemReportRepository, InvoiceRepository invoiceRepository, ShopReportRepository shopReportRepository) {
        this.dayReportRepository = dayReportRepository;
        this.weekReportRepository = weekReportRepository;
        this.monthReportRepository = monthReportRepository;
        this.yearReportRepository = yearReportRepository;
        this.soldItemReportRepository = soldItemReportRepository;
        this.invoiceRepository = invoiceRepository;
        this.shopReportRepository = shopReportRepository;
    }

    public List<DayReport> getDayReportsList() {

        return dayReportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportDate"));
    }

    public DayReport getDayReport(LocalDate reportDate) {

        return dayReportRepository.findByReportDate(reportDate);

        /*return dayReportRepository.findByReportName(
                "Day Report - "
                        + reportDate.getDayOfMonth() + "."
                        + reportDate.getMonthValue() + "."
                        + reportDate.getYear());*/
    }

    public List<WeekReport> getWeekReportsList() {

        return weekReportRepository.findAll();
    }

    public WeekReport getWeekReport(LocalDate reportDate) {

        return weekReportRepository.findByReportDate(reportDate);

       /* WeekFields weekFields = WeekFields.ISO;

        return weekReportRepository.findByReportName(
                "Week Report - "
                + reportDate.getDayOfMonth()+"-"
                + reportDate.getDayOfMonth()+6+"."
                + reportDate.getMonthValue() + "."
                + reportDate.getYear()
                );
*/

    }

    public List<MonthReport> getMonthReportsList() {

        return monthReportRepository.findAll();
    }

    public MonthReport getMonthReport(LocalDate reportDate) {

        return monthReportRepository.findByReportDate(reportDate);

     /*   return monthReportRepository.findByReportName(
                "Month Report - "
                        + reportDate.getMonthValue() + "."
                        + reportDate.getYear());
    }*/

    }

    public List<YearReport> getYearReportsList() {

        return yearReportRepository.findAll();
    }

    public YearReport getYearReport(LocalDate reportDate) {

        return yearReportRepository.findByReportDate(reportDate);

      /*  return yearReportRepository.findByReportName(
                "Year Report - "
                        + reportDate.getYear());*/
    }

    public void createDayReport(LocalDate reportDate) {
        DayReport dayReport = new DayReport();

        List<Invoice> invoiceList = invoiceRepository.findAll(Sort.by(Sort.Direction.ASC, "date"));

        ShopReport shopReport = shopReportRepository.getShopReportByDate(reportDate);

        dayReport.setShopReport(shopReport);
        dayReport.setReportName("Day Report - "
                + reportDate.getDayOfMonth() + "."
                + reportDate.getMonthValue() + "."
                + reportDate.getYear());
        dayReport.setReportDate(reportDate);
        dayReport.setGrossIncome(shopReport.getEarnings());


        List<SoldItemReport> soldItemReportList = new ArrayList<>();

        for (Item item : shopReport.getSoldProducts()
        ) {
            SoldItemReport soldItemReport = new SoldItemReport();

            soldItemReport.setGrossIncome(item.getPrice());
            soldItemReport.setQuantity(item.getQuantity());


            Double cost = 0.;
            Double soldItemCounter = item.getQuantity();

            for (Invoice invoice : invoiceList
            ) {
                for (Item invoiceItem : invoice.getBoughtProducts()
                ) {
                    if (item.getProduct().getName().equals(invoiceItem.getProduct().getName())) {
                        if (!invoiceItem.getIsSold()) {
                            if (invoiceItem.getLeftItems() <= soldItemCounter) {
                                cost += invoiceItem.getLeftItems() * invoiceItem.getPrice();
                                soldItemCounter -= invoiceItem.getLeftItems();
                                invoiceItem.setLeftItems(0.);
                                invoiceItem.setIsSold(true);
                            }else{
                                cost += soldItemCounter* invoiceItem.getPrice();
                                soldItemCounter -= 0.;
                                invoiceItem.setLeftItems(invoiceItem.getLeftItems() - soldItemCounter);
                            }
                        }
                        soldItemReport.setSoldinvoiceItem(invoiceItem);
                        break;
                    }

                }

                soldItemReport.setNetIncome(soldItemReport.getGrossIncome()-cost);

                soldItemReportList.add(soldItemReport);

                if(soldItemCounter==0.){
                    break;
                }
            }
        }

            dayReport.setSoldItemReportList(soldItemReportList);


            Double dayReportCost=soldItemReportList.stream().map(SoldItemReport::getNetIncome).mapToDouble(s->s).reduce(0,Double::sum);
            dayReport.setNetIncome(dayReport.getGrossIncome()-dayReportCost);

            dayReport.setExpenses(dayReportCost);

            dayReportRepository.save(dayReport);


        }

        public void createWeekReport (LocalDate reportDate){
        WeekReport weekReport = new WeekReport();

        List<DayReport> dayReportList = new ArrayList<>();

        for(int i=0;i<7;i++){
            DayReport dayReport = new DayReport();
            if(dayReportRepository.existsByReportDate(reportDate)) {
                dayReportRepository.findByReportDate(reportDate);
            }
            dayReportList.add(dayReport);
        }
        weekReport.setDayReportList(dayReportList);

        weekReport.setExpenses(dayReportList.stream().map(DayReport::getExpenses).reduce(0.,Double::sum));

        weekReport.setGrossIncome(dayReportList.stream().map(DayReport::getGrossIncome).reduce(0.,Double::sum));

        weekReport.setNetIncome(dayReportList.stream().map(DayReport::getNetIncome).reduce(0.,Double::sum));

        weekReport.setReportDate(reportDate);

        weekReport.setReportName(  "Week Report - "
                + reportDate.getDayOfMonth()+"-"
                + reportDate.getDayOfMonth()+6+"."
                + reportDate.getMonthValue() + "."
                + reportDate.getYear()
                );

        weekReportRepository.save(weekReport);
        }

        public void createMonthReport (LocalDate reportDate){
        }

        public void createYearReport (LocalDate reportDate){
        }
    }
