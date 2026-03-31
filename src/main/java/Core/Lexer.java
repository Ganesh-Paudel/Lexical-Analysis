package Core;

import Checkers.Identifier;
import Checkers.Numbers;
import Checkers.SingleOperators;
import Checkers.Strings;
import Checkers.Comments;
import Token.Classes;
import Token.Tokens;
import Utils.Conditions;
import Utils.LexemeData;
import Utils.Helpers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Lexer {

    Identifier ident;
    Numbers numbers;
    SingleOperators operators;
    Strings strings;
    Comments comments;
    ArrayList<LexemeData> tokenizedLexemes;

    private ArrayList<Character> lexemeList;
    private CharacterExtractor reader;
    private char nextChar;
    private Classes charClass;
    private Tokens nextToken;
    private LexemeData lexemeData;

    public Lexer(File file) throws IOException {
        lexemeList = new ArrayList<>();
        tokenizedLexemes = new ArrayList<>();
        reader = new CharacterExtractor(file);
        ident = new Identifier(reader);
        numbers = new Numbers(reader);
        operators = new SingleOperators(reader);
        strings = new Strings(reader);
        comments = new Comments(reader);
        run();
    }

    private void run() throws IOException {
        getCharacter();
        do {
            classify();
        } while (this.nextToken != Tokens.EOF);
        Helpers.prettyPrint(this.tokenizedLexemes);
    }

    private void classify() throws IOException {
        this.lexemeList.clear();
        this.getRidOfSpaces();
        if (this.charClass == Classes.EOF) {
            this.nextToken = Tokens.EOF;
            lexemeList.add('E');
            lexemeList.add('O');
            lexemeList.add('F');
            this.lexemeData = new LexemeData(Helpers.getString(lexemeList), Tokens.EOF);
            tokenizedLexemes.add(this.lexemeData);
            return;
        }
        if (nextChar == '/' && (reader.peek() == '/' || reader.peek() == '*')) {
            classifyComment();
        } else {
            switch (this.charClass) {
                case Classes.LETTER -> {
                    classifyLetter();
                }
                case Classes.DIGIT -> {
                    classifyDigits();
                }
                case Classes.QUOTES -> {
                    classifyString();
                }
                case Classes.UNKNOWN -> {
                    classifyUnknown();
                }
            }
        }
    }

    private void classifyComment() throws IOException {
        classifyCurrentLexeme(comments.scan(this.nextChar));
        getCharacter();
    }

    private void classifyString() throws IOException {
        classifyCurrentLexeme(strings.scan(this.nextChar));
        getCharacter();
    }

    private void classifyLetter() throws IOException {
        classifyCurrentLexeme(ident.check(this.nextChar));
        getCharacter();
    }

    private void classifyDigits() throws IOException {
        classifyCurrentLexeme(numbers.check(this.nextChar));
        getCharacter();
    }

    private void classifyUnknown() throws IOException {
        LexemeData data = operators.check(this.nextChar);
        if (data.getToken() == Tokens.SUB_OP) {
            data = numbers.check(this.nextChar);
        }
        classifyCurrentLexeme(data);
        getCharacter();
    }

    private void classifyCurrentLexeme(LexemeData data) throws IOException {
        this.lexemeData = data;
        this.nextToken = data.getToken();
        this.tokenizedLexemes.add(this.lexemeData);
    }

    private void getRidOfSpaces() throws IOException {
        while (Conditions.isWhiteSpace(nextChar)) {
            getCharacter();
        }
    }

    private void getCharacter() throws IOException {
        int next = reader.getNextCharacter();
        if (next != -1) {
            this.nextChar = (char) next;

            if (Conditions.isDigit(this.nextChar)) {
                this.charClass = Classes.DIGIT;
            } else if (Conditions.isLetter(this.nextChar)) {
                this.charClass = Classes.LETTER;
            } else if (Conditions.isWhiteSpace(this.nextChar)) {
                this.charClass = Classes.WHITESPACE;
            } else if (Conditions.isQuote(this.nextChar)) {
                this.charClass = Classes.QUOTES;
            } else {
                this.charClass = Classes.UNKNOWN;
            }
        } else {
            this.nextChar = '\0';
            charClass = Classes.EOF;
        }
    }

}
