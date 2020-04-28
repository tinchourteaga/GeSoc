import javax.swing.*;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

public class PasswordValidator {
    //singleton class
    static public void validatePassword(String password) throws IOException, CommonPasswordException, LengthException, SpecialCharacterException, NumberException {
        if(checkCommonPasswords(password)) throw new CommonPasswordException();

        if(!checkPasswordLength(password)) throw new LengthException();

        if(!hasSpecialCharacters(password)) throw new SpecialCharacterException();

        if(!hasNumbers(password)) throw new NumberException();

        //Si pasa todos los if la password es valida
    }

    private static boolean checkCommonPasswords(String password) throws IOException {
        List<String> commonPasswords = getCommonPasswords();

        return commonPasswords.stream().anyMatch(commonPassword -> commonPassword.equals(password));
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

    //Vamos a manejar el geteo de las passwords con un archivo
    private static List<String> getCommonPasswords() throws IOException {
        String line;
        List<String> commonPassword = new ArrayList();

        File file = new File("10000_commonPasswords.txt");

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        while ((line = buffer.readLine()) != null)
            commonPassword.add(line);

        return commonPassword;
    }
}
