import java.util.ArrayList;
import java.util.Scanner;

public class UI {

    public static void printHeader(String ...values){

        System.out.print("| ");
        for(String value: values){
            System.out.print(value + " |");
        }
        System.out.println();
        System.out.println("-".repeat(20));

    }


    public static void printNicely(int nextToken, ArrayList<Character> lexeme) {
        System.out.print("| " +  nextToken);
        System.out.print(" ".repeat(4) + " | ");
        for(int i = 0; i < lexeme.size(); i++){
            System.out.print(lexeme.get(i));
        }
        System.out.println();
    }
}
