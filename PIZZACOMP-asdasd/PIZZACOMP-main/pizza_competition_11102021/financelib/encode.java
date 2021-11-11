package financelib;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class encode {

    public static String enc_pass (String pass) {
        MessageDigest digest;
        byte[] encodedhash = new byte[256];
        try {
            digest = MessageDigest.getInstance("SHA-256");
            encodedhash = digest.digest(
            pass.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Method enc_pass failed");
            e.printStackTrace();
        }

        return bytesToHex(encodedhash);
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
