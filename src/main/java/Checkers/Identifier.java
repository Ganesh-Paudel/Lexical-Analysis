package Checkers;

import Core.CharacterExtractor;
import Core.Lexer;
import Token.Tokens;
import Utils.Conditions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Identifier extends Checker{

    private static final HashSet<String> keywords = new HashSet<>(Arrays.asList(
            "if", "else", "for", "int", "float", "double", "do", "while", "String", "char"
    ));

    public Identifier(CharacterExtractor reader) throws IOException{
        super(reader);
    }

    public Tokens check(char nextCharacter) throws IOException {
        String lexeme = getLexeme(nextCharacter);
        System.out.println("Lexeme: " + lexeme);
        return checkForKeyWords(lexeme);
    }


    private String getLexeme(char currentCharacter) throws IOException {
        StringBuilder ss = new StringBuilder();
        ss.append(currentCharacter);
        while(true){
            int nextCharacter = reader.peek();
            if(nextCharacter == -1){
                break;
            }
            char nextChar = (char) nextCharacter;

            if(!(Conditions.isLetter(nextChar) || Conditions.isDigit(nextChar) || nextChar == '_')) {
                break;
            }
            ss.append((char) reader.getNextCharacter());

        }
       return ss.toString();
    }

    private Tokens checkForKeyWords(String lexeme) throws IOException{
        if(keywords.contains(lexeme)){
            return Tokens.KEYWORD;
        }
        return Tokens.IDENTIFIER;
    }



}
