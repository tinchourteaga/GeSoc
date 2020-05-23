package Validation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Validation.Exceptions.*;

public class CheckCommonPasswords implements IValidation{
    private static List<String> commonPassword;

    // Vuela cuando sea base de datos
    static {
        try {
            commonPassword = getCommonPasswords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void validate(String password) throws CommonPasswordException {

        boolean passwordFound = commonPassword.stream().anyMatch(commonPassword -> commonPassword.equals(password));

        if(passwordFound) throw new CommonPasswordException();
    }

    //Vamos a manejar el geteo de las passwords con un archivo
    private static List<String> getCommonPasswords() throws IOException {
        String line;
        List<String> commonPasswords = new ArrayList();

        File file = new File("10000_commonPasswords.txt");

        BufferedReader buffer = new BufferedReader(new FileReader(file));

        while ((line = buffer.readLine()) != null)
            commonPasswords.add(line);

        return commonPasswords;
    }
}
