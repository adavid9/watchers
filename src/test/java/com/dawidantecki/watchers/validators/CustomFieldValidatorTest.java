package com.dawidantecki.watchers.validators;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CustomFieldValidatorTest {

    @Test
    public void should_be_invalid_email() {
        String[] invalidEmails = new String[]{"abc@abc@.com", "abc.com", "abc@.com", "abc@email.@"};
        for (String invalidEmail: invalidEmails) {
            assertFalse(CustomFieldValidator.isEmailValid(invalidEmail));
        }
    }

    @Test
    public void should_be_valid_email() {
        String[] validEmails = new String[]{"abc@abc.com", "abc@email.de", "abc@watchers.com"};
        for (String validEmail: validEmails) {
            assertTrue(CustomFieldValidator.isEmailValid(validEmail));
        }
    }
}