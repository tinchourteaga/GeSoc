package Validation.Exceptions;

public class LengthException extends Exception{
    public LengthException(){
        super("Password does not have enough characters");
    }
}
