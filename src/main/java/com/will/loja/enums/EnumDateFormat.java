package com.will.loja.enums;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public enum EnumDateFormat {

    DD_MM(newThreadLocalSimpleDateFormat("dd-MM")),
    DDMM(newThreadLocalSimpleDateFormat("dd/MM")),
    DD_MM_YY(newThreadLocalSimpleDateFormat("dd-MM-yy")),
    DDMMYY(newThreadLocalSimpleDateFormat("dd/MM/yy")),
    DD_MM_YYYY(newThreadLocalSimpleDateFormat("dd-MM-yyyy")),
    DDMMYYYY(newThreadLocalSimpleDateFormat("dd/MM/yyyy")),
    YYYY_MM_DD(newThreadLocalSimpleDateFormat("yyyy-MM-dd")),
    YYYYMMDD(newThreadLocalSimpleDateFormat("yyyy/MM/dd")),
    DD_MM_HH_MM(newThreadLocalSimpleDateFormat("dd-MM HH:mm")),
    DDMMHHMM(newThreadLocalSimpleDateFormat("dd/MM/yyyy HH:mm")),
    DD_MM_HH_MM_SS(newThreadLocalSimpleDateFormat("dd-MM HH:mm:ss")),
    DDMMHHMMSS(newThreadLocalSimpleDateFormat("dd/MM HH:mm:ss")),
    DD_MM_YY_HH_MM_SS(newThreadLocalSimpleDateFormat("dd-MM-yy HH:mm:ss")),
    DDMMYYHHMMSS(newThreadLocalSimpleDateFormat("dd/MM/yy HH:mm:ss")),
    YYYYMMDDTHHMMSS(newThreadLocalSimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")),
    EXTENSO(newThreadLocalSimpleDateFormat("EEEE',' dd 'de' yyyy 'as' HH:mm:ss"))
    ;

    private final ThreadLocal<SimpleDateFormat> formatter;

    EnumDateFormat(final ThreadLocal<SimpleDateFormat> formatter) {
        this.formatter = formatter;
    }

    private static ThreadLocal<SimpleDateFormat> newThreadLocalSimpleDateFormat(final String formatterString) {
        return ThreadLocal.withInitial(() -> new SimpleDateFormat(formatterString));
    }

    public SimpleDateFormat getFormat() {
        return this.formatter.get();
    }

    public String format(final Date date) {
        return getFormat().format(date);
    }

    public Date parse(String source) throws ParseException {
        return this.formatter.get().parse(source);
    }
}
