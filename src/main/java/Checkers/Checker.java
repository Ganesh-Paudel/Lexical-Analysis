package Checkers;

import Core.CharacterExtractor;

abstract class Checker {

    protected CharacterExtractor reader;
    protected char currentCharacter;

    public Checker(CharacterExtractor reader, char  currentCharacter){
        this.reader=reader;
        this.currentCharacter = currentCharacter;
    }
}
