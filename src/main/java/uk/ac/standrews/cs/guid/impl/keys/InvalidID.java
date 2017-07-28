package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.ALGORITHM;
import uk.ac.standrews.cs.guid.BASE;
import uk.ac.standrews.cs.guid.IGUID;
import uk.ac.standrews.cs.guid.IKey;

import java.math.BigInteger;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class InvalidID implements IGUID {

    public BigInteger key_value;

    public InvalidID() {
        key_value = new BigInteger("0");
    }

    @Override
    public ALGORITHM algorithm() {
        return ALGORITHM.INVALID;
    }

    @Override
    public BigInteger bigIntegerRepresentation() {
        return key_value;
    }

    @Override
    public byte[] bytes() {
        return new byte[0];
    }

    @Override
    public String toString(BASE base) {
        return null;
    }

    @Override
    public boolean isInvalid() {
        return true;
    }

    @Override
    public int compareTo(Object o) {
        try {
            IKey k = (IKey) o;
            return key_value.compareTo(k.bigIntegerRepresentation());
        } catch (ClassCastException e) {
            return 0;
        }

    }
}
