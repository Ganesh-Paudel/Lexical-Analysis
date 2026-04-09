package Lexer.Checkers;

import Utils.LexemeData;
import Lexer.Core.CharacterExtractor;
import Lexer.Token.Tokens;
import Utils.Helpers;
import Utils.Conditions;

import java.io.IOException;
import java.util.ArrayList;

import static Utils.Helpers.getString;

/**
 * Parses and classifies numeric literals (integers and floating-point numbers).
 * 
 * This checker recognizes sequences of digits, optional decimal points, and validates
 * that numbers fall within 32-bit integer or valid floating-point ranges. It handles
 * negative numbers (those starting with a minus sign) and enforces a 5-decimal-place
 * limit on floating-point numbers.
 * 
 * @author Ganesh
 * @version 1.0
 * @see Checker
 */
public class Numbers extends Checker {

    /**
     * Constructs a Numbers checker with access to the character source.
     * 
     * @param reader The CharacterExtractor for reading characters
     */
    public Numbers(CharacterExtractor reader) {
        super(reader);
    }

    /**
     * Checks if the current character starts a number and extracts the complete lexeme.
     * 
     * Extracts the number string, classifies it, and for floating-point numbers,
     * limits decimal places to 5.
     * 
     * @param currentCharacter The starting character (digit or minus sign)
     * @return A LexemeData object with the number and token type (INT, FLOAT, or INVALID_INT)
     * @throws IOException If a read error occurs
     */
    public LexemeData check(char currentCharacter) throws IOException {
        String lexeme = getLexeme(currentCharacter);
        Tokens token = classifyTokens(lexeme);
        if (token == Tokens.FLOAT) {
            lexeme = getValidFloat(lexeme);
        }
        return new LexemeData(lexeme, token);
    }

    /**
     * Limits floating-point numbers to 5 decimal places maximum.
     * 
     * Truncates the number if it has more than 5 significant digits after the decimal point.
     * 
     * @param lex The floating-point string to validate
     * @return The number limited to 5 decimal places
     */
    private String getValidFloat(String lex) {
        boolean startCounter = false;
        int totalDecimals = 0;
        StringBuilder ss = new StringBuilder();
        for (int i = 0; i < lex.length(); i++) {
            ss.append(lex.charAt(i));
            if (lex.charAt(i) == '.') {
                startCounter = true;
            }
            if (startCounter) {
                totalDecimals++;
            }
            if (totalDecimals > 5) {
                break;
            }
        }
        return ss.toString();
    }

    /**
     * Classifies a numeric string into INT, FLOAT, or INVALID token type.
     * 
     * Checks for invalid content (letters), decimal point (FLOAT), negative sign (sign only = SUB_OP),
     * and 32-bit integer range.
     * 
     * @param lexeme The numeric string to classify
     * @return The token type of the number
     */
    private Tokens classifyTokens(String lexeme) {
        if (Helpers.containsLetters(lexeme)) {
            return Tokens.INVALID_IDENTIFIER;
        }

        if (Helpers.isFloat(lexeme)) {
            return Tokens.FLOAT;
        }
        boolean isNegative = Helpers.checkForNegative(lexeme);
        if (isNegative && lexeme.length() == 1) {
            return Tokens.SUB_OP;
        }
        return check32BitInteger(lexeme, isNegative);
    }

    /**
     * Validates that a number is within 32-bit integer range.
     * 
     * Checks both Integer.MAX_VALUE and Integer.MIN_VALUE limits.
     * 
     * @param lexeme The number string to validate
     * @param isNegative Whether the number is negative
     * @return INT if valid, INVALID_INT if out of range
     */
    private Tokens check32BitInteger(String lexeme, boolean isNegative) {
        if (isNegative) {
            lexeme = Helpers.getSubString(lexeme, 1);
        }
        return Helpers.isInteger(lexeme, isNegative);
    }

    /**
     * Extracts the complete number lexeme starting with the given character.
     * 
     * Handles negative numbers (starting with '-'), decimal points, and enforces
     * a 5-decimal-place limit. Stops reading at non-digit characters.
     * 
     * @param currentCharacter The first character (digit or minus sign)
     * @return The complete number string
     * @throws IOException If a read error occurs
     */
    private String getLexeme(char currentCharacter) throws IOException {
        ArrayList<Character> lexemeList = new ArrayList<>();
        lexemeList.add(currentCharacter);

        boolean isFloat = false;
        int decimalDigits = 0;

        if (currentCharacter == '-') {
            int next = reader.peek();
            if (!Conditions.isDigit((char) next)) {
                return getString(lexemeList);
            }
            currentCharacter = (char) reader.getNextCharacter();
            lexemeList.add(currentCharacter);
        }

        while (true) {
            int nextChar = reader.peek();
            if (nextChar == -1) {
                break;
            }
            char nextCharacter = (char) nextChar;
            if (Conditions.isDigit(nextCharacter) || Conditions.isLetter(nextCharacter)) {
                reader.getNextCharacter();
                if (!isFloat || decimalDigits < 5) {
                    lexemeList.add(nextCharacter);
                    if (isFloat) {
                        decimalDigits++;
                    }
                } else {
                    break;
                }
            } else if (nextCharacter == '.' && !isFloat) {
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
