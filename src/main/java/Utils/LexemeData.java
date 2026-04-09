package Utils;

import Lexer.Token.Tokens;

/**
 * Data container for a single token (lexeme + token type).
 * 
 * This class represents one complete token in the lexicalized input, pairing
 * the actual text (value) with its classified token type. It serves as the
 * fundamental data structure passed between the lexer and parser.
 * 
 * @author Ganesh
 * @version 1.0
 */
public class LexemeData {
    /** The actual text of the token as it appears in the source */
    private String value;
    /** The token classification (INT, IDENTIFIER, OPERATOR, etc.) */
    private Tokens token;

    /**
     * Constructs a LexemeData with the given lexeme value and token type.
     * 
     * @param value The actual text of the token
     * @param token The token type/classification
     */
    public LexemeData(String value, Tokens token) {
        this.value = value;
        this.token = token;
    }

    /**
     * Returns the token type of this lexeme.
     * 
     * @return The Tokens enum value representing this token's classification
     */
    public Tokens getToken() {
        return this.token;
    }

    /**
     * Returns the actual text of this token.
     * 
     * @return The lexeme string as it appeared in the source
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Returns a string representation of this lexeme in the format "value -> TokenName".
     * 
     * @return A formatted string showing both the value and token type
     */
    @Override
    public String toString() {
        return this.value + " -> " + this.token.getName();
    }
}
