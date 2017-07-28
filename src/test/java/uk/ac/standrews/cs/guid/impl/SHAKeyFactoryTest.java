package uk.ac.standrews.cs.guid.impl;

import org.junit.Test;
import uk.ac.standrews.cs.guid.BASE;
import uk.ac.standrews.cs.guid.IGUID;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.keys.KeyImpl;

import static org.testng.Assert.assertEquals;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class SHAKeyFactoryTest {


    @Test
    public void shaHex() throws GUIDGenerationException {

        IGUID guid = (KeyImpl) SHAKeyFactory.generateKey("abc".getBytes(), BASE.HEX);
        assertEquals(guid.toString(), "a9993e364706816aba3e25717850c26c9cd0d89d");

    }

    @Test
    public void shaBase_64() throws GUIDGenerationException {

        IGUID guid = (KeyImpl) SHAKeyFactory.generateKey("abc".getBytes(), BASE.BASE_64);
        assertEquals(guid.toString(64), "qZk+NkcGgWq6PiVxeFDCbJzQ2J0=");

    }
}