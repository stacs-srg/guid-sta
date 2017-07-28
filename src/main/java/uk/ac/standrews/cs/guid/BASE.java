package uk.ac.standrews.cs.guid;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public enum BASE {

    INVALID(0), HEX(16), BASE_64(64);

    private final int val;

    BASE(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public static BASE get(int val) {
        for(BASE v : values())
            if(v.getVal() == val) return v;
        throw new IllegalArgumentException();
    }
}
