import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public class PasswordValidator {
    //singleton class
    static public void validate(String password) throws PasswordException {
        if(checkCommonPasswords(password)){
            throw new PasswordException();
        }

        //Hacer las otras validaciones


    }

    private static boolean checkCommonPasswords(String password){
        //Verifica contra las 10000 pass del archivo
        return true;
    }

    private static boolean checkPasswordLength(String password){
        return password.length() >= 8;
    }

    private static boolean hasSpecialCharacters(String password){
        List<Character> passwordCharacters = StringConverter.makeListOfString(password);

        return passwordCharacters.stream().anyMatch(StringConverter::isSpecial);
    }

    private static boolean hasNumbers(String password){
        List<Character> passwordCharacters = StringConverter.makeListOfString(password);

        return passwordCharacters.stream().anyMatch(StringConverter::isNumber);
    }
}
