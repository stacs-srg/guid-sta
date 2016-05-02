package uk.ac.standrews.cs;

import org.testng.annotations.Test;
import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.utils.StreamsUtils;

import java.io.InputStream;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDTest {

    public static final String TEST_STRING = "TEST";
    // Hash generated using http://www.sha1-online.com/
    public static final String TEST_STRING_HASHED = "984816fd329622876e14907634264e6f332e9fb3";

    @Test
    public void testGetHashHexAndSize() throws Exception {
        InputStream inputStreamFake = StreamsUtils.StringToInputStream(TEST_STRING);
        IGUID guid = GUIDFactory.generateGUID(inputStreamFake);
        assertEquals(TEST_STRING_HASHED, guid.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void testNullStream() throws Exception {
        InputStream stream = null;
        GUIDFactory.generateGUID(stream);
    }

    @Test
    public void testGenerateGUID() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(TEST_STRING);
        assertEquals(TEST_STRING_HASHED, guid.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void testGenerateGUIDNullString() throws Exception {
        String string = null;
        GUIDFactory.generateGUID(string);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void testGenerateGUIDNullStream() throws Exception {
        InputStream stream = null;
        GUIDFactory.generateGUID(stream);
    }

}