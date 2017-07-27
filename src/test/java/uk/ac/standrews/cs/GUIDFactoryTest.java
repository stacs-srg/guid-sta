package uk.ac.standrews.cs;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import uk.ac.standrews.cs.guid.GUIDFactory;
import uk.ac.standrews.cs.guid.IGUID;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.SHAKeyFactory;
import uk.ac.standrews.cs.guid.impl.keys.InvalidID;
import uk.ac.standrews.cs.utils.StreamsUtils;

import java.io.InputStream;
import java.util.LinkedHashSet;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 *
 * NIST tests: http://csrc.nist.gov/groups/ST/toolkit/examples.html
 * Other tests have been reproduced using http://www.sha1-online.com/
 *
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class GUIDFactoryTest {

    public static final String TEST_STRING = "TEST";
    public static final String TEST_EMPTY_STRING = "";

    public static final String TEST_STRING_HASHED = "984816fd329622876e14907634264e6f332e9fb3";
    public static final String TEST_EMPTY_STRING_HASHED = "da39a3ee5e6b4b0d3255bfef95601890afd80709";

    @BeforeMethod
    public void setUp() {
        // Make sure that the default SHA algorithm is set
        SHAKeyFactory.setSHAAlgorithm(SHAKeyFactory.SHA_ALGORITHMS.SHA1);
    }

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
        assertNotEquals(guid, new InvalidID());
    }

    @Test
    public void manyRandomGUIDTest() throws Exception {
        LinkedHashSet<IGUID> guids = new LinkedHashSet<>();
        for(int i = 0; i < 100; i++) {
            guids.add(GUIDFactory.generateRandomGUID());
        }

        assertEquals(100, guids.size());
    }



    @Test
    public void generateGUID_abc_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID("abc");
        assertEquals("a9993e364706816aba3e25717850c26c9cd0d89d", guid.toString());
    }

    @Test
    public void generateGUID_long_string_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID("abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq");
        assertEquals("84983e441c3bd26ebaae4aa1f95129e5e54670f1", guid.toString());
    }

    @Test
    public void generateGUID_256_abc_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA256, "abc");
        assertEquals("ba7816bf8f01cfea414140de5dae2223b00361a396177a9cb410ff61f20015ad", guid.toString());
    }

    @Test
    public void generateGUID_256_long_string_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA256,
                "abcdbcdecdefdefgefghfghighijhijkijkljklmklmnlmnomnopnopq");
        assertEquals("248d6a61d20638b8e5c026930c3e6039a33ce45964ff2167f6ecedd419db06c1", guid.toString());
    }

    @Test
    public void generateGUID_384_abc_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA384, "abc");
        assertEquals("cb00753f45a35e8bb5a03d699ac65007272c32ab0eded1631a8b605a43ff5bed8086072ba1e7cc2358baeca134c825a7", guid.toString());
    }

    @Test
    public void generateGUID_384_long_string_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA384,
                "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu");
        assertEquals("09330c33f71147e83d192fc782cd1b4753111b173b3b05d22fa08086e3b0f712fcc7c71a557e2db966c3e9fa91746039", guid.toString());
    }

    @Test
    public void generateGUID_512_abc_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA512, "abc");
        assertEquals("ddaf35a193617abacc417349ae20413112e6fa4e89a97ea20a9eeee64b55d39a2192992a274fc1a836ba3c23a3feebbd454d4423643ce80e2a9ac94fa54ca49f", guid.toString());
    }

    @Test
    public void generateGUID_512_long_string_NIST_Test() throws Exception {
        IGUID guid = GUIDFactory.generateGUID(SHAKeyFactory.SHA_ALGORITHMS.SHA512,
                "abcdefghbcdefghicdefghijdefghijkefghijklfghijklmghijklmnhijklmnoijklmnopjklmnopqklmnopqrlmnopqrsmnopqrstnopqrstu");
        assertEquals("8e959b75dae313da8cf4f72814fc143f8f7779c6eb9f7fa17299aeadb6889018501d289e4900f7e4331b99dec4b5433ac7d329eeb6dd26545e96e55b874be909", guid.toString());
    }

    @Test
    public void shortStringTest() throws Exception {
        InputStream inputStreamFake = StreamsUtils.StringToInputStream(TEST_STRING);
        IGUID guid = GUIDFactory.generateGUID(inputStreamFake);

        assertTrue(TEST_STRING_HASHED.startsWith(guid.toShortString()));
    }
}
