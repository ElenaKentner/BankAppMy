package com.example.bankapp.util;

import java.util.UUID;

public class UUIDValidator {
    public static boolean isValidUUID(String id) {
        try {
            UUID.fromString(id);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
