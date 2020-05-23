package Validation;

import Validation.Exceptions.*;

public class CheckPasswordLength implements IValidation{

    @Override
    public void validate(String password) throws LengthException {
        boolean enoughLength = password.length() >= 8;

        if(!enoughLength) throw new LengthException();
    }
}
