package ru.finam.date4gwt.java.text;

import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.i18n.client.constants.NumberConstants;

import java.util.Locale;

/**
 * User: korzhevskiy
 * Date: 18.06.13
 */
public class DecimalFormatSymbols {
    public DecimalFormatSymbols() {
    }

    public DecimalFormatSymbols(Locale locale) {
    }

    public char getDecimalSeparator() {
        NumberConstants constants = LocaleInfo.getCurrentLocale().getNumberConstants();
        return constants.decimalSeparator().charAt(0);
    }
}
