package com.company.Game;

import com.company.Customer.Customer;
import com.company.Vehicles.Car;
import com.company.Vehicles.Motorcycle;
import com.company.Vehicles.Truck;
import com.company.Vehicles.Vehicle;
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
    public static Double startingMoney = 30000.00;
    public int numStarterCars = 12;
    public int numStarterClients = 8;
    public Boolean showStartScreen = true;
    public Boolean gameEnded = false;
    public ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    public ArrayList<Customer> availableCustomers = new ArrayList<>();

    public GameBoard() {
    }
    public void Play() {
        //printMenu()
        //waitForMove()
        //Check for winning conditions
        //Continue()
    }

    public void newGame() throws SQLException {
        System.out.println("Welcome to Car Monopoly. A fantastic game I made to pass the Object Oriented Programming Class.");
        Player.setupPlayers(this, this.numPlayers);
        Vehicle.getVehicle(this, this.numStarterCars);
        Customer.setupCustomers(this, this.numStarterClients);
        //initWorkshops()
        while (!gameEnded) {
            Play();
        }
        //finishGame();
    }
}
