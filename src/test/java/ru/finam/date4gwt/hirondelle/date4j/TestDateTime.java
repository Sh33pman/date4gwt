package hirondelle.date4j;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.i18n.client.TimeZone;
import com.google.gwt.i18n.client.constants.TimeZoneConstants;
import com.google.gwt.junit.client.GWTTestCase;

import hirondelle.date4j.DateTime;
import hirondelle.date4j.DateTimeParser;
import hirondelle.date4j.Util;

/**
 * JUnit tests.
 */
public class TestDateTime extends GWTTestCase {

    private static final boolean SUCCESS = true;
    private static final boolean FAIL = false;
    // TEST CASES
    private static final boolean LESS = true;
    private static final boolean MORE = false;

//    public TestDateTime() {
//        setName("TestDateTime");
//    }

    /**
     * Run the test cases.
     */
    public static void main(String args[]) {
//        String[] testCaseName = {TestDateTime.class.getName()};
//        TestRunner.main(testCaseName);
    }

    private static void log(Object aThing) {
        System.out.println(String.valueOf(aThing));
    }

    private static void log(String aMsg) {
        System.out.println(aMsg);
    }

    public void testCtorWithStringStandardFormat() {
        //note that you can pass just about anything to the constructor - except null
        testStandardFormatCtorSuccess("A");
        testStandardFormatCtorSuccess("ABC");
        testStandardFormatCtorSuccess("");
        testStandardFormatCtorSuccess("  ");
        testStandardFormatCtorSuccess("2009");
        testStandardFormatCtorSuccess("2009-01");
        testStandardFormatCtorSuccess("2009-01-01");
        testStandardFormatCtorSuccess("2009-01-01 23");
        testStandardFormatCtorSuccess("2009-01-01 23:40");
        testStandardFormatCtorSuccess("2009-01-01 23:40:19");
        testStandardFormatCtorSuccess("2009-01-01 23:40:19.123");
        testStandardFormatCtorFail(null);

        //accepts with (ISO-8601) AND without (human hand) leading zeroes.
        testStandardFormatCtorPlusParseSuccess("0009-01-01 23:40:19.0", 9, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("9-01-01 23:40:19.0", 9, 1, 1, 23, 40, 19, 0);

        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19.0", 2009, 1, 1, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40:19", 2009, 1, 1, 23, 40, 19, null);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23:40", 2009, 1, 1, 23, 40, null, null);
        testStandardFormatCtorPlusParseSuccess("2009-01-01 23", 2009, 1, 1, 23, null, null, null);

        testStandardFormatCtorPlusParseSuccess("2009-01-01", 2009, 1, 1, null, null, null, null);
        testStandardFormatCtorPlusParseSuccess("2009-01", 2009, 1, null, null, null, null, null);
        testStandardFormatCtorPlusParseSuccess("2009", 2009, null, null, null, null, null, null);
        testStandardFormatCtorPlusParseSuccess("1", 1, null, null, null, null, null, null);
        testStandardFormatCtorPlusParseSuccess("0001", 1, null, null, null, null, null, null);

        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19.0", null, null, null, 23, 40, 19, 0);
        testStandardFormatCtorPlusParseSuccess("23:40:19", null, null, null, 23, 40, 19, null);
        testStandardFormatCtorPlusParseSuccess("23:40", null, null, null, 23, 40, null, null);

        testStandardFormatCtorPlusParseFail("A");
        testStandardFormatCtorPlusParseFail(" ");
        testStandardFormatCtorPlusParseFail("");
        testStandardFormatCtorPlusParseFail("12345-01-01 23:40:19.123");
        testStandardFormatCtorPlusParseFail("0-01-01 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 12h:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 12 pm");
        testStandardFormatCtorPlusParseFail("2009-01-01 45:40:60");
        testStandardFormatCtorPlusParseFail("2009-01-01 16:40:19.1234567890");
        testStandardFormatCtorPlusParseFail("2009-01-01 24:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 16:60:19.123");
        testStandardFormatCtorPlusParseFail("2009-13-01 16:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-32 16:40:19.123");
        testStandardFormatCtorPlusParseFail("-1-01-01 16:40:19.123");


        testStandardFormatCtorPlusParseFail("2009-1-01 16:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-1 16:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 6:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 16:0:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 16:40:1.123");
    }

    public void testCtorWithStringStandardFormatWithT() {
        testStandardFormatCtorPlusParseSuccess("2009-01-01T23:40:19.123", 2009, 1, 1, 23, 40, 19, 123);
        testStandardFormatCtorPlusParseSuccess("2009-01-01T23:40:19.12", 2009, 1, 1, 23, 40, 19, 120);
        testStandardFormatCtorPlusParseSuccess("2009-01-01T23:40:19.1", 2009, 1, 1, 23, 40, 19, 100);
        testStandardFormatCtorPlusParseSuccess("2009-01-01T23:40:19", 2009, 1, 1, 23, 40, 19, null);
        testStandardFormatCtorPlusParseSuccess("2009-01-01T23:40", 2009, 1, 1, 23, 40, null, null);
        testStandardFormatCtorPlusParseSuccess("2009-01-01T23", 2009, 1, 1, 23, null, null, null);

        testStandardFormatCtorPlusParseSuccess("0009-01-01T23:40:19.123", 9, 1, 1, 23, 40, 19, 123);
        testStandardFormatCtorPlusParseSuccess("9-01-01T23:40:19.123", 9, 1, 1, 23, 40, 19, 123);

        testStandardFormatCtorPlusParseFail("2009-01-01A23:40:19.123");
        testStandardFormatCtorPlusParseFail("12345-01-01T23:40:19.123");
        testStandardFormatCtorPlusParseFail("0-01-01T23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01T12h:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01T12 pm");
        testStandardFormatCtorPlusParseFail("2009-01-01T45:40:60");
        testStandardFormatCtorPlusParseFail("2009-01-01T16:40:19.1234567890");
        testStandardFormatCtorPlusParseFail("2009-01-01T24:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01T16:60:19.123");
        testStandardFormatCtorPlusParseFail("2009-13-01T16:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-32T16:40:19.123");
        testStandardFormatCtorPlusParseFail("-1-01-01T16:40:19.123");

        testStandardFormatCtorPlusParseFail("2009-1-01 16:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-1 16:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 6:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 16:0:19.123");
        testStandardFormatCtorPlusParseFail("2009-01-01 16:40:1.123");
    }

