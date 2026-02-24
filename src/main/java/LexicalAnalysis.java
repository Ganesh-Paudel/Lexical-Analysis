import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalysis {

    public enum classes{
        LETTER,
        DIGIT,
        UNKNOWN,
        EOF,
        WHITESPACE
    }

    public enum tokens{
        INT_LIT(10),
        IDENT(11),
        ASSIGN_OP(20),
        ADD_OP(21),
        SUB_OP(22),
        MULT_OP(23),
        DIV_OP(24),
        LEFT_PAREN(25),
        RIGHT_PAREN(26),
        SEMICOLON(30),
        EOF(-1);

        private final int value;
        tokens(int value) {
            this.value = value;
        }
        public int getValue() {
            return this.value;
        }
    }

    /*
    Variables:
     */

    private int charClass;
    private ArrayList<Character> lexeme = new ArrayList<>();
    private Character nextChar;
    private int lexLen;
    private int token;
    private int nextToken;
    private BufferedReader reader;

    public LexicalAnalysis(File f) throws IOException{
        reader = new BufferedReader(new FileReader("C:\\Users\\paude\\Desktop\\Projects\\front.txt"));
        getChar();
        int i = -2;
        do{
            i++;
            lex();
        } while(nextToken != classes.EOF.ordinal());
    }

    public void addChar(){
        if(lexLen <= 98) {
            lexeme.add(nextChar);
        } else {
            System.out.println("Error: lexeme");
        }
    }
    public void getChar() throws IOException {
        int next = reader.read();
        if(next != -1){
            nextChar = (char) next;
            if(Character.isDigit(nextChar)){
                charClass = classes.DIGIT.ordinal();
            } else if(Character.isAlphabetic(nextChar)){
                charClass = classes.LETTER.ordinal();
            } else if (Character.isWhitespace(nextChar)) {
                charClass = classes.WHITESPACE.ordinal();
            } else {
                charClass = classes.UNKNOWN.ordinal();
            }
        } else {
            charClass = classes.EOF.ordinal();
        }

    }
    public void getNonBlank() throws IOException {
        while(Character.isWhitespace(nextChar)){
            getChar();
        }
    }
    public int lex() throws IOException {
        lexeme.clear();
        lexLen = 0;
        getNonBlank();
        switch (charClass) {

            case 0:
                addChar();
                getChar();
                while (charClass == classes.LETTER.ordinal() || charClass == classes.DIGIT.ordinal()) {
                    addChar();
                    getChar();
                }
                nextToken = tokens.IDENT.getValue();
                break;
            case 1:
                addChar();
                getChar();
                while ((charClass == classes.DIGIT.ordinal() || charClass == classes.LETTER.ordinal())) {
                    addChar();
                    getChar();
                }
                StringBuilder sb = new StringBuilder(lexeme.size());
                for (Character c : lexeme) {
                    sb.append(c);
                }
                String result = sb.toString();

                if(!is32Bit(result)){
                    System.out.println("Error: Not an 32 bit integer.");
                }

                nextToken = tokens.INT_LIT.getValue();
                break;
            case 2:
                lookUp(nextChar);
                getChar();
                break;
            case 3:
                nextToken = classes.EOF.ordinal();
                lexeme.add('E');
                lexeme.add('O');
                lexeme.add('F');
                lexeme.add('0');
                break;
        }

        UI.printNicely(nextToken, lexeme);
//        System.out.println("NextToken is: " + nextToken + ", Next Lexeme is : "+ lexeme.toString());
        return nextToken;
    }

    private int lookUp(char nextChar) {
        switch (nextChar) {
            case '(':
                addChar();
                nextToken = tokens.LEFT_PAREN.getValue();
                break;
            case ')':
                addChar();
                nextToken = tokens.RIGHT_PAREN.getValue();
                break;
            case '+':
                addChar();
                nextToken = tokens.ADD_OP.getValue();
                break;
            case '-':
                addChar();
                nextToken = tokens.SUB_OP.getValue();
                break;
            case '*':
                addChar();
                nextToken = tokens.MULT_OP.getValue();
                break;
            case '/':
                addChar();
                nextToken = tokens.DIV_OP.getValue();
                break;
            case ';':
                addChar();
                nextToken = tokens.SEMICOLON.getValue();
                break;
            default:
                addChar();
                nextToken = tokens.EOF.getValue();
                break;
        }
        return nextToken;
    }

    private boolean is32Bit(String str) {
        try{
        if (str == null || str.length() > 11){
            return false;
        }
        long val = Long.parseLong(str);
        return val >= Integer.MIN_VALUE && val <= Integer.MAX_VALUE;

        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidVariable(String str){
        for(int i = 0; i < str.length(); i++){
            if(i == 0 && Character.isDigit(str.charAt(i))){
                return false;
            }
            if(!Character.isAlphabetic(str.charAt(i)) || !Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }
    private boolean convertLong(String str){
        try{
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}



