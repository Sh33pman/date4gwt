package java.util;

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
        if ("UTC".equals(id)) {
            return new TimeZone(true);
        }
        throw new UnsupportedOperationException();
    }

    public int getRawOffset() {
        return isUTC ? 0 : getNativeRawOffset();
    }

    public int getOffset(long date) {
        return 0;//TODO
    }

    private static native int getNativeRawOffset() /*-{
        return new Date().getTimezoneOffset() * 60 * 1000;
    }-*/;

}