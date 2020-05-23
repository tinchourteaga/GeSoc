package Validation.Exceptions;

public class NumberException extends Exception{
    public NumberException(){
        super("Password does not have any number");
    }
}