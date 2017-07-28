package uk.ac.standrews.cs.guid;

import org.testng.annotations.Test;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryRecreateGUIDsTest {

    @Test
    public void recreateGUIDTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID("SHA1:16:984816fd329622876e14907634264e6f332e9fb3");
        assertEquals(guid.toString(), "984816fd329622876e14907634264e6f332e9fb3");
    }

    @Test
    public void recreateGUIDFromMultihashTest() throws GUIDGenerationException {
        IGUID guid = GUIDFactory.recreateGUID("SHA1:16:984816fd329622876e14907634264e6f332e9fb3");
        assertEquals( "984816fd329622876e14907634264e6f332e9fb3", guid.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateGUIDWrongMultihashTest_0() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("SHA1:16");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateGUIDWrongMultihashTest_1() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("984816fd329622876e14907634264e6f332e9fb3");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateGUIDWrongMultihashTest_2() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("SHA1:10:984816fd329622876e14907634264e6f332e9fb3");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateGUIDWrongMultihashTest_3() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("SHA1:984816fd329622876e14907634264e6f332e9fb3");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateEmptyGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID("");
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void recreateNullGUIDTest() throws GUIDGenerationException {
        GUIDFactory.recreateGUID(null);
    }

    @Test
    public void recreateGUID_SHA1_256_abc_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.recreateGUID("SHA256:16:ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad");
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", guid.toString());
    }

    @Test
    public void recreateGUID_SHA1_abc_NIST_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.recreateGUID("SHA1:64:qZk+NkcGgWq6PiVxeFDCbJzQ2J0=");
        assertEquals("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", guid.toString(BASE.BASE_64));
    }

    @Test
    public void recreateGUID_SHA256_abc_NIST_Base64_Test() throws Exception {

        IGUID guid = GUIDFactory.recreateGUID("SHA256:64:ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=");
        assertEquals("ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", guid.toString(BASE.BASE_64));
    }
    @Test
    public void recreateGUID_SHA512_abc_NIST_Base64_Test() throws Exception {

        IGUID guid = GUIDFactory.recreateGUID("SHA512:64:jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==");
        assertEquals("jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", guid.toString(BASE.BASE_64));
    }

    @Test
    public void recreateGUID_SHA1_abc_CANON_Test() throws GUIDGenerationException {

        IGUID guid = GUIDFactory.recreateGUID("SHA1:1:A9993E36-4706-816A-BA3E-25717850C26C9CD0D89D");
        assertEquals(guid.toString(), "a9993e364706816aba3e25717850c26c9cd0d89d");
    }
}
