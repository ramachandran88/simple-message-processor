package com.company.domain.messagehandlers;

import com.company.domain.AdjustmentOperation;
import com.company.service.SaleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AdjustmentSaleMessageHandlerTest {
    @Test
    public void shouldNotProcessInvalidMessage() {
        SaleService saleService = Mockito.mock(SaleService.class);
        AdjustmentSaleMessageHandler handler = new AdjustmentSaleMessageHandler(saleService);
        handler.handle("20 sales of apples at 10p each");
        Mockito.verifyZeroInteractions(saleService);
    }

    @Test
    public void shouldNotProcessInvalidMessage2() {
        SaleService saleService = Mockito.mock(SaleService.class);
        AdjustmentSaleMessageHandler handler = new AdjustmentSaleMessageHandler(saleService);
        handler.handle("apple at 10p");
        Mockito.verifyZeroInteractions(saleService);
    }

    @Test
    public void shouldProcessValidMessage() {
        SaleService saleService = Mockito.mock(SaleService.class);
        AdjustmentSaleMessageHandler handler = new AdjustmentSaleMessageHandler(saleService);
        handler.handle("Add 20p apples");
        Mockito.verify(saleService).adjustPrice("apples", AdjustmentOperation.Add,
                .20);

    }
}