package com.rtech.eazywalls.utils;

public class RegexValidatorsUtil {
    public static boolean isValidEmail(String input) {
        return input.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$");
    }

}
