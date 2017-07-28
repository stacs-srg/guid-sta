package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.ALGORITHM;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class SHA1Key extends KeyImpl {

    private static final int KEYLENGTH = 160;
    private static final int DEFAULT_TO_STRING_LENGTH = 40;

    public SHA1Key(String string) throws GUIDGenerationException {
        super(ALGORITHM.SHA1, string);
    }

    public SHA1Key(byte[] input) throws GUIDGenerationException {
        super(ALGORITHM.SHA1, input);
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
