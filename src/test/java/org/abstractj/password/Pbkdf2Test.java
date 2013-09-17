package org.abstractj.password;

import org.abstractj.crypto.Random;
import org.junit.Test;

import static org.abstractj.fixture.TestVectors.INVALID_PASSWORD;
import static org.abstractj.fixture.TestVectors.PASSWORD;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Pbkdf2Test {

    @Test
    public void testPasswordValidationWithRandomSaltProvided() throws Exception {
        Pbkdf2 pbkdf2 = new Pbkdf2();
        byte[] salt = new Random().randomBytes();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD, salt);
        assertTrue("Password should be valid", pbkdf2.validate(PASSWORD, rawPassword, salt));
    }

    @Test
    public void testPasswordValidationWithSaltGenerated() throws Exception {
        Pbkdf2 pbkdf2 = new Pbkdf2();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD);
        assertTrue("Password should be valid", pbkdf2.validate(PASSWORD, rawPassword, pbkdf2.getSalt()));
    }

    @Test
    public void testInvalidPasswordValidationWithRandomSaltProvided() throws Exception {
        Pbkdf2 pbkdf2 = new Pbkdf2();
        byte[] salt = new Random().randomBytes();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD, salt);
        assertFalse("Password should be valid", pbkdf2.validate(INVALID_PASSWORD, rawPassword, salt));
    }

    @Test
    public void testInvalidPasswordValidationWithSaltGenerated() throws Exception {
        Pbkdf2 pbkdf2 = new Pbkdf2();
        byte[] rawPassword = pbkdf2.encrypt(PASSWORD);
        assertFalse("Password should be valid", pbkdf2.validate(INVALID_PASSWORD, rawPassword, pbkdf2.getSalt()));
    }

    @Test(expected = RuntimeException.class)
    public void testThrowExceptionWithPoorSaltProvided() throws Exception {
        Pbkdf2 pbkdf2 = new Pbkdf2();
        byte[] salt = "42".getBytes();
        pbkdf2.encrypt(PASSWORD, salt);
    }

    @Test(expected = RuntimeException.class)
    public void testThrowExceptionWithPoorIterationProvided() throws Exception {
        Pbkdf2 pbkdf2 = new Pbkdf2();
        byte[] salt = new Random().randomBytes();
        int iterations = 42;
        pbkdf2.encrypt(PASSWORD, salt, iterations);
    }
}
