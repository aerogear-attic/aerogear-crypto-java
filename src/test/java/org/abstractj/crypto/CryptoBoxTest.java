/**
 * Copyright 2013 Bruno Oliveira, and individual contributors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.abstractj.crypto;

import org.abstractj.keys.PrivateKey;
import org.junit.Test;

import java.util.Arrays;

import static org.abstractj.fixture.TestVectors.CRYPTOBOX_CIPHERTEXT;
import static org.abstractj.fixture.TestVectors.CRYPTOBOX_IV;
import static org.abstractj.fixture.TestVectors.CRYPTOBOX_MESSAGE;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.abstractj.fixture.TestVectors.BOB_SECRET_KEY;
import static org.abstractj.encoders.Encoder.HEX;

public class CryptoBoxTest {

    @Test
    public void testAcceptStrings() throws Exception {
        try {
            new CryptoBox(BOB_SECRET_KEY, HEX);
        } catch (Exception e) {
            fail("CryptoBox should accept strings");
        }
    }

    @Test
    public void testAcceptPrivateKey() throws Exception {
        try {
            new CryptoBox(new PrivateKey(BOB_SECRET_KEY));
        } catch (Exception e) {
            fail("CryptoBox should accept key pairs");
        }
    }

    @Test(expected = RuntimeException.class)
    public void testNullPrivateKey() throws Exception {
        String key = null;
        new CryptoBox(new PrivateKey(key));
        fail("Should raise an exception");
    }

    @Test(expected = RuntimeException.class)
    public void testInvalidPrivateKey() throws Exception {
        String key = "hello";
        new CryptoBox(new PrivateKey(key));
        fail("Should raise an exception");
    }

    @Test
    public void testEncryptRawBytes() throws Exception {
        CryptoBox cryptoBox = new CryptoBox(new PrivateKey(BOB_SECRET_KEY));
        byte[] message = HEX.decode(CRYPTOBOX_MESSAGE);
        byte[] ciphertext = HEX.decode(CRYPTOBOX_CIPHERTEXT);

        byte[] result = cryptoBox.encrypt(message);
        System.out.println(cryptoBox.decrypt(result));
        assertTrue("failed to generate ciphertext", Arrays.equals(result, ciphertext));
    }

    @Test
    public void testEncryptRawBytesWithIV() throws Exception {
        CryptoBox cryptoBox = new CryptoBox(new PrivateKey(BOB_SECRET_KEY));
        byte[] IV = HEX.decode(CRYPTOBOX_IV);
        byte[] message = HEX.decode(CRYPTOBOX_MESSAGE);
        byte[] ciphertext = HEX.decode(CRYPTOBOX_CIPHERTEXT);

        byte[] result = cryptoBox.encrypt(message);
        assertTrue("failed to generate ciphertext", Arrays.equals(result, ciphertext));
    }

//    @Test
//    public void testDecryptRawBytes() throws Exception {
//        Box box = new Box(new PublicKey(ALICE_PUBLIC_KEY), new PrivateKey(BOB_SECRET_KEY));
//        byte[] nonce = HEX.decode(BOX_NONCE);
//        byte[] expectedMessage = HEX.decode(BOX_MESSAGE);
//        byte[] ciphertext = box.encrypt(nonce, expectedMessage);
//
//        Box pandora = new Box(new PublicKey(BOB_PUBLIC_KEY), new PrivateKey(ALICE_PRIVATE_KEY));
//        byte[] message = pandora.decrypt(nonce, ciphertext);
//        assertTrue("failed to decrypt ciphertext", Arrays.equals(message, expectedMessage));
//    }
//
//    @Test
//    public void testEncryptHexBytes() throws Exception {
//        Box box = new Box(new PublicKey(ALICE_PUBLIC_KEY), new PrivateKey(BOB_SECRET_KEY));
//        byte[] ciphertext = HEX.decode(BOX_CIPHERTEXT);
//
//        byte[] result = box.encrypt(BOX_NONCE, BOX_MESSAGE, HEX);
//        assertTrue("failed to generate ciphertext", Arrays.equals(result, ciphertext));
//    }
//
//    @Test
//    public void testDecryptHexBytes() throws Exception {
//        Box box = new Box(new PublicKey(ALICE_PUBLIC_KEY), new PrivateKey(BOB_SECRET_KEY));
//        byte[] expectedMessage = HEX.decode(BOX_MESSAGE);
//        byte[] ciphertext = box.encrypt(BOX_NONCE, BOX_MESSAGE, HEX);
//
//        Box pandora = new Box(new PublicKey(BOB_PUBLIC_KEY), new PrivateKey(ALICE_PRIVATE_KEY));
//        byte[] message = pandora.decrypt(BOX_NONCE, HEX.encode(ciphertext), HEX);
//        assertTrue("failed to decrypt ciphertext", Arrays.equals(message, expectedMessage));
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void testDecryptCorruptedCipherText() throws Exception {
//        Box box = new Box(new PublicKey(ALICE_PUBLIC_KEY), new PrivateKey(BOB_SECRET_KEY));
//        byte[] nonce = HEX.decode(BOX_NONCE);
//        byte[] message = HEX.decode(BOX_MESSAGE);
//        byte[] ciphertext = box.encrypt(nonce, message);
//        ciphertext[23] = ' ';
//
//        Box pandora = new Box(new PublicKey(BOB_PUBLIC_KEY), new PrivateKey(ALICE_PRIVATE_KEY));
//        pandora.decrypt(nonce, ciphertext);
//        fail("Should raise an exception");
//    }
}
