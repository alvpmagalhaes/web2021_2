package br.ufscar.dc.dsw.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class DataUtil {

    private static final String FORMATO_DATA = "yyyy-MM-ddThh:mm";

    public static Date convertStringToDate(String dataString) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA);
        return dateFormat.parse(dataString);
    }

    public static String convertDateToString(Date data) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMATO_DATA);
        return dateFormat.format(data);
    }

}