    public void testRangeYear() {
        testRange(SUCCESS, "2009-01-01");
        testRange(SUCCESS, "1-01-01");
        testRange(SUCCESS, "01-01-01");
        testRange(SUCCESS, "0001-01-01");
        testRange(SUCCESS, "0010-01-01");
        testRange(SUCCESS, "10-01-01");
        testRange(SUCCESS, "100-01-01");
        testRange(SUCCESS, "0100-01-01");
        testRange(SUCCESS, "9999-01-01");
        testRange(SUCCESS, "2300-01-01");
        testRange(SUCCESS, "7998-01-01");

        testRange(FAIL, "99999-01-01");
        testRange(FAIL, "0-01-01");
        testRange(FAIL, "10000-01-01");
        testRange(FAIL, "10001-01-01");
    }

    public void testRangeMonth() {
        testRange(SUCCESS, "2009-01-01");
        testRange(SUCCESS, "2009-02-01");
        testRange(SUCCESS, "2009-10-01");
        testRange(SUCCESS, "2009-11-01");
        testRange(SUCCESS, "2009-12-01");

        testRange(FAIL, "2009-00-01");
        testRange(FAIL, "2009-13-01");
        testRange(FAIL, "2009-99-01");
    }

    public void testRangeDay() {
        testRange(SUCCESS, "2009-01-01");
        testRange(SUCCESS, "2009-02-02");
        testRange(SUCCESS, "2009-10-10");
        testRange(SUCCESS, "2009-11-30");
        testRange(SUCCESS, "2009-12-31");

        testRange(FAIL, "2009-10-00");
        testRange(FAIL, "2009-10-32");
    }

    public void testRangeHour() {
        testRange(SUCCESS, "2009-01-01 00:01:01");
        testRange(SUCCESS, "2009-01-01 01:01:01");
        testRange(SUCCESS, "2009-01-01 23:01:01");

        testRange(FAIL, "2009-01-01 24:01:01");
        testRange(FAIL, "2009-01-01 99:01:01");
    }

    public void testRangeMinute() {
        testRange(SUCCESS, "2009-01-01 00:00:01");
        testRange(SUCCESS, "2009-01-01 01:01:01");
        testRange(SUCCESS, "2009-01-01 23:59:01");

        testRange(FAIL, "2009-01-01 23:60:01");
        testRange(FAIL, "2009-01-01 23:99:01");
    }

    public void testRangeSecond() {
        testRange(SUCCESS, "2009-01-01 00:00:00");
        testRange(SUCCESS, "2009-01-01 01:01:01");
        testRange(SUCCESS, "2009-01-01 23:59:59");

        testRange(FAIL, "2009-01-01 23:59:60");
        testRange(FAIL, "2009-01-01 23:59:99");
    }

    public void testRangeNanosecond() {
        testRange(SUCCESS, "2009-01-01 00:00:00.000");
        testRange(SUCCESS, "2009-01-01 01:01:01.000");
        testRange(SUCCESS, "2009-01-01 01:01:01.000");
        testRange(SUCCESS, "2009-01-01 23:59:59.123");
        testRange(SUCCESS, "2009-01-01 23:59:59.999");

        testRange(FAIL, "2009-01-01 23:59:59.9990000000");
        testRange(FAIL, "2009-01-01 23:59:59.0000000000");
        testRange(FAIL, "2009-01-01 23:59:59.0000000000");
        testRange(FAIL, "2009-01-01 23:59:59.1230000000");
    }

    public void testMonthOverflow() {
        testStandardFormatCtorPlusParseFail("2009-01-32 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-02-29 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-03-32 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-04-31 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-05-32 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-06-31 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-07-32 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-08-32 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-09-31 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-10-32 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-11-31 23:40:19.123");
        testStandardFormatCtorPlusParseFail("2009-12-32 23:40:19.123");

        testStandardFormatCtorPlusParseFail("2008-02-30 23:40:19.123"); //leap year
    }

    public void testLeapYear() {
        testLeapYear(SUCCESS, "2008-01-01");
        testLeapYear(SUCCESS, "2004-01-01");
        testLeapYear(SUCCESS, "1996-01-01");
        testLeapYear(SUCCESS, "2000-01-01");
        testLeapYear(SUCCESS, "1600-01-01");
        testLeapYear(SUCCESS, "1200-01-01");
        testLeapYear(SUCCESS, "800-01-01");
        testLeapYear(SUCCESS, "400-01-01");

        testLeapYear(FAIL, "2009-01-01");
        testLeapYear(FAIL, "1900-01-01");
        testLeapYear(FAIL, "1800-01-01");
        testLeapYear(FAIL, "1700-01-01");
        testLeapYear(FAIL, "1900-01-01");
        testLeapYear(FAIL, "1500-01-01");
        testLeapYear(FAIL, "1400-01-01");
        testLeapYear(FAIL, "1300-01-01");
        testLeapYear(FAIL, "1100-01-01");
        testLeapYear(FAIL, "1000-01-01");
        testLeapYear(FAIL, "900-01-01");
        testLeapYear(FAIL, "700-01-01");
        testLeapYear(FAIL, "600-01-01");
        testLeapYear(FAIL, "500-01-01");
        testLeapYear(FAIL, "300-01-01");
        testLeapYear(FAIL, "200-01-01");
        testLeapYear(FAIL, "100-01-01");

        testLeapYearFails("11:18:.16.23");
    }

