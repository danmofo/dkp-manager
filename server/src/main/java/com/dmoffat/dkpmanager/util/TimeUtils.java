package com.dmoffat.dkpmanager.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TimeUtils {

    public String format(LocalDate date) {
        return DateTimeFormatter.ofPattern("dd/MM/yyyy").format(date);
    }

}
