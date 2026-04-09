package parser.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import Lexer.Token.Tokens;
import Utils.LexemeData;

/**
 * Converts infix mathematical expressions to postfix notation (RPN).
 * 
 * This class implements the Shunting Yard algorithm by Dijkstra, which converts
 * standard infix expressions (e.g., "2 + 3 * 4") into equivalent postfix/RPN
 * expressions (e.g., "2 3 4 * +"). This format is ideal for stack-based evaluation.
 * 
 * Features:
 * <ul>
 *   <li>Handles operator precedence (+ and - = 1, * and / = 2)</li>
 *   <li>Respects parentheses for grouping</li>
 *   <li>Detects unbalanced parentheses</li>
 * </ul>
 * 
 * @author Ganesh
 * @version 1.0
 */
public class InfixToPostfix {
    /** Operator precedence map: higher number = higher precedence */
    private static final HashMap<String, Integer> PRECEDENCE = new HashMap<>();

    /**
     * Static initializer to set up operator precedence.
     * + and - have precedence 1 (lowest)
     * * and / have precedence 2 (higher)
     */
    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
    }

    /**
     * Converts a list of tokens in infix notation to postfix notation.
     * 
     * Implements the Shunting Yard algorithm:
     * <ol>
     *   <li>Operands (numbers, identifiers) are added directly to output</li>
     *   <li>Left parenthesis goes on operator stack</li>
     *   <li>Right parenthesis pops operators until matching left paren</li>
     *   <li>Operators are popped based on precedence: pop while top has >= precedence</li>
     *   <li>Remaining operators are popped at the end</li>
     * </ol>
     * 
     * @param tokenizedData The ArrayList of LexemeData tokens in infix notation
     * @return A new ArrayList of LexemeData tokens in postfix notation
     */
    public static ArrayList<LexemeData> infixToPostfix(ArrayList<LexemeData> tokenizedData) {
        ArrayList<LexemeData> postFixList = new ArrayList<>();
        Deque<LexemeData> stack = new ArrayDeque<>();

        for (LexemeData token : tokenizedData) {
            if (token.getToken() == Tokens.INT ||
                    token.getToken() == Tokens.IDENTIFIER ||
                    token.getToken() == Tokens.FLOAT) {
                postFixList.add(token);
            } else if (token.getToken() == Tokens.LEFT_PAREN) {
                stack.push(token);
            } else if (token.getToken() == Tokens.RIGHT_PAREN) {
                while (!stack.isEmpty() &&
                        stack.peek().getToken() != Tokens.LEFT_PAREN) {
                    postFixList.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().getToken() == Tokens.LEFT_PAREN) {
                    stack.pop();
                }
            } else {
                while (!stack.isEmpty() &&
                        stack.peek().getToken() != Tokens.LEFT_PAREN &&
                        PRECEDENCE.getOrDefault(stack.peek().getValue(), 0) >= PRECEDENCE.getOrDefault(token.getValue(),
                                0)) {
                    postFixList.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            LexemeData remaining = stack.pop();

            if (remaining.getToken() != Tokens.LEFT_PAREN && remaining.getToken() != Tokens.RIGHT_PAREN) {
                postFixList.add(remaining);
            } else {
                System.out.println("Unbalanced Parenthessis detected");
            }

        }
        return postFixList;
    }
}
