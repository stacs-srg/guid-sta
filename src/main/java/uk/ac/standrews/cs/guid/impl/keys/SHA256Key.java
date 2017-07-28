package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.ALGORITHM;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class SHA256Key extends KeyImpl {

    private static final int KEYLENGTH = 256;
    private static final int DEFAULT_TO_STRING_LENGTH = 64;

    public SHA256Key(String string) throws GUIDGenerationException {
        super(ALGORITHM.SHA256, string);
    }

    public SHA256Key(byte[] input) throws GUIDGenerationException {
        super(ALGORITHM.SHA256, input);
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
