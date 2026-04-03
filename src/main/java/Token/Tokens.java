package Token;

public enum Tokens {
    INT(10, "integer"),
    FLOAT(11, "float"),
    IDENTIFIER(12, "identifier"),
    KEYWORD(13, "keyword"),
    ASSIGN_OP(20, "Assignment Operator"),
    ADD_OP(21, "Addition Operator"),
    SUB_OP(22, "Difference Operator"),
    MULT_OP(23, "Multiplication Operator"),
    DIV_OP(24, "Division Operator"),
    LEFT_PAREN(25, "Left Braces"),
    RIGHT_PAREN(26, "Right Braces"),
    SEMICOLON(30, "SemiColon"),
    SINGLE_QUOTE(31, "Single Quote"),
    DOUBLE_QUOTE(32, "Double Quote"),
    STRING(33, "String"),
    COMMENT(35, "Comments"),
    CHAR(37, "Character"),
    UNKNOWN(-2, "Unknown Token"),
    INVALID_INT(-2, "** Invalid Integer **"),
    INVALID_STRING(-2, "** Invlid String **"),
    INVALID_COMMENT(-2, "** Unclosed Comment **"),
    INVALID_IDENTIFIER(-2, "** Invalid Identifier **"),
    EOF(-1, "EOF");

    private final int value;
    private String name;

    Tokens(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public void setName(String name) {
        if (name != null) {
            this.name = name;
        }
    }

    public int getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

}
