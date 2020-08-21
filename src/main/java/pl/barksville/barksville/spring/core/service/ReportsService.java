package pl.barksville.barksville.spring.core.service;


import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.barksville.barksville.spring.model.dal.repositories.*;
import pl.barksville.barksville.spring.model.entities.data.Invoice;
import pl.barksville.barksville.spring.model.entities.data.Item;
import pl.barksville.barksville.spring.model.entities.data.ShopReport;
import pl.barksville.barksville.spring.model.entities.reports.*;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReportsService {

    final private DayReportRepository dayReportRepository;
    final private WeekReportRepository weekReportRepository;
    final private MonthReportRepository monthReportRepository;
    final private YearReportRepository yearReportRepository;
    final private SoldItemReportRepository soldItemReportRepository;
    final private InvoiceService invoiceService;
    //cyclic dependency ShopReportService- change to shopReportRepository;
    //final private ShopReportService shopReportService;
    final private ShopReportRepository shopReportRepository;
    final private ItemService itemService;

    public ReportsService(DayReportRepository dayReportRepository,
                          WeekReportRepository weekReportRepository,
                          MonthReportRepository monthReportRepository,
                          YearReportRepository yearReportRepository,
                          SoldItemReportRepository soldItemReportRepository,
                          InvoiceService invoiceService,
                          ShopReportRepository shopReportRepository, ItemService itemService) {

        this.dayReportRepository = dayReportRepository;
        this.weekReportRepository = weekReportRepository;
        this.monthReportRepository = monthReportRepository;
        this.yearReportRepository = yearReportRepository;
        this.soldItemReportRepository = soldItemReportRepository;
        this.invoiceService = invoiceService;
        this.shopReportRepository = shopReportRepository;

        this.itemService = itemService;
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

        return weekReportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportDate"));
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

        return monthReportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportDate"));
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

        return yearReportRepository.findAll(Sort.by(Sort.Direction.DESC, "reportDate"));
    }

    public YearReport getYearReport(LocalDate reportDate) {

        return yearReportRepository.findByReportDate(reportDate);

      /*  return yearReportRepository.findByReportName(
                "Year Report - "
                        + reportDate.getYear());*/
    }


    @Transactional
    public void createDayReport(LocalDate reportDate) {
        DayReport dayReport;
        if (dayReportRepository.existsByReportDate(reportDate)) {
            dayReport = dayReportRepository.findByReportDate(reportDate);

            for (SoldItemReport soldItemReport : dayReport.getSoldItemReportList()
            ) {
                if(soldItemReport.getSoldInvoiceItem()!=null) {
                    Item item = itemService.findById(soldItemReport.getSoldInvoiceItem().getId());
                    item.setLeftItems(item.getQuantity() + soldItemReport.getQuantity() / item.getParts());
                    item.setIsSold(false);
                    itemService.saveItem(item);
                }
            }

            List<Long> soldItemId= dayReport.getSoldItemReportList().stream().map(report-> report.getSoldInvoiceItem().getId()).collect(Collectors.toList());
            for (Long id:soldItemId
                 ) {
                soldItemReportRepository.deleteById(id);
            }

        } else {
            dayReport = new DayReport();
        }


        List<Invoice> invoiceList = invoiceService.findAll(Sort.by(Sort.Direction.ASC, "date"));
        if (invoiceList.isEmpty()) {
            return;
        }

        ShopReport shopReport = shopReportRepository.getShopReportByDate(reportDate);
        if (shopReport == null) {
            return;
        }


        dayReport.setShopReport(shopReport);
        dayReport.setReportName("Day Report - "
                + reportDate.getDayOfMonth() + "."
                + reportDate.getMonthValue() + "."
                + reportDate.getYear());
        dayReport.setReportDate(reportDate);
        dayReport.setGrossIncome(shopReport.getEarnings());
        dayReport.setIsCorrect(true);


        List<SoldItemReport> soldItemReportList = new ArrayList<>();

        for (Item item : shopReport.getSoldProducts()
        ) {



            Double cost = 0.;
            Double soldItemCounter = item.getQuantity();

            for (Invoice invoice : invoiceList
            ) {
                for (Item invoiceItem : invoice.getBoughtProducts()
                ) {
                    if (item.getProduct().getName().equals(invoiceItem.getProduct().getName())) {
                        if (!invoiceItem.getIsSold()) {

                            SoldItemReport soldItemReport = new SoldItemReport();




                            if (invoiceItem.getLeftItems()* invoiceItem.getParts() <= soldItemCounter) {



                                soldItemReport.setGrossIncome((item.getPrice()/item.getQuantity())*invoiceItem.getLeftItems()*invoiceItem.getParts());
                                soldItemReport.setQuantity(invoiceItem.getLeftItems()*invoiceItem.getParts());

                                cost += invoiceItem.getLeftItems() * invoiceItem.getPrice()/ invoiceItem.getParts();
                                soldItemCounter -= invoiceItem.getLeftItems()* invoiceItem.getParts();
                                invoiceItem.setLeftItems(0.);
                                invoiceItem.setIsSold(true);



                            } else {
                                soldItemReport.setGrossIncome((item.getPrice()/item.getQuantity())*invoiceItem.getLeftItems()*invoiceItem.getParts());
                                soldItemReport.setQuantity(soldItemCounter);

                                cost += soldItemCounter * invoiceItem.getPrice();;
                                soldItemCounter -= 0.;
                                invoiceItem.setLeftItems(invoiceItem.getLeftItems() - soldItemCounter/invoiceItem.getParts());
                            }

                            soldItemReport.setSoldInvoiceItem(invoiceItem);
                            soldItemReport.setNetIncome(soldItemReport.getGrossIncome() - cost);
                            soldItemReportRepository.save(soldItemReport);
                            soldItemReportList.add(soldItemReport);
                            if (soldItemCounter == 0.) {
                                break;
                            }
                        }


                    }


                }
                if (soldItemCounter == 0.) {
                    break;
                }

            }

           if(soldItemCounter>0){
               dayReport.setIsCorrect(false);
           }
        }

        dayReport.setSoldItemReportList(soldItemReportList);

if(dayReport.getIsCorrect()) {
    Double dayReportDayNetIncome = soldItemReportList.stream().map(SoldItemReport::getNetIncome).mapToDouble(s -> s).reduce(0, Double::sum);
    dayReport.setNetIncome(dayReportDayNetIncome);

    dayReport.setExpenses(dayReport.getGrossIncome() - dayReportDayNetIncome);
} else {
    dayReport.setNetIncome(0);
    dayReport.setExpenses(0);
}



        dayReportRepository.save(dayReport);


    }

    @Transactional
    public void deleteDayReport(LocalDate localDate) {
        if (dayReportRepository.existsByReportDate(localDate)) {
            dayReportRepository.deleteByReportDate(localDate);
        }
    }

    public void createWeekReport(LocalDate reportDate) {
        WeekReport weekReport;
        if (weekReportRepository.existsByReportDate(reportDate)) {
            weekReport = weekReportRepository.findByReportDate(reportDate);
        } else {
            weekReport = new WeekReport();
        }

        List<DayReport> dayReportList = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            DayReport dayReport;
            if (dayReportRepository.existsByReportDate(reportDate.plusDays(i))) {
                dayReport = dayReportRepository.findByReportDate(reportDate.plusDays(i));
                dayReportList.add(dayReport);
            }

        }

        if (dayReportList.isEmpty()) {
            return;
        }

        weekReport.setDayReportList(dayReportList);

        weekReport.setExpenses(dayReportList.stream().map(DayReport::getExpenses).reduce(0., Double::sum));

        weekReport.setGrossIncome(dayReportList.stream().map(DayReport::getGrossIncome).reduce(0., Double::sum));

        weekReport.setNetIncome(dayReportList.stream().map(DayReport::getNetIncome).reduce(0., Double::sum));

        weekReport.setReportDate(reportDate);

         int endOfWeekDay=reportDate.getDayOfMonth()+6;

        weekReport.setReportName("Week Report - "
                + reportDate.getDayOfMonth() + "-"
                + endOfWeekDay+ "."
                + reportDate.getMonthValue() + "."
                + reportDate.getYear()
        );

        weekReportRepository.save(weekReport);
    }

    public void deleteWeekReport(LocalDate localDate) {
        if (weekReportRepository.existsByReportDate(localDate)) {
            weekReportRepository.deleteByReportDate(localDate);
        }
    }

    public void createMonthReport(LocalDate reportDate) {
        MonthReport monthReport;
        if (monthReportRepository.existsByReportDate(reportDate)) {
            monthReport = monthReportRepository.findByReportDate(reportDate);
        } else {
            monthReport = new MonthReport();
        }

        List<DayReport> dayReportList = new ArrayList<>();


        for (int i = 0; i < reportDate.lengthOfMonth(); i++) {
            DayReport dayReport;
            if (dayReportRepository.existsByReportDate(reportDate.plusDays(i))) {
                dayReport = dayReportRepository.findByReportDate(reportDate.plusDays(i));
                dayReportList.add(dayReport);
            }
        }

        if (dayReportList.isEmpty()) {
            return;
        }

        monthReport.setDayReportList(dayReportList);
        monthReport.setExpenses(dayReportList.stream().map(DayReport::getExpenses).reduce(0., Double::sum));
        monthReport.setGrossIncome(dayReportList.stream().map(DayReport::getGrossIncome).reduce(0., Double::sum));
        monthReport.setNetIncome(dayReportList.stream().map(DayReport::getNetIncome).reduce(0., Double::sum));
        monthReport.setReportDate(reportDate);
        monthReport.setReportName("Month Report - "
                + reportDate.getMonthValue() + "."
                + reportDate.getYear());

        monthReportRepository.save(monthReport);

    }

    public void deleteMonthReport(LocalDate localDate) {
        if (monthReportRepository.existsByReportDate(localDate)) {
            monthReportRepository.deleteByReportDate(localDate);
        }
    }

    public void createYearReport(LocalDate reportDate) {
        YearReport yearReport;
        if (yearReportRepository.existsByReportDate(reportDate)) {
            yearReport = yearReportRepository.findByReportDate(reportDate);
        } else {
            yearReport = new YearReport();
        }


        List<MonthReport> monthReportList = new ArrayList<>();

        for (int i = 0; i < 12; i++) {
            MonthReport monthReport;
            if (monthReportRepository.existsByReportDate(reportDate.plusMonths(i))) {
               monthReport= monthReportRepository.findByReportDate(reportDate.plusMonths(i));
                monthReportList.add(monthReport);
            }
        }

        if (monthReportList.isEmpty()) {
            return;
        }




        yearReport.setMonthReportList(monthReportList);
        yearReport.setExpenses(monthReportList.stream().map(MonthReport::getExpenses).reduce(0., Double::sum));
        yearReport.setGrossIncome(monthReportList.stream().map(MonthReport::getGrossIncome).reduce(0., Double::sum));
        yearReport.setNetIncome(monthReportList.stream().map(MonthReport::getNetIncome).reduce(0., Double::sum));
        yearReport.setReportDate(reportDate);
        yearReport.setReportName("Year Report - "
                + reportDate.getYear());

        yearReportRepository.save(yearReport);
    }

    public void deleteYearReport(LocalDate localDate) {
        if (yearReportRepository.existsByReportDate(localDate)) {
            yearReportRepository.deleteByReportDate(localDate);
        }
    }

    public void recreateWrongDayReports() {
        List<DayReport> dayReportList = new ArrayList<>();

        for (DayReport dayReport: dayReportRepository.findAll(Sort.by(Sort.Direction.ASC, "reportDate"))
             ) {
            if(!dayReport.getIsCorrect()){
                dayReportList.add(dayReport);
            }
        }

        for (DayReport dayReport:dayReportList
             ) {
            createDayReport(dayReport.getReportDate());
        }

    }

    public void createNotCreatedDayReports() {
      List<ShopReport> shopReportList =  shopReportRepository.findAll();
        for (ShopReport shopReport: shopReportList
             ) {
            if(!dayReportRepository.existsByReportDate(shopReport.getDate())){
                createDayReport(shopReport.getDate());
            }
        }
    }
}
