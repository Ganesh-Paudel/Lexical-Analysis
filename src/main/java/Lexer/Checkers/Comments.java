package Lexer.Checkers;

import Lexer.Core.CharacterExtractor;
import Lexer.Token.Tokens;
import Utils.LexemeData;
import Utils.Helpers;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Parses line and block comments from the source code.
 * * This checker recognizes two types of comments:
 * <ul>
 * <li><b>Line comments:</b> Starting with '//', extending to end of line</li>
 * <li><b>Block comments:</b> Enclosed in "/*" and "&#42;/", may span multiple lines </li>
 * </ul>
 * * Returns INVALID_COMMENT if a block comment is not properly closed (EOF reached
 * before finding '&#42;/').
 * * @author Ganesh
 * @version 1.0
 * @see Checker
 */
public class Comments extends Checker {

    /**
     * Constructs a Comments checker with access to the character source.
     * 
     * @param reader The CharacterExtractor for reading characters
     */
    public Comments(CharacterExtractor reader) {
        super(reader);
    }

    /**
     * Scans and parses a comment starting with the given character.
     * 
     * Assumes the current character is '/' and checks the next character to
     * determine comment type:
     * <ul>
     *   <li>If next is '/': Parses a line comment (to end of line or EOF)</li>
     *   <li>If next is '*': Parses a block comment (to "&#42;/" or EOF)</li>
     *   <li>Otherwise: Returns the '/' as a DIV_OP token (not a comment)</li>
     * </ul>
     * 
     * @param currentCharacter The '/' character starting potential comment
     * @return A LexemeData object with COMMENT, INVALID_COMMENT, or DIV_OP token
     * @throws IOException If a read error occurs
     */
    public LexemeData scan(char currentCharacter) throws IOException {
        ArrayList<Character> lexemeList = new ArrayList<>();
        lexemeList.add(currentCharacter);
        int nextCharacter = reader.peek();

        if (nextCharacter == -1) {
            return new LexemeData(Helpers.getString(lexemeList), Tokens.DIV_OP);
        }

        if (nextCharacter == '/') {
            lexemeList.add((char) reader.getNextCharacter());
            while (true) {
                nextCharacter = reader.getNextCharacter();
                if (nextCharacter == -1 || nextCharacter == '\n') {
                    break;
                }
                lexemeList.add((char) nextCharacter);
            }
            return new LexemeData(Helpers.getString(lexemeList), Tokens.COMMENT);
        } else if (nextCharacter == '*') {
            lexemeList.add((char) reader.getNextCharacter());
            while (true) {
                nextCharacter = reader.getNextCharacter();
                if (nextCharacter == -1) {
                    return new LexemeData(Helpers.getString(lexemeList), Tokens.INVALID_COMMENT);
                }
                if ((char) nextCharacter != '\n') {
                    lexemeList.add((char) nextCharacter);
                } else {
                    lexemeList.add(' ');
                }

                if (nextCharacter == '*') {
                    int slash = reader.peek();
                    if (slash == '/') {
                        lexemeList.add((char) reader.getNextCharacter());
                        break;
                    }
                }
            }
            return new LexemeData(Helpers.getString(lexemeList), Tokens.COMMENT);
        }

        return new LexemeData(Helpers.getString(lexemeList), Tokens.DIV_OP);
    }
}
