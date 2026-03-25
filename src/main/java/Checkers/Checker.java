package Checkers;

import Core.CharacterExtractor;

abstract class Checker {

    protected CharacterExtractor reader;

    public Checker(CharacterExtractor reader){
        this.reader=reader;
    }
}
