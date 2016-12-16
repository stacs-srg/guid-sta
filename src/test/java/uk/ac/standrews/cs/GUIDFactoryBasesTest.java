package uk.ac.standrews.cs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.ac.standrews.cs.impl.SHAKeyFactory;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryBasesTest {

    @BeforeMethod
    public void setUp() {
        // Make sure that the default SHA algorithm is set
        SHAKeyFactory.setSHAAlgorithm(SHAKeyFactory.SHA_ALGORITHMS.SHA1);
    }

    @Test
    public void generateGUID_abc_NIST_Base32_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID("abc");
        assertEquals("L6CJSDI70Q0MLEHU4LONGK62DIED1M4T", guid.toString(32).toUpperCase());
    }

    @Test
    public void generateGUID_abc_NIST_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID("abc");
        assertEquals("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", guid.toString(64));
    }

    @Test
    public void generateGUID_512_long_string_NIST_Base64Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA512,
                "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu");
        assertEquals("jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", guid.toString(64));
    }

    @Test
    public void generateGUID_512_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA512,
                "abc");
        assertEquals("3a81oZNherrMQXNJriBBMRLm+k6JqX6iCp7u5ktV05ohkpkqJ0/BqDa6PCOj/uu9RU1EI2Q86A4qmslPpUyknw==", guid.toString(64));
    }

    @Test
    public void generateGUID_256_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA256,
                "abc");
        assertEquals("ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", guid.toString(64));
    }

    @Test
    public void generateGUID_256_abc_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.recreateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA256, "ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", 16);
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", guid.toString());
    }

}
