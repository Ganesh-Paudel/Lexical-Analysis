package Utils;

/**
 * Character classification utility methods.
 * 
 * This utility class provides static methods for classifying individual characters,
 * determining whether they are digits, letters, whitespace, or quotes. These methods
 * form the basis for character-level decisions throughout the lexical analyzer.
 * 
 * All methods use simple ASCII range checks for fast character classification.
 * 
 * @author Ganesh
 * @version 1.0
 */
public class Conditions {

    /**
     * Determines if a character is a decimal digit (0-9).
     * 
     * @param ch The character to check
     * @return true if the character is '0' through '9', false otherwise
     */
    public static boolean isDigit(char ch) {
        return ch >= '0' && ch <= '9';
    }

    /**
     * Determines if a character is an alphabetic letter (A-Z or a-z).
     * 
     * @param ch The character to check
     * @return true if the character is 'A'-'Z' or 'a'-'z', false otherwise
     */
    public static boolean isLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    /**
     * Determines if a character is whitespace.
     * 
     * Whitespace includes: space, newline (\n), carriage return (\r), and tab (\t).
     * 
     * @param ch The character to check
     * @return true if the character is whitespace, false otherwise
     */
    public static boolean isWhiteSpace(char ch) {
        return ch == ' ' || ch == '\n' || ch == '\r' || ch == '\t';
    }

    /**
     * Determines if a character is a quote character (single or double).
     * 
     * @param ch The character to check
     * @return true if the character is '"' or ''', false otherwise
     */
    public static boolean isQuote(char ch) {
        return (ch == '"' || ch == '\'');
    }

}
