package uk.ac.standrews.cs.guid.impl.keys;

import uk.ac.standrews.cs.guid.ALGORITHM;
import uk.ac.standrews.cs.guid.BASE;
import uk.ac.standrews.cs.guid.exceptions.GUIDGenerationException;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class SHA384Key extends KeyImpl {

    private static final int KEYLENGTH = 384;
    private static final int DEFAULT_TO_STRING_LENGTH = 96;

    public SHA384Key(String string) throws GUIDGenerationException {
        super(ALGORITHM.SHA384, string);
    }

    public SHA384Key(String string, BASE base) throws GUIDGenerationException {
        super(ALGORITHM.SHA384, string, base);
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
