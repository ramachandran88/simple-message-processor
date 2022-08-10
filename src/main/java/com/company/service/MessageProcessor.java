package com.company.service;

import com.company.domain.messagehandlers.SaleMessageHandler;
import com.company.report.SalesReportGenerator;

import java.util.List;

public class MessageProcessor {
    private static final int SALE_REPORTING_FREQUENCY = 10;
    private static final int MAX_MESSAGE_TO_PROCESS = 50;
    private final List<SaleMessageHandler> saleMessageHandlers;
    private final SalesReportGenerator salesReportGenerator;
    private int counter = 0;

    public MessageProcessor(List<SaleMessageHandler> saleMessageHandlers,
                            SalesReportGenerator salesReportGenerator) {
        this.saleMessageHandlers = saleMessageHandlers;
        this.salesReportGenerator = salesReportGenerator;
    }

    public void process(String message) {
        if (isValidMessage(message)) {
            return;
        }
        counter++;

        saleMessageHandlers
                .forEach(saleMessageHandler -> saleMessageHandler.handle(message));

        if (counter % SALE_REPORTING_FREQUENCY == 0) {
            salesReportGenerator.printSalesReport();
        }

        if (counter % MAX_MESSAGE_TO_PROCESS == 0) {
            System.out.println("System will pause now and not accept more messages");
            salesReportGenerator.printSalesAdjustmentReport();
            System.exit(0);
        }
    }

    private boolean isValidMessage(String message) {
        return message == null || message.isEmpty();
    }
}
