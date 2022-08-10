package com.company.service;

import com.company.domain.AdjustmentOperation;
import com.company.domain.Sale;
import com.company.repository.SaleRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;

@RunWith(MockitoJUnitRunner.class)
public class SaleServiceTest {

    @Test
    public void shouldAddSale() {
        SaleRepository saleRepository = Mockito.mock(SaleRepository.class);
        SaleService saleService = new SaleService(saleRepository);
        Sale sale = new Sale("apple", .20);
        saleService.add(sale);

        Mockito.verify(saleRepository).save(sale);
    }

    @Test
    public void shouldAdjustPriceOfExistingSales() {
        SaleRepository saleRepository = Mockito.mock(SaleRepository.class);
        SaleService saleService = new SaleService(saleRepository);
        Sale sale = new Sale("apple", .20);
        Mockito.when(saleRepository.findBy("apple")).thenReturn(Collections.singletonList(sale));
        saleService.adjustPrice("apple", AdjustmentOperation.Add, .20);

        Assert.assertEquals(.40, sale.getAdjustedValue(), 0);
    }

    @Test
    public void shouldGetAllProductSales() {
        SaleRepository saleRepository = Mockito.mock(SaleRepository.class);
        SaleService saleService = new SaleService(saleRepository);
        saleService.getAllProductSales();

        Mockito.verify(saleRepository).findAllGroupByProductType();
    }
}