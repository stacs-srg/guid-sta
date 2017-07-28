package uk.ac.standrews.cs.guid;/*
 * Created on 19-Aug-2005
 */

import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.SHAKeyFactory;
import uk.ac.standrews.cs.guid.impl.keys.InvalidID;
import uk.ac.standrews.cs.guid.impl.keys.KeyImpl;

import java.io.InputStream;

public class GUIDFactory {
    
    public static IGUID generateRandomGUID() {
        try {
            return (KeyImpl) SHAKeyFactory.generateRandomKey();
        } catch (GUIDGenerationException e) {
            return new InvalidID();
        }
    }
    
    public static IGUID recreateGUID(String string, BASE base) throws GUIDGenerationException {
        return (KeyImpl) SHAKeyFactory.recreateKey(string, base);
    }

    public static IGUID recreateGUID(String string) throws GUIDGenerationException {
        return (KeyImpl) SHAKeyFactory.recreateKey(string, BASE.HEX);
    }

    public static IGUID generateGUID(String string) throws GUIDGenerationException {
        return (KeyImpl) SHAKeyFactory.generateKey(string);
    }

    public static IGUID generateGUID(InputStream inputStream) throws GUIDGenerationException {
        return (KeyImpl) SHAKeyFactory.generateKey(inputStream);
    }

    public static IGUID generateRandomGUID(ALGORITHM algorithm) throws GUIDGenerationException {
        SHAKeyFactory.setSHAAlgorithm(algorithm);

        try {
            return (KeyImpl) SHAKeyFactory.generateRandomKey();
        } catch (GUIDGenerationException e) {
            return new InvalidID();
        }
    }

    public static IGUID recreateGUID(ALGORITHM algorithm, String string, BASE base) throws GUIDGenerationException {
        SHAKeyFactory.setSHAAlgorithm(algorithm);
        return (KeyImpl) SHAKeyFactory.recreateKey(string, base);
    }

    public static IGUID generateGUID(ALGORITHM algorithm, String string) throws GUIDGenerationException {
        SHAKeyFactory.setSHAAlgorithm(algorithm);
        return (KeyImpl) SHAKeyFactory.generateKey(string);
    }

    public static IGUID generateGUID(ALGORITHM algorithm, InputStream inputStream) throws GUIDGenerationException {
        SHAKeyFactory.setSHAAlgorithm(algorithm);
        return (KeyImpl) SHAKeyFactory.generateKey(inputStream);
    }
    
}
