package com.company.repository;

import com.company.domain.Sale;

import java.util.*;

public class SaleRepository {

    private final Map<String, List<Sale>> saleMap = new HashMap<>();

    public List<Sale> findBy(String productType) {
        return saleMap.getOrDefault(productType, Collections.emptyList());
    }

    public Sale save(Sale sale) {
        List<Sale> saleList = saleMap.getOrDefault(sale.getProductType(), new ArrayList<>());
        saleList.add(sale);
        saleMap.put(sale.getProductType(), saleList);
        return sale;
    }

    public Map<String, List<Sale>> findAllGroupByProductType() {
        return Collections.unmodifiableMap(saleMap);
    }
}
