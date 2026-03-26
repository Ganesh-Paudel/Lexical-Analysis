package Utils;

import Token.Classes;

public class Conditions {

    public static boolean isDigit(char ch){
        return ch >= '0' && ch <= '9';
    }

    public static boolean isLetter(char ch){
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    public static boolean isWhiteSpace(char ch){
        return ch == ' ';
    }
    
    public static boolean isQuote(char ch){
      return (ch == '"' || ch == '\'');
    }

}
