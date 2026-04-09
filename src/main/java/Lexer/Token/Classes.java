package Lexer.Token;

/**
 * Enumeration of character classifications used during lexical analysis.
 * 
 * Each character in the input is classified into one of these categories,
 * which determines how the lexer will process it:
 * <ul>
 *   <li>LETTER: Alphabetic characters (A-Z, a-z)</li>
 *   <li>DIGIT: Numeric characters (0-9)</li>
 *   <li>QUOTES: Quote characters (" and ')</li>
 *   <li>COMMENT: Comment indicators (/ for potential comments)</li>
 *   <li>UNKNOWN: Any other character</li>
 *   <li>EOF: End of file marker</li>
 *   <li>WHITESPACE: Whitespace characters (space, tab, newline, carriage return)</li>
 * </ul>
 * 
 * Each class has an associated numeric value for identification purposes.
 * 
 * @author Project Team
 * @version 1.0
 */
public enum Classes {

    /** Alphabetic character (26 possible values for tracking) */
    LETTER(26),
    /** Numeric digit (10 possible values) */
    DIGIT(10),
    /** Quote character (single or double) */
    QUOTES(2),
    /** Comment indicator character */
    COMMENT(3),
    /** Unknown/unclassified character */
    UNKNOWN(0),
    /** End of file */
    EOF(-1),
    /** Whitespace character (space, tab, newline, carriage return) */
    WHITESPACE(1);

    /** Numeric value associated with this classification */
    private final int value;

    /**
     * Constructs a Classes enum constant with the given numeric value.
     * 
     * @param value The numeric identifier for this classification
     */
    Classes(int value) {
        this.value = value;
    }

    /**
     * Returns the numeric value of this character classification.
     * 
     * @return The numeric identifier for this classification
     */
    public int getValue() {
        return this.value;
    }
}
