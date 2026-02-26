import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalysis {

    public enum classes{
        LETTER(0),
        DIGIT(9),
        UNKNOWN (99),
        EOF (-1),
        WHITESPACE (1);

        private final int value;
        classes(int value){
            this.value = value;
        }

        public int getValue() {return this.value;}

    }

    public enum tokens{
        INT_LIT(10, "Integer"),
        IDENT(11, "Identifier"),
        ASSIGN_OP(20, "Assignment Operator"),
        ADD_OP(21, "Addition Operator"),
        SUB_OP(22, "Subtraction Operator"),
        MULT_OP(23, "Multiplication Operator"),
        DIV_OP(24, "Division Operator"),
        LEFT_PAREN(25, "Left Parenthesis"),
        RIGHT_PAREN(26, "Right Parenthesis"),
        SEMICOLON(30, "Semi colon"),
        EOF(-1, "End Of File");

        private final int value;
        private final String name;

        tokens(int value, String name) {
            this.value = value;
            this.name = name;
        }
        public int getValue() {
            return this.value;
        }
        public String getName() {
            return this.name;
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
    private String tokenName;
    private BufferedReader reader;

    public LexicalAnalysis(File f) throws IOException{
        reader = new BufferedReader(new FileReader(f));
        getChar();
        do{
            lex();
        } while(nextToken != tokens.EOF.getValue());
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
                charClass = classes.DIGIT.getValue();
            } else if((nextChar >= 'a' && nextChar <= 'z') || (nextChar >= 'A' && nextChar <= 'Z')){
                charClass = classes.LETTER.getValue();
            } else if (Character.isWhitespace(nextChar)) {
                charClass = classes.WHITESPACE.getValue();
            } else {
                charClass = classes.UNKNOWN.getValue();
            }
        } else {
            charClass = classes.EOF.getValue();
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
                while (charClass == classes.LETTER.getValue() || charClass == classes.DIGIT.getValue()) {
                    addChar();
                    getChar();
                }
                setTokenAndName(tokens.IDENT);
                break;
            case 9:
                addChar();
                getChar();
                while ((charClass == classes.DIGIT.getValue() || charClass == classes.LETTER.getValue())) {
                    addChar();
                    getChar();
                }
//                System.out.println("Here after the loop");
                StringBuilder sb = new StringBuilder(lexeme.size());
                for (Character c : lexeme) {
                    sb.append(c);
                }
                String result = sb.toString();
//                System.out.println(result);

                if(result.matches(".*[a-zA-Z].*")){
                    setTokenAndName(tokens.IDENT);
                    System.out.println("Error: Invalid Variable Name");
                    break;
                }else if(!is32Bit(result)){
                    setTokenAndName(tokens.INT_LIT);
                    System.out.println("Error: Not an 32 bit integer.");
                    break;
                }

                setTokenAndName(tokens.INT_LIT);
                break;
            case 99:
                lookUp(nextChar);
                getChar();
                break;
            case -1:
                setTokenAndName(tokens.EOF);
                lexeme.add('E');
                lexeme.add('O');
                lexeme.add('F');
                lexeme.add('0');
                break;
        }

        UI.printNicely(tokenName, lexeme);
//        System.out.println("NextToken is: " + nextToken + ", Next Lexeme is : "+ lexeme.toString());
        return nextToken;
    }

    private int lookUp(char nextChar) {
        switch (nextChar) {
            case '(':
                addChar();
                setTokenAndName(tokens.LEFT_PAREN);
                break;
            case ')':
                addChar();
                setTokenAndName(tokens.RIGHT_PAREN);
                break;
            case '+':
                addChar();
                setTokenAndName(tokens.ADD_OP);
                break;
            case '-':
                addChar();
                setTokenAndName(tokens.SUB_OP);
                break;
            case '*':
                addChar();
                setTokenAndName(tokens.MULT_OP);
                break;
            case '/':
                addChar();
                setTokenAndName(tokens.DIV_OP);
                break;
            case ';':
                addChar();
                setTokenAndName(tokens.SEMICOLON);
                break;
            case '=':
                addChar();
                setTokenAndName(tokens.ASSIGN_OP);
                break;
            default:
                addChar();
                setTokenAndName(tokens.EOF);
                break;
        }
        return nextToken;
    }

    private void setTokenAndName(tokens clazz){

        nextToken = clazz.getValue();
        tokenName = clazz.getName();

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
}



