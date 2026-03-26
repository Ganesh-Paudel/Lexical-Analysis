package Token;

public enum Classes {

    LETTER(26),
    DIGIT(10),
    QUOTES(2),
    UNKNOWN(0),
    EOF(-1),
    WHITESPACE(1);


    private final int value;

    Classes(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
