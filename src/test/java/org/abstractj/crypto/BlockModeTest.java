package org.abstractj.crypto;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class BlockModeTest {

    @Test
    public void testGcmToString() throws Exception {
        String expectedMode = "GCM/NoPadding";
        BlockMode mode = BlockMode.GCM;
        assertEquals("Should return formatted block mode", expectedMode, mode.toString());
    }

    @Test
    public void testCbcToString() throws Exception {
        String expectedMode = "CBC/PKCS7Padding";
        BlockMode mode = BlockMode.CBC;
        assertEquals("Should return formatted block mode", expectedMode, mode.toString());
    }
}
