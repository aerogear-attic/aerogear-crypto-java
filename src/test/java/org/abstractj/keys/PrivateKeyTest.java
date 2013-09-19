package org.abstractj.keys;

import org.junit.Test;

import java.util.Arrays;

import static org.abstractj.encoders.Encoder.HEX;
import static org.abstractj.fixture.TestVectors.BOB_SECRET_KEY;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class PrivateKeyTest {

    @Test
    public void testGeneratePrivateKey() {
        try {
            new PrivateKey();
        } catch (Exception e) {
            fail("Should not raise any exception and generate the private key");
        }
    }

    @Test
    public void testAcceptsRawValidKey() throws Exception {
        try {
            byte[] rawKey = HEX.decode(BOB_SECRET_KEY);
            new PrivateKey(rawKey);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should return a valid key size");
        }
    }

    @Test
    public void testAcceptsHexValidKey() throws Exception {
        try {
            new PrivateKey(BOB_SECRET_KEY, HEX);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should return a valid key size");
        }
    }

    @Test
    public void testCreateHexValidKey() throws Exception {
        try {
            new PrivateKey(BOB_SECRET_KEY, HEX).toString();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should return a valid key size");
        }
    }

    @Test
    public void testCreateByteValidKey() throws Exception {
        try {
            new PrivateKey(BOB_SECRET_KEY, HEX).toBytes();
        } catch (Exception e) {
            e.printStackTrace();
            fail("Should return a valid key size");
        }
    }

    @Test(expected = RuntimeException.class)
    public void testRejectNullKey() throws Exception {
        byte[] key = null;
        new PrivateKey(key);
        fail("Should reject null keys");
    }

    @Test(expected = RuntimeException.class)
    public void testRejectShortKey() throws Exception {
        byte[] key = "short".getBytes();
        new PrivateKey(key);
        fail("Should reject short keys");
    }

    @Test
    public void testPrivateKeyToString() throws Exception {
        try {
            PrivateKey key = new PrivateKey(BOB_SECRET_KEY, HEX);
            assertEquals("Correct private key expected", BOB_SECRET_KEY, key.toString());
        } catch (Exception e) {
            fail("Should return a valid key size");
        }
    }

    @Test
    public void testPrivateKeyToBytes() throws Exception {
        try {
            PrivateKey key = new PrivateKey(BOB_SECRET_KEY, HEX);
            assertTrue("Correct private key expected", Arrays.equals(HEX.decode(BOB_SECRET_KEY),
                    key.toBytes()));
        } catch (Exception e) {
            fail("Should return a valid key size");
        }
    }
}
