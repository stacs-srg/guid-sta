package uk.ac.standrews.cs;/*
 * Created on Dec 15, 2004 at 10:56:29 PM.
 */


import org.testng.annotations.Test;
import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.SHAKeyFactory;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNotSame;


/**
 * Test class for KeyFactory.
 *
 * @author graham
 * @author sic2 - added tests for recreateKey()
 */
public class KeyFactoryTest {

    /*
     * Class under test for Key generateKey()
     */
    @Test
    public void testGenerateKey() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.generateKey();
        IKey k2 = SHAKeyFactory.generateKey();
    	
        // Key generated by SHA1 from string "null".
        assertEquals("2be88ca4242c76e8253ac62474851065032d6833", k1.toString());

        // Subsequent calls should return equal but non-identical uk.ac.standrews.cs.impl.
        assertNotSame(k1, k2);
        assertEquals(0, k1.compareTo(k2));
    }

    /*
     * Class under test for Key generateKey(String)
     */
    @Test
    public void testGenerateKeyWithString() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.generateKey("null");
        IKey k2 = SHAKeyFactory.generateKey("quick brown fox");
        IKey k3 = SHAKeyFactory.generateKey("quick brown fox");
    	
        // Key generated by SHA1 from string "null".
        assertEquals("2be88ca4242c76e8253ac62474851065032d6833", k1.toString());

        // Key generated by SHA1 from string "quick brown fox".
        assertEquals("a9762606f9e33e452f06b4562e253efb6038b512", k2.toString());
        
        // Subsequent calls should return equal uk.ac.standrews.cs.impl.
        assertEquals(0, k2.compareTo(k3));
        assertNotEquals(0, k1.compareTo(k2));
    }

    @Test
    public void testRecreateKey() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.recreateKey("195e24370036a4062b0d325c03153a2b9fc9c92e", 16);
        assertEquals("195e24370036a4062b0d325c03153a2b9fc9c92e", k1.toString());
    }

    @Test
    public void testRecreateKeyOverflow() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.recreateKey("195e24370036a4062b0d325c03153a2b9fc9c92e1", 16);
        assertEquals("95e24370036a4062b0d325c03153a2b9fc9c92e1", k1.toString());
    }

    @Test
    public void testRecreateKeyUpperCase() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.recreateKey("195E24370036A4062B0D325C03153A2B9FC9C92E", 16);
        assertEquals("195e24370036a4062b0d325c03153a2b9fc9c92e", k1.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void testRecreateKeyNull() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.recreateKey(null, 16);
        assertEquals("195e24370036a4062b0d325c03153a2b9fc9c92e", k1.toString());
    }

    @Test (expectedExceptions = GUIDGenerationException.class)
    public void testRecreateKeyEmpty() throws GUIDGenerationException {
        IKey k1 = SHAKeyFactory.recreateKey("", 16);
        assertEquals("195e24370036a4062b0d325c03153a2b9fc9c92e", k1.toString());
    }
}
