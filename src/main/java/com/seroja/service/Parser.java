package com.seroja.service;

import com.seroja.lexemes.LexBuffer;
import com.seroja.lexemes.LexType;
import com.seroja.lexemes.Lexeme;

public class Parser {
    public static int expr(LexBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        if (lexeme.getType() == LexType.EOF) {
            return 0;
        } else {
            lexemes.back();
            return plusminus(lexemes);
        }
    }

    public static int plusminus(LexBuffer lexemes) {
        int value = multdiv(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.getType()) {
                case OP_PLUS:
                    value += multdiv(lexemes);
                    break;
                case OP_MINUS:
                    value -= multdiv(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static int multdiv(LexBuffer lexemes) {
        int value = factor(lexemes);
        while (true) {
            Lexeme lexeme = lexemes.next();
            switch (lexeme.getType()) {
                case OP_MUL:
                    value *= factor(lexemes);
                    break;
                case OP_DIV:
                    value /= factor(lexemes);
                    break;
                case EOF:
                case RIGHT_BRACKET:
                case OP_PLUS:
                case OP_MINUS:
                    lexemes.back();
                    return value;
                default:
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getPos());
            }
        }
    }

    public static int factor(LexBuffer lexemes) {
        Lexeme lexeme = lexemes.next();
        switch (lexeme.getType()) {
            case OP_MINUS:
                int value = factor(lexemes);
                return -value;
            case NUMBER:
                return Integer.parseInt(lexeme.getValue());
            case LEFT_BRACKET:
                value = plusminus(lexemes);
                lexeme = lexemes.next();
                if (lexeme.getType() != LexType.RIGHT_BRACKET) {
                    throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                            + " at position: " + lexemes.getPos());
                }
                return value;
            default:
                throw new RuntimeException("Unexpected token: " + lexeme.getValue()
                        + " at position: " + lexemes.getPos());
        }
    }

}
