package com.example.hospitalapp;

import com.example.hospitalapp.utils.ValidationUtils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ValidationUtilsTest {

    @Test
    public void nhsNumber_valid_returnsTrue() {
        assertTrue(ValidationUtils.isValidNhsNumber("1234567890"));
    }

    @Test
    public void nhsNumber_invalid_returnsFalse() {
        assertFalse(ValidationUtils.isValidNhsNumber("12345"));
    }

    @Test
    public void fullName_valid_returnsTrue() {
        assertTrue(ValidationUtils.isValidFullName("John Smith"));
    }

    @Test
    public void dob_valid_returnsTrue() {
        assertTrue(ValidationUtils.isValidDob("12/08/2001"));
    }

    @Test
    public void contact_invalid_returnsFalse() {
        assertFalse(ValidationUtils.isValidContact("abc"));
    }
}
