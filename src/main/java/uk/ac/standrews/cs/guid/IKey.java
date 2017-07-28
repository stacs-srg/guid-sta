package uk.ac.standrews.cs.guid;

import java.math.BigInteger;

/**
 * Interface defining uk.ac.standrews.cs.impl.
 *
 * @author sja7, al, stuart, graham
 */
public interface IKey extends Comparable {

    /**
     *
     * @return the algorithm used for this key
     */
    ALGORITHM algorithm();

    /**
     *
     * @return the encoding base for this key
     */
    BASE base();

    /**
     * @return a BigInteger representation of this key
     */
    BigInteger bigIntegerRepresentation();

    /**
     * @return a string representation of this key in base 16
     *
     * TODO - use the base as in the local field
     */
    String toString();

    /**
     *
     * @param base the base must be a power of 2
     * @return a string representation of this key with the given base
     */
    String toString(int base);

    /**
     * Return a short representation of the key
     * @return shorter string representation of the key
     */
    default String toShortString() {
        return toString().substring(0, 5);
    }

    /**
     * The key in string format as a combination of its algorithm, the base and the key itself
     * @return multihash key
     */
    default String toMultiHash() {

        return algorithm().toString() + ":" + base().getVal() + ":" + toString();
    }

    /**
     *
     * @return true if this key is not valid
     */
    boolean isInvalid();
}
