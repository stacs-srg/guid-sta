package uk.ac.standrews.cs.guid;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.SHAKeyFactory;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryRecreateGUIDsTest {

    public static final String TEST_STRING_HASHED = "984816fd329622876e14907634264e6f332e9fb3";

    @BeforeMethod
    public void setUp() throws GUIDGenerationException {
        // Make sure that the default SHA algorithm is set
        SHAKeyFactory.setSHAAlgorithm(ALGORITHM.SHA1);
    }

    @Test
    public void recreateGUIDTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID("984816fd329622876e14907634264e6f332e9fb3", BASE.HEX);
        assertEquals(guid.toString(), "984816fd329622876e14907634264e6f332e9fb3");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateGUIDWrongBaseTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID(TEST_STRING_HASHED, BASE.INVALID);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateEmptyGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("", BASE.HEX);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateEmptyGUIDBase64Test() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("", BASE.BASE_64);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null, BASE.HEX);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDBase64Test() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null, BASE.BASE_64);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDBase10Test() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null, BASE.INVALID);
    }

    @Test
    public void recreateGUID_abc_NIST_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.recreateGUID("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", BASE.BASE_64);
        assertEquals("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", guid.toString(64));

        guid = GUIDFactory.recreateGUID(ALGORITHM.SHA256, "ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", BASE.BASE_64);
        assertEquals("ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", guid.toString(64));

        guid = GUIDFactory.recreateGUID(ALGORITHM.SHA512, "jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", BASE.BASE_64);
        assertEquals("jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", guid.toString(64));
    }

    @Test
    public void recreateFromMultiHash() {

    }
}
