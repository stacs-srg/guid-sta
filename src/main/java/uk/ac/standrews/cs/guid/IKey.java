package uk.ac.standrews.cs.guid;

import java.math.BigInteger;

/**
 * Interface defining uk.ac.standrews.cs.impl.
 *
 * @author sja7, al, stuart, graham
 */
public interface IKey extends Comparable {

    public static final String MULTI_HASH_DELIMITER = "_";

    /**
     *
     * @return the algorithm used for this key
     */
    ALGORITHM algorithm();

    /**
     * @return a BigInteger representation of this key
     */
    BigInteger bigIntegerRepresentation();

    /**
     * The bytes that make the key
     * @return
     */
    byte[] bytes();

    /**
     * @return a string representation of this key in base 16
     */
    String toString();

    /**
     *
     * @param base the base must be a power of 2
     * @return a string representation of this key with the given base
     */
    String toString(BASE base);

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
    default String toMultiHash(BASE base) {

        return algorithm().toString() + MULTI_HASH_DELIMITER + base.getVal() + MULTI_HASH_DELIMITER + toString(base);
    }

    /**
     * @return true if this key is not valid
     */
    boolean isInvalid();
}
