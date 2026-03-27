package Utils;

import Token.Tokens;

public class LexemeData {
    private String value;
    private Tokens token;

    public LexemeData(String value, Tokens token) {
        this.value = value;
        this.token = token;
    }

    public Tokens getToken() {
        return this.token;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value + " -> " + this.token.getName();
    }
}
