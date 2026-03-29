package Checkers;

import Core.CharacterExtractor;
import Token.Tokens;
import Utils.LexemeData;

public class SingleOperators extends Checker {

    public SingleOperators(CharacterExtractor reader) {
        super(reader);
    }

    public LexemeData check(char currentCharacter) {

        switch (currentCharacter) {
            case '(' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.RIGHT_PAREN);
            }
            case ')' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.LEFT_PAREN);
            }
            case '+' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.ADD_OP);
            }
            case '-' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.SUB_OP);
            }
            case '*' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.MULT_OP);
            }
            case '/' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.DIV_OP);
            }
            case ';' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.SEMICOLON);
            }
            case '=' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.ASSIGN_OP);
            }
            case '"' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.DOUBLE_QUOTE);
            }
            case '\'' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.SINGLE_QUOTE);
            }
            default -> {
                return new LexemeData(String.valueOf(String.valueOf(currentCharacter)), Tokens.UNKNOWN);
            }

        }

    }

}
