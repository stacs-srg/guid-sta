/*
 * Created on 26-Oct-2004
 */
package uk.ac.standrews.cs.guid.impl;

import org.apache.commons.codec.digest.DigestUtils;
import uk.ac.standrews.cs.guid.ALGORITHM;
import uk.ac.standrews.cs.guid.BASE;
import uk.ac.standrews.cs.guid.IKey;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.keys.SHA1Key;
import uk.ac.standrews.cs.guid.impl.keys.SHA256Key;
import uk.ac.standrews.cs.guid.impl.keys.SHA384Key;
import uk.ac.standrews.cs.guid.impl.keys.SHA512Key;

import java.io.IOException;
import java.io.InputStream;
import java.security.SecureRandom;
import java.util.Random;

import static uk.ac.standrews.cs.guid.ALGORITHM.SHA1;

/**
 * Provides various ways to generate uk.ac.standrews.cs.impl.
 *
 * @author stuart, graham
 */
public class SHAKeyFactory {

    private static ALGORITHM algorithm = SHA1;

    /**
     * Set the SHA Algorithm to use.
     * The default is SHA-1 if this method is not used.
     *
     * @param shaAlgorithm
     */
    public static void setSHAAlgorithm(ALGORITHM shaAlgorithm) throws GUIDGenerationException {

        switch(shaAlgorithm) {
            case SHA1:
            case SHA256:
            case SHA384:
            case SHA512:
                SHAKeyFactory.algorithm = shaAlgorithm;
                break;
            default:
                throw new GUIDGenerationException("Algorithm not valid for the SHAKeyFactory");
        }
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

        return generateKey(string.getBytes(), BASE.HEX);
    }

    /**
     * Creates a new key using the String representation of a BigInteger
     *
     * @param string - the String representation of a serialised Key
     * @param base - the base used for the key
     * @return a new Key using the parameter s as a long value
     */
    public static IKey recreateKey(String string, BASE base) throws GUIDGenerationException {
        if (string == null || string.isEmpty()) {
            throw new GUIDGenerationException();
        }

        switch(algorithm) {
            case SHA1:
                return new SHA1Key(string, base);
            case SHA256:
                return new SHA256Key(string, base);
            case SHA384:
                return new SHA384Key(string, base);
            case SHA512:
                return new SHA512Key(string, base);
            default:
            throw new GUIDGenerationException("Unsupported sha algorithm: " + algorithm);
        }
    }

    /**
     * Creates a key with a value generated from the given byte array.
     *
     * @param bytes the array from which to generate the key's value
     * @return a key with a value generated from bytes
     */
    public static IKey generateKey(byte[] bytes, BASE base) throws GUIDGenerationException {
        if (bytes == null || bytes.length == 0) {
            throw new GUIDGenerationException();
        }

        return hash(bytes, base);
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

    private static IKey hash(byte[] source, BASE base) throws GUIDGenerationException {

        String hex = "";

        switch(algorithm) {
            case SHA1:
                byte[] hexB = DigestUtils.sha1(source);
                return new SHA1Key(hexB, base);
            case SHA256:
                hex = DigestUtils.sha256Hex(source);
                return new SHA256Key(hex, base);
            case SHA384:
                hex = DigestUtils.sha384Hex(source);
                return new SHA384Key(hex, base);
            case SHA512:
                hex = DigestUtils.sha512Hex(source);
                return new SHA512Key(hex, base);
            default:
                throw new GUIDGenerationException("Unsupported sha algorithm: " + algorithm);
        }

    }

    private static IKey hash(InputStream source) throws GUIDGenerationException, IOException {

        String hex = "";

        switch(algorithm) {
            case SHA1:
                hex = DigestUtils.sha1Hex(source);
                return new SHA1Key(hex);
            case SHA256:
                hex = DigestUtils.sha256Hex(source);
                return new SHA256Key(hex);
            case SHA384:
                hex = DigestUtils.sha384Hex(source);
                return new SHA384Key(hex);
            case SHA512:
                hex = DigestUtils.sha512Hex(source);
                return new SHA512Key(hex);
            default:
                throw new GUIDGenerationException("Unsupported sha algorithm: " + algorithm);
        }

    }

}
