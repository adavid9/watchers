package com.dawidantecki.watchers.validators;

/**
 * This class is used to validate fields with custom
 * logic instead of using annotations inside entities
 */
public class CustomFieldValidator {

    /**
     * Checks if the passed email is valid
     * @param email The email passed by user
     * @return true if the email is valid
     */
    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
