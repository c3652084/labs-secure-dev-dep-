package com.example.hospitalapp.utils;

import android.util.Patterns;

public class ValidationUtils {

    public static boolean isValidNhsNumber(String nhsNumber) {
        return nhsNumber != null && nhsNumber.matches("\\d{10}");
    }

    public static boolean isValidFullName(String fullName) {
        return fullName != null && fullName.trim().length() >= 3;
    }

    public static boolean isValidDob(String dob) {
        return dob != null && dob.matches("^\\d{2}/\\d{2}/\\d{4}$");
    }

    public static boolean isValidContact(String contact) {
        if (contact == null || contact.trim().isEmpty()) return false;
        String trimmed = contact.trim();
        boolean emailValid = Patterns.EMAIL_ADDRESS.matcher(trimmed).matches();
        boolean phoneValid = trimmed.matches("^\\+?[0-9]{10,15}$");
        return emailValid || phoneValid;
    }
}
