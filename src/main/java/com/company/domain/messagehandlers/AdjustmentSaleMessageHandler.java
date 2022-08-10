package com.company.domain.messagehandlers;

import com.company.domain.AdjustmentOperation;
import com.company.service.SaleService;

import java.util.Arrays;
import java.util.stream.Collectors;

import static com.company.util.MessageCleanerUtil.*;

public class AdjustmentSaleMessageHandler implements SaleMessageHandler {

    private final SaleService saleService;

    public AdjustmentSaleMessageHandler(SaleService saleService) {
        this.saleService = saleService;
    }

    @Override
    public void handle(String message) {
        String[] messageArray = message.trim().split(MESSAGE_DELIMITER);

        if (!shouldProcess(messageArray)) {
            return;
        }

        AdjustmentOperation operation = AdjustmentOperation.valueOf(messageArray[0]);
        String productType = cleanProductType(messageArray[2]);
        double adjustedPrice = cleanPrice(messageArray[1]);

        saleService.adjustPrice(productType, operation, adjustedPrice);

    }

    private boolean shouldProcess(String[] messageArray) {
        return messageArray.length == 3 &&
                messageArray[0].matches(getOperationRegex());
    }

    private String getOperationRegex() {
        return Arrays.stream(AdjustmentOperation.values())
                .map(Enum::name)
                .collect(Collectors.joining("|"));
    }


}
