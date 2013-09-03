package org.abstractj.password;

import org.abstractj.crypto.Random;
import org.junit.Test;

import static org.abstractj.fixture.TestVectors.INVALID_PASSWORD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.abstractj.fixture.TestVectors.PASSWORD;

public class PBKDF2Test {

    @Test
    public void testPasswordValidationWithRandomSaltProvided() throws Exception {
        PBKDF2 pbkdf2 = new PBKDF2();
        byte[] salt = new Random().randomBytes();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD, salt);
        assertTrue("Password should be valid", pbkdf2.validate(PASSWORD, rawPassword, salt));
    }

    @Test
    public void testPasswordValidationWithSaltGenerated() throws Exception {
        PBKDF2 pbkdf2 = new PBKDF2();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD);
        assertTrue("Password should be valid", pbkdf2.validate(PASSWORD, rawPassword, pbkdf2.getSalt()));
    }

    @Test
    public void testInvalidPasswordValidationWithRandomSaltProvided() throws Exception {
        PBKDF2 pbkdf2 = new PBKDF2();
        byte[] salt = new Random().randomBytes();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD, salt);
        assertFalse("Password should be valid", pbkdf2.validate(INVALID_PASSWORD, rawPassword, salt));
    }

    @Test
    public void testInvalidPasswordValidationWithSaltGenerated() throws Exception {
        PBKDF2 pbkdf2 = new PBKDF2();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD);
        assertFalse("Password should be valid", pbkdf2.validate(INVALID_PASSWORD, rawPassword, pbkdf2.getSalt()));
    }

    @Test(expected = RuntimeException.class)
    public void testThrowExceptionWithPoorSaltProvided() throws Exception {
        PBKDF2 pbkdf2 = new PBKDF2();
        byte[] salt = "42".getBytes();
        pbkdf2.encrypt(PASSWORD, salt);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowExceptionWithPoorIterationProvided() throws Exception {
        PBKDF2 pbkdf2 = new PBKDF2();
        byte[] salt = new Random().randomBytes();
        int iterations = 42;
        pbkdf2.encrypt(PASSWORD, salt, iterations);
    }
}
