package com.company.util;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class MessageCleanerUtil {

    public static String MESSAGE_DELIMITER = "\\s+";

    public static double format(double value) {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return Double.parseDouble(df.format(value));
    }

    // Parse the price and get only the value
    // @return[double] e.g "20p" will become 0.20
    public static double cleanPrice(String rawPrice) {
        double price = Double.parseDouble(rawPrice.replaceAll("£|p", ""));
        if (!rawPrice.contains(".")) {
            price = price / 100d;
        }
        return price;
    }

    public static String cleanProductType(String rawProductType) {
        String parsedType = "";
        String typeWithoutLastChar = rawProductType.substring(0, rawProductType.length() - 1);
        if (rawProductType.endsWith("o")) {
            parsedType = String.format("%soes", typeWithoutLastChar);
        } else if (rawProductType.endsWith("y")) {
            parsedType = String.format("%sies", typeWithoutLastChar);
        } else if (rawProductType.endsWith("h")) {
            parsedType = String.format("%shes", typeWithoutLastChar);
        } else if (!rawProductType.endsWith("s")) {
            parsedType = String.format("%ss", rawProductType);
        } else {
            parsedType = String.format("%s", rawProductType);
        }
        return parsedType.toLowerCase();
    }
}
