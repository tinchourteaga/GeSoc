package Validation;

import Validation.Exceptions.*;
import Converter.StringConverter;
import java.util.List;

public class HasNumbers implements IValidation{
    @Override
    public void validate(String password) throws NumberException {
        List<Character> passwordCharacters = StringConverter.makeListOfString(password);

        boolean hasNumbers = passwordCharacters.stream().anyMatch(StringConverter::isNumber);

        if(!hasNumbers) throw new NumberException();
    }
}
