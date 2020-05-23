package Validation;

import Validation.Exceptions.*;
import java.io.IOException;

public interface IValidation {

    void validate(String password) throws CommonPasswordException, IOException, LengthException, NumberException, SpecialCharacterException;
}
