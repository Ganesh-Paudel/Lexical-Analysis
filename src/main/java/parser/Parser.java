package parser;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

import Lexer.Token.Tokens;
import Utils.Helpers;
import Utils.LexemeData;
import parser.Utils.InfixToPostfix;
import parser.Tree.*;

/**
 * Main parser that converts a token stream into abstract syntax trees (ASTs).
 * 
 * This class performs the parsing phase of compilation, converting the sequential
 * list of tokens produced by the lexer into binary expression trees. For each
 * statement (terminated by semicolon or EOF), it:
 * <ol>
 *   <li>Detects assignment statements (identifier = expression)</li>
 *   <li>Converts infix expressions to postfix notation (Shunting Yard algorithm)</li>
 *   <li>Builds a binary tree from the postfix expression</li>
 *   <li>Evaluates the tree and displays results with visualization</li>
 * </ol>
 * 
 * @author Ganesh
 * @version 1.0
 * @see InfixToPostfix
 * @see TreeNode
 * @see Memory
 */
public class Parser {

  /** Collection of parse trees built from all statements */
  private ArrayList<TreeNode> parseTrees = new ArrayList<>();

  /**
   * Parses all tokens and returns the collection of parse trees.
   * 
   * Splits the token stream at SEMICOLON and EOF markers, processing each
   * statement independently. Handles both expressions and assignments.
   * 
   * @param tokenizedData The ArrayList of LexemeData tokens from the lexer
   * @return An ArrayList of TreeNode roots representing parsed expressions
   */
  public ArrayList<TreeNode> parseAll(ArrayList<LexemeData> tokenizedData) {
    ArrayList<LexemeData> lineBuffer = new ArrayList<>();

    for (LexemeData token : tokenizedData) {

      if (token.getToken() == Tokens.SEMICOLON || token.getToken() == Tokens.EOF) {
        processLine(lineBuffer);
        lineBuffer.clear();
      } else {
        lineBuffer.add(token);
      }
    }

    return parseTrees;
  }

  /**
   * Processes a single statement (assignment or expression).
   * 
   * Detects if the statement is an assignment (identifier = expression) and
   * creates an AssignmentNode, otherwise creates a general expression tree.
   * Displays the statement, parse tree, and result to the console.
   * 
   * @param statement The ArrayList of tokens representing one statement
   */
  private void processLine(ArrayList<LexemeData> statement) {
    if (statement.isEmpty()) {
      return;
    }

    TreeNode root;

    if (statement.size() > 1 && statement.get(0).getToken() == Tokens.IDENTIFIER
        && statement.get(1).getToken() == Tokens.ASSIGN_OP) {
      String variableName = statement.get(0).getValue();

      ArrayList<LexemeData> rightSide = new ArrayList<>(statement.subList(2, statement.size()));
      ArrayList<LexemeData> postFix = InfixToPostfix.infixToPostfix(rightSide);
      root = new AssignmentNode(variableName, buildTree(postFix));
    } else {
      ArrayList<LexemeData> postFix = InfixToPostfix.infixToPostfix(statement);
      root = buildTree(postFix);
    }

    System.out.print("For Statement: " + Helpers.getStringFromLexemeData(statement));
    System.out.println("Parse Tree for the above statement: ");
    BinaryTreeVisualizer.print(root);
    System.out.println("Result: " + root.evaluate());

    parseTrees.add(root);
  }

  /**
   * Builds a binary expression tree from a postfix (RPN) expression.
   * 
   * Uses a stack-based algorithm: operands are pushed onto the stack, and when
   * an operator is encountered, it pops two operands, creates an OperationNode,
   * and pushes the result back. The final stack contains one element: the root
   * of the expression tree.
   * 
   * @param postFixData The ArrayList of tokens in postfix notation
   * @return The root TreeNode of the built expression tree
   */
  private TreeNode buildTree(ArrayList<LexemeData> postFixData) {

    Deque<TreeNode> stack = new ArrayDeque<>();

    for (LexemeData token : postFixData) {
      if (token.getToken() == Tokens.INT || token.getToken() == Tokens.FLOAT) {
        double value = Double.parseDouble(token.getValue());
        stack.push(new NumberNode(value));
      } else if (token.getToken() == Tokens.IDENTIFIER) {
        stack.push(new VariableNode(token.getValue()));
      } else {
        TreeNode right = stack.pop();
        TreeNode left = stack.pop();
        stack.push(new OperationNode(token.getValue(), left, right));
      }
    }
    return stack.pop();
  }

}
