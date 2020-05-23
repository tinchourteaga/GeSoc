package Validation;

import Validation.Exceptions.CommonPasswordException;
import Validation.Exceptions.LengthException;
import Validation.Exceptions.NumberException;
import Validation.Exceptions.SpecialCharacterException;

import java.io.*;
import java.util.*;


public class PasswordValidator {
    //singleton class
    private static List<IValidation> validationsList = new ArrayList<IValidation>(){{
        add(new CheckPasswordLength());
        add(new CheckCommonPasswords());
        add(new HasNumbers());
        add(new HasSpecialCharacter());}};

    public static void validatePassword(String password) {
        //No me gusta como esta esto
        validationsList.forEach(validation -> {
            try {
                validation.validate(password);
            } catch(CommonPasswordException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (LengthException e) {
                e.printStackTrace();
            } catch (NumberException e) {
                e.printStackTrace();
            } catch (SpecialCharacterException e) {
                e.printStackTrace();
            }
        });
    }
}
