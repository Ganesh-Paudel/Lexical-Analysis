package Lexer.Checkers;

import Lexer.Core.CharacterExtractor;
import Utils.LexemeData;
import Utils.Helpers;
import java.io.IOException;
import java.util.ArrayList;
import Lexer.Token.Tokens;

/**
 * Parses string and character literals from the source code.
 * 
 * This checker recognizes quoted literals enclosed in either single or double quotes.
 * It distinguishes between character literals (single character in single quotes, classified as CHAR)
 * and string literals (any content in double quotes, classified as STRING). Unclosed quotes result
 * in INVALID_STRING tokens.
 * 
 * @author Ganesh
 * @version 1.0
 * @see Checker
 */
public class Strings extends Checker {


    /**
     * Constructs a Strings checker with access to the character source.
     * 
     * @param reader The CharacterExtractor for reading characters
     */
    public Strings(CharacterExtractor reader) {
        super(reader);
    }

    /**
     * Scans and parses a quoted literal starting with the given quote character.
     * 
     * Reads characters until the closing quote is found. Returns:
     * <ul>
     *   <li>CHAR token if it's a single character in single quotes (e.g., 'a')</li>
     *   <li>STRING token if it's content in double quotes (e.g., "hello")</li>
     *   <li>INVALID_STRING if no closing quote is found before EOF</li>
     * </ul>
     * 
     * @param currentCharacter The opening quote character (' or ")
     * @return A LexemeData object with the literal and appropriate token type
     * @throws IOException If a read error occurs
     */
    public LexemeData scan(char currentCharacter) throws IOException {
        ArrayList<Character> lexemelist = new ArrayList<>();
        lexemelist.add(currentCharacter);
        int nextCharacter;

        while (true) {
            nextCharacter = reader.peek();
            if (nextCharacter == -1) {
                return new LexemeData(Helpers.getString(lexemelist), Tokens.INVALID_STRING);
            }
            lexemelist.add((char) reader.getNextCharacter());
            if ((char) nextCharacter == currentCharacter) {
                break;
            }
        }

        if (currentCharacter == '\'' && lexemelist.size() == 3) {
            return new LexemeData(Helpers.getString(lexemelist), Tokens.CHAR);
        }

        return new LexemeData(Helpers.getString(lexemelist), Tokens.STRING);
    }

}
