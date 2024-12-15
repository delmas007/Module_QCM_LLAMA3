package ci.devai.technicaltest;

import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

@Component("dateUtils")
public class DateUtils {
    public String format(LocalDateTime date, String pattern) {
        return date.format(DateTimeFormatter.ofPattern(pattern));
    }
}
