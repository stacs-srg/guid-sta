package uk.ac.standrews.cs.impl;

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
     * @return a string representation of this key
     */
    String toString();

    /**
     *
     * @return true if this key is not valid
     */
    boolean isInvalid();
}
