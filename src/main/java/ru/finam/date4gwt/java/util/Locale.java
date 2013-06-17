package java.util;

/**
 * User: korzhevskiy
 * Date: 04.06.13
 */
public final class Locale {
    //TODO use com.google.gwt.i18n.client.LocaleInfo ?
    private String language;
    private String country;

    private Locale(String language) {
        this(language, "");
    }

    private Locale(String language, String country) {
        this.language = language;
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public static Locale getDefault() {
        return new Locale(getBrowserLocale());
    }

    private static native String getBrowserLocale() /*-{
        var navigator = $wnd.navigator;
        return navigator.language || navigator.browserLanguage
            || navigator.systemLanguage || navigator.userLanguage || 'en';
    }-*/;

    @Override
    public String toString() {
        return country != null && !country.isEmpty() ? language + '_' + country : language;
    }
}
