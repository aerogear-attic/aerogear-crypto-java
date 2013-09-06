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

public class CryptoBox {

    private CipherScheme cipherScheme;
    private byte[] privateKey;

    public CryptoBox(byte[] privateKey) {
        checkLength(privateKey, AES_SECRETKEY_BYTES);
        this.privateKey = privateKey;
        this.cipherScheme = new CipherScheme();
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

    public byte[] encrypt(byte[] input) {

        byte[] cipherText = null;

        try {
            Cipher cipher = cipherScheme.getNewCipher();
            SecretKeySpec secretKeySpec = new SecretKeySpec(privateKey, AES.toString());
            System.out.println(privateKey.length);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(cipherScheme.getIV()));
            cipherText = new byte[cipher.getOutputSize(input.length)];
            int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
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

    public byte[] decrypt(byte[] IV, byte[] cipherText) {

        byte[] plainText = null;

        try {
            Cipher cipher = cipherScheme.getNewCipher();
            SecretKeySpec secretKeySpec = new SecretKeySpec(privateKey, AES.toString());
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(IV));
            plainText = new byte[cipher.getOutputSize(cipherText.length)];
            int ptLength = cipher.update(cipherText, 0, cipherText.length, plainText, 0);
            cipher.doFinal(plainText, ptLength);
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (ShortBufferException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }

        return plainText;
    }

    public byte[] decrypt(byte[] cipherText) {
        return decrypt(cipherText, cipherScheme.getIV());
    }
}
