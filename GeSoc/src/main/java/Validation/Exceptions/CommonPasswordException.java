package Validation.Exceptions;

public class CommonPasswordException extends Exception{
    public CommonPasswordException(){
        super("Password is too common");
    }
}
