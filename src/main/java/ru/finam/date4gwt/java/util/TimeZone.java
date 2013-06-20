package java.util;

import com.google.gwt.core.client.JsDate;

/**
 * User: korzhevskiy
 * Date: 04.06.13
 */
public final class TimeZone {
    //TODO use com.google.gwt.i18n.client.TimeZone ?

    private boolean isUTC;

    private TimeZone(boolean isUTC) {
        this.isUTC = isUTC;
    }

    public static TimeZone getDefault() {
        return new TimeZone(false);
    }

    public static TimeZone getTimeZone(String id) {
        boolean isUTC = "UTC".equals(id);
        return new TimeZone(isUTC);
    }

    public int getRawOffset() {
        return isUTC ? 0 : getNativeRawOffset(JsDate.create());
    }

    public int getOffset(long date) {
        return getNativeRawOffset(JsDate.create(date));
    }

    private static int getNativeRawOffset(JsDate date) {
        return date.getTimezoneOffset() * 60 * 1000;
    }

}