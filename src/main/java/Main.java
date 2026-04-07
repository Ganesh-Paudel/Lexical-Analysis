import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import Lexer.Core.Lexer;
import Utils.Helpers;
import Utils.LexemeData;
import parser.Parser;
import parser.Tree.TreeNode;

public class Main {

    public static void main(String[] args) throws IOException {
        Lexer analyze = new Lexer(new File("../../../front.txt"));
        ArrayList<LexemeData> tokenized = analyze.tokenize();
        Parser parse = new Parser();
        ArrayList<TreeNode> parseTrees = parse.parseAll(tokenized);
    }

}
