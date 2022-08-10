package com.company.domain.messagehandlers;


import com.company.domain.Sale;
import com.company.service.SaleService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SingleSaleMessageHandlerTest {
    @Test
    public void shouldNotProcessInvalidMessage() {
        SaleService saleService = Mockito.mock(SaleService.class);
        SingleSaleMessageHandler handler = new SingleSaleMessageHandler(saleService);
        handler.handle("20 sales of apples at 10p each");
        Mockito.verifyZeroInteractions(saleService);
    }

    @Test
    public void shouldNotProcessInvalidMessage2() {
        SaleService saleService = Mockito.mock(SaleService.class);
        SingleSaleMessageHandler handler = new SingleSaleMessageHandler(saleService);
        handler.handle("Add 20p apples");
        Mockito.verifyZeroInteractions(saleService);
    }

    @Test
    public void shouldProcessValidMessage() {
        SaleService saleService = Mockito.mock(SaleService.class);
        SingleSaleMessageHandler handler = new SingleSaleMessageHandler(saleService);
        handler.handle("apple at 10p");
        ArgumentCaptor<Sale> saleArgumentCaptor = ArgumentCaptor.forClass(Sale.class);
        Mockito.verify(saleService).add(saleArgumentCaptor.capture());
        Sale actualSale = saleArgumentCaptor.getValue();

        Assert.assertEquals("apples", actualSale.getProductType());
        Assert.assertEquals(.10, actualSale.getTotalValue(), 0);
    }
}