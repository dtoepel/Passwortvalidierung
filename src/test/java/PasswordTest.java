import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PasswordTest {

    @Test
    public void test1() {
        String password = "geheim";
        assertFalse(PasswordValidation.isLongEnough(password));
        assertFalse(PasswordValidation.containsUpperAndLowerCase(password));
        assertFalse(PasswordValidation.containsSpecial(password));
        assertFalse(PasswordValidation.containsNumbers(password));
        assertFalse(PasswordValidation.isCommonPassword(password));
        assertFalse(PasswordValidation.isValid(password));
    }

    @Test
    public void test2() {
        String password = "äBcd3fGツ";
        assertTrue(PasswordValidation.isLongEnough(password));
        assertTrue(PasswordValidation.containsUpperAndLowerCase(password));
        assertTrue(PasswordValidation.containsSpecial(password));
        assertTrue(PasswordValidation.containsNumbers(password));
        assertFalse(PasswordValidation.isCommonPassword(password));
        assertTrue(PasswordValidation.isValid(password));
    }

    @Test
    public void test3() {
        String password = "Passwort-123";
        assertTrue(PasswordValidation.isLongEnough(password));
        assertTrue(PasswordValidation.containsUpperAndLowerCase(password));
        assertTrue(PasswordValidation.containsSpecial(password));
        assertTrue(PasswordValidation.containsNumbers(password));
        assertTrue(PasswordValidation.isCommonPassword(password));
        assertFalse(PasswordValidation.isValid(password));
    }
    @Test
    public void test4() {
        assertTrue(PasswordValidation.isLongEnough("ganzlangespasswort-ganzlangespasswort-ganzlangespasswort-ganzlangespasswort"));
        assertFalse(PasswordValidation.isLongEnough(""));
        assertTrue(PasswordValidation.containsUpperCase("Aaa"));
        assertFalse(PasswordValidation.containsUpperCase("Äää"));
        assertTrue(PasswordValidation.containsLowerCase("Aaa"));
        assertFalse(PasswordValidation.containsLowerCase("Äää"));
        assertTrue(PasswordValidation.containsSpecial("Äää"));
        assertFalse(PasswordValidation.containsSpecial("Aaa"));
        assertTrue(PasswordValidation.containsNumbers("123äöüß"));
        assertFalse(PasswordValidation.containsNumbers("Aäa"));
    }

}
