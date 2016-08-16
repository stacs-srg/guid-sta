/*
 * Created on 26-Oct-2004
 */
package uk.ac.standrews.cs.impl;

import org.apache.commons.codec.digest.DigestUtils;
import uk.ac.standrews.cs.IKey;
import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.keys.*;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Random;

import static uk.ac.standrews.cs.impl.SHAKeyFactory.SHA_ALGORITHMS.SHA1;

/**
 * Provides various ways to generate uk.ac.standrews.cs.impl.
 *
 * @author stuart, graham
 */
public class SHAKeyFactory {

    public enum SHA_ALGORITHMS { SHA1, SHA256, SHA384, SHA512};
    private static SHA_ALGORITHMS SHA_ALGORITHM = SHA1;

    /**
     * Set the SHA Algorithm to use.
     * The default is SHA-1
     *
     * @param shaAlgorithm
     */
    public static void setSHAAlgorithm(SHA_ALGORITHMS shaAlgorithm) {
        SHAKeyFactory.SHA_ALGORITHM = shaAlgorithm;
    }

    /**
     * Creates a key with an arbitrary value.
     *
     * @return a key with an arbitrary value
     */
    public static IKey generateKey() throws GUIDGenerationException {
        return generateKey("null");
    }

    /**
     * Creates a key with a value generated from the given string.
     *
     * @param string the string from which to generate the key's value
     * @return a key with a value generated from s
     */
    public static IKey generateKey(String string) throws GUIDGenerationException {
        if (string == null || string.isEmpty()) {
            throw new GUIDGenerationException();
        }

        return generateKey(string.getBytes());
    }

    /**
     * Creates a new key using the String representation of a BigInteger
     *
     * @param string - the String representation of a serialised Key
     * @return a new Key using the parameter s as a long value
     */
    public static IKey recreateKey(String string) throws GUIDGenerationException {
        if (string == null || string.isEmpty()) {
            throw new GUIDGenerationException();
        }

        return new KeyImpl(string);
    }

    /**
     * Creates a key with a value generated from the given byte array.
     *
     * @param bytes the array from which to generate the key's value
     * @return a key with a value generated from bytes
     */
    public static IKey generateKey(byte[] bytes) throws GUIDGenerationException {
        if (bytes == null || bytes.length == 0) {
            throw new GUIDGenerationException();
        }

        return hash(bytes);
    }

    /**
     * Creates a key with a value generated from the given input stream.
     *
     * @param source
     * @return
     * @throws GUIDGenerationException
     */
    public static IKey generateKey(InputStream source) throws GUIDGenerationException {
        if (source == null) {
            throw new GUIDGenerationException();
        }

        try {
            return hash(source);
        } catch (IOException e) {
            throw new GUIDGenerationException("IOException while generating GUID");
        }
    }

    /**
     * Creates a key with a pseudo-random value.
     *
     * @return a key with a pseudo-random value
     */
    public static IKey generateRandomKey() throws GUIDGenerationException {
        Random rand = new SecureRandom();
        long seed = rand.nextLong();
        return generateKey(String.valueOf(seed));
    }

    private static IKey hash(byte[] source) throws GUIDGenerationException {

        String hex = "";
        if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA1) {
            hex = DigestUtils.sha1Hex(source);
            return new SHA1Key(hex);
        } else if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA256) {
            hex = DigestUtils.sha256Hex(source);
            return new SHA256Key(hex);
        } else if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA384) {
            hex = DigestUtils.sha384Hex(source);
            return new SHA384Key(hex);
        } else if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA512) {
            hex = DigestUtils.sha512Hex(source);
            return new SHA512Key(hex);
        } else {
            throw new GUIDGenerationException("Unsupported sha algorithm: " + SHA_ALGORITHM);
        }

    }

    private static IKey hash(InputStream source) throws GUIDGenerationException, IOException {

        String hex = "";
        if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA1) {
            hex = DigestUtils.sha1Hex(source);
            return new SHA1Key(hex);
        } else if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA256) {
            hex = DigestUtils.sha256Hex(source);
            return new SHA256Key(hex);
        } else if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA384) {
            hex = DigestUtils.sha384Hex(source);
            return new SHA384Key(hex);
        } else if (SHA_ALGORITHM == SHA_ALGORITHMS.SHA512) {
            hex = DigestUtils.sha512Hex(source);
            return new SHA512Key(hex);
        } else {
            throw new GUIDGenerationException("Unsupported sha algorithm: " + SHA_ALGORITHM);
        }

    }

}
