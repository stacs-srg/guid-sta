package uk.ac.standrews.cs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.SHAKeyFactory;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryRecreateGUIDsTest {

    public static final String TEST_STRING_HASHED = "984816fd329622876e14907634264e6f332e9fb3";

    @BeforeMethod
    public void setUp() {
        // Make sure that the default SHA algorithm is set
        SHAKeyFactory.setSHAAlgorithm(SHAKeyFactory.SHA_ALGORITHMS.SHA1);
    }

    @Test
    public void recreateGUIDTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID("984816fd329622876e14907634264e6f332e9fb3", 16);
        assertEquals(guid.toString(), "984816fd329622876e14907634264e6f332e9fb3");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateGUIDWrongBaseTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID(TEST_STRING_HASHED, 10);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateEmptyGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("", 16);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateEmptyGUIDBase64Test() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("", 64);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null, 16);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDBase64Test() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null, 64);
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDBase10Test() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null, 10);
    }

    @Test
    public void recreateGUID_abc_NIST_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.recreateGUID("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", 64);
        assertEquals("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", guid.toString(64));

        guid = GUIDFactory.recreateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA256, "ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", 64);
        assertEquals("ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", guid.toString(64));

        guid = GUIDFactory.recreateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA512, "jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", 64);
        assertEquals("jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", guid.toString(64));
    }
}
