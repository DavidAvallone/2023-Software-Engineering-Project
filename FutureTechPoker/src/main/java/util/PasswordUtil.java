package util;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.security.SecureRandom;

public class PasswordUtil {

    private static final int COST = 6;
    public static String hash(String txtPassword){
        byte[] hashedbytes = BCrypt.with(new SecureRandom()).hash(COST, txtPassword.toCharArray());
        return new String(hashedbytes);
    }
    public static boolean compare(String unhashed, String hashed){
        BCrypt.Result result = BCrypt.verifyer().verify(unhashed.toCharArray(), hashed.toCharArray());
        return result.verified;
    }

}
