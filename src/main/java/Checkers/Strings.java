package Checkers;

import Core.CharacterExtractor;
import Utils.LexemeData;
import Utils.Helpers;
import java.io.IOException;
import java.util.ArrayList;
import Token.Tokens;


public class Strings extends Checker{
  
  private char startingQuote;

  public Strings(CharacterExtractor reader){
    super(reader);
  }
  

  public LexemeData scan(char currentCharacter) throws IOException{
    ArrayList<Character> lexemelist = new ArrayList<>();
    lexemelist.add(currentCharacter);
    int nextCharacter;

    while(true){
      nextCharacter = reader.peek();
      if(nextCharacter == -1){
        return new LexemeData(Helpers.getString(lexemelist), Tokens.INV_STRING);
      }
      lexemelist.add((char) reader.getNextCharacter());
      if((char) nextCharacter == currentCharacter){
        break;
      }
    }

    

    return new LexemeData(Helpers.getString(lexemelist), Tokens.STRING);
  }

}
