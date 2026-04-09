package Lexer.Checkers;

import Lexer.Core.CharacterExtractor;
import Lexer.Token.Tokens;
import Utils.LexemeData;

/**
 * Recognizes single-character operators and delimiters.
 * 
 * This checker maps individual operator and delimiter characters to their
 * corresponding token types. Handles arithmetic operators (+, -, *, /),
 * assignment (=), parentheses, semicolon, and quotes.
 * 
 * @author Ganesh
 * @version 1.0
 * @see Checker
 */
public class SingleOperators extends Checker {

    /**
     * Constructs a SingleOperators checker with access to the character source.
     * 
     * @param reader The CharacterExtractor for reading characters
     */
    public SingleOperators(CharacterExtractor reader) {
        super(reader);
    }

    /**
     * Maps a character to its corresponding operator or delimiter token type.
     * 
     * Supported characters:
     * <ul>
     *   <li>'+' → ADD_OP</li>
     *   <li>'-' → SUB_OP</li>
     *   <li>'*' → MULT_OP</li>
     *   <li>'/' → DIV_OP</li>
     *   <li>'=' → ASSIGN_OP</li>
     *   <li>';' → SEMICOLON</li>
     *   <li>'(' → LEFT_PAREN</li>
     *   <li>')' → RIGHT_PAREN</li>
     *   <li>'"' → DOUBLE_QUOTE</li>
     *   <li>''' → SINGLE_QUOTE</li>
     *   <li>Other → UNKNOWN</li>
     * </ul>
     * 
     * @param currentCharacter The operator or delimiter character to classify
     * @return A LexemeData object with the character and its token type
     */
    public LexemeData check(char currentCharacter) {

        switch (currentCharacter) {
            case ')' -> {
                return new LexemeData(String.valueOf(currentCharacter), Tokens.RIGHT_PAREN);
            }
            case '(' -> {
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
