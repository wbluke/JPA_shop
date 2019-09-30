package study.jpashop.utils;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@Slf4j
public class DateFormatter {
    private static final SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd", Locale.KOREA);

    private DateFormatter() {
    }

    public static String formatToSimpleDate(Date date) {
        String simpleDate = formatter.format(date);
        log.debug("simple date >>>>>>>>>>>>> {}", simpleDate);

        return simpleDate;
    }
}
