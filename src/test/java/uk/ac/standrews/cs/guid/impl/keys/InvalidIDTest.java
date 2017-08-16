package uk.ac.standrews.cs.guid.impl.keys;

import org.testng.annotations.Test;
import uk.ac.standrews.cs.guid.BASE;
import uk.ac.standrews.cs.guid.IKey;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class InvalidIDTest {

    @Test
    public void stringTest() {

        IKey key = new InvalidID();
        assertEquals(key.toString(), "0");
    }

    @Test
    public void stringBaseTest() {

        IKey key = new InvalidID();
        assertEquals(key.toString(BASE.HEX), "0");
    }

    @Test
    public void multihashTest() {

        IKey key = new InvalidID();
        assertEquals(key.toMultiHash(), "INVALID_0_0");
    }

    @Test
    public void isInvalidTest() {

        assertTrue(new InvalidID().isInvalid());
    }
}