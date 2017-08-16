package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.ALGORITHM;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class MD5Key extends KeyImpl {

    private static final int KEYLENGTH = 128;
    private static final int DEFAULT_TO_STRING_LENGTH = 32;

    public MD5Key(String string) throws GUIDGenerationException {
        super(ALGORITHM.MD5, string);
    }

    public MD5Key(byte[] input) throws GUIDGenerationException {
        super(ALGORITHM.MD5, input);
    }

    @Override
    protected int getKeylength() {
        return KEYLENGTH;
    }

    @Override
    protected int getStringLength() {
        return DEFAULT_TO_STRING_LENGTH;
    }
}
