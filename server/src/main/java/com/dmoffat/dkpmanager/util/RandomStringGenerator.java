package com.dmoffat.dkpmanager.util;

import java.security.SecureRandom;

public class RandomStringGenerator {
    private static SecureRandom random = new SecureRandom();

    public static String generate() {
        return generate(128);
    }

    public static String generate(int length) {
        if(length < 0) {
            throw new IllegalArgumentException("Length must be greater than 0.");
        }
        if(length % 2 != 0) {
            throw new IllegalArgumentException("Length must be even.");
        }

        byte[] randomBytes = new byte[length / 2];
        random.nextBytes(randomBytes);
        StringBuilder out = new StringBuilder();
        for(byte b : randomBytes) {
            out.append(String.format("%02x", b));
        }
        return out.toString();
    }
}
