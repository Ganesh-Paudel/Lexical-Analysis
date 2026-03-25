package Utils;

import Token.Tokens;

import java.util.ArrayList;

public class Helpers {

    public static String getString(ArrayList<Character> list){
        StringBuilder sb = new StringBuilder();
        for(Character c: list){
            sb.append(c);
        }
        return sb.toString();
    }
    public static boolean isFloat(String lexeme) {
        for(int i = 0; i < lexeme.length(); i++){
            if(lexeme.charAt(i) == '.'){
                return true;
            }
        }
        return false;
    }


    public static Tokens isInteger(String lexeme) {
        System.out.println(lexeme);
        System.out.println("Length of int: "+ lexeme.length());
        if(lexeme.length() > 10){
            return Tokens.INVALID_INT;
        } else if(lexeme.length() < 10){
            return Tokens.INT;
        } else {
            return Tokens.INT;
        }
    }


}
