import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import Lexer.Core.Lexer;
import Utils.LexemeData;
import parser.Parser;
import parser.Tree.TreeNode;

/**
 * Main entry point for the Lexical Analysis and Expression Parser application.
 * 
 * This class orchestrates the complete pipeline of lexical analysis and expression parsing:
 * <ol>
 *   <li>Reads source code from "front.txt" file</li>
 *   <li>Tokenizes the input using the Lexer to produce a list of lexemes</li>
 *   <li>Parses the tokens into abstract syntax trees (ASTs)</li>
 *   <li>Evaluates the parsed expressions and displays results with tree visualization</li>
 * </ol>
 * 
 * The application processes mathematical expressions and assignment statements,
 * storing variables in memory and computing results.
 * 
 * @author Ganesh
 * @version 1.0
 */
public class Main {

  /**
   * Main method - entry point for the application.
   * 
   * Executes the complete lexical analysis and parsing pipeline on the input file.
   * 
   * @param args Command line arguments (not used)
   * @throws IOException If file reading or I/O operations fail
   */
  public static void main(String[] args) throws IOException {
    Lexer analyze = new Lexer(new File("./front.txt"));
    ArrayList<LexemeData> tokenized = analyze.tokenize();
    Parser parse = new Parser();
    ArrayList<TreeNode> parseTrees = parse.parseAll(tokenized);
  }

}
