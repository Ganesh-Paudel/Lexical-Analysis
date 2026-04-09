package Utils;

/**
 * Console-based UI utility for displaying tokens in tabular format.
 * 
 * This class provides methods for printing token lists in a nicely formatted
 * table with consistent alignment and borders. It's used by the Helpers class
 * to display lexeme tokens in a human-readable format.
 * 
 * Table format:
 * <pre>
 * +---------------------------+---------------------------+
 * | TOKEN TYPE                | LEXEME                    |
 * +---------------------------+---------------------------+
 * | integer                   | 123                       |
 * | identifier                | myVar                     |
 * +---------------------------+---------------------------+
 * </pre>
 * 
 * @author Ganesh
 * @version 1.0
 */
public class UI {

    /**
     * Prints the table header with borders and column labels.
     * 
     * Outputs two decorative borders flanking the column headers "TOKEN TYPE" and "LEXEME".
     */
    public static void printHeader() {
        System.out.println("+---------------------------+---------------------------+");
        System.out.println("| TOKEN TYPE                | LEXEME                    |");
        System.out.println("+---------------------------+---------------------------+");
    }

    /**
     * Prints a single token row in the table.
     * 
     * Formats and aligns the token type and lexeme value in fixed-width columns (25 chars each),
     * flanked by vertical borders.
     * 
     * @param nextToken The token type name (e.g., "integer", "identifier")
     * @param lexeme The lexeme value (e.g., "123", "myVar")
     */
    public static void printNicely(String nextToken, String lexeme) {
        System.out.printf("| %-25s | %-25s |%n", nextToken, lexeme);
    }

    /**
     * Prints the table footer border.
     * 
     * Outputs a decorative border matching the header format.
     */
    public static void printFooter() {
        System.out.println("+---------------------------+---------------------------+");
    }

}
