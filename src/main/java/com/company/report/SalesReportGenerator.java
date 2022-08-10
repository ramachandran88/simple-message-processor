package com.company.report;

import com.company.domain.Sale;
import com.company.service.SaleService;

import java.util.List;
import java.util.Map;

import static com.company.util.MessageCleanerUtil.format;

public class SalesReportGenerator {
    private final SaleService saleService;

    public SalesReportGenerator(SaleService saleService) {
        this.saleService = saleService;
    }

    public void printSalesReport() {
        Map<String, List<Sale>> allProductSales = saleService.getAllProductSales();
        System.out.println("------------------------------------------------------------");
        System.out.format("%20s%20s%20s", "ProductType", "NumberOfSale", "TotalValue\n");
        for (Map.Entry<String, List<Sale>> entry : allProductSales.entrySet()) {
            System.out.format("%20s%20s%20s\n", entry.getKey(), getTotalSales(entry.getValue()),
                    format(getTotalValue(entry.getValue())));
        }
        System.out.println("-----------------------------------------------------------");
    }

    public void printSalesAdjustmentReport() {
        Map<String, List<Sale>> allProductSales = saleService.getAllProductSales();
        System.out.println("-------------------------Adjusted Sale Report Start-----------------------------------");
        System.out.format("%20s%20s\n", "ProductType", "AdjustedValue");
        for (Map.Entry<String, List<Sale>> entry : allProductSales.entrySet()) {
            System.out.format("%20s%20s\n", entry.getKey(), format(getTotalAdjustValue(entry.getValue())));
        }
        System.out.println("-------------------------Adjusted Sale Report End----------------------------------");
    }

    private double getTotalAdjustValue(List<Sale> value) {
        return value.stream()
                .map(Sale::getAdjustedValue)
                .mapToDouble(Double::doubleValue)
                .sum();
    }

    private int getTotalSales(List<Sale> value) {
        return value.stream()
                .map(Sale::getQuantity)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private double getTotalValue(List<Sale> value) {
        return value.stream()
                .map(Sale::getTotalValue)
                .mapToDouble(Double::doubleValue)
                .sum();
    }


}
