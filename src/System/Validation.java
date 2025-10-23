package System;

public abstract class Validation {
    // use all static methods, only for validating inputs
    // abstract class to prevent instantiation

    public static boolean login(String username, String password) {
        // hardcoded for simplicity
        return username.equals("admin") && password.equals("admin123");
    }

}
