package com.company.domain.messagehandlers;

import com.company.domain.Sale;
import com.company.service.SaleService;

import static com.company.util.MessageCleanerUtil.*;

public class MultiSaleMessageHandler implements SaleMessageHandler {
    private final SaleService saleService;

    public MultiSaleMessageHandler(SaleService saleService) {
        this.saleService = saleService;
    }

    @Override
    public void handle(String message) {

        String[] messageArray = message.trim().split(MESSAGE_DELIMITER);

        if (!shouldProcess(messageArray)) {
            return;
        }

        String productType = cleanProductType(messageArray[3]);
        double productPrice = cleanPrice(messageArray[5]);
        int productQuantity = Integer.parseInt(messageArray[0]);

        Sale sale = new Sale(productType, productPrice, productQuantity);
        saleService.add(sale);
    }

    private boolean shouldProcess(String[] messageArray) {
        return messageArray.length == 7;
    }
}
