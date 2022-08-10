package com.company.repository;


import com.company.domain.Sale;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SaleRepositoryTest {
    @Test
    public void shouldSaveSale() {
        Sale sale1 = new Sale("apple", .20);
        Sale sale2 = new Sale("apple", .40);
        SaleRepository repository = new SaleRepository();
        repository.save(sale1);
        repository.save(sale2);

        List<Sale> saleList = repository.findBy("apple");

        Assert.assertEquals(2, saleList.size());
    }

    @Test
    public void shouldFindByProductType() {
        Sale sale1 = new Sale("apple", .20);
        Sale sale2 = new Sale("orange", .40);
        SaleRepository repository = new SaleRepository();
        repository.save(sale1);
        repository.save(sale2);

        Assert.assertEquals(1, repository.findBy("apple").size());
        Assert.assertEquals(1, repository.findBy("orange").size());
    }

    @Test
    public void shouldReturnAllSaleGroupByProductType() {
        Sale sale1 = new Sale("apple", .20);
        Sale sale2 = new Sale("orange", .40);
        SaleRepository repository = new SaleRepository();
        repository.save(sale1);
        repository.save(sale2);

        Assert.assertEquals(2, repository.findAllGroupByProductType().size());
    }
}