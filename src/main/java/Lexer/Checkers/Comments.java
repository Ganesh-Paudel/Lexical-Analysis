package Lexer.Checkers;

import Lexer.Core.CharacterExtractor;
import Lexer.Token.Tokens;
import Utils.LexemeData;
import Utils.Helpers;

import java.io.IOException;
import java.util.ArrayList;

public class Comments extends Checker {

    public Comments(CharacterExtractor reader) {
        super(reader);
    }

    public LexemeData scan(char currentCharacter) throws IOException {
        ArrayList<Character> lexemeList = new ArrayList<>();
        lexemeList.add(currentCharacter);
        int nextCharacter = reader.peek();

        if (nextCharacter == -1) {
            return new LexemeData(Helpers.getString(lexemeList), Tokens.DIV_OP);
        }

        if (nextCharacter == '/') {
            lexemeList.add((char) reader.getNextCharacter());
            while (true) {
                nextCharacter = reader.getNextCharacter();
                if (nextCharacter == -1 || nextCharacter == '\n') {
                    break;
                }
                lexemeList.add((char) nextCharacter);
            }
            return new LexemeData(Helpers.getString(lexemeList), Tokens.COMMENT);
        } else if (nextCharacter == '*') {
            lexemeList.add((char) reader.getNextCharacter());
            while (true) {
                nextCharacter = reader.getNextCharacter();
                if (nextCharacter == -1) {
                    return new LexemeData(Helpers.getString(lexemeList), Tokens.INVALID_COMMENT);
                }
                if ((char) nextCharacter != '\n') {
                    lexemeList.add((char) nextCharacter);
                } else {
                    lexemeList.add(' ');
                }

                if (nextCharacter == '*') {
                    int slash = reader.peek();
                    if (slash == '/') {
                        lexemeList.add((char) reader.getNextCharacter());
                        break;
                    }
                }
            }
            return new LexemeData(Helpers.getString(lexemeList), Tokens.COMMENT);
        }

        return new LexemeData(Helpers.getString(lexemeList), Tokens.DIV_OP);
    }
}