    public void testToString() {
        //Этот кусок работает!!!
        testToString(SUCCESS, "2001-01-01 12:34:56.123", "2001-01-01 12:34:56.123");
        testToString(SUCCESS, "2001-01-01 12:34:56.12", "2001-01-01 12:34:56.12");
        testToString(SUCCESS, "2001-01-01 12:34:56.1", "2001-01-01 12:34:56.1");

        testToString(SUCCESS, "2001-01-01 12:34:56", "2001-01-01 12:34:56");
        testToString(SUCCESS, "2001-01-01 12:34", "2001-01-01 12:34");
        testToString(SUCCESS, "2001-01-01 12", "2001-01-01 12");
        testToString(SUCCESS, "2001-01-01", "2001-01-01");
        testToString(SUCCESS, "2001-12", "2001-12");
        testToString(SUCCESS, "2001", "2001");

        // The String constructor is lenient in the extreme:
        testToString(SUCCESS, "BLAH", "BLAH");
        testToString(SUCCESS, "Humpday Jan 1, 2001 12:34:56.123", "Humpday Jan 1, 2001 12:34:56.123");

        testToString(FAIL, "2001-01-01", "2001-01-01 ");
        testToString(FAIL, "2001-12-01", " 2001-12-01 ");

        //date only
        testToString(SUCCESS, 2001, 1, 1, null, null, null, null, "2001-01-01");
        testToString(SUCCESS, 2001, 1, 31, null, null, null, null, "2001-01-31");
        testToString(SUCCESS, 2001, 11, 30, null, null, null, null, "2001-11-30");
        testToString(SUCCESS, 1, 11, 30, null, null, null, null, "1-11-30");
        testToString(SUCCESS, 2001, 1, null, null, null, null, null, "2001-01");
        testToString(SUCCESS, 2001, null, null, null, null, null, null, "2001");
        //date and time
        testToString(SUCCESS, 2001, 1, 31, 13, 30, 59, 123000000, "2001-01-31 13:30:59.123000000");
        testToString(SUCCESS, 2001, 1, 31, 13, 30, 59, 0, "2001-01-31 13:30:59.000000000");
        testToString(SUCCESS, 2001, 1, 31, 13, 30, 59, null, "2001-01-31 13:30:59");
        testToString(SUCCESS, 2001, 1, 31, 13, 30, null, null, "2001-01-31 13:30");
        testToString(SUCCESS, 2001, 1, 31, 13, null, null, null, "2001-01-31 13");
        //time only
        testToString(SUCCESS, null, null, null, 13, 30, 59, 123000000, "13:30:59.123000000");
        testToString(SUCCESS, null, null, null, 13, 30, 59, null, "13:30:59");

        //bizarre combination of formats
        testToString(SUCCESS, 2001, 1, 31, 13, null, 59, 123000000, "Y:2001 M:1 D:31 h:13 m:null s:59 f:123000000");

        //extra trailing space
        testToString(FAIL, 2001, 1, 31, 13, 30, 59, 123000000, "2001-01-31 13:30:59.123000000 ");
    }

    public void testDayOfWeek() {
        testDayOfWeek(SUCCESS, "2009-01-01", 5);
        testDayOfWeek(SUCCESS, "2009-01-02", 6);
        testDayOfWeek(SUCCESS, "2009-01-03", 7);
        testDayOfWeek(SUCCESS, "2009-01-04", 1);
        testDayOfWeek(SUCCESS, "2009-01-05", 2);
        testDayOfWeek(SUCCESS, "2009-01-06", 3);
        testDayOfWeek(SUCCESS, "2012-02-28", 3);
        testDayOfWeek(SUCCESS, "2012-02-29", 4);
        testDayOfWeek(SUCCESS, "1921-11-03", 5);
        testDayOfWeek(SUCCESS, "1928-05-29", 3);
        testDayOfWeek(SUCCESS, "1954-06-30", 4);
        testDayOfWeek(FAIL, "1954-06", 4);
        testDayOfWeek(FAIL, "1954", 4);
        testDayOfWeek(FAIL, "11:05:13", 4);
    }

    public void testDayOfYear() {
        testDayOfYear(SUCCESS, "1978-11-14", 318);
        testDayOfYear(SUCCESS, "1988-04-22", 113);
        testDayOfYear(SUCCESS, "1978-01-01", 1);
        testDayOfYear(SUCCESS, "1962-12-31", 365);
        testDayOfYear(SUCCESS, "1960-12-31", 366);
        testDayOfYear(FAIL, "1988-04", 113);
        testDayOfYear(FAIL, "1988", 113);
        testDayOfYear(FAIL, "11:13:59.123", 113);
    }

    public void testSameDayAs() {
        testSameDayAs(SUCCESS, "1955-04-30", "1955-04-30");
        testSameDayAs(SUCCESS, "1955-04-30 01:23:15", "1955-04-30");
        testSameDayAs(SUCCESS, "1955-04-30 01:23:15", "1955-04-30 16:56:07");
        testSameDayAs(SUCCESS, "1955-04-30 01:23", "1955-04-30 16:56:07");
        testSameDayAs(SUCCESS, "1955-04-30 01", "1955-04-30 16:56:07");
        testSameDayAs(SUCCESS, "1955-04-30 01:23:15", "1955-04-30 16:56:07.23");
        testSameDayAs(SUCCESS, "1955-04-30 16:56:07.233", "1955-04-30 16:56:07.23");

        testSameDayAs(FAIL, "1955-04-30", "1955-05-30");
        testSameDayAs(FAIL, "1955-04-30", "1955-04-03");
    }

