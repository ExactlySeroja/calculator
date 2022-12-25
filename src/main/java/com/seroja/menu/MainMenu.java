package com.seroja.menu;

import com.seroja.lexemes.LexBuffer;
import com.seroja.lexemes.Lexeme;
import com.seroja.service.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static com.seroja.lexemes.LexAnalyze.lexAnalyze;
import static com.seroja.service.Parser.expr;

public class MainMenu {
    Connection connection;

    {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/calculator", "root", "01042004");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    Service service = new Service(connection);

    public void startMenu() throws SQLException {
        System.out.println("""
                Choose action:
                 1 - Add new expression
                 2 - View all expressions
                  3 - Update DataBase
                 4 - Search by condition""");
        Scanner scanner = new Scanner(System.in);
        int actionNumber = scanner.nextInt();
        scanner.nextLine();
        switch (actionNumber) {
            case 1 -> {
                System.out.println("Enter the expression");
                String expression = scanner.nextLine();
                List<Lexeme> lexemes = lexAnalyze(expression);
                LexBuffer lexBuffer = new LexBuffer(lexemes);
                service.toDataBase(expression, expr(lexBuffer));
                System.out.println("\nExpression successfully added to DataBase");
            }
            case 2 -> service.getExpressions();
            case 3 -> {
                System.out.println("Choose expression id ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter new expression");
                String newExpression = scanner.nextLine();
                int result = service.getResult(id);
                List<Lexeme> lexemes = lexAnalyze(newExpression);
                LexBuffer lexBuffer = new LexBuffer(lexemes);
                int newResult = expr(lexBuffer);
                if (newResult == result) {
                    service.updateDataBase(newExpression, id);
                } else System.out.println("New expression isn't correct");
            }
            case 4 -> {
                System.out.println("Choose condition:\n 1 - 'is equals(=)'\n 2- 'more than(>)'\n 3- 'less than(<)'\n" +
                        " 4 - 'more or equals (>=)'\n 5 - 'less or equals");
                int conditionType = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter filter number");
                int filterNumber = scanner.nextInt();
                scanner.nextLine();
                service.findByCondition(conditionType, filterNumber);
            }
        }

    }

}
