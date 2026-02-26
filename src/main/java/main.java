import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    public static void main(String[] args) throws IOException {

        String pathToFile;

        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path to the file: (Absolute path is appreciated) : ");
        pathToFile = input.nextLine();
        UI.printHeader();
        LexicalAnalysis analyze = new LexicalAnalysis(new File(pathToFile));
        UI.printFooter();
    }

}


