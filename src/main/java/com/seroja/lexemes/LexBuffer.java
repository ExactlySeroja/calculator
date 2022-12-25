package com.seroja.lexemes;

import java.util.List;

public class LexBuffer {

    private int pos;

    public List<Lexeme> lexemes;

    public LexBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next() {
        return lexemes.get(pos++);
    }

    public void back() {
        pos--;
    }

    public int getPos() {
        return pos;
    }

    @Override
    public String toString() {
        return "LexBuffer{" +
                "pos=" + pos +
                ", lexemes=" + lexemes +
                '}';
    }
}