    public void testCompare() {
        testCompare("1959-05-08", "1965-08-01", LESS);
        testCompare("1965-08-01", "1965-08-02", LESS);
        testCompare("1965-08-01", "1965-08-30", LESS);
        testCompare("1965-08-01", "1965-12-01", LESS);
        testCompare("1965-08-01", "1966-08-01", LESS);
        testCompare("1959-05-08 13:13:59", "1965-08-01", LESS);
        testCompare("1959-05-08", "1959-05-08 13:13:59", LESS);
        testCompare("1959-05-08", "1959-05-08 00:00:00", LESS);
        testCompare("1959-05-08", "1959-05-08 00:00", LESS);
        testCompare("1959-05-08", "1959-05-08 00", LESS);

        testCompare("1965-08-01", "1959-05-08", MORE);
        testCompare("900-12-31", "1-01-01", MORE);
        testCompare("1900-12-31", "1801-01-01", MORE);
        testCompare("5000-12-31", "1801-01-01", MORE);
    }

    public void testBefore() {
        testBefore("1579-05-31", "5856-03-01");
        testBefore("1579-05-31", "1579-06-01");
        testBefore("900-05-01", "1578-05-01");
    }

    public void testAfter() {
        testAfter("5856-03-01", "1579-05-31");
        testAfter("1579-06-01", "1579-05-31");
        testAfter("1901-05-01", "1579-05-01");
    }

    public void testTruncate() {
        testTruncate(SUCCESS, "1999-05-15 11:34:19.123", "1999-05-15 11:34:19", DateTime.Unit.SECOND);
        testTruncate(SUCCESS, "1999-05-15 11:34:19.123", "1999-05-15 11:34", DateTime.Unit.MINUTE);
        testTruncate(SUCCESS, "1999-05-15 11:34:19.123", "1999-05-15 11", DateTime.Unit.HOUR);
        testTruncate(SUCCESS, "1999-05-15 11:34:19.123", "1999-05-15", DateTime.Unit.DAY);
        testTruncate(SUCCESS, "1999-05-15 11:34:19.123", "1999-05", DateTime.Unit.MONTH);
        testTruncate(SUCCESS, "1999-05-15 11:34:19.123", "1999", DateTime.Unit.YEAR);
    }

    public void testNumDaysInMonth() {
        testNumDaysInMonth("2001-01-01", 31);
        testNumDaysInMonth("2001-02-01", 28);
        testNumDaysInMonth("2001-03-01", 31);
        testNumDaysInMonth("2001-04-01", 30);
        testNumDaysInMonth("2001-05-01", 31);
        testNumDaysInMonth("2001-06-01", 30);
        testNumDaysInMonth("2001-07-01", 31);
        testNumDaysInMonth("2001-08-01", 31);
        testNumDaysInMonth("2001-09-01", 30);
        testNumDaysInMonth("2001-10-01", 31);
        testNumDaysInMonth("2001-11-01", 30);
        testNumDaysInMonth("2001-12-01", 31);

        testNumDaysInMonth("2000-01-01", 31);
        testNumDaysInMonth("2000-02-01", 29);
        testNumDaysInMonth("2000-03-01", 31);
        testNumDaysInMonth("2000-04-01", 30);
        testNumDaysInMonth("2000-05-01", 31);
        testNumDaysInMonth("2000-06-01", 30);
        testNumDaysInMonth("2000-07-01", 31);
        testNumDaysInMonth("2000-08-01", 31);
        testNumDaysInMonth("2000-09-01", 30);
        testNumDaysInMonth("2000-10-01", 31);
        testNumDaysInMonth("2000-11-01", 30);
        testNumDaysInMonth("2000-12-01", 31);

        testNumDaysInMonth("1960-01-01", 31);
        testNumDaysInMonth("1960-02-01", 29);
        testNumDaysInMonth("1960-03-01", 31);
        testNumDaysInMonth("1960-04-01", 30);
        testNumDaysInMonth("1960-05-01", 31);
        testNumDaysInMonth("1960-06-01", 30);
        testNumDaysInMonth("1960-07-01", 31);
        testNumDaysInMonth("1960-08-01", 31);
        testNumDaysInMonth("1960-09-01", 30);
        testNumDaysInMonth("1960-10-01", 31);
        testNumDaysInMonth("1960-11-01", 30);
        testNumDaysInMonth("1960-12-01", 31);
    }

    public void testModifiedJulianDate() {
        testModifiedJD("1858-11-17", 0);
        testModifiedJD("2004-01-01", 53005);
        testModifiedJD("1900-12-15", 15368);
        testModifiedJD("1995-09-27", 49987);
        testModifiedJD("1858-11-17", 0);
        testModifiedJD("1858-11-18", 1);
        testModifiedJD("1858-11-16", -1);
        testModifiedJD("1858-11-01", -16);
        testModifiedJD("2005-05-24", 53514);
        testModifiedJD("2006-12-19", 54088);
        testModifiedJD("1834-12-15", -8738);
        testModifiedJD("1957-10-05", 36116);
        testModifiedJD("2000-01-01", 51544);
        testModifiedJD("1987-01-27", 46822);
        testModifiedJD("1987-06-19", 46965);
        testModifiedJD("1988-01-27", 47187);
        testModifiedJD("1988-06-19", 47331);
        testModifiedJD("1900-01-01", 15020);
        testModifiedJD("1600-01-01", -94553);
        testModifiedJD("1600-12-31", -94188);
        testModifiedJD("837-04-10", -373133); //Proleptic Gregorian (B=-4), not Julian (B=0)
    }

