package Checkers;

import Core.CharacterExtractor;
import Token.Tokens;

public class SingleOperators extends Checker{

  public SingleOperators(CharacterExtractor reader){
    super(reader);
  }

  public Tokens check(char currentCharacter){

    switch(currentCharacter){
      case '(' -> {return Tokens.LEFT_PAREN;}
      case ')' -> {return Tokens.RIGHT_PAREN;}
      case '+' -> {return Tokens.ADD_OP;}
      case '-' -> {return Tokens.SUB_OP;}
      case '*' -> {return Tokens.MULT_OP;}
      case '/' -> {return Tokens.DIV_OP;}
      case ';' -> {return Tokens.SEMICOLON;}
      case '=' -> {return Tokens.ASSIGN_OP;}
      case '"' -> {return Tokens.DOUBLE_QUOTE;}
      case '\'' -> {return Tokens.SINGLE_QUOTE;} 
      default -> {return Tokens.EOF;}

    }

  }

}
