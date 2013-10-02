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
package org.jboss.aerogear.crypto;

public class Util {

    private static final boolean IS_ANDROID;

    static {
        boolean check;
        try {
            Class.forName("android.app.Activity");
            check = true;
        } catch (ClassNotFoundException ignore) {
            check = false;
        }
        IS_ANDROID = check;
    }

    public static byte[] checkLength(byte[] data, int size) {
        if (data == null || data.length != size)
            throw new RuntimeException("Invalid length: " + data.length);
        return data;
    }

    public static int checkSize(int size, int minimumSize) {
        if (size < minimumSize)
            throw new RuntimeException("Invalid size: " + size);
        return size;
    }

    public static String formatter(Algorithm algorithm, BlockCipher.Mode mode) {
        return String.format("%s/%s", algorithm, mode);
    }

    public static String formatter(BlockCipher.Mode mode, Padding padding) {
        return String.format("%s/%s", mode, padding);
    }

    public static byte[] newBuffer(int length) {
        return new byte[length];
    }

    public static byte[] newByteArray(byte[] data) {
        byte[] buffer = new byte[data.length];
        System.arraycopy(data, 0, buffer, 0, data.length);
        return buffer;
    }

    public static boolean isAndroid() {
        return IS_ANDROID;
    }

}