    public void testStartOfDay() {
        testStartOfDay("2005-08-13 13:15:58", "2005-08-13 00:00:00.000000000");
        testStartOfDay("2005-08-13 00:00:00", "2005-08-13 00:00:00.000000000");
        testStartOfDay("2005-08-13 00:00:00.0", "2005-08-13 00:00:00.000000000");
        testStartOfDay("2005-08-13 00:00:00.000", "2005-08-13 00:00:00.000000000");
        testStartOfDay("2005-08-13", "2005-08-13 00:00:00.000000000");
    }

    public void testEndOfDay() {
        testEndOfDay("2005-08-13 13:15:58", "2005-08-13 23:59:59.999000000");
        testEndOfDay("2005-08-13", "2005-08-13 23:59:59.999000000");
    }

    public void testStartOfMonth() {
        testStartOfMonth("2005-08-13 13:15:58", "2005-08-01 00:00:00.000000000");
        testStartOfMonth("2005-08-13", "2005-08-01 00:00:00.000000000");
    }

    public void testEndOfMonth() {
        testEndOfMonth("2005-08-13 13:15:58", "2005-08-31 23:59:59.999000000");
        testEndOfMonth("2005-01-13 13:15:58", "2005-01-31 23:59:59.999000000");
        testEndOfMonth("2005-02-13 13:15:58", "2005-02-28 23:59:59.999000000");
        testEndOfMonth("2005-02-13", "2005-02-28 23:59:59.999000000");
        testEndOfMonth("2005-02-13 13:15", "2005-02-28 23:59:59.999000000");
        testEndOfMonth("2005-02-13 13:15:12.23", "2005-02-28 23:59:59.999000000");
    }

    // PRIVATE

    public void testDateFromJD() {
        //http://isotropic.org/cgi-bin/date.pl?date=333-01-27
        //Считает Юлианский день

        testDateFromJD("2000-01-01", 2451545);
        testDateFromJD("1957-10-04", 2436116);
        testDateFromJD("1987-06-19", 2446966);
        testDateFromJD("1988-06-19", 2447332);
        testDateFromJD("1988-06-19", 2447332);
        testDateFromJD("2009-12-23", 2455189);
        testDateFromJD("1987-01-27", 2446823);

        testDateFromJD("1582-10-16", 2299162);
        testDateFromJD("1582-10-15", 2299161);
        testDateFromJD("1582-10-14", 2299160);
        testDateFromJD("1582-10-13", 2299159);

        testDateFromJD("333-01-27", 1842712);
    }

    public void testNow() {
        TimeZoneConstants constants = GWT.create(TimeZoneConstants.class);
        TimeZone tz = TimeZone.createTimeZone(constants.americaHalifax());
        DateTime now = DateTime.now(tz);
        //log(now);

        DateTime today = DateTime.today(tz);
        //log(today);

        DateTime future = new DateTime("2500-01-31");
        assertTrue(future.isInTheFuture(tz));

        DateTime past = new DateTime("1900-01-31");
        assertTrue(past.isInThePast(tz));
    }

    public void testEquals() {
        testEquals("1938-01-31", "1938-01-31", SUCCESS);
        testEquals("1938-01-31 18:13:15", "1938-01-31 18:13:15", SUCCESS);
        testEquals("1938-01-31 18:13:15.0", "1938-01-31 18:13:15.0", SUCCESS);
        testEquals("1938-01-31 18:13:15.123", "1938-01-31 18:13:15.123", SUCCESS);
        testEquals("18:13:15.1", "18:13:15.1", SUCCESS);
        testEquals("18:13:15", "18:13:15", SUCCESS);
        testEquals("18:13", "18:13", SUCCESS);

        testEquals("1938-01-31 00:00:00", "1938-01-31", FAIL);
        testEquals("1938-01-31 18:31:25", "1938-01-31", FAIL);

        testEquals(new DateTime(2156, 1, 3, 18, 31, 25, 0), new DateTime(2156, 1, 3, 18, 31, 25, 0), SUCCESS);
        testEquals(new DateTime(2156, 1, 3, 18, 31, 25, null), new DateTime(2156, 1, 3, 18, 31, 25, null), SUCCESS);
        testEquals(new DateTime(2156, 1, 3, 18, 31, null, null), new DateTime(2156, 1, 3, 18, 31, null, null), SUCCESS);
        testEquals(new DateTime(2156, 1, 3, null, null, null, null), new DateTime(2156, 1, 3, null, null, null, null), SUCCESS);
        testEquals(new DateTime(2156, 1, 3, 18, 31, 25, null), new DateTime(2156, 1, 3, 18, 31, 25, 0), FAIL);
        testEquals(new DateTime(null, 1, 3, 18, 31, 25, 0), new DateTime(2156, 1, 3, 18, 31, 25, 0), FAIL);
        testEquals(new DateTime(2156, 1, 3, null, null, null, null), new DateTime(2156, 1, 3, 18, 31, 25, 0), FAIL);
    }

