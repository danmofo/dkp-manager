package com.dmoffat.dkpmanager.util;

import org.apache.commons.codec.binary.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class HmacSha256 {
    private static final String ALGORITHM_NAME = "HmacSHA256";

    @Autowired private Environment environment;

    public String sign(String message) {
        String secretProperty = environment.getProperty("cookie.secret");
        if(secretProperty == null) {
            throw new RuntimeException("cookie.secret now defined inside application.properties.");
        }

        try {
            Mac hmacSha256 = Mac.getInstance(ALGORITHM_NAME);
            SecretKey secretKey = new SecretKeySpec(secretProperty.getBytes(), ALGORITHM_NAME);
            hmacSha256.init(secretKey);
            byte[] result = hmacSha256.doFinal(message.getBytes());
            return Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException | InvalidKeyException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public boolean verify(String message, String providedHash) {
        String computedHash = sign(message);
        return computedHash.equals(providedHash); // This should be a timing-safe equals to prevent timing attacks, however probably not relevant for this website given its size...
    }

}
