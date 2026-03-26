package Checkers;

import Utils.LexemeData;
import Core.CharacterExtractor;
import Token.Tokens;
import Utils.Helpers;
import Utils.Conditions;

import java.io.IOException;
import java.util.ArrayList;

import static Utils.Helpers.getString;

public class Numbers extends Checker{

    public Numbers(CharacterExtractor reader){
        super(reader);
    }

    public LexemeData check(char currentCharacter) throws IOException {
        String lexeme = getLexeme(currentCharacter);
        Tokens token = classifyTokens(lexeme);
        return new LexemeData(lexeme, token);
    }

    private Tokens classifyTokens(String lexeme) {
        if(Helpers.isFloat(lexeme)){
            return Tokens.FLOAT;
        }
        boolean isNegative = Helpers.checkForNegative(lexeme);
        if(isNegative && lexeme.length() == 1){ 
          return Tokens.SUB_OP; 
    }
        return check32BitInteger(lexeme, isNegative);
    }

    private Tokens check32BitInteger(String lexeme, boolean isNegative) {
        if(isNegative){
            lexeme = Helpers.getSubString(lexeme, 1);
        }
        return Helpers.isInteger(lexeme, isNegative);
    }


    private String getLexeme(char currentCharacter) throws IOException{
        ArrayList<Character> lexemeList = new ArrayList<>();
        lexemeList.add(currentCharacter);

        boolean isFloat = false;
        int decimalDigits = 0;

        if(currentCharacter == '-'){
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
