package Core;

import Checkers.Identifier;
import Checkers.Numbers;
import Checkers.SingleOperators;
import Token.Classes;
import Utils.Conditions;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Lexer {

    Identifier ident;
    Numbers numbers;
    SingleOperators operators;

    private ArrayList<Character> lexemeList;
    private CharacterExtractor reader;
    private char nextChar;
    private Classes charClass;
    private Classes nextToken;

    public Lexer(File file) throws IOException {
        lexemeList = new ArrayList<>();
        reader = new CharacterExtractor(file);
        run();
    }

    private void run() throws IOException {
        getCharacter();
        do{
            classify();
        } while(this.nextToken != Classes.EOF);
    }


    private void classify() throws IOException{
        this.lexemeList.clear();
        this.getRidOfSpaces();
        switch (this.charClass){
            case Classes.LETTER -> {
                classifyLetter();
            }
            case Classes.DIGIT -> {
                classifyDigits();
            }
            case Classes.UNKNOWN -> {
                classifyUnknown();
            }
            case Classes.EOF-> {
                this.nextToken = Classes.EOF;
                lexemeList.add('E');
                lexemeList.add('O');
                lexemeList.add('F');
            }
        }

    }

    private void classifyLetter() throws IOException{

    }

    private void classifyDigits() throws IOException{

    }

    private void classifyUnknown() throws IOException{

    }

    private void getRidOfSpaces() throws IOException {
        while(Conditions.isWhiteSpace(nextChar)){
            getCharacter();
        }
    }


    private void getCharacter() throws IOException {
        int next = reader.getNextCharacter();
        if(next != -1){
            this.nextChar = (char)next;

            if (Conditions.isDigit(this.nextChar)){
                this.charClass = Classes.DIGIT;
            } else if(Conditions.isLetter(this.nextChar)){
                this.charClass = Classes.LETTER;
            } else if(Conditions.isWhiteSpace(this.nextChar)){
                this.charClass = Classes.WHITESPACE;
            } else {
                this.charClass = Classes.UNKNOWN;
            }
        } else {
            charClass = Classes.UNKNOWN;
        }
    }


}
