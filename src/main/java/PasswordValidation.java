import java.util.HashSet;
import java.util.Set;

public class PasswordValidation {
    private static Set<String> commonPwds = new HashSet<String>();
    private static String availableChars =
            "abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
            "0123456789" +
            "=!\"§$%&/()?<>,;.:-_#'+*~ " +
            "äöüßÄÖÜẞ€£";

    static{
        commonPwds.add("Passwort-123");
    }

    public static boolean isValid(String password) {
        return isLongEnough(password)
                && containsNumbers(password)
                && containsUpperAndLowerCase(password)
                && containsSpecial(password)
                && !isCommonPassword(password);
    }

    public static boolean isLongEnough(String password) {
        return password.length() >= 8;
    }

    public static boolean containsNumbers(String password) {
        for(int i = 0; i < password.length(); i++){
            char c =  password.charAt(i);
            if(c >= '0' && c <= '9') return true;
        }
        return false;
    }

    public static boolean containsUpperCase(String password) {
        for(int i = 0; i < password.length(); i++){
            char c =  password.charAt(i);
            if(c >= 'A' && c <= 'Z') return true;
        }
        return false;
    }

    public static boolean containsLowerCase(String password) {
        for(int i = 0; i < password.length(); i++){
            char c =  password.charAt(i);
            if(c >= 'a' && c <= 'z') return true;
        }
        return false;
    }

    public static boolean containsSpecial(String password) {
        for(int i = 0; i < password.length(); i++){
            char c =  password.charAt(i);
            boolean uc = c >= 'A' && c <= 'Z';
            boolean lc = c >= 'a' && c <= 'z';
            boolean num = c >= '0' && c <= '9';

            if((!uc) && (!lc) && (!num)) return true;
        }
        return false;
    }

    public static boolean containsUpperAndLowerCase(String password) {
        return containsUpperCase(password) && containsLowerCase(password);
    }

    public static boolean isCommonPassword(String password) {
        return commonPwds.contains(password);
    }

    public static String generatePwd(int length) {
        String s = "";
        for(int i = 0; i < length; i++){
            int j = (int)(Math.random() * availableChars.length());
            char c = availableChars.charAt(j);
            s += c;
        }
        if(isValid(s)) return s;
        return null;
        //return generatePwd(length);
    }

    public static void main(String[] args) {
        for(int i = 0; i < 5; i++){
            String s = generatePwd(12);
            if(s != null) System.out.println(s);
        }
    }
}
