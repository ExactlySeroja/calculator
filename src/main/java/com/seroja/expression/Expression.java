package com.seroja.expression;

public class Expression {

    String expression;
    int result;

    public Expression(String expression, int result) {
        this.expression = expression;
        this.result = result;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "Expression{" +
                "expression='" + expression + '\'' +
                ", result=" + result +
                '}';
    }
}
