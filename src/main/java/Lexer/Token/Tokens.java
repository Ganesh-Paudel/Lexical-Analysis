package Lexer.Token;

/**
 * Enumeration of all possible token types in the language being analyzed.
 * 
 * This enum defines every token category that the lexer can recognize,
 * including literals, keywords, operators, delimiters, and error types.
 * Each token has a numeric value for identification and a descriptive name.
 * 
 * Token categories:
 * <ul>
 *   <li><b>Literals:</b> INT, FLOAT, STRING, CHAR</li>
 *   <li><b>Keywords:</b> KEYWORD (reserved words like if, else, for, etc.)</li>
 *   <li><b>Identifiers:</b> IDENTIFIER (variable/function names)</li>
 *   <li><b>Operators:</b> ASSIGN_OP, ADD_OP, SUB_OP, MULT_OP, DIV_OP</li>
 *   <li><b>Delimiters:</b> LEFT_PAREN, RIGHT_PAREN, SEMICOLON</li>
 *   <li><b>Literals:</b> SINGLE_QUOTE, DOUBLE_QUOTE</li>
 *   <li><b>Comments:</b> COMMENT (line and block comments)</li>
 *   <li><b>Error Type:</b> INVALID_INT, INVALID_STRING, INVALID_COMMENT, INVALID_IDENTIFIER, UNKNOWN</li>
 *   <li><b>Special:</b> EOF (end of file)</li>
 * </ul>
 * 
 * @author Ganesh
 * @version 1.0
 */
public enum Tokens {
    /** Integer literal token (e.g., 123) */
    INT(10, "integer"),
    /** Floating-point literal token (e.g., 3.14) */
    FLOAT(11, "float"),
    /** Identifier token (variable or function name) */
    IDENTIFIER(12, "identifier"),
    /** Keyword token (reserved word like if, else, for) */
    KEYWORD(13, "keyword"),
    /** Assignment operator (=) */
    ASSIGN_OP(20, "Assignment Operator"),
    /** Addition operator (+) */
    ADD_OP(21, "Addition Operator"),
    /** Subtraction operator (-) */
    SUB_OP(22, "Difference Operator"),
    /** Multiplication operator (*) */
    MULT_OP(23, "Multiplication Operator"),
    /** Division operator (/) */
    DIV_OP(24, "Division Operator"),
    /** Left parenthesis/brace (() */
    LEFT_PAREN(25, "Left Braces"),
    /** Right parenthesis/brace ()) */
    RIGHT_PAREN(26, "Right Braces"),
    /** Semicolon (;) */
    SEMICOLON(30, "SemiColon"),
    /** Single quote character (') */
    SINGLE_QUOTE(31, "Single Quote"),
    /** Double quote character (") */
    DOUBLE_QUOTE(32, "Double Quote"),
    /** String literal token (text in double quotes) */
    STRING(33, "String"),
    /** Comment token (line or block comment) */
    COMMENT(35, "Comments"),
    /** Character literal token (single character in single quotes) */
    CHAR(37, "Character"),
    /** Unknown token (unrecognized character or sequence) */
    UNKNOWN(-2, "Unknown Token"),
    /** Invalid integer token (out of range or malformed) */
    INVALID_INT(-2, "** Invalid Integer **"),
    /** Invalid string token (unclosed or malformed) */
    INVALID_STRING(-2, "** Invlid String **"),
    /** Invalid comment token (unclosed block comment) */
    INVALID_COMMENT(-2, "** Unclosed Comment **"),
    /** Invalid identifier token */
    INVALID_IDENTIFIER(-2, "** Invalid Identifier **"),
    /** End of file marker */
    EOF(-1, "EOF");

    /** Numeric value for token identification */
    private final int value;
    /** Human-readable description of the token */
    private String name;

    /**
     * Constructs a Tokens enum constant with the given value and name.
     * 
     * @param value The numeric identifier for this token type
     * @param name The descriptive name of this token type
     */
    Tokens(int value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * Sets the descriptive name of this token type.
     * 
     * @param name The new name to set (if non-null)
     */
    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    /**
     * Returns the numeric value of this token type.
     * 
     * @return The numeric identifier for this token type
     */
    public int getValue() {
        return this.value;
    }

    /**
     * Returns the descriptive name of this token type.
     * 
     * @return The human-readable name of this token type
     */
    public String getName() {
        return this.name;
    }

}
