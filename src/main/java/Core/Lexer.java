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
        Helpers.prettyPrint(tokenizedLexemes);
    }

    private void classify() throws IOException {
        this.lexemeList.clear();
        this.getRidOfSpaces();
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
                case Classes.EOF -> {
                    this.nextToken = Tokens.EOF;
                    lexemeList.add('E');
                    lexemeList.add('O');
                    lexemeList.add('F');
                    this.lexemeData = new LexemeData(Helpers.getString(lexemeList), Tokens.EOF);
                    tokenizedLexemes.add(this.lexemeData);
                }
            }
        }
    }

    private void classifyComment() throws IOException {
        this.lexemeData = comments.scan(this.nextChar);
        this.nextToken = this.lexemeData.getToken();
        tokenizedLexemes.add(this.lexemeData);
        getCharacter();
    }

    private void classifyString() throws IOException {
        this.lexemeData = strings.scan(this.nextChar);
        this.nextToken = this.lexemeData.getToken();
        tokenizedLexemes.add(this.lexemeData);
        getCharacter();
    }

    private void classifyLetter() throws IOException {
        this.lexemeData = ident.check(this.nextChar);
        this.nextToken = this.lexemeData.getToken();
        tokenizedLexemes.add(this.lexemeData);
        getCharacter();
    }

    private void classifyDigits() throws IOException {
        this.lexemeData = numbers.check(this.nextChar);
        this.nextToken = this.lexemeData.getToken();
        tokenizedLexemes.add(this.lexemeData);
        getCharacter();
    }

    private void classifyUnknown() throws IOException {

        this.lexemeData = operators.check(this.nextChar);
        this.nextToken = this.lexemeData.getToken();

        if (this.nextToken == Tokens.SUB_OP) {
            this.lexemeData = numbers.check(this.nextChar);
            this.nextToken = this.lexemeData.getToken();
        }

        tokenizedLexemes.add(this.lexemeData);
        getCharacter();
    }

    private void getRidOfSpaces() throws IOException {
        while (this.charClass != Classes.EOF && Conditions.isWhiteSpace(nextChar)) {
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
            this.charClass = Classes.EOF;
        }
    }

}
