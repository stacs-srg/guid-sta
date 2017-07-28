package uk.ac.standrews.cs.guid;

import org.testng.annotations.Test;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryBasesTest {

    @Test
    public void generateGUID_abc_NIST_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(ALGORITHM.SHA1, "abc");
        assertEquals("qZk+NkcGgWq6PiVxeFDCbJzQ2J0=", guid.toString(BASE.BASE_64));
    }

    @Test
    public void generateGUID_512_long_string_NIST_Base64Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(ALGORITHM.SHA512, "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu");
        assertEquals("jpWbddrjE9qM9PcoFPwUP493ecbrn3+hcpmurbaIkBhQHSieSQD35DMbmd7EtUM6x9Mp7rbdJlReluVbh0vpCQ==", guid.toString(BASE.BASE_64));
    }

    @Test
    public void generateGUID_512_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(ALGORITHM.SHA512, "abc");
        assertEquals("3a81oZNherrMQXNJriBBMRLm+k6JqX6iCp7u5ktV05ohkpkqJ0/BqDa6PCOj/uu9RU1EI2Q86A4qmslPpUyknw==", guid.toString(BASE.BASE_64));
    }

    @Test
    public void generateGUID_256_Base64_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(ALGORITHM.SHA256, "abc");
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", guid.toString());
        assertEquals("ungWv48Bz+pBQUDeXa4iI7ADYaOWF3qctBD/YfIAFa0=", guid.toString(BASE.BASE_64));
    }

    @Test
    public void generateGUID_abc_CANON_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(ALGORITHM.SHA1, "abc");
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", guid.toString(BASE.HEX));
        assertEquals("A9993E36-4706-816A-BA3E-25717850C26C9CD0D89D", guid.toString(BASE.CANON));
    }

}
