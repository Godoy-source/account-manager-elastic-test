package com.whiteboard.accountmanager.utils;

import lombok.experimental.UtilityClass;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DataUtils {

    public String formatOffsetData(OffsetDateTime data) {
        return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(data);
    }
}