    public void testChangeTimeZone() {
        TimeZoneConstants constants = GWT.create(TimeZoneConstants.class);
        TimeZone from = TimeZone.createTimeZone(constants.americaHalifax());
        TimeZone to = TimeZone.createTimeZone(constants.americaMontreal());
        testChangeTimeZone("2010-01-15 18:13:59.123", "2010-01-15 17:13:59.123", from, to);
        testChangeTimeZone("2010-01-15 18:13:59.1", "2010-01-15 17:13:59.1", from, to);
        testChangeTimeZone("2010-01-15 18:13:59", "2010-01-15 17:13:59", from, to);
        testChangeTimeZone("2010-01-15 18:13", "2010-01-15 17:13", from, to);
        testChangeTimeZone("2010-01-15 18", "2010-01-15 17", from, to);

        from = TimeZone.createTimeZone(constants.europeLondon());
        to = TimeZone.createTimeZone(constants.americaMontreal());
        testChangeTimeZone("2010-01-15 18:00", "2010-01-15 13:00", from, to);
        testChangeTimeZone("2010-01-15 23:00", "2010-01-15 18:00", from, to);
        testChangeTimeZone("2010-01-16 00:00", "2010-01-15 19:00", from, to);
        testChangeTimeZone("2010-01-16 01:00", "2010-01-15 20:00", from, to);
        testChangeTimeZone("2010-01-16 02:00", "2010-01-15 21:00", from, to);

        testChangeTimeZone("2010-08-15 18:00", "2010-08-15 13:00", from, to);
        testChangeTimeZone("2010-08-15 23:00", "2010-08-15 18:00", from, to);
        testChangeTimeZone("2010-08-16 00:00", "2010-08-15 19:00", from, to);
        testChangeTimeZone("2010-08-16 01:00", "2010-08-15 20:00", from, to);
        testChangeTimeZone("2010-08-16 02:00", "2010-08-15 21:00", from, to);

        from = TimeZone.createTimeZone(constants.europeLondon());
        to = TimeZone.createTimeZone(constants.asiaJakarta());
        testChangeTimeZone("2010-01-15 15:00", "2010-01-15 22:00", from, to);
        testChangeTimeZone("2010-01-15 16:00", "2010-01-15 23:00", from, to);
        testChangeTimeZone("2010-01-15 17:00", "2010-01-16 00:00", from, to);
        testChangeTimeZone("2010-01-15 18:00", "2010-01-16 01:00", from, to);
        testChangeTimeZone("2010-08-15 15:00", "2010-08-15 21:00", from, to);
        testChangeTimeZone("2010-08-15 16:00", "2010-08-15 22:00", from, to);
        testChangeTimeZone("2010-08-15 17:00", "2010-08-15 23:00", from, to);
        testChangeTimeZone("2010-08-15 18:00", "2010-08-16 00:00", from, to);
        testChangeTimeZone("2010-08-15 19:00", "2010-08-16 01:00", from, to);

        from = TimeZone.createTimeZone(constants.europeLondon());
        to = TimeZone.createTimeZone(constants.americaStJohns());
        //-3h30m, Newfoundland is offset by a non-integral number of hours
        testChangeTimeZone("2010-01-15 15:00", "2010-01-15 11:30", from, to);
        testChangeTimeZone("2010-08-15 15:00", "2010-08-15 11:30", from, to);
        testChangeTimeZone("2010-08-15 23:00", "2010-08-15 19:30", from, to);
        testChangeTimeZone("2010-08-16 00:00", "2010-08-15 20:30", from, to);
        testChangeTimeZone("2010-08-16 01:00", "2010-08-15 21:30", from, to);

        testChangeTimeZoneFails("2010-01-15", "2010-01-15", from, to);
        testChangeTimeZoneFails("2010-01-15", "2010-01-14", from, to);
        testChangeTimeZoneFails("2010-01", "2010-01", from, to);
        testChangeTimeZoneFails("2010", "2010", from, to);
    }

    public void testGetMilliseconds() {
        TimeZoneConstants constants = GWT.create(TimeZoneConstants.class);
        TimeZone timeZone = TimeZone.createTimeZone(0);//GMT

        testGetMillis("1970-01-01", timeZone, 0);
        testGetMillis("1970-01-02", timeZone, 86400000);
        testGetMillis("1969-12-31", timeZone, -86400000);
        testGetMillis("1970-01-01 00:00:00.0", timeZone, 0);
        testGetMillis("1970-01-01 00:00:00.001", timeZone, 1);
        testGetMillis("1970-01-01 00:00:00.1", timeZone, 100);
        testGetMillis("1970-01-01 00:00:01.0", timeZone, 1000);
        testGetMillis("1970-01-02 00:00:00.0", timeZone, 86400000);
        testGetMillis("1969-12-31 00:00:00.0", timeZone, -86400000);
        testGetMillis("1970-01-01 01:00", timeZone, 3600000);
        testGetMillis("1969-12-31 00:00:00.1", timeZone, -86399900);
        testGetMillis("1969-12-31 23:59:59.1", timeZone, -900);

        timeZone = TimeZone.createTimeZone(constants.americaMontreal());

        testGetMillis("1970-01-01 00:00:00.0", timeZone, 18000000);

        timeZone = TimeZone.createTimeZone(0);

        //nanos are truncated, not rounded
        testGetMillis("1970-01-01 00:00:00.0001", timeZone, 0);
        testGetMillis("1970-01-01 00:00:00.000000001", timeZone, 0);
        testGetMillis("1970-01-01 00:00:00.001999", timeZone, 1);

        //TO-DO
        //consistency of 2 related methods
        //dt from millis
        //millis from dt
        //compare
    }

    public void testInterConversion() {
        TimeZoneConstants constants = GWT.create(TimeZoneConstants.class);
        TimeZone timeZone = TimeZone.createTimeZone(0);

        testInterConversion("2010-01-15 19:53:22.123", timeZone);

        timeZone = TimeZone.createTimeZone(constants.asiaJakarta());

        testInterConversion("2010-01-15 19:53:22.123", timeZone);

        timeZone = TimeZone.createTimeZone(constants.americaMontreal());

        testInterConversion("2010-01-15 19:53:22.123", timeZone);
        testInterConversion("2010-09-15 19:53:22.123", timeZone);
        testInterConversion("2010-09-15 19:53:22.1", timeZone);
        testInterConversion("1802-09-15 19:53:22.1", timeZone);
        testInterConversion("9875-09-15 19:53:22.1", timeZone);
    }

    private void testStandardFormatCtorSuccess(String aDate) {
        try {
            DateTime dateTime = new DateTime(aDate);
        } catch (Throwable ex) {
            fail("Cannot construct using standard format: " + Util.quote(aDate));
        }
    }

