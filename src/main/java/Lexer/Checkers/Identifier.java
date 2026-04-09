package Lexer.Checkers;

import Utils.LexemeData;
import Lexer.Core.CharacterExtractor;
import Lexer.Token.Tokens;
import Utils.Conditions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static Utils.Helpers.getString;

/**
 * Recognizes and classifies identifiers and keywords in the source code.
 * 
 * This checker identifies sequences of letters, digits, and underscores
 * that start with a letter. It then checks if the identifier is a reserved
 * keyword (if, else, for, int, float, double, do, while, String, char).
 * 
 * @author Ganesh
 * @version 1.0
 * @see Checker
 */
public class Identifier extends Checker {

    /** Set of Java reserved keywords */
    private static final HashSet<String> keywords = new HashSet<>(Arrays.asList(
            "if", "else", "for", "int", "float", "double", "do", "while", "String", "char"));

    /**
     * Constructs an Identifier checker with access to the character source.
     * 
     * @param reader The CharacterExtractor for reading characters
     * @throws IOException If an I/O error occurs
     */
    public Identifier(CharacterExtractor reader) throws IOException {
        super(reader);
    }

    /**
     * Checks if the current character starts an identifier and extracts the complete lexeme.
     * 
     * Assumes the current character is a letter. Reads letters, digits, and underscores
     * until a non-identifier character is encountered.
     * 
     * @param nextCharacter The starting character (must be a letter)
     * @return A LexemeData object with the identifier and token type (IDENTIFIER or KEYWORD)
     * @throws IOException If a read error occurs
     */
    public LexemeData check(char nextCharacter) throws IOException {
        String lexeme = getLexeme(nextCharacter);
        Tokens token = checkForKeyWords(lexeme);
        return new LexemeData(lexeme, token);
    }

    /**
     * Extracts the complete identifier lexeme starting with the given character.
     * 
     * Continues reading characters as long as they are letters, digits, or underscores.
     * 
     * @param currentCharacter The first character of the identifier
     * @return The complete identifier string
     * @throws IOException If a read error occurs
     */
    private String getLexeme(char currentCharacter) throws IOException {
        ArrayList<Character> lexemeList = new ArrayList<>();
        lexemeList.add(currentCharacter);
        while (true) {
            int nextCharacter = reader.peek();
            if (nextCharacter == -1) {
                break;
            }
            char nextChar = (char) nextCharacter;

            if (!(Conditions.isLetter(nextChar) || Conditions.isDigit(nextChar) || nextChar == '_')) {
                break;
            }
            lexemeList.add((char) reader.getNextCharacter());

        }
        return getString(lexemeList);
    }

    /**
     * Checks if the identifier is a reserved keyword.
     * 
     * @param lexeme The identifier string to check
     * @return KEYWORD if the identifier is a reserved keyword, IDENTIFIER otherwise
     * @throws IOException If an error occurs (though this method doesn't actually throw)
     */
    private Tokens checkForKeyWords(String lexeme) throws IOException {
        if (keywords.contains(lexeme)) {
            return Tokens.KEYWORD;
        }
        return Tokens.IDENTIFIER;
    }

}
