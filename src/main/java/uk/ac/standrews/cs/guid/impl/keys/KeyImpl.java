package uk.ac.standrews.cs.guid.impl.keys;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import uk.ac.standrews.cs.guid.*;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Base64;

/**
 * Implementation of key.
 *
 * @author stuart, al, graham, sja7 - original authors
 * @author sic2 - removed p2p dependencies, enabled multi-bases and multi-algorithms
 */
public class KeyImpl implements IGUID, IPID {

    private static final int KEYLENGTH = 256;
    private static final BigInteger TWO = BigInteger.ONE.add(BigInteger.ONE);

    private static final int DEFAULT_TO_STRING_RADIX = BASE.HEX.getVal(); // The radix used in converting the key's value to a string.
    private static final int DEFAULT_TO_STRING_LENGTH = 64; // The length of the key's value in digits.

    private byte[] key_value_bytes;
    public static BigInteger KEYSPACE_SIZE;
    private BigInteger bigInteger;

    private ALGORITHM algorithm;

    /**
     * Default constructor - initialises the keyspace
     */
    private KeyImpl() {}

    private KeyImpl(byte[] key_value_bytes) {
        this.key_value_bytes = key_value_bytes;

        String stringValue = new String(Hex.encodeHex(key_value_bytes));
        bigInteger = new BigInteger(stringValue, DEFAULT_TO_STRING_RADIX);

        KEYSPACE_SIZE = TWO.pow(getKeylength());
        bigInteger = bigInteger.remainder(KEYSPACE_SIZE);

        // Allow for negative key value.
        if (bigInteger.compareTo(BigInteger.ZERO) < 0)
            bigInteger = bigInteger.add(KEYSPACE_SIZE);
    }

    // TODO - ability to change string lenght and keylength!!!!
    public KeyImpl(BigInteger bigInteger) throws GUIDGenerationException {
        this.algorithm = ALGORITHM.NONE;

        try {
            KEYSPACE_SIZE = TWO.pow(getKeylength());
            bigInteger = bigInteger.remainder(KEYSPACE_SIZE);

            // Allow for negative key value.
            if (bigInteger.compareTo(BigInteger.ZERO) < 0)
                bigInteger = bigInteger.add(KEYSPACE_SIZE);

            String hexString = bigInteger.toString(DEFAULT_TO_STRING_RADIX);
            if (hexString.length() % 2 == 1) hexString = "0" + hexString;
            key_value_bytes = Hex.decodeHex(hexString.toCharArray());
        } catch (DecoderException e) {
            throw new GUIDGenerationException();
        }
    }

    // TODO - ability to change string lenght and keylength!!!!
    public KeyImpl(String string) throws GUIDGenerationException {
        this(new BigInteger(string, DEFAULT_TO_STRING_RADIX));
    }

    public KeyImpl(ALGORITHM algorithm, byte[] input) throws GUIDGenerationException {
        this(input);

        this.algorithm = algorithm;
    }

    /**
     * Creates a new key using a string representation of a BigInteger to base DEFAULT_TO_STRING_RADIX.
     *
     * @param string the string value of the key
     * @see #DEFAULT_TO_STRING_RADIX
     */
    public KeyImpl(ALGORITHM algorithm, String string) throws GUIDGenerationException {
        this(algorithm, string.getBytes());
    }

    public ALGORITHM algorithm() {
        return algorithm;
    }

    /**
     * Returns the representation of this key.
     *
     * @return the representation of this key
     */
    public BigInteger bigIntegerRepresentation() {

        KEYSPACE_SIZE = TWO.pow(getKeylength());

        BigInteger bigInteger = new BigInteger(new String(Hex.encodeHex(key_value_bytes)), DEFAULT_TO_STRING_RADIX);
        bigInteger = bigInteger.remainder(KEYSPACE_SIZE);

        // Allow for negative key value.
        if (bigInteger.compareTo(BigInteger.ZERO) < 0)
            bigInteger = bigInteger.add(KEYSPACE_SIZE);


        return bigInteger;
    }

    @Override
    public byte[] bytes() {
        return key_value_bytes;
    }

    @Override
    public boolean isInvalid() {
        return false;
    }

    protected int getKeylength() {
        return KEYLENGTH;
    }

    protected int getStringLength() {
        return DEFAULT_TO_STRING_LENGTH;
    }

    /**
     * Returns a string representation of the key value.
     *
     * @return a string representation of the key value using the default radix and length
     */
    public String toString() {
        return toString(BASE.HEX);
    }

    /**
     * Returns a string representation of the key value.
     *
     * @param base the radix
     * @return a string representation of the key value using the given radix
     */
    @Override
    public String toString(BASE base) {

        String retval = "";

        switch(base) {
            case HEX:
                retval = applyPadding(Hex.encodeHexString(key_value_bytes), getStringLength());
                break;
            case BASE_64:
                retval = Base64.getEncoder().encodeToString(key_value_bytes);
                break;
            case CANON:
                retval = applyPadding(Hex.encodeHexString(key_value_bytes), getStringLength());
                retval = applyCANONFormat(retval);
                break;
        }

        return retval;
    }

    private String applyPadding(String string, int stringLength) {
        StringBuilder result = new StringBuilder(string);
        while (result.length() < stringLength) result.insert(0, '0');
        return result.toString();
    }

    private String applyCANONFormat(String string) {
        String raw = string.toUpperCase();
        StringBuffer sb = new StringBuffer();
        sb.append(raw.substring(0, 8));
        sb.append("-");
        sb.append(raw.substring(8, 12));
        sb.append("-");
        sb.append(raw.substring(12, 16));
        sb.append("-");
        sb.append(raw.substring(16, 20));
        sb.append("-");
        sb.append(raw.substring(20));

        return sb.toString();
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
        if (k.algorithm() != algorithm) {
            throw new ClassCastException();
        } else {
            return bigIntegerRepresentation().compareTo(k.bigIntegerRepresentation());
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KeyImpl key = (KeyImpl) o;

        if (!Arrays.equals(key_value_bytes, key.key_value_bytes)) return false;
        return algorithm == key.algorithm;
    }

    @Override
    public int hashCode() {
        int result = Arrays.hashCode(key_value_bytes);
        result = 31 * result + (algorithm != null ? algorithm.hashCode() : 0);
        return result;
    }

}