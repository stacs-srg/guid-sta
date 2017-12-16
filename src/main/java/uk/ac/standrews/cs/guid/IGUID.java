package uk.ac.standrews.cs.guid;/*
 * Created on May 20, 2005 at 11:35:34 AM.
 */

/**
 * A Globally Unique Identifier
 *
 * @author al
 */
public interface IGUID extends IKey {

    // No additional functionality.

    default String toSTDFormat() {
            /*
     * Convert to the standard format for GUID
     * (Useful for SQL Server UniqueIdentifiers, etc.)
     * Example: C2FEEEAC-CFCD-11D1-8B05-00600806D9B6
     */

        String raw = toString().toUpperCase();

        return raw.substring(0, 8) +
                "-" +
                raw.substring(8, 12) +
                "-" +
                raw.substring(12, 16) +
                "-" +
                raw.substring(16, 20) +
                "-" +
                raw.substring(20);
    }


}
