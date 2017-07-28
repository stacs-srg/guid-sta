package uk.ac.standrews.cs.guid.impl.keys;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import uk.ac.standrews.cs.guid.*;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.RadixMethods;

import java.math.BigInteger;
import java.util.Base64;

/**
 * Implementation of key.
 *
 * @author stuart, al, graham, sja7 - original authors
 * @author sic2 - removed p2p dependencies, enabled multi-bases and multi-algorithms
 */
public class KeyImpl implements IGUID, IPID {

    private static final int KEYLENGTH = 160;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    private static final int DEFAULT_TO_STRING_RADIX = BASE.HEX.getVal(); // The radix used in converting the key's value to a string.
    private static final int DEFAULT_TO_STRING_LENGTH = 40; // The length of the key's value in digits.

    public static BigInteger KEYSPACE_SIZE;
    private BigInteger key_value;
    private byte[] key_value_bytes;

    private ALGORITHM algorithm;
    private BASE base;

    /**
     * Default constructor - initialises the keyspace
     */
    private KeyImpl() {}

    private void init(BigInteger key_value) throws GUIDGenerationException {
        KEYSPACE_SIZE = TWO.pow(getKeylength());
        if (key_value == null) {
            throw new GUIDGenerationException();
        }

        try {
            this.key_value = key_value.remainder(KEYSPACE_SIZE);

            // Allow for negative key value.
            if (this.key_value.compareTo(BigInteger.ZERO) < 0)
                this.key_value = this.key_value.add(KEYSPACE_SIZE);

        } catch (NumberFormatException e) {
            throw new GUIDGenerationException();
        }

        try {
            String hexString = this.key_value.toString(DEFAULT_TO_STRING_RADIX);
            if (hexString.length() % 2 == 1) hexString = "0"+hexString;
            key_value_bytes = Hex.decodeHex(hexString.toCharArray());
        } catch (DecoderException e) {
            e.printStackTrace();
            throw new GUIDGenerationException("Unable to decode key to hex array");
        }
    }

    public KeyImpl(BigInteger key_value) throws GUIDGenerationException {
        this.algorithm = ALGORITHM.NONE;
        this.base = BASE.HEX;

        init(key_value);
    }

    /**
     * Creates a new key using the given value modulo the key space size.
     *
     * BASE: 16
     *
     * @param key_value the value of the key
     */
    public KeyImpl(ALGORITHM algorithm, BigInteger key_value) throws GUIDGenerationException {
        this.algorithm = algorithm;
        this.base = BASE.HEX;

        init(key_value);
    }

    /**
     * Creates a new key using a string representation of a BigInteger to base DEFAULT_TO_STRING_RADIX.
     *
     * @param string the string value of the key
     * @see #DEFAULT_TO_STRING_RADIX
     */
    public KeyImpl(ALGORITHM algorithm, String string) throws GUIDGenerationException {
        this.algorithm = algorithm;
        this.base = BASE.HEX;

        init(new BigInteger(string, DEFAULT_TO_STRING_RADIX));
    }

    public KeyImpl(ALGORITHM algorithm, String string, BASE base) throws GUIDGenerationException {
        this.algorithm = algorithm;
        this.base = base;

        if (base == BASE.HEX) {
            init(new BigInteger(string, DEFAULT_TO_STRING_RADIX));
        } else if (base == BASE.BASE_64) {
            init(new BigInteger(Base64.getDecoder().decode(string)));
        } else {
            throw new GUIDGenerationException("The base " + base + " is not supported");
        }
    }

    public KeyImpl(ALGORITHM algorithm, byte[] input, BASE base) throws GUIDGenerationException {
        this.algorithm = algorithm;
        this.base = base;

        key_value_bytes = input;
    }

    public ALGORITHM algorithm() {
        return algorithm;
    }

    @Override
    public BASE base() {
        return base;
    }

    /**
     * Returns the representation of this key.
     *
     * @return the representation of this key
     */
    public BigInteger bigIntegerRepresentation() {
        return key_value;
    }

    /**
     * Returns a string representation of the key value.
     *
     * @return a string representation of the key value using the default radix and length
     */
    public String toString() {
        return toString(DEFAULT_TO_STRING_RADIX, getStringLength());
    }

    @Override
    public boolean isInvalid() {
        return false;
    }

    /**
     * Returns a string representation of the key value.
     *
     * @param radix the radix
     * @return a string representation of the key value using the given radix
     */
    @Override
    public String toString(int radix) {

        if (radix == 64) {
            return toStringBase64();
        } else {
            int bits_per_digit = RadixMethods.bitsNeededTORepresent(radix);
            int toStringLength = getKeylength() / bits_per_digit;

            return toString(radix, toStringLength);
        }
    }

    /**
     * Returns a string representation of the key value.
     *
     * @param radix the radix
     * @param stringLength the length to which the key representation should be padded
     * @return a string representation of the key value using the given radix
     *
     * NOTE: this function will work up to radix 32
     */
    private String toString(int radix, int stringLength) {
        return applyPadding(key_value.toString(radix), stringLength);
    }

    private String applyPadding(String string, int stringLength) {
        StringBuilder result = new StringBuilder(string);
        while (result.length() < stringLength) result.insert(0, '0');
        return result.toString();
    }

    private String toStringBase64() {
        return Base64.getEncoder().encodeToString(key_value_bytes);
    }

    /**
     * Compares this key with another.
     *
     * @param o the key to compare
     * @return -1, 0, or 1 if the argument key is greater, equal to, or less
     *         than this node, respectively
     */
    public int compareTo(Object o) {

        if (this == o) return 0;
        if (o == null || getClass() != o.getClass()) throw new ClassCastException();

        IKey k = (IKey) o;
        if (k.algorithm() != algorithm || k.base() != base) {
            throw new ClassCastException();
        } else {
            return key_value.compareTo(k.bigIntegerRepresentation());
        }
    }

    /**
     * Compares this key with another.
     *
     * @param o the key to compare
     * @return true if the argument key's representation is equal to that of this node
     */
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        IKey k = (IKey) o;

        if (k.algorithm() != algorithm || k.base() != base) {
            return false;
        } else {
            return key_value.equals(k.bigIntegerRepresentation());
        }
    }

    public int hashCode(){
        return toMultiHash().hashCode();
    }

    protected int getKeylength() {
        return KEYLENGTH;
    }

    protected int getStringLength() {
        return DEFAULT_TO_STRING_LENGTH;
    }
}