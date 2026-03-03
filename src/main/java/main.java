import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class main {

    /**
     * Entry point of the program
     * Get's the file to read and then calls the analysis class for lexical analysis.
     * @param args
     * @throws IOException If the given file doesn't exist the exception is called.
     */
    public static void main(String[] args) throws IOException {

        String pathToFile;

        // Scanner to read the file path form the user in the terminal
        Scanner input = new Scanner(System.in);
        System.out.println("Enter the path to the file: (Absolute path is appreciated) : ");
        pathToFile = input.nextLine();
        // simple static output printer to have a good and claer output
        UI.printHeader();
        // calling the analysiss class for the processing
        LexicalAnalysis analyze = new LexicalAnalysis(new File(pathToFile));
        // to have the pretty output calling the definded footer printer
        UI.printFooter();
    }

}


