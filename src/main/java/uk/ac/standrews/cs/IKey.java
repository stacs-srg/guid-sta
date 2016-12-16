package uk.ac.standrews.cs;

import java.math.BigInteger;

/**
 * Interface defining uk.ac.standrews.cs.impl.
 *
 * @author sja7, al, stuart, graham
 */
public interface IKey extends Comparable {
    
    /**
     * @return a BigInteger representation of this key
     */
    BigInteger bigIntegerRepresentation();
    
    /**
     * @return a string representation of this key in base 16
     */
    String toString();

    /**
     *
     * @param base the base must be a power of 2
     @return a string representation of this key with the given base
     */
    String toString(int base);

    /**
     *
     * @return true if this key is not valid
     */
    boolean isInvalid();
}
