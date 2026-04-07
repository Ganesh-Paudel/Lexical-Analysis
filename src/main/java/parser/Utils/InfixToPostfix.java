package parser.Utils;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;

import Lexer.Token.Tokens;
import Utils.LexemeData;

public class InfixToPostfix {
    private static final HashMap<String, Integer> PRECEDENCE = new HashMap<>();

    static {
        PRECEDENCE.put("+", 1);
        PRECEDENCE.put("-", 1);
        PRECEDENCE.put("*", 2);
        PRECEDENCE.put("/", 2);
    }

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
