package br.ufscar.dc.dsw.atividadeaa3.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public abstract class DataUtil {

    private static final String FORMATO_DATA = "yyyy-MM-dd hh:mm";

    public static Date convertStringToDate(String dataString) throws ParseException {
        return Date.from(LocalDateTime.parse(dataString).atZone(ZoneId.systemDefault()).toInstant());
    }

    public static String convertDateToString(Date data) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(FORMATO_DATA);
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime().format(formatter);
    }

    public static Date converteLocalDateToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

}
