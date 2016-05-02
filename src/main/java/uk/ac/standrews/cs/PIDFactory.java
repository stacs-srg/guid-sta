/*
 * Created on 19-Aug-2005
 */
package uk.ac.standrews.cs;

import uk.ac.standrews.cs.exceptions.GUIDGenerationException;
import uk.ac.standrews.cs.impl.KeyImpl;
import uk.ac.standrews.cs.impl.SHA1KeyFactory;

public class PIDFactory {
    
    public static IPID generateRandomPID() {

        try {
            return (KeyImpl) SHA1KeyFactory.generateRandomKey();
        } catch (GUIDGenerationException e) {
            e.printStackTrace();
            return null;
        }
    }
}
