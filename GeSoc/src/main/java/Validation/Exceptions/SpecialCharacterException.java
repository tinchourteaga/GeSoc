package Validation.Exceptions;

public class SpecialCharacterException extends Exception{
    public SpecialCharacterException(){
        super("Password does not have any special character");
    }
}