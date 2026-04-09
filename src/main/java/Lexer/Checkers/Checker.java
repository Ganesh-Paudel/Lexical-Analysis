package Lexer.Checkers;

import Lexer.Core.CharacterExtractor;

/**
 * Abstract base class for all token type checkers.
 * 
 * This class serves as the foundation for all specialized lexeme/token detectors
 * in the lexical analyzer. Subclasses implement specific recognition logic for
 * different token categories (identifiers, numbers, strings, etc.).
 * 
 * All checkers have access to the shared CharacterExtractor to read characters
 * and lookahead as needed during token recognition.
 * 
 * @author Ganesh
 * @version 1.0
 * @see Identifier
 * @see Numbers
 * @see SingleOperators
 * @see Strings
 * @see Comments
 */
abstract class Checker {

    /** Shared character source for all checkers */
    protected CharacterExtractor reader;

    /**
     * Constructs a Checker with access to the given CharacterExtractor.
     * 
     * @param reader The shared character source for reading and lookahead
     */
    public Checker(CharacterExtractor reader) {
        this.reader = reader;
    }
}
