package org.abstractj.crypto;

import org.junit.Test;

import static org.abstractj.crypto.BlockCipher.Mode;
import static org.junit.Assert.assertEquals;

public class BlockModeTest {

    @Test
    public void testGcmToString() throws Exception {
        String expectedMode = "GCM/NoPadding";
        Mode mode = Mode.GCM;
        assertEquals("Should return formatted block mode", expectedMode, mode.toString());
    }
}
