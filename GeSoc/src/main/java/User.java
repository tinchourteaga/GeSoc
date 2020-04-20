public class User {

    private String role;
    private String name;
    private String password;

    private User(String role, String name, String password) throws PasswordException {
        this.role = role;
        this.name = name;
        PasswordValidator.validate(password);
        this.password = password;
    }

    private static void makeOperation(/*Operacion operacion*/){

    }


}
