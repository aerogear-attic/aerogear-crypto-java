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

package org.abstractj.fixture;

public class TestVectors {

    public static final String PASSWORD = "My Bonnie lies over the ocean, my Bonnie lies over the sea";
    public static final String INVALID_PASSWORD = "invalid";

    //TEST VECTORS
    public static final String BOB_SECRET_KEY = "3e0ee6ac7305c8a74426316bc0398ff794c17d2c3609411c";

    public static final String CRYPTOBOX_IV = "69696ee955b62b73cd62bda875fc73d68219e0036b7a0b37";
    public static final String CRYPTOBOX_MESSAGE = "be075fc53c81f2d5cf141316ebeb0c7b5228c52a4c62cbd44b66849b64244ffc" +
            "e5ecbaaf33bd751a1ac728d45e6c61296cdc3c01233561f41db66cce314adb31" +
            "0e3be8250c46f06dceea3a7fa1348057e2f6556ad6b1318a024a838f21af1fde" +
            "048977eb48f59ffd4924ca1c60902e52f0a089bc76897040e082f93776384864" +
            "5e0705";
    public static final String CRYPTOBOX_CIPHERTEXT = "b0da984501f9ab4898da59ea7783495e323c563471a4422154c92831a889f" +
            "cfefc61da727394a83e58184299bcfaca2327fee6b1f21b919c9d2cf1977c3bfbc62449d92325dcc9290a31b7677845436482ee4f" +
            "47315b95d3070457723168a7dfa62feed60487ec1b08e4979c9dd137ae53938882c8088753cb2cc62c63fb3a7dc75c6d7bcb88b2" +
            "0d03fd56fd47a946a916189b";

}
