package hirondelle.date4j;

import com.google.gwt.junit.client.GWTTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Run all JUnit tests.
 */
public final class TestAll extends GWTTestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite("ru.finam.date4gwt.Date4gwt");

        suite.addTestSuite(TestDateTime.class);
        suite.addTestSuite(TestDateTimeFormatter.class);
        suite.addTestSuite(TestDateTimeInterval.class);

        return suite;
    }

    @Override
    public String getModuleName() {
        return "ru.finam.date4gwt.Date4gwt";
    }
}
