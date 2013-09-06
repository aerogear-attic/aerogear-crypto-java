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

import org.abstractj.encoders.Encoder;
import org.abstractj.encoders.Hex;
import org.abstractj.keys.PrivateKey;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.ShortBufferException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;

import static org.abstractj.CryptoParty.AES_SECRETKEY_BYTES;
import static org.abstractj.crypto.Algorithm.AES;
import static org.abstractj.crypto.Util.checkLength;
import static org.abstractj.encoders.Encoder.HEX;

public class CryptoBox {

    private SecretKeySpec secretKeySpec;
    private CipherScheme cipherScheme;

    public CryptoBox(byte[] privateKey) {
        checkLength(privateKey, AES_SECRETKEY_BYTES);
        this.cipherScheme = new CipherScheme();
        this.secretKeySpec = new SecretKeySpec(privateKey, AES.toString());

    }

    public CryptoBox(PrivateKey privateKey) {
        this(privateKey.toBytes());
    }

    public CryptoBox(String privateKey, Encoder encoder) {
        this(encoder.decode(privateKey));
    }

    public CryptoBox(byte[] privateKey, byte[] publicKey) {
        //TODO
    }

    public byte[] encrypt(byte[] IV, byte[] message) {

        byte[] cipherText = null;

        try {
            Cipher cipher = cipherScheme.getNewCipher();
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));
            cipherText = new byte[cipher.getOutputSize(message.length)];
            int ctLength = cipher.update(message, 0, message.length, cipherText, 0);
            cipher.doFinal(cipherText, ctLength);
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }


        return cipherText;
    }

    public byte[] encrypt(byte[] input) {
        return encrypt(cipherScheme.getIV(), input);
    }

    public byte[] encrypt(String IV, String message, Encoder encoder) {
        return encrypt(encoder.decode(IV), encoder.decode(message));
    }

    public byte[] decrypt(byte[] IV, byte[] cipherText) {

        byte[] plainText = null;

        try {
            Cipher cipher = cipherScheme.getNewCipher();
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));
            plainText = new byte[cipher.getOutputSize(cipherText.length)];
            int ptLength = cipher.update(cipherText, 0, cipherText.length, plainText, 0);
            cipher.doFinal(plainText, ptLength);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            throw new RuntimeException("Decryption failed. Ciphertext verification failed.", e);
        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return plainText;
    }

    public byte[] decrypt(byte[] cipherText) {
        return decrypt(cipherScheme.getIV(), cipherText);
    }

    public byte[] decrypt(String IV, String cipherText, Encoder encoder) {
        return decrypt(encoder.decode(IV), encoder.decode(cipherText));
    }
}
