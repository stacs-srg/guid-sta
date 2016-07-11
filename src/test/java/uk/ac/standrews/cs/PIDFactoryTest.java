package uk.ac.standrews.cs;

import org.testng.annotations.Test;
import uk.ac.standrews.cs.impl.ZeroID;

import static org.testng.Assert.assertNotEquals;
import static org.testng.AssertJUnit.assertNotNull;

/**
 * @author Simone I. Conte "sic2@st-andrews.ac.uk"
 */
public class PIDFactoryTest {

    @Test
    public void randomPIDTest() throws Exception {

        IPID pid = PIDFactory.generateRandomPID();
        assertNotNull(pid);
        assertNotEquals(pid, new ZeroID());
    }

}