package com.company.Game;

import com.company.Customer.Customer;
import com.company.Garages.CheapJanuszPol;
import com.company.Garages.Garage;
import com.company.Garages.LuxJanuszAuto;
import com.company.Garages.MediumMarianCars;
import com.company.Menu.Menu;
import com.company.Vehicles.Vehicle;
import com.company.Player.Player;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GameBoard {
    //Select rules
    public int numPlayers = 1;
    public ArrayList<Player> players = new ArrayList<>();
    public static Double startingMoney = 150000.00;
    public int numStarterCars = 12;
    public int numStarterClients = 8;
    public Boolean showStartScreen = true;
    public static Boolean gameEnded = false;
    public ArrayList<Vehicle> availableVehicles = new ArrayList<>();
    public ArrayList<Customer> availableCustomers = new ArrayList<>();
    public Set<Garage> availableGarages = new HashSet<>();
    private int currentPlayerId;

    public int getCurrentPlayerId() {
        return currentPlayerId;
    }

    public Player getCurrentPlayer() {
        return this.players.get(this.currentPlayerId);
    }

    public GameBoard() {
    }
    public void Play(Menu menu, Integer playerId) throws IOException {
        this.currentPlayerId = playerId;
        menu.PrintMenu();
        Integer choice = menu.getChoice();
        menu.selectAction(choice);
        //printMenu()
        //waitForMove()
        //Check for winning conditions
        //Continue()
    }

    public void newGame() throws SQLException, IOException {
        System.out.println("Welcome to Car Monopoly. A fantastic game I made to pass the Object Oriented Programming Class.");
        Player.setupPlayers(this, this.numPlayers);
        Vehicle.setupVehicles(this, this.numStarterCars);
        Customer.setupCustomers(this, this.numStarterClients);
        this.availableGarages.add(new CheapJanuszPol("Cheap", 5));
        this.availableGarages.add(new MediumMarianCars("Medium", 10));
        this.availableGarages.add(new LuxJanuszAuto("Expensive", 0));
        Menu mainMenu = new Menu(this);
        Integer index = 0;
        while (!gameEnded) {
            Play(mainMenu, this.players.get(index).id);
        }
        //finishGame();
    }

    public Vehicle getVehicleById(Integer id) {
        for (Vehicle veh : this.availableVehicles) {
            if (veh.id.equals(id)) {
                return veh;
            }
        }
        System.out.println(String.format("No vehicle with id: %d", id));
        return null;
    }

    public void listAllVehicles() {
        for (Vehicle vehicle : this.availableVehicles) {
            System.out.println(vehicle.toString());
        }
    }
}
