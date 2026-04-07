import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import Lexer.Core.Lexer;
import Utils.Helpers;
import Utils.UI;
import parser.Tree.BinaryTreeVisualizer;
import Utils.LexemeData;
import parser.Parser;

public class Main {

    public static void main(String[] args) throws IOException {
        UI.printHeader();
        Lexer analyze = new Lexer(new File("../../../front.txt"));
        ArrayList<LexemeData> tokenized = analyze.tokenize();
        Helpers.prettyPrint(tokenized);

        Parser parse = new Parser(tokenized);

    }

}
