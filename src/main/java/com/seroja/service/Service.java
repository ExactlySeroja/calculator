package com.seroja.service;

import com.seroja.expression.Expression;
import com.seroja.expression.ExpressionMapper;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private final Connection connection;

    public Service(Connection connection) {
        this.connection = connection;
    }

    private void executeUpdate(String sql) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ResultSet executeQuery(String sql) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void toDataBase(String expression, int result) {
        executeUpdate(String.format("insert into expressions(expression, result) values('%s', '%d')", expression, result));
    }

    public List<Expression> getExpressions() throws SQLException {
        System.out.println("Expression list:\n");
        ArrayList<String> expressions = new ArrayList<>();
        ResultSet set = executeQuery("select * from expressions");
        while (set.next()){
            expressions.add(set.getString(2));
        }
        expressions.sort(String::compareToIgnoreCase);
        for (String i: expressions
        ) {
            System.out.println(i);
        }
        System.out.println(" ");
        ExpressionMapper map = new ExpressionMapper();
        return map.mapCooks(set);
    }
    public void updateDataBase(String newExpression, int id) {
        executeUpdate(String.format("update expressions set expression = '%s' where id = '%d'", newExpression, id));
    }

    public void findByCondition(int conditionType, int filterNumber) throws SQLException {
        ResultSet set;
        switch (conditionType) {
            case 1 -> {
                set = executeQuery(String.format("select expression from expressions where result = '%d'", filterNumber));
                while (set.next()) {
                    System.out.println(set.getString(1));
                }
            }
            case 2 -> {
                set = executeQuery(String.format("select expression from expressions where result > '%d'", filterNumber));
                while (set.next()) {
                    System.out.println(set.getString(1));
                }
            }
            case 3 ->{
                set = executeQuery(String.format("select expression from expressions where result < '%d'", filterNumber));
                while (set.next()) {
                    System.out.println(set.getString(1));
                }
            }
            case 4 ->{
                set = executeQuery(String.format("select expression from expressions where result >= '%d'", filterNumber));
                while (set.next()) {
                    System.out.println(set.getString(1));
                }
            }
            case 5 ->{
                set = executeQuery(String.format("select expression from expressions where result <= '%d'", filterNumber));
                while (set.next()) {
                    System.out.println(set.getString(1));
                }
            }
            default -> System.out.println("Wrong condition type!");
        }
    }

    public int getResult(int id) throws SQLException {
        ResultSet set = executeQuery(String.format("select result from expressions where id = '%d'", id));
        int result=0;
        while (set.next()) {
            result = set.getInt(1);
        }
        return result;
    }

    public String getExpression(int id) throws SQLException {
        ResultSet set = executeQuery(String.format("select expression from expressions where id = '%d'", id));
        String expression = "empty";
        return set.getString(expression);
    }

}
