/*
 * Created on 19-Aug-2005
 */
package uk.ac.standrews.cs.guid;

import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.guid.impl.SHAKeyFactory;
import uk.ac.standrews.cs.guid.impl.keys.InvalidID;
import uk.ac.standrews.cs.guid.impl.keys.KeyImpl;

public class PIDFactory {

    public static IPID generateRandomPID() {

        try {
            return (KeyImpl) SHAKeyFactory.generateRandomKey(ALGORITHM.SHA256);
        } catch (GUIDGenerationException e) {
            return new InvalidID();
        }
    }

    public static IPID generateRandomPID(ALGORITHM algorithm) {

        try {
            return (KeyImpl) SHAKeyFactory.generateRandomKey(algorithm);
        } catch (GUIDGenerationException e) {
            return new InvalidID();
        }
    }

    public static IPID recreateGUID(String multihash) throws GUIDGenerationException {
        return (KeyImpl) GUIDFactory.recreateGUID(multihash);
    }
}
