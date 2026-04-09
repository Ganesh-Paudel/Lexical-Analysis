package Utils;

import Lexer.Token.Tokens;
import parser.Tree.BinaryTreeVisualizer;
import parser.Tree.TreeNode;

import java.util.ArrayList;

import com.sun.source.tree.Tree;

/**
 * Collection of miscellaneous utility methods for string manipulation and validation.
 * 
 * This utility class provides helper functions used throughout the lexer and parser,
 * including token list formatting, string building, number validation, and tree visualization.
 * 
 * @author Ganesh
 * @version 1.0
 */
public class Helpers {

    /**
     * Pretty-prints a list of lexeme data tokens to the console in tabular format.
     * 
     * Uses the UI class to display tokens in aligned columns with borders.
     * 
     * @param list The ArrayList of LexemeData tokens to display
     */
    public static void prettyPrint(ArrayList<LexemeData> list) {
        for (int i = 0; i < list.size(); i++) {
            UI.printNicely(list.get(i).getToken().getName(), list.get(i).getValue());
        }
    }

    /**
     * Builds a string from a list of LexemeData tokens by concatenating their values.
     * 
     * Joins lexeme values with spaces between them and adds a newline at the end.
     * 
     * @param list The ArrayList of LexemeData tokens to concatenate
     * @return A single string containing all lexeme values concatenated with spaces
     */
    public static String getStringFromLexemeData(ArrayList<LexemeData> list) {

        StringBuilder string = new StringBuilder();

        for (LexemeData data : list) {
            string.append(data.getValue());
            string.append(" ");
        }
        string.append("\n");
        return string.toString();
    }

    /**
     * Prints all parse trees in a collection using ASCII art visualization.
     * 
     * @param trees The ArrayList of TreeNode root nodes to visualize
     */
    public static void printParseTrees(ArrayList<TreeNode> trees) {
        for (TreeNode tree : trees) {
            BinaryTreeVisualizer.print(tree);
        }
    }

    /**
     * Converts an ArrayList of characters to a string.
     * 
     * @param list The ArrayList of Character objects to convert
     * @return A string containing all characters in sequence
     */
    public static String getString(ArrayList<Character> list) {
        StringBuilder sb = new StringBuilder();
        for (Character c : list) {
            sb.append(c);
        }
        return sb.toString();
    }

    /**
     * Determines if a string contains a decimal point.
     * 
     * @param lexeme The string to check
     * @return true if the string contains '.', false otherwise
     */
    public static boolean isFloat(String lexeme) {
        for (int i = 0; i < lexeme.length(); i++) {
            if (lexeme.charAt(i) == '.') {
                return true;
            }
        }
        return false;
    }

    /**
     * Determines if a string starts with a minus sign.
     * 
     * @param lexeme The string to check
     * @return true if the first character is '-', false otherwise
     */
    public static boolean checkForNegative(String lexeme) {
        if (lexeme.length() >= 1)
            return lexeme.charAt(0) == '-';
        return false;
    }

    /**
     * Extracts a substring starting at the given offset to the end of the string.
     * 
     * @param lexeme The source string
     * @param offset The starting position (0-based)
     * @return A substring from offset to the end, or the original string if offset is beyond bounds
     */
    public static String getSubString(String lexeme, int offset) {
        StringBuilder ss = new StringBuilder();
        if (lexeme.length() < offset + 1) {
            return lexeme;
        }
        for (int i = offset; i < lexeme.length(); i++) {
            ss.append(lexeme.charAt(i));
        }
        return ss.toString();
    }

    /**
     * Determines if a numeric string is a valid 32-bit integer.
     * 
     * Checks both the range (Integer.MIN_VALUE to Integer.MAX_VALUE) and enforces
     * that 32-bit integers are at most 10 digits long.
     * 
     * @param lexeme The numeric string to validate
     * @param negative Whether the number represents a negative value
     * @return INT if valid, INVALID_INT if the number is out of 32-bit range
     */
    public static Tokens isInteger(String lexeme, boolean negative) {
        if (lexeme.length() > 10) {
            return Tokens.INVALID_INT;
        } else if (lexeme.length() < 10) {
            return Tokens.INT;
        } else {
            if (negative) {
                String maxValue = "" + Integer.MIN_VALUE;
                return compareNumber(lexeme, maxValue);
            } else {
                String maxValue = "" + Integer.MAX_VALUE;
                return compareNumber(lexeme, maxValue);
            }
        }
    }

    /**
     * Compares a numeric string against a maximum value string.
     * 
     * Performs lexicographic comparison character by character to determine if
     * the number exceeds the maximum valid value.
     * 
     * @param number The numeric string to validate
     * @param maxValue The maximum valid number as a string
     * @return INT if number <= maxValue, INVALID_INT otherwise
     */
    private static Tokens compareNumber(String number, String maxValue) {
        for (int i = 0; i < number.length(); i++) {
            if (number.charAt(i) > maxValue.charAt(i)) {
                return Tokens.INVALID_INT;
            }
        }
        return Tokens.INT;
    }

    /**
     * Determines if a string contains any alphabetic letters.
     * 
     * @param lexeme The string to check
     * @return true if the string contains one or more letters, false otherwise
     */
    public static boolean containsLetters(String lexeme) {

        for (int i = 0; i < lexeme.length(); i++) {
            if (Conditions.isLetter(lexeme.charAt(i))) {
                return true;
            }
        }
        return false;
    }

}
