public class PasswordException extends Exception{
 //No la usamos, pero quedo para dar un nombre general
}

class LengthException extends Exception{
    public LengthException(){
        super("Password does not have enough characters");
    }
}

class SpecialCharacterException extends Exception{
    public SpecialCharacterException(){
        super("Password does not have any special character");
    }
}

class NumberException extends Exception{
    public NumberException(){
        super("Password does not have any number");
    }
}

class CommonPasswordException extends Exception{
    public CommonPasswordException(){
        super("Password is too common");
    }
}
