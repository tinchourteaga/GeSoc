package User;

import java.io.IOException;
import Validation.*;
import Validation.Exceptions.*;

public class User {

    private String role;
    private String name;
    private String password;

    private User(String role, String name, String password) throws SpecialCharacterException, CommonPasswordException, NumberException, LengthException, IOException {
        this.role = role;
        this.name = name;
        PasswordValidator.validatePassword(password);
        this.password = password;
    }
}