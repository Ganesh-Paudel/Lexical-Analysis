package Checkers;

import Core.CharacterExtractor;
import Token.Tokens;
import Utils.Conditions;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;

public class Identifier extends Checker{

    private static final HashSet<String> keywords = new HashSet<>(Arrays.asList(
            "if", "else", "for", "int", "float", "double", "do", "while", "String", "char"
    ));

    public Identifier(CharacterExtractor reader, char currentCharacter) throws IOException{
        super(reader, currentCharacter);
    }

    public Tokens check() throws IOException {
        String lexeme = getLexeme();
        return checkForKeyWords(lexeme);
    }


    private String getLexeme() throws IOException {
        StringBuilder ss = new StringBuilder();
        ss.append(currentCharacter);
        currentCharacter = (char) reader.peek();
        while(Conditions.isLetter(currentCharacter) || Conditions.isDigit(currentCharacter) || currentCharacter == '_'
    ){
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
