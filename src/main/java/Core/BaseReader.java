package Core;

import Utils.Conditions;
import Token.Classes;

import java.io.IOException;

public class BaseReader {
    private CharacterExtractor reader;
    private char nextChar;
    private Classes charClass;

    public BaseReader(CharacterExtractor reader) throws IOException {
        this.reader = reader;
        getCharacter();
    }

    public void getRidOfSpaces() throws IOException {
        while (Conditions.isWhiteSpace(nextChar)) {
            getCharacter();
        }
    }

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

    public char getNextChar() {
        return nextChar;
    }

    public Classes getCharClass() {
        return charClass;
    }

    public CharacterExtractor getExtractor() {
        return reader;
    }

}
