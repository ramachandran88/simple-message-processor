package com.company.service;

import com.company.domain.AdjustmentOperation;
import com.company.domain.Sale;
import com.company.repository.SaleRepository;

import java.util.List;
import java.util.Map;

public class SaleService {
    private final SaleRepository saleRepository;

    public SaleService(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public void adjustPrice(String productType, AdjustmentOperation operation, double adjustedPrice) {
        saleRepository.findBy(productType)
                .forEach(sale -> sale.adjustPrice(operation, adjustedPrice));
    }

    public Map<String, List<Sale>> getAllProductSales() {
        return saleRepository.findAllGroupByProductType();
    }

    public void add(Sale sale) {
        saleRepository.save(sale);
    }
}
