package uk.ac.standrews.cs;/*
 * Created on 19-Aug-2005
 */

import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.InvalidID;
import uk.ac.standrews.cs.impl.KeyImpl;
import uk.ac.standrews.cs.impl.SHA1KeyFactory;

import java.io.InputStream;

public class GUIDFactory {
    
    public static IGUID generateRandomGUID() {
        try {
            return (KeyImpl) SHA1KeyFactory.generateRandomKey();
        } catch (GUIDGenerationException e) {
            e.printStackTrace();
            return new InvalidID();
        }
    }
    
    public static IGUID recreateGUID(String string) throws GUIDGenerationException {
        return (KeyImpl)SHA1KeyFactory.recreateKey(string);
    }

    public static IGUID generateGUID(String string) throws GUIDGenerationException {
        return (KeyImpl) SHA1KeyFactory.generateKey(string);
    }

    public static IGUID generateGUID(InputStream inputStream) throws GUIDGenerationException {
        return (KeyImpl) SHA1KeyFactory.generateKey(inputStream);
    }
    
}
