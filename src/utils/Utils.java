package utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    private static final DateTimeFormatter timeParser = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    public static LocalDate getLocalDateFromFormatedDateString(String date) {
        return  LocalDate.parse(date, timeParser);
    }

    public static String formatLocalDate(LocalDate date) {
        return timeParser.format(date);
    }

    private static DecimalFormat getDecimalParser() {
        DecimalFormatSymbols monetarySymbol = new DecimalFormatSymbols();
        monetarySymbol.setDecimalSeparator(',');
        monetarySymbol.setGroupingSeparator('.');
        return new DecimalFormat("#,###.00", monetarySymbol);
    }

    public static String formatMonetaryValue(BigDecimal value) {
        DecimalFormat monetaryParser = getDecimalParser();
        return  monetaryParser.format(value);
    }
}
