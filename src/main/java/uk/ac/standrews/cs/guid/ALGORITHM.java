package uk.ac.standrews.cs.guid;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public enum ALGORITHM {

    INVALID("INVALID"),
    NONE("NONE"),
    SHA1("SHA1"),
    SHA256("SHA256"),
    SHA384("SHA384"),
    SHA512("SHA512");

    private final String text;

    ALGORITHM(final String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public static ALGORITHM get(String value) {
        for(ALGORITHM v : values())
            if(v.toString().equalsIgnoreCase(value)) return v;
        throw new IllegalArgumentException();
    }
}
