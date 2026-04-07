package Core;

import Checkers.*;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import Utils.LexemeData;
import Token.Tokens;
import Utils.Helpers;
import Token.Classes;

public class Lexer {
    private BaseReader source;
    private Identifier ident;
    private Numbers numbers;
    private SingleOperators operators;
    private Strings strings;
    private Comments comments;

    private ArrayList<LexemeData> tokenizedLexemes = new ArrayList<>();
    private Tokens currentToken;

    public Lexer(File file) throws IOException {
        CharacterExtractor reader = new CharacterExtractor(file);
        this.source = new BaseReader(reader);
        this.ident = new Identifier(reader);
        this.numbers = new Numbers(reader);
        this.operators = new SingleOperators(reader);
        this.strings = new Strings(reader);
        this.comments = new Comments(reader);
    }

    public ArrayList<LexemeData> tokenize() throws IOException {
        do {
            nextLexeme();
        } while (this.currentToken != Tokens.EOF);
        return this.tokenizedLexemes;
    }

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

    private LexemeData handleUnknown(char c) throws IOException {
        LexemeData data = operators.check(c);
        if (data.getToken() == Tokens.SUB_OP) {
            return numbers.check(c);
        }
        return data;
    }

    private void recordToken(LexemeData data) {
        this.currentToken = data.getToken();
        this.tokenizedLexemes.add(data);
    }
}
