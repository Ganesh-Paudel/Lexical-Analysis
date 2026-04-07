import java.io.IOException;
import java.io.File;
import java.util.ArrayList;

import Lexer.Core.Lexer;
import Utils.Helpers;
import Utils.UI;
import Utils.LexemeData;

public class Main {

    public static void main(String[] args) throws IOException {
        UI.printHeader();
        Lexer analyze = new Lexer(new File("../../../front.txt"));
        ArrayList<LexemeData> tokenized = analyze.tokenize();
        Helpers.prettyPrint(tokenized);

    }

}
