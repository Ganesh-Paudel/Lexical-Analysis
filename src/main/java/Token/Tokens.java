package Token;

public enum Tokens {
    INT(10, "integer"),
    INVALID_INT(9,"invalid integer"),
    FLOAT(11, "float"),
    IDENTIFIER(12, "identifier"),
    KEYWORD(13,"keyword"),
    ASSIGN_OP(20, "="),
    ADD_OP(21, "+"),
    SUB_OP(22, "-"),
    MULT_OP(23, "*"),
    DIV_OP(24,"/"),
    LEFT_PAREN(25, "("),
    RIGHT_PAREN(26, ")"),
    SEMICOLON(30, ";"),
    SINGLE_QUOTE(31, "'"),
    DOUBLE_QUOTE(32, " \" "),
    STRING(33, "String"),
    EOF(-1, "EOF");

    private final int value;
    private final String name;
    Tokens(int value, String name){
        this.value = value;
        this.name = name;
    }


    public int getValue(){
        return this.value;
    }
    
    public String getName(){
        return this.name;
    }

}
