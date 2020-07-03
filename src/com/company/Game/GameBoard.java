package com.company.Game;

import com.company.Cars.Vehicle;
import com.company.Database.Connector;
import com.company.Player.Player;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard {
    //Select rules
    public int numPlayers = 1;
    public ArrayList<Player> players = new ArrayList<>();
    public Double startingMoney = 30000.00;
    public int numStarterCars = 12;
    public int numStarterClients = 8;
    public Boolean showStartScreen = true;
    public Boolean gameEnded = false;
    public ArrayList<Vehicle> availableVehicles = new ArrayList<>();

    public GameBoard() {
    }
    public void Play() {
        //printMenu()
        //waitForMove()
        //Check for winning conditions
        //Continue()
    }

    public void setupPlayers(Integer howMany) {
        Scanner scanner = new Scanner(System.in);
        String name;
        for (int i = 0; i < howMany; i++) {
            System.out.println("Your name please:");
            name = scanner.next();
            Player player = new Player(name, startingMoney, i+1);
            players.add(player);
            System.out.println(this.players.get(i).toString());
        }
    }

    public void getCars(Integer howMany) throws SQLException {
        Integer allCars = Connector.getNumRows("vehicles");
        Set<Integer> ids = new HashSet<>();
        while (ids.size() != howMany) {
            ids.add(ThreadLocalRandom.current().nextInt(1, allCars));
        }
        for (Integer id : ids) {
            String sql = String.format("Select * from vehicles where vehicles.id = %d", id);
            ResultSet res = Connector.executeQuery(sql);
            res.next();
            String vehType = res.getString("type");
            Double value = res.getDouble("value");
            String make = res.getString("make");
            Integer mileage = res.getInt("mileage");
            String color = res.getString("Color");

            System.out.println("");
        }

        System.out.println("a");
    }
    public void newGame() throws SQLException {
        System.out.println("Welcome to Car Monopoly. A fantastic game I made to pass the Object Oriented Programming Class.");
        setupPlayers(numPlayers);
        getCars(numStarterCars);
        //getClients()
        //initWorkshops()
        while (!gameEnded) {
            Play();
        }
        //finishGame();
    }
}
