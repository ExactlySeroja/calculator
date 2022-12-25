package com.seroja;

import com.seroja.menu.MainMenu;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        MainMenu menu = new MainMenu();
        menu.startMenu();
    }
}
