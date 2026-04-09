package Lexer.Core;

import Lexer.Checkers.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import Utils.LexemeData;
import Lexer.Token.Tokens;
import Lexer.Token.Classes;

/**
 * Main lexical analyzer that tokenizes input source code.
 * 
 * This class implements the lexical analysis phase of the compiler, converting
 * a stream of characters into a sequence of tokens (lexemes). It orchestrates
 * specialized checker classes that each handle different types of tokens:
 * <ul>
 *   <li>Identifier: Keywords and variable names</li>
 *   <li>Numbers: Integer and floating-point literals</li>
 *   <li>SingleOperators: Arithmetic operators and delimiters</li>
 *   <li>Strings: String and character literals</li>
 *   <li>Comments: Line and block comments</li>
 * </ul>
 * 
 * The lexer processes characters based on their classification and produces
 * a list of LexemeData objects representing the tokens found in the input.
 * 
 * @author Ganesh
 * @version 1.0
 * @see BaseReader
 * @see LexemeData
 * @see Tokens
 */
public class Lexer {
  /** Character source with classification */
  private BaseReader source;
  /** Identifier/keyword checker */
  private Identifier ident;
  /** Number literal parser */
  private Numbers numbers;
  /** Operator and delimiter recognizer */
  private SingleOperators operators;
  /** String and character literal scanner */
  private Strings strings;
  /** Comment parser for line and block comments */
  private Comments comments;

  /** List of tokenized lexemes produced by the analyzer */
  private ArrayList<LexemeData> tokenizedLexemes = new ArrayList<>();
  /** Type of the current token being processed */
  private Tokens currentToken;

  /**
   * Constructs a Lexer for the specified input file.
   * 
   * Initializes the character extractor, base reader, and all specialized checker
   * instances. Does not perform tokenization; call {@link #tokenize()} to process
   * the file.
   * 
   * @param file The source file to tokenize
   * @throws IOException If the file cannot be opened or read
   */
  public Lexer(File file) throws IOException {
    CharacterExtractor reader = new CharacterExtractor(file);
    this.source = new BaseReader(reader);
    this.ident = new Identifier(reader);
    this.numbers = new Numbers(reader);
    this.operators = new SingleOperators(reader);
    this.strings = new Strings(reader);
    this.comments = new Comments(reader);
  }

  /**
   * Tokenizes the input file and returns the complete list of tokens.
   * 
   * Repeatedly processes lexemes until EOF is reached. Tokens are extracted based
   * on character classification and delegated to appropriate checker classes.
   * 
   * @return An ArrayList of LexemeData objects representing all tokens in the file
   * @throws IOException If a read error occurs during tokenization
   */
  public ArrayList<LexemeData> tokenize() throws IOException {
    do {
      nextLexeme();
    } while (this.currentToken != Tokens.EOF);
    return this.tokenizedLexemes;
  }

  /**
   * Processes a single lexeme (token) from the input.
   * 
   * Skips whitespace, then determines token type based on character classification:
   * <ul>
   *   <li>LETTER: Identifier or keyword via {@link Identifier}</li>
   *   <li>DIGIT: Number literal via {@link Numbers}</li>
   *   <li>QUOTES: String/character literal via {@link Strings}</li>
   *   <li>OTHER: Operator/delimiter via {@link SingleOperators}</li>
   * </ul>
   * Special handling for comments (starting with '/') and negative numbers.
   * 
   * @throws IOException If a read error occurs
   */
  private void nextLexeme() throws IOException {
    source.getRidOfSpaces();

    if (source.getCharClass() == Classes.EOF) {
      recordToken(new LexemeData("EOF", Tokens.EOF));
      return;
    }

    char c = source.getNextChar();
    LexemeData data;

    if (c == '/' && (source.getExtractor().peek() == '/' || source.getExtractor().peek() == '*')) {
      data = comments.scan(c);
    } else {
      data = switch (source.getCharClass()) {
        case LETTER -> ident.check(c);
        case DIGIT -> numbers.check(c);
        case QUOTES -> strings.scan(c);
        default -> handleUnknown(c);
      };
    }

    recordToken(data);
    source.getCharacter();
  }

  /**
   * Handles unknown character types that could be operators or negative numbers.
   * 
   * If the checker returns a SUB_OP (minus sign), attempts to parse it as a
   * negative number. Otherwise returns the operator result as-is.
   * 
   * @param c The character to handle
   * @return A LexemeData object with the appropriate token type
   * @throws IOException If a read error occurs
   */
  private LexemeData handleUnknown(char c) throws IOException {
    LexemeData data = operators.check(c);
    if (data.getToken() == Tokens.SUB_OP) {
      return numbers.check(c);
    }
    return data;
  }

  /**
   * Records a token in the tokenized lexemes list and updates the current token.
   * 
   * @param data The LexemeData token to record
   */
  private void recordToken(LexemeData data) {
    this.currentToken = data.getToken();
    this.tokenizedLexemes.add(data);
  }
}
