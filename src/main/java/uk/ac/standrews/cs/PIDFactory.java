/*
 * Created on 19-Aug-2005
 */
package uk.ac.standrews.cs;

import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.SHAKeyFactory;
import uk.ac.standrews.cs.impl.keys.InvalidID;
import uk.ac.standrews.cs.impl.keys.KeyImpl;

public class PIDFactory {
    
    public static IPID generateRandomPID() {

        try {
            return (KeyImpl) SHAKeyFactory.generateRandomKey();
        } catch (GUIDGenerationException e) {
            return new InvalidID();
        }
    }
}
