package Checkers;

import Core.CharacterExtractor;
import Token.Tokens;
import Utils.Conditions;

import java.io.IOException;
import java.util.ArrayList;

import static Utils.Helpers.getString;

public class Numbers extends Checker{

    public Numbers(CharacterExtractor reader){
        super(reader);

    }

    public Tokens check(char currentCharacter) throws IOException {
        String lexeme = getLexeme(currentCharacter);
        System.out.println("AFte lexeme: "+ lexeme);
        return classifyTokens(lexeme);
    }

    private Tokens classifyTokens(String lexeme) {
        if(isFloat(lexeme)){
            return Tokens.FLOAT;
        }
        boolean isNegative = checkForNegative(lexeme);
        return check32BitInteger(lexeme, isNegative);
    }

    private boolean checkForNegative(String lexeme) {
        return lexeme.charAt(0) == '-';
    }

    private Tokens check32BitInteger(String lexeme, boolean isNegative) {
        if(isNegative){
            lexeme = getSubString(lexeme, 1);
        }
        return isInteger(lexeme);
    }

    private String getSubString(String lexeme, int offset) {
        StringBuilder ss = new StringBuilder();
        if(lexeme.length() < offset + 1){
            return lexeme;
        }
        for(int i = offset; i < lexeme.length(); i++){
            ss.append(lexeme.charAt(i));
        }
        return ss.toString();
    }

    private Tokens isInteger(String lexeme) {
        System.out.println(lexeme);
        System.out.println("Length of int: "+ lexeme.length());
        if(lexeme.length() > 10){
            return Tokens.INVALID_INT;
        }
        return Tokens.INT;
    }

    private boolean isFloat(String lexeme) {
        for(int i = 0; i < lexeme.length(); i++){
            if(lexeme.charAt(i) == '.'){
                return true;
            }
        }
        return false;
    }

    private String getLexeme(char currentCharacter) throws IOException{
        ArrayList<Character> lexemeList = new ArrayList<>();
        lexemeList.add(currentCharacter);

        boolean isFloat = false;
        int decimalDigits = 0;

        if(currentCharacter == '-'){
            lexemeList.add(currentCharacter);
            int next = reader.peek();
            if(!Conditions.isDigit((char) next)){
                return getString(lexemeList);
            }
            currentCharacter = (char) reader.getNextCharacter();
            lexemeList.add(currentCharacter);
        }

        while(true){
            int nextChar = reader.peek();
            if(nextChar == -1){
                break;
            }
            char nextCharacter = (char) nextChar;
            if(Conditions.isDigit(nextCharacter)){
                reader.getNextCharacter();
                lexemeList.add(nextCharacter);
                if(isFloat && decimalDigits < 5){
                    lexemeList.add(nextCharacter);
                    decimalDigits++;
                }
            } else if(nextCharacter == '.' && !isFloat){
                isFloat = true;
                reader.getNextCharacter();
                lexemeList.add(nextCharacter);
            } else {
                break;
            }

        }
        return getString(lexemeList);
    }


}
