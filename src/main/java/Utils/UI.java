package Utils;

import java.util.ArrayList;

public class UI {
    //
    // public static void printHeader(String... values) {
    //
    // System.out.print("| ");
    // for (String value : values) {
    // System.out.print(value + " |");
    // }
    // System.out.println();
    // System.out.println("-".repeat(20));
    //
    // }
    //
    // public static void printNicely(String nextToken, ArrayList<Character> lexeme)
    // {
    // System.out.print("| " + nextToken);
    // System.out.print(" ".repeat(4) + " | ");
    // for(int i = 0; i < lexeme.size(); i++){
    // System.out.print(lexeme.get(i));
    // }
    // System.out.println();
    // }

    // public static void printNicely(int nextToken, ArrayList<Character> lexeme) {
    // System.out.print("| " + nextToken);
    // System.out.print(" ".repeat(4) + " | ");
    // for (int i = 0; i < lexeme.size(); i++) {
    // System.out.print(lexeme.get(i));
    // }
    // System.out.println();
    // }
    //
    public static void printHeader() {
        System.out.println("+---------------------------+---------------------------+");
        System.out.println("| TOKEN TYPE                | LEXEME                    |");
        System.out.println("+---------------------------+---------------------------+");
    }

    public static void printNicely(String nextToken, String lexeme) {
        System.out.printf("| %-25s | %-25s |%n", nextToken, lexeme);
    }

    public static void printFooter() {
        System.out.println("+---------------------------+---------------------------+");
    }

}
