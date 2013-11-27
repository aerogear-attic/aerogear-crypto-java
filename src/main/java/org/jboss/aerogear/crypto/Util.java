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

/**
 * Utility class for platform validation and cryptographic operations
 */
public class Util {

    private static final boolean IS_ANDROID;

    private static final String ANDROID_ACTIVITY_CLASS_NAME = "android.app.Activity";

    static {
        boolean check;
        try {
            Class.forName(ANDROID_ACTIVITY_CLASS_NAME);
            check = true;
        } catch (ClassNotFoundException ignore) {
            check = false;
        }
        IS_ANDROID = check;
    }

    /**
     * Validates the length of the provided data
     *
     * @param data
     * @param size
     * @return data provided if valid
     */
    public static byte[] checkLength(byte[] data, int size) {
        if (data == null || data.length < size)
            throw new RuntimeException("Invalid length: " + data.length);
        return data;
    }

    /**
     * Validate the minimum size supported by the parameter
     *
     * @param size
     * @param minimumSize
     * @return size provided if valid
     */
    public static int checkSize(int size, int minimumSize) {
        if (size < minimumSize)
            throw new RuntimeException("Invalid size: " + size);
        return size;
    }

    /**
     * Utility method to format algorithms name in Java like way
     *
     * @param algorithm
     * @param mode
     * @return string name with the formatted algorithm
     */
    public static String formatter(Algorithm algorithm, BlockCipher.Mode mode) {
        return String.format("%s/%s", algorithm, mode);
    }

    /**
     * Utility method to format algorithms name in Java like way
     *
     * @param mode
     * @param padding
     * @return string name with the formatted algorithm
     */
    public static String formatter(BlockCipher.Mode mode, Padding padding) {
        return String.format("%s/%s", mode, padding);
    }

    /**
     * Create a new buffer with the specified size
     *
     * @param length
     * @return new empty byte array
     */
    public static byte[] newBuffer(int length) {
        return new byte[length];
    }

    /**
     * Copy the provided data
     *
     * @param data data to copy
     * @return byte array of copied data
     */
    public static byte[] newByteArray(byte[] data) {
        byte[] buffer = new byte[data.length];
        System.arraycopy(data, 0, buffer, 0, data.length);
        return buffer;
    }

    /**
     * Verify if the platform is running on Android
     *
     * @return true if the platform is Android, false otherwise
     */
    public static boolean isAndroid() {
        return IS_ANDROID;
    }

}
