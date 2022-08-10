package com.company;

import com.company.domain.messagehandlers.AdjustmentSaleMessageHandler;
import com.company.domain.messagehandlers.MultiSaleMessageHandler;
import com.company.domain.messagehandlers.SaleMessageHandler;
import com.company.domain.messagehandlers.SingleSaleMessageHandler;
import com.company.repository.SaleRepository;
import com.company.service.MessageProcessor;
import com.company.service.SaleService;
import com.company.service.SalesReportGenerator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Application {

    public static void main(String[] args) {
        String fileName = "testinput/input.txt";
        MessageProcessor messageProcessor = buildMessageProcessor();
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(messageProcessor::process);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static MessageProcessor buildMessageProcessor() {
        SaleService saleService = new SaleService(new SaleRepository());
        List<SaleMessageHandler> handlers = Arrays.asList(
                new SingleSaleMessageHandler(saleService),
                new MultiSaleMessageHandler(saleService),
                new AdjustmentSaleMessageHandler(saleService)
        );
        return new MessageProcessor(handlers, new SalesReportGenerator(saleService));
    }
}
