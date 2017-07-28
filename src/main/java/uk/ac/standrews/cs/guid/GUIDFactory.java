package uk.ac.standrews.cs.guid;/*
 * Created on 19-Aug-2005
 */

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.SHAKeyFactory;
import uk.ac.standrews.cs.guid.impl.keys.InvalidID;
import uk.ac.standrews.cs.guid.impl.keys.KeyImpl;

import java.io.InputStream;
import java.util.Base64;

import static uk.ac.standrews.cs.guid.IKey.MULTI_HASH_DELIMITER;

public class GUIDFactory {

    public static IGUID generateRandomGUID(ALGORITHM algorithm) {

        try {
            return (KeyImpl) SHAKeyFactory.generateRandomKey(algorithm);
        } catch (GUIDGenerationException e) {
            return new InvalidID();
        }
    }

    public static IGUID generateGUID(ALGORITHM algorithm, String string) throws GUIDGenerationException {
        return (KeyImpl) SHAKeyFactory.generateKey(algorithm, string);
    }

    public static IGUID generateGUID(ALGORITHM algorithm, InputStream inputStream) throws GUIDGenerationException {
        return (KeyImpl) SHAKeyFactory.generateKey(algorithm, inputStream);
    }

    /**
     * Recreate a GUID from its multihash string format.
     *
     * @param multihash ALGORITHM:BASE:KEY (e.g. SHA1-16-a9993e364706816aba3e25717850c26c9cd0d89d)
     * @return GUID object
     * @throws GUIDGenerationException if the GUID could not be recreated
     */
    public static IGUID recreateGUID(String multihash) throws GUIDGenerationException {

        if (multihash == null || multihash.isEmpty()) throw new GUIDGenerationException();

        String[] multihashComponents = multihash.split(MULTI_HASH_DELIMITER);
        if (multihashComponents.length != 3) throw new GUIDGenerationException();

        try {
            ALGORITHM algorithm = ALGORITHM.get(multihashComponents[0]);
            BASE base = BASE.get(Integer.parseInt(multihashComponents[1]));

            switch (base) {
                case HEX:
                {
                    byte[] input = Hex.decodeHex(multihashComponents[2].toCharArray());
                    return (KeyImpl) SHAKeyFactory.recreateKey(algorithm, input);
                }
                case CANON:
                {
                    String cleanedKey = multihashComponents[2].replace("-", "");
                    byte[] input = Hex.decodeHex(cleanedKey.toCharArray());
                    return (KeyImpl) SHAKeyFactory.recreateKey(algorithm, input);
                }
                case BASE_64:
                {
                    byte[] input = Base64.getDecoder().decode(multihashComponents[2]);
                    return (KeyImpl) SHAKeyFactory.recreateKey(algorithm, input);
                }
                case INVALID:
                default:
                    return new InvalidID();
            }
        } catch (DecoderException | IllegalArgumentException e) {
            throw new GUIDGenerationException();
        }

    }

}
