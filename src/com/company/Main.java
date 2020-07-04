package com.company;

import com.company.Database.Connector;
import com.company.Game.GameBoard;
import com.company.Menu.Menu;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello World!");
        Connector.connect();

        GameBoard thisGame = new GameBoard();
        thisGame.newGame();

    }
}