    private void testStandardFormatCtorPlusParseSuccess(String aDate, Integer y, Integer m, Integer d, Integer h, Integer min, Integer sec, Integer frac) {
        try {
            DateTime dt = new DateTime(aDate);
            assertTrue("y", eq(dt.getYear(), y));
            assertTrue("m", eq(dt.getMonth(), m));
            assertTrue("d", eq(dt.getDay(), d));
            assertTrue("h", eq(dt.getHour(), h));
            assertTrue("min", eq(dt.getMinute(), min));
            assertTrue("sec", eq(dt.getSecond(), sec));
            if (frac != null) {
                frac = frac * 1000000;
            }
            assertTrue("frac", eq(dt.getNanoseconds(), frac));
        } catch (Throwable ex) {
            fail("Cannot construct/parse using standard format: " + Util.quote(aDate) + ex);
        }
    }

    private void testStandardFormatCtorPlusParseFail(String aDate) {
        try {
            DateTime dt = new DateTime(aDate);
            dt.ensureParsed();
            fail("Expected failure to parse: " + Util.quote(aDate));
        } catch (DateTime.ItemOutOfRange ex) {
            //good branch
        } catch (DateTimeParser.UnknownDateTimeFormat ex) {
            //good branch
        }
    }

    private boolean eq(Integer aThis, Integer aThat) {
        boolean result = false;
        if (aThis == null && aThat == null) {
            result = true;
        } else if (aThis != null && aThat != null) {
            result = aThis.equals(aThat);
        } else {
            log("At least one is null");
        }
        return result;
    }

    private void testStandardFormatCtorFail(String aDate) {
        try {
            DateTime dateTime = new DateTime(aDate);
            fail("Expected failure didn't happen.");
        } catch (Throwable ex) {
            //good branch
        }
    }

    private void testRange(boolean aSuccess, String aDateTime) {
        if (aSuccess) {
            try {
                DateTime dateTime = new DateTime(aDateTime);
                dateTime.ensureParsed();
            } catch (IllegalArgumentException ex) {
                fail("Item out of range:" + aDateTime);
            } catch (DateTimeParser.UnknownDateTimeFormat ex) {
                fail("Unknown format:" + aDateTime);
            }
        }
        if (!aSuccess) {
            try {
                DateTime dateTime = new DateTime(aDateTime);
                dateTime.ensureParsed();
                fail("Item is not out of range:" + aDateTime);
            } catch (DateTime.ItemOutOfRange ex) {
                //do nothing - expected
            } catch (DateTimeParser.UnknownDateTimeFormat ex) {
                //do nothing - expected
            }
        }
    }

    private void testLeapYear(boolean aSuccess, String aDate) {
        DateTime dt = new DateTime(aDate);
        if (aSuccess) {
            if (!dt.isLeapYear()) {
                fail("Expected leap year, but isn't");
            }
        } else {
            if (dt.isLeapYear()) {
                fail("Expected non-leap year, but actually is a leap year.");
            }
        }
    }

    private void testLeapYearFails(String aDate) {
        DateTime dt = new DateTime(aDate);
        try {
            dt.isLeapYear();
            fail();
        } catch (RuntimeException ex) {
            //OK - expected branch
        }
    }

    private void testToString(boolean aSuccess, String aDate, String aExpected) {
        DateTime dt = new DateTime(aDate);
        if (aSuccess) {
            if (!dt.toString().equals(aExpected)) {
                fail("Expected toString of " + aExpected + " but really is " + dt.toString());
            }
        } else {
            if (dt.toString().equals(aExpected)) {
                fail("Expected failure for toString being " + aExpected);
            }
        }
    }

    private void testToString(boolean aSuccess, Integer y, Integer m, Integer d, Integer h, Integer min, Integer sec, Integer frac, String aExpected) {
        DateTime dt = new DateTime(y, m, d, h, min, sec, frac);
        //System.out.println("DateTime = " + dt.toString());
        if (aSuccess) {
            String result = dt.toString();
            if (!result.equals(aExpected)) {
                fail("Expected toString of " + aExpected + " but really is " + dt.toString());
            }
        } else {
            if (dt.toString().equals(aExpected)) {
                fail("Expected failure for toString being " + aExpected);
            }
        }
    }

    private void testDayOfWeek(boolean aSuccess, String aDateTime, int aWeekday) {
        DateTime dt = new DateTime(aDateTime);
        if (aSuccess) {
            if (dt.getWeekDay() != aWeekday) {
                fail("Expected weekday '" + aWeekday + "', but was actually " + dt.getWeekDay());
            }
        } else {
            try {
                int dayOfWeek = dt.getWeekDay();
                fail("Expected failure");
            } catch (RuntimeException ex) {
                //OK - expected
            }
        }
    }

    private void testDayOfYear(boolean aSuccess, String aDateTime, int aExpectedDay) {
        DateTime dt = new DateTime(aDateTime);
        if (aSuccess) {
            if (dt.getDayOfYear() != aExpectedDay) {
                fail("Expected weekday '" + aExpectedDay + "', but was actually " + dt.getDayOfYear());
            }
        } else {
            try {
                dt.getDayOfYear();
                fail();
            } catch (RuntimeException ex) {
                //OK - expected branch
            }
        }
    }

    private void testSameDayAs(boolean aSuccess, String aA, String aB) {
        DateTime a = new DateTime(aA);
        DateTime b = new DateTime(aB);
        if (aSuccess) {
            if (!a.isSameDayAs(b)) {
                fail("The date " + aA + " should be the same day as this, but isn't: " + aB);
            }
        }
        if (!aSuccess) {
            if (a.isSameDayAs(b)) {
                fail("The date " + aA + " should NOT be the same day as this, but it is : " + aB);
            }
        }
    }

