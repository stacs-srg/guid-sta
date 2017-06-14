package uk.ac.standrews.cs.impl.keys;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import uk.ac.standrews.cs.IGUID;
import uk.ac.standrews.cs.IKey;
import uk.ac.standrews.cs.IPID;
import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.RadixMethods;

import java.math.BigInteger;
import java.util.Base64;

/**
 * Implementation of key.
 * 
 * @author stuart, al, graham, sja7
 * @author sic2 - removed p2p dependencies
 */
public class KeyImpl implements IGUID, IPID {

    private static final int KEYLENGTH = 160;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    private static final int DEFAULT_TO_STRING_RADIX = 16; // The radix used in converting the key's value to a string.
    private static final int DEFAULT_TO_STRING_LENGTH = 40; // The length of the key's value in digits.

    public static BigInteger KEYSPACE_SIZE;
    private BigInteger key_value;
    private byte[] key_value_bytes;

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

    /**
     * Creates a new key using the given value modulo the key space size.
     * 
     * @param key_value the value of the key
     */
    public KeyImpl(BigInteger key_value) throws GUIDGenerationException {
        init(key_value);
    }

    /**
     * Creates a new key using a string representation of a BigInteger to base DEFAULT_TO_STRING_RADIX.
     *
     * @param string the string value of the key
     * @see #DEFAULT_TO_STRING_RADIX
     */
    public KeyImpl(String string) throws GUIDGenerationException {
        init(new BigInteger(string, DEFAULT_TO_STRING_RADIX));
    }

    public KeyImpl(String string, int base) throws GUIDGenerationException {
        if (base == 16) {
            init(new BigInteger(string, DEFAULT_TO_STRING_RADIX));
        } else if (base == 64) {
            init(new BigInteger(Base64.getDecoder().decode(string)));
        } else {
            throw new GUIDGenerationException("Base " + base + " not supported");
        }
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
        try {
            IKey k = (IKey) o;
            return key_value.compareTo(k.bigIntegerRepresentation());
        } catch (ClassCastException e) {
            return 0;
        }
    }

    /**
     * Compares this key with another.
     *
     * @param o the key to compare
     * @return true if the argument key's representation is equal to that of this node
     */
    public boolean equals(Object o) {
        try {
            IKey k = (IKey) o;
            return key_value.equals(k.bigIntegerRepresentation());
        } catch (ClassCastException e) {
            return false;
        }
    }

    public int hashCode(){
        return toString().hashCode();
    }

    protected int getKeylength() {
        return KEYLENGTH;
    }

    protected int getStringLength() {
        return DEFAULT_TO_STRING_LENGTH;
    }
}