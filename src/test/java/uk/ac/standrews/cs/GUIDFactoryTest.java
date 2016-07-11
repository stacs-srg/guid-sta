package uk.ac.standrews.cs;

import org.testng.Assert;
import org.testng.annotations.Test;
import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.ZeroID;
import uk.ac.standrews.cs.utils.StreamsUtils;

import java.io.InputStream;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryTest {

    public static final String TEST_STRING = "TEST";
    public static final String TEST_EMPTY_STRING = "";

    // Hash generated using http://www.sha1-online.com/
    public static final String TEST_STRING_HASHED = "984816fd329622876e14907634264e6f332e9fb3";
    public static final String TEST_EMPTY_STRING_HASHED = "da39a3ee5e6b4b0d3255bfef95601890afd80709";

    @Test
    public void generateGUIDFromStreamTest() throws Exception {
        InputStream inputStreamFake = StreamsUtils.StringToInputStream(TEST_STRING);
        IGUID guid = GUIDFactory.generateGUID(inputStreamFake);
        assertEquals(TEST_STRING_HASHED, guid.toString());
    }

    @Test
    public void generateGUIDFromEmptyStreamTest() throws Exception {
        InputStream inputStreamFake = StreamsUtils.StringToInputStream(TEST_EMPTY_STRING);
        IGUID guid = GUIDFactory.generateGUID(inputStreamFake);
        assertNotNull(guid);
        assertEquals(TEST_EMPTY_STRING_HASHED, guid.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void nullStreamTest() throws Exception {
        InputStream stream = null;
        GUIDFactory.generateGUID(stream);
    }

    @Test
    public void generateGUIDTest() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(TEST_STRING);
        assertEquals(TEST_STRING_HASHED, guid.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void generateGUIDNullStringTest() throws Exception {
        String string = null;
        GUIDFactory.generateGUID(string);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void generateGUIDNullStreamTest() throws Exception {
        InputStream stream = null;
        GUIDFactory.generateGUID(stream);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void generateGUIDEmptyStringTest() throws Exception {
        GUIDFactory.generateGUID(TEST_EMPTY_STRING);
    }

    @Test
    public void randomGUIDTest() throws Exception {
        IGUID guid = GUIDFactory.generateRandomGUID();
        assertNotNull(guid);
        assertNotEquals(guid, new ZeroID());
    }

    @Test
    public void recreateGUIDTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID(TEST_STRING_HASHED);
        Assert.assertEquals(guid.toString(), TEST_STRING_HASHED);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateEmptyGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null);
    }
}
