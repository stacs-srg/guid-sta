package uk.ac.standrews.cs.impl.keys;

import uk.ac.standrews.cs.exceptions.GUIDGenerationException;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class SHA256Key extends KeyImpl {

    private static final int KEYLENGTH = 256;
    private static final int DEFAULT_TO_STRING_LENGTH = 64;

    public SHA256Key(String string) throws GUIDGenerationException {
        super(string);
    }

    public SHA256Key(String string, int base) throws GUIDGenerationException {
        super(string, base);
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
