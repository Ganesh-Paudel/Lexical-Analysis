package Token;

public enum Tokens {
    INT(10),
    FLOAT(11),
    IDENTIFIER(12),
    KEYWORD(13),
    ASSIGN_OP(20),
    ADD_OP(21),
    SUB_OP(22),
    MULT_OP(23),
    DIV_OP(24),
    LEFT_PAREN(25),
    RIGHT_PAREN(26),
    SEMICOLON(30),
    SINGLE_QUOTE(31),
    DOUBLE_QUOTE(32),
    STRING(33),
    EOF(-1);

    private final int value;
    Tokens(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

}
