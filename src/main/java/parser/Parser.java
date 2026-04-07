package parser;

import java.util.ArrayList;

import Lexer.Token.Tokens;
import Utils.LexemeData;
import parser.Utils.InfixToPostfix;

public class Parser {

    public Parser(ArrayList<LexemeData> tokenizedData) {
        parseAll(tokenizedData);
    }

    private void parseAll(ArrayList<LexemeData> tokenizedData) {
        ArrayList<LexemeData> lineBuffer = new ArrayList<>();

        for (LexemeData token : tokenizedData) {

            if (token.getToken() == Tokens.SEMICOLON) {
                ArrayList<LexemeData> postFix = InfixToPostfix.infixToPostfix(lineBuffer);
                System.out.println(postFix);
                lineBuffer.clear();
            } else {
                lineBuffer.add(token);
            }
        }
    }
}
