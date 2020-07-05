package Dominio.Convertidor;

import java.util.ArrayList;
import java.util.List;

public class ConvertidorString {

    public static List<Character> hacerListaDeStrings(String str){
        List<Character> chars = new ArrayList<>();

        for (char ch : str.toCharArray()) {

            chars.add(ch);
        }

        return chars;
    }

    public static boolean esEspecial(Character ch){
        return ch >= 33 && ch <= 47 || ch >= 58 && ch <= 64 || ch >= 91 && ch <= 96 || ch >= 123 && ch <= 126;
    }

    public static boolean esNumero(Character ch){
        return ch >= 48 && ch <= 57;
    }
}
