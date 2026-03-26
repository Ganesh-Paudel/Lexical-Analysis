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

    public static boolean checkForNegative(String lexeme) {
       if (lexeme.length() >= 1) return lexeme.charAt(0) == '-';
        return false;
    }


    public static String getSubString(String lexeme, int offset) {
        StringBuilder ss = new StringBuilder();
        if(lexeme.length() < offset + 1){
            return lexeme;
        }
        for(int i = offset; i < lexeme.length(); i++){
            ss.append(lexeme.charAt(i));
        }
        return ss.toString();
    }


    public static Tokens isInteger(String lexeme, boolean negative) {
        if(lexeme.length() > 10){
            return Tokens.INVALID_INT;
        } else if(lexeme.length() < 10){
            return Tokens.INT;
        } else {
            if(negative){
                String maxValue = ""+Integer.MIN_VALUE;
                return compareNumber(lexeme, maxValue);
            } else{
                String maxValue = ""+Integer.MAX_VALUE;
                return compareNumber(lexeme, maxValue);
            }
        }
    }

    private static Tokens compareNumber(String number, String maxValue){
        for(int i = 0; i < number.length(); i++){
            if(number.charAt(i) > maxValue.charAt(i)){
                return Tokens.INVALID_INT;
            }
        }
        return Tokens.INT;
    }


}
