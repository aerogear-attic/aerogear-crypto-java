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

import org.abstractj.CryptoParty;
import org.bouncycastle.crypto.engines.AESEngine;
import org.bouncycastle.crypto.modes.AEADBlockCipher;
import org.bouncycastle.crypto.modes.GCMBlockCipher;

import static org.abstractj.crypto.BlockCipher.Mode.GCM;

public class BlockCipher {

    static {
        CryptoParty.loadProvider();
    }

    private BlockCipher() {
    }

    public static AEADBlockCipher getInstance() {
        return getNewCipher(GCM);
    }


    public static AEADBlockCipher getNewCipher(Mode blockMode) {

        AESEngine aesEngine = new AESEngine();

        switch (blockMode) {

            case GCM:
                return new GCMBlockCipher(aesEngine);
            default:
                throw new RuntimeException("Block cipher not found");
        }
    }

    public static byte[] getIV() {
        return new Random().randomBytes();
    }

    public enum Mode {
        GCM("GCM", Padding.NONE);

        private final Padding padding;
        private String mode;

        private Mode(String mode, Padding padding) {
            this.mode = mode;
            this.padding = padding;
        }

        @Override
        public String toString() {
            return String.format("%s/%s", mode, padding);
        }
    }
}
