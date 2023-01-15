package com.whiteboard.accountmanager.utils;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {

    public static String formatOffsetData(OffsetDateTime data) {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(data);
    }
}
