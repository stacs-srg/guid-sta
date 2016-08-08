package uk.ac.standrews.cs.impl.keys;

import uk.ac.standrews.cs.exceptions.GUIDGenerationException;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class SHA512Key extends KeyImpl {

    private static final int KEYLENGTH = 512;
    private static final int DEFAULT_TO_STRING_LENGTH = 128;

    public SHA512Key(String string) throws GUIDGenerationException {
        super(string);
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
