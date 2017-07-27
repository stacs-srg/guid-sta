package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.IGUID;
import uk.ac.standrews.cs.guid.IKey;
import uk.ac.standrews.cs.guid.IPID;

import java.math.BigInteger;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class InvalidID implements IGUID, IPID {

    public BigInteger key_value;

    public InvalidID() {
        key_value = new BigInteger("0");
    }

    @Override
    public BigInteger bigIntegerRepresentation() {
        return key_value;
    }

    @Override
    public String toString(int base) {
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
