package util;

import util.PasswordUtil;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PasswordUtilTest {

    @Test
    public void testHashing(){
        String mypass = "123456";
        String hashed = PasswordUtil.hash(mypass);
        assertAll("Hashing Assertions",
                () -> assertNotEquals(mypass,hashed,"String Password must be different from Hashed Password"),
                () -> assertTrue( hashed.length() > 6, "Hashed password must have a length greater than 6")
        );
    }

    @Test public void testPasswordCompare(){
        String mypass = "654321";
        String hashed = PasswordUtil.hash(mypass);
        boolean equals = PasswordUtil.compare(mypass,hashed);
        assertTrue(equals,"Unhashed and Hashed must be equal when compared");
    }

    @Test public void testPasswordCompareDifferentPasswords(){
        String mypass = "654321";
        String incorrect = "65432";
        String hashed = PasswordUtil.hash(mypass);
        boolean equals = PasswordUtil.compare(incorrect,hashed);
        assertFalse(equals,"Hashed must be different from Incorrect Password when compared");
    }
}