package uk.ac.standrews.cs.impl;

import uk.ac.standrews.cs.IGUID;
import uk.ac.standrews.cs.IPID;

import java.math.BigInteger;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class ZeroID implements IGUID, IPID {

    public BigInteger key_value;

    public ZeroID() {
        key_value = new BigInteger("0");
    }

    @Override
    public BigInteger bigIntegerRepresentation() {
        return key_value;
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
