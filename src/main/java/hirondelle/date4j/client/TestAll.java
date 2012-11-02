package hirondelle.date4j.client;

import com.google.gwt.junit.client.GWTTestCase;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Run all JUnit tests.
 */
public final class TestAll extends GWTTestCase {

    public static Test suite() {
        TestSuite suite = new TestSuite("hirondelle.date4j");

        suite.addTestSuite(TestDateTime.class);
        suite.addTestSuite(TestDateTimeFormatter.class);
        suite.addTestSuite(TestDateTimeInterval.class);

        return suite;
    }

    @Override
    public String getModuleName() {
        return "hirondelle.date4j.Date4j";
    }
}
