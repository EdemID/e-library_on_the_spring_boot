package org.example.util;

import org.example.model.Book;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Examine {

    public static void bookExpire(final Book book) {
        LocalDateTime tenDaysAgo = LocalDateTime.now().minusDays(10);
        ZonedDateTime zdt = tenDaysAgo.atZone(ZoneId.systemDefault());
        Date output = Date.from(zdt.toInstant());

        if (book.getTakenAt().before(output)) {
            book.setExpired(true);
        }
    }
}
