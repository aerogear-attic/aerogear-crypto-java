/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.fixture;

public class TestVectors {

    public static final String PASSWORD = "My Bonnie lies over the ocean, my Bonnie lies over the sea";
    public static final String INVALID_PASSWORD = "invalid";

    //TEST VECTORS
    public static final String BOB_SECRET_KEY = "f3a9375ec8746cc6e78e4b02d7e748988e10c74525e5c0a41d63fe5d21f84224";

    public static final String CRYPTOBOX_IV = "69696ee955b62b73cd62bda875fc73d68219e0036b7a0b37";
    public static final String CRYPTOBOX_MESSAGE = "be075fc53c81f2d5cf141316ebeb0c7b5228c52a4c62cbd44b66849b64244ffc" +
            "e5ecbaaf33bd751a1ac728d45e6c61296cdc3c01233561f41db66cce314adb31" +
            "0e3be8250c46f06dceea3a7fa1348057e2f6556ad6b1318a024a838f21af1fde" +
            "048977eb48f59ffd4924ca1c60902e52f0a089bc76897040e082f93776384864" +
            "5e0705";
    public static final String CRYPTOBOX_CIPHERTEXT = "b05177ed87371685bcb4ba37ec51ec66712950fb18778e3c915be42a9" +
            "73f127f15f8b053c57c223ef7c2f7bea59bc5b63f32ac0d2daaecb75cb489489a46cbcdb749c09cdc0f6adfbf1cb625053f7" +
            "b20800f9a7c1ef6a4379df67a9873ec696e0ca484861a70de22254fe3790f02b39746fb1f6a47d95294fdfc3e55ff3a38a855" +
            "c1572e518ad0a036b7c75f7e894858562992";

    public static final String BOB_PRIVATE_KEY = "5dab087e624a8a4b79e17f8b83800ee66f3bb1292618b6fd1c2f8b27ff88e0eb";
    public static final String BOB_PUBLIC_KEY = "de9edb7d7b7dc1b4d35b61c2ece435373f8343c85b78674dadfc7e146f882b4f";

    public static final String ALICE_PRIVATE_KEY = "77076d0a7318a57d3c16c17251b26645df4c2f87ebc0992ab177fba51db92c2a";
    public static final String ALICE_PUBLIC_KEY = "8520f0098930a754748b7ddcb43ef75a0dbf3a0d26381af4eba4a98eaa9b4e6a";

    public static final String BOX_NONCE = "69696ee955b62b73cd62bda875fc73d68219e0036b7a0b37";
    public static final String BOX_MESSAGE = "be075fc53c81f2d5cf141316ebeb0c7b5228c52a4c62cbd44b66849b64244ffc" +
            "e5ecbaaf33bd751a1ac728d45e6c61296cdc3c01233561f41db66cce314adb31" +
            "0e3be8250c46f06dceea3a7fa1348057e2f6556ad6b1318a024a838f21af1fde" +
            "048977eb48f59ffd4924ca1c60902e52f0a089bc76897040e082f93776384864" +
            "5e0705";
    public static final String BOX_CIPHERTEXT = "fa11d3d36080df6f68d1201bf98d761886dc17003129b1a2ffa8fcb96292ed53f178b07dbd6b451a746f3ae1a47c194f0003b331ef9d0be1b81b49392870a26e4f8e3307e299d9ca23adc0f704559de9bd93e0e95d044829f2b0b2a2b830be0f82de4ea774341cc882c8a63b7914285323e19af6b862281597b14847b16dc840de8d8d2f7b526b5c4a0516d11c4bd5e4415b20";

}
