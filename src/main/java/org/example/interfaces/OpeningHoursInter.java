package org.example.factories;

import org.example.entities.OpeningHours;
import java.time.LocalTime;

public class OpeningHoursFactory {

    public static OpeningHours createOpeningHours(String day, LocalTime open, LocalTime close) {
        OpeningHours oh = new OpeningHours();
        oh.setDay(day);
        oh.setOpenTime(open);
        oh.setCloseTime(close);
        return oh;
    }
}
