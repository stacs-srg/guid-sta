package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.*;

import java.math.BigInteger;
import java.util.Objects;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class InvalidID implements IGUID, IPID {

    private final BigInteger key_value;

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
    public String toString() {
        return "0";
    }

    @Override
    public String toString(BASE base) {
        return "0";
    }

    @Override
    public String toMultiHash(BASE base) {

        return algorithm().toString() + MULTI_HASH_DELIMITER + BASE.INVALID.getVal() + MULTI_HASH_DELIMITER + toString(base);
    }

    @Override
    public boolean isInvalid() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvalidID invalidID = (InvalidID) o;
        return Objects.equals(key_value, invalidID.key_value);
    }

    @Override
    public int hashCode() {

        return Objects.hash(key_value);
    }

    @Override
    public int compareTo(Object o) {
        try {
            if (this.equals(o)) return 0;

            IKey k = (IKey) o;
            return key_value.compareTo(k.bigIntegerRepresentation());
        } catch (ClassCastException e) {
            return -1;
        }

    }
}
