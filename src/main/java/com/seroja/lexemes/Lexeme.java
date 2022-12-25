package com.seroja.lexemes;
public class Lexeme {
   private LexType type;
   private String value;

    public Lexeme(LexType type, String value) {
        this.type = type;
        this.value = value;
    }

    public Lexeme(LexType type, Character value) {
        this.type = type;
        this.value = value.toString();
    }

    public LexType getType() {
        return type;
    }

    public void setType(LexType type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Lexeme{" +
                "type=" + type +
                ", value='" + value + '\'' +
                '}';
    }

}
