import java.util.ArrayList;
import java.util.List;

public class StringConverter {

    public static List<Character> makeListOfString(String str){
        List<Character> chars = new ArrayList<>();

        for (char ch : str.toCharArray()) {

            chars.add(ch);
        }

        return chars;
    }

    public static boolean isSpecial(Character ch){
        return ch >= 33 && ch <= 47 || ch >= 58 && ch <= 64 || ch >= 91 && ch <= 96 || ch >= 123 && ch <= 126;
    }

    public static boolean isNumber(Character ch){
        return ch >= 48 && ch <= 57;
    }
}
