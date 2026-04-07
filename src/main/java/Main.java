import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import Lexer.Core.Lexer;
import Utils.LexemeData;
import parser.Parser;

public class Main {

    public static void main(String[] args) throws IOException {
        Lexer analyze = new Lexer(new File("../../../front.txt"));
        ArrayList<LexemeData> tokenized = analyze.tokenize();
        Parser parse = new Parser(tokenized);
    }

}
