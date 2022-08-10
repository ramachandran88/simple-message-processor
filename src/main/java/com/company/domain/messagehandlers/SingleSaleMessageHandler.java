package com.company.domain.messagehandlers;


import com.company.domain.Sale;
import com.company.service.SaleService;

import static com.company.util.MessageCleanerUtil.*;

public class SingleSaleMessageHandler implements SaleMessageHandler {
    private final SaleService saleService;

    public SingleSaleMessageHandler(SaleService saleService) {
        this.saleService = saleService;
    }

    @Override
    public void handle(String message) {
        String[] messageArray = message.trim().split(MESSAGE_DELIMITER);

        if (!shouldProcess(messageArray)) {
            return;
        }

        String productType = cleanProductType(messageArray[0]);
        double productPrice = cleanPrice(messageArray[2]);

        Sale sale = new Sale(productType, productPrice);
        saleService.add(sale);

    }

    private boolean shouldProcess(String[] messageArray) {
        return messageArray.length == 3 && messageArray[1].contains("at");
    }
}
