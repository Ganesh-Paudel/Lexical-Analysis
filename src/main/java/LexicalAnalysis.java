import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class LexicalAnalysis {

    /**
     * Class to classify the characters into different categories,
     * Given the character, they can be classified into the bottom categories and actions are performed based on that
     * The value for each class is to have a safe check; instead of using the ordinal of enum which can cause errors if the order is changed, using values is best which means change in order won't factor in the value change
     * a getter value to get the value when needed
     */
    public enum classes{
        LETTER(0),
        DIGIT(9),
        UNKNOWN (99),
        EOF (-1),
        WHITESPACE (1);

        private final int value;

        /**
         * Constructor for the enum class
         * @param value a integer value for the given class for comparision
         */
        classes(int value){
            this.value = value;
        }

        /**
         * Getter for the value of classes
         * @return The value for the class that is used to call the function
         */
        public int getValue() {return this.value;}

    }

    /**
     * All the tokens that are being used to define the given input
     * Each token has a value same reason as the classes to have a comparision and a name which is used for the output
     *
     */
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

        // value and name variable declaration
        private final int value;
        private final String name;

        /**
         * constructor for the tokens
         * @param value the numerical value that will be associated with the token and can be used for comparision betn two tokesn
         * @param name the name of the token, should be string and can be accessed using the getter if needed
         */
        tokens(int value, String name) {
            this.value = value;
            this.name = name;
        }
        // Getters for the value and name
        public int getValue() {
            return this.value;
        }
        public String getName() {
            return this.name;
        }

    }

    // Variables:

    private int charClass; // to define the class of the current character, stores the numerical value of that class
    private ArrayList<Character> lexeme = new ArrayList<>(); // this variable is used to get a whole lexeme, arraylist is used since it needs to be dynamic
    private Character nextChar; // stores the nextCharacter in lin
    private int lexLen; // stores the length of the lexeme in the lexeme list
    private int nextToken; // stores the next token with the token value (numerical)
    private String tokenName; // stores the token name, for the output
    private BufferedReader reader; // the reader for the file to get the characters

    /**
     * Constructor for the class, Takes in the file and processes it
     * @param f The file that is to be read for the analysis
     * @throws IOException If the file doesn't exist it throws the exception
     */
    public LexicalAnalysis(File f) throws IOException{
        reader = new BufferedReader(new FileReader(f));
        getChar();
        do{
            lex();
        } while(nextToken != tokens.EOF.getValue());
    }

    /**
     * Adds the current character to the arraylist lexeme
     */
    public void addChar(){
        if(lexLen <= 98) {
            lexeme.add(nextChar);
        } else {
            System.out.println("Error: lexeme");
        }
    }

    /**
     * Gets the next character from the file, It points the nextChar variable from current one to the next one in file
     * This also checks if the value is eof (-1) and assigns the respective class value to charClass variable
     * @throws IOException if the file has any errors it returns an exception
     */
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

    /**
     * If we encounter whiteSpace this function will keep going through the file till we encounter a non whitespace character
     *
     * @throws IOException since it reads from file, any file issues throw io exception
     */
    public void getNonBlank() throws IOException {
        while(Character.isWhitespace(nextChar)){
            getChar();
        }
    }

    /**
     * The main body, after the character is read, and is classified a category,
     * The function checks for the respective class and branches into all the possibilities that it can be in terms of tokens
     * and then AFter checks and validation it returns the token for that character which is displayed in the console
     * @return returns the token value for the current lexeme
     * @throws IOException file issue with the input file
     */
    public int lex() throws IOException {
        // everytime we call this funciton we are dealing with a new lexeem, so we need to clear the previous
        // also the length and whitesapce are skipped
        lexeme.clear();
        lexLen = 0;
        getNonBlank();
        switch (charClass) {
            /**
             * Given the character is a Letter, it can only be a identifier so far,
             * so it will get all the characters and add it to lexeme, calls the funciton {@link #getLexemeWithLetterAndDigits()} which will get the whole lexeme
             * and sets the token for that lexeme to identifier
             */
            case 0:
                addChar();
                getChar();
                getLexemeWithLetterAndDigits();
                setTokenAndName(tokens.IDENT);
                break;

            /**
             * Given the character is a digit, it can be number, or a invalid variable name
             * it will get the whole lexeme using the function {@link #getLexemeWithLetterAndDigits()}
             * and set's the token by calling {@link #isItNumberOrName(String)} which checks for invalid number or variable name and assigns the token
             */
            case 9:
                addChar();
                getChar();
                getLexemeWithLetterAndDigits();

                isItNumberOrName(lexemeToString());
                break;
            /**
             * In case it's not a number or letter, it is assigned unknown class which can be all operators, assignment keywords,
             * This will call {@link #lookUp(char)} to assign the specific token for that
             */
            case 99:
                lookUp(nextChar);
                getChar();
                break;
            /**
             * if we have eof as the class value, then it will simpley set it as the tokne name and
             * update lexeme to have EOF0 as value
             */
            case -1:
                setTokenAndName(tokens.EOF);
                lexeme.add('E');
                lexeme.add('O');
                lexeme.add('F');
                lexeme.add('0');
                break;
        }
        // prints the token and lexeme to the console prettily
        UI.printNicely(tokenName, lexeme);
//        System.out.println("NextToken is: " + nextToken + ", Next Lexeme is : "+ lexeme.toString());

        return nextToken;
    }

    /**
     * This funciton is called when the class of the character is defined as unknown,
     * There are a lot of possibility for that so we have switch where it assigns the tokne
     * @param nextChar The current character that is going to get a token
     * @return returns the tokne value for the character or lexeem if it can be lexeme
     *
     * @throws IOException it deals with getchar() funciton which has exception which is thrown from here as well
     */
    private int lookUp(char nextChar) throws IOException {
        /**
         * All of the following deals with just one character and assigns them,
         * except for the '-' sign which has a possibility for being a negative number.
         */
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
            /**
             * Given the character is '-' if the next character is a digit,
             * then it's a negative value, we get the whole lexeme, and then
             * check for validity for 32 bit integer or invalid identifier
             * then assign token,
             * if the next character is not a digit, then it's just a subtraction operator
             */
            case '-':
                addChar();
                getChar();
                
                if(charClass == classes.DIGIT.getValue()){
                    getLexemeWithLetterAndDigits();
                    isItNumberOrName(lexemeToString());
                } else{
                    setTokenAndName(tokens.SUB_OP);
                }
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

    /**
     * Converts current arraylist lexeme to string
     * This is used when a string is required to comparision or check
     * @return the string version of the lexeme
     */
    private String lexemeToString() {
        StringBuilder sb = new StringBuilder(lexeme.size());
        for (Character c : lexeme) sb.append(c);
        return sb.toString();
    }

    /**
     * Funciton for the setting token and name for the lexeem,
     * it is repetative so using a single funciton and calling for less redundant code
     * @param clazz the token that will be assigned tot he current lexeme
     */
    private void setTokenAndName(tokens clazz){

        nextToken = clazz.getValue();
        tokenName = clazz.getName();

    }

    /**
     * Checks if the given string lexeme is a 32 bit integer or not
     *
     * @param str the string of lexeme which will be compared
     * @return return true for valid integer and false for not valid integer
     */
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

    /**
     * Helper function to check if the lexeme contains letter and based on that assigns the token
     * @param lexeme the lexeme that will be checked
     */
    private void isItNumberOrName(String lexeme){
        boolean hasLetter = false;

        for(char c: lexeme.toCharArray()){
            if(Character.isLetter(c)){
                hasLetter = true;
                break;
            }
        }

        if(hasLetter){
            setTokenAndName(tokens.IDENT);
            System.out.println("Error: Invalid variableName (Cannot start with a number) ");
        } else {
            if(!is32Bit(lexeme)){
                System.out.println("Error: Not a 32 bit integer.");
            }
            setTokenAndName(tokens.INT_LIT);
        }
    }

    /**
     * Helper funciton to reduce the repetative code block down tot just one funciton call
     * @throws IOException {@link #getChar()} is called in the funtion.
     */
    private void getLexemeWithLetterAndDigits() throws IOException {
        while(charClass == classes.DIGIT.getValue() || charClass == classes.LETTER.getValue()){
            addChar();
            getChar();
        }
    }
}