    private void testCompare(String aThis, String aThat, boolean aIsLess) {
        DateTime a = new DateTime(aThis);
        DateTime b = new DateTime(aThat);
        if (aIsLess) {
            if (a.compareTo(b) >= 0) {
                fail(aThis + " is meant to be less than this date, but isn't: " + aThat);
            }
        }
    }

    private void testBefore(String aThis, String aThat) {
        DateTime a = new DateTime(aThis);
        DateTime b = new DateTime(aThat);
        assertTrue(a.lt(b));
    }

    private void testAfter(String aThis, String aThat) {
        DateTime a = new DateTime(aThis);
        DateTime b = new DateTime(aThat);
        assertTrue(a.gt(b));
    }

    private void testTruncate(boolean aSuccess, String aInput, String aExpected, DateTime.Unit aPrecision) {
        DateTime a = new DateTime(aInput);
        DateTime aTruncated = a.truncate(aPrecision);
        DateTime expected = new DateTime(aExpected);
        if (aSuccess) {
            assertTrue(aTruncated.equals(expected));
        } else {
            assertFalse(aTruncated.equals(expected));
        }
    }

    private void testNumDaysInMonth(String aInput, int aExpected) {
        DateTime a = new DateTime(aInput);
        assertTrue(a.getNumDaysInMonth() == aExpected);
    }

    private void testModifiedJD(String aDateTime, Integer aExpected) {
        DateTime dt = new DateTime(aDateTime);
        if (!dt.getModifiedJulianDayNumber().equals(aExpected)) {
            fail("Expected: " + aExpected + " Actual: " + dt.getModifiedJulianDayNumber());
        }
    }

    private void testStartOfDay(String aDateTime, String aExpected) {
        DateTime dt = new DateTime(aDateTime);
        DateTime expected = new DateTime(aExpected);
        DateTime calc = dt.getStartOfDay();
        if (!calc.equals(expected)) {
            fail("Expected: " + aExpected + " Actual: " + dt.getStartOfDay());
        }
    }

    private void testEndOfDay(String aDateTime, String aExpected) {
        DateTime dt = new DateTime(aDateTime);
        DateTime expected = new DateTime(aExpected);
        if (!dt.getEndOfDay().equals(expected)) {
            fail("Expected: " + aExpected + " Actual: " + dt.getStartOfDay());
        }
    }

    private void testStartOfMonth(String aDateTime, String aExpected) {
        DateTime dt = new DateTime(aDateTime);
        DateTime expected = new DateTime(aExpected);
        DateTime calc = dt.getStartOfMonth();
        if (!calc.equals(expected)) {
            fail("Expected: " + aExpected + " Actual: " + dt.getStartOfDay());
        }
    }

    private void testEndOfMonth(String aDateTime, String aExpected) {
        DateTime dt = new DateTime(aDateTime);
        DateTime expected = new DateTime(aExpected);
        DateTime calc = dt.getEndOfMonth();
        if (!calc.equals(expected)) {
            fail("Expected: " + aExpected + " Actual: " + dt.getStartOfDay());
        }
    }

    private void testDateFromJD(String aExpected, int aJD) {
        DateTime expected = new DateTime(aExpected);
        DateTime temp = DateTime.fromJulianDayNumberAtNoon(aJD);
        if (!(temp.equals(expected))) {
            fail("Expected: " + aExpected + " Actual: " + DateTime.fromJulianDayNumberAtNoon(aJD).format("YYYY-MM-DD"));
        }
    }

    private void testEquals(String aThis, String aThat, boolean aSuccess) {
        DateTime thisDt = new DateTime(aThis);
        DateTime thatDt = new DateTime(aThat);
        if (aSuccess) {
            assertTrue(thisDt.equals(thatDt));
        } else {
            assertFalse(thisDt.equals(thatDt));
        }
    }

    private void testEquals(DateTime aThis, DateTime aThat, boolean aSuccess) {
        if (aSuccess) {
            assertTrue(aThis.equals(aThat));
        } else {
            assertFalse(aThis.equals(aThat));
        }
    }

    private void testChangeTimeZone(String aForDate, String aExpected, TimeZone aFrom, TimeZone aTo) {
        DateTime from = new DateTime(aForDate);
        DateTime actual = from.changeTimeZone(aFrom, aTo);
        DateTime expected = new DateTime(aExpected);
        if (!actual.equals(expected)) {
            fail("Expected:" + aExpected + " Actual:" + actual);
        }
    }

    private void testChangeTimeZoneFails(String aForDate, String aExpected, TimeZone aFrom, TimeZone aTo) {
        DateTime from = new DateTime(aForDate);
        try {
            DateTime actual = from.changeTimeZone(aFrom, aTo);
            fail();
        } catch (RuntimeException ex) {
            //ok
        }
    }

    private void testGetMillis(String aDate, TimeZone aTimeZone, int aExpected) {
        DateTime dt = new DateTime(aDate);
        long actual = dt.getMilliseconds(aTimeZone);
        if (actual != aExpected) {
            fail("Actual: " + actual + " Expected: " + aExpected);
        }
    }

    private void testInterConversion(String aDateTime, TimeZone aTimeZone) {
        DateTime dt = new DateTime(aDateTime);
        //loss of precision :
        long millis = dt.getMilliseconds(aTimeZone);
        DateTime again = DateTime.forInstant(millis, aTimeZone);
        dt.ensureParsed();
        again.ensureParsed();
        if (dt.compareTo(again) != 0) {
            fail(aDateTime + " not the same as " + again.format("YYYY-MM-DD hh:mm:ss.fffffffff"));
        }
    }

    @Override
    public String getModuleName() {
        return "ru.finam.date4gwt.Date4gwt";
    }


}