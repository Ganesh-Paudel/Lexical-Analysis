package Lexer.Core;

import Utils.Conditions;
import Lexer.Token.Classes;

import java.io.IOException;

/**
 * Character classification layer between raw file input and the lexer.
 * 
 * This class wraps CharacterExtractor and adds automatic character classification.
 * It determines whether each character is a digit, letter, whitespace, quote, or unknown,
 * allowing the main Lexer to make tokenization decisions based on character class rather
 * than individual characters.
 * 
 * Features:
 * <ul>
 *   <li>Automatic character classification (letter, digit, whitespace, quotes, unknown)</li>
 *   <li>Whitespace skipping utility</li>
 *   <li>Single-character lookahead via underlying extractor</li>
 * </ul>
 * 
 * @author Ganesh
 * @version 1.0
 * @see CharacterExtractor
 * @see Classes
 */
public class BaseReader {
    /** Underlying character source */
    private CharacterExtractor reader;
    /** Current character being processed */
    private char nextChar;
    /** Classification of the current character */
    private Classes charClass;

    /**
     * Constructs a BaseReader wrapping the provided CharacterExtractor.
     * 
     * Immediately reads the first character and classifies it.
     * 
     * @param reader The underlying CharacterExtractor to wrap
     * @throws IOException If the first character cannot be read
     */
    public BaseReader(CharacterExtractor reader) throws IOException {
        this.reader = reader;
        getCharacter();
    }

    /**
     * Consumes consecutive whitespace characters.
     * 
     * Repeatedly calls {@link #getCharacter()} until a non-whitespace character
     * is encountered or end of file is reached.
     * 
     * @throws IOException If a read error occurs
     */
    public void getRidOfSpaces() throws IOException {
        while (Conditions.isWhiteSpace(nextChar)) {
            getCharacter();
        }
    }

    /**
     * Reads and classifies the next character from the underlying extractor.
     * 
     * Updates nextChar with the new character and classifies it into one of:
     * DIGIT, LETTER, WHITESPACE, QUOTES, or UNKNOWN. Returns EOF for end of file.
     * 
     * @throws IOException If a read error occurs
     */
    public void getCharacter() throws IOException {
        int next = reader.getNextCharacter();
        if (next != -1) {
            this.nextChar = (char) next;

            if (Conditions.isDigit(this.nextChar)) {
                this.charClass = Classes.DIGIT;
            } else if (Conditions.isLetter(this.nextChar)) {
                this.charClass = Classes.LETTER;
            } else if (Conditions.isWhiteSpace(this.nextChar)) {
                this.charClass = Classes.WHITESPACE;
            } else if (Conditions.isQuote(this.nextChar)) {
                this.charClass = Classes.QUOTES;
            } else {
                this.charClass = Classes.UNKNOWN;
            }
        } else {
            this.nextChar = '\0';
            charClass = Classes.EOF;
        }
    }

    /**
     * Classifies a character into its character class.
     * 
     * @param c The character to classify
     * @return The character class (DIGIT, LETTER, WHITESPACE, QUOTES, or UNKNOWN)
     */
    private Classes determineClass(char c) {
        if (Conditions.isDigit(c))
            return Classes.DIGIT;
        if (Conditions.isLetter(c))
            return Classes.LETTER;
        if (Conditions.isWhiteSpace(c))
            return Classes.WHITESPACE;
        if (Conditions.isQuote(c))
            return Classes.QUOTES;
        return Classes.UNKNOWN;
    }

    /**
     * Returns the current character.
     * 
     * @return The character currently being processed
     */
    public char getNextChar() {
        return nextChar;
    }

    /**
     * Returns the classification of the current character.
     * 
     * @return The character class of the current character
     */
    public Classes getCharClass() {
        return charClass;
    }

    /**
     * Returns the underlying CharacterExtractor for direct access if needed.
     * 
     * @return The wrapped CharacterExtractor instance
     */
    public CharacterExtractor getExtractor() {
        return reader;
    }

}
