package Validation;

import Validation.Exceptions.*;
import Converter.StringConverter;
import java.util.List;

public class HasSpecialCharacter implements IValidation{
    @Override
    public void validate(String password) throws SpecialCharacterException {
        List<Character> passwordCharacters = StringConverter.makeListOfString(password);

        boolean hasSpecialCharacter = passwordCharacters.stream().anyMatch(StringConverter::isSpecial);

        if(!hasSpecialCharacter) throw new SpecialCharacterException();

    }
}
