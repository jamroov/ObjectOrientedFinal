package com.company.Player;

import com.company.Game.GameBoard;
import com.company.Transactions.Transaction;
import com.company.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Player {
    public int id;
    private String name;
    private ArrayList<Vehicle> Vehicles = new ArrayList<>();
    private Set<Transaction> Transactions = new HashSet<>();
    private Double Money;
    public Integer moveNumber = 0;

    public Player(String name, Double money, int id) {
        this.id = id;
        this.name = name;
        Money = money;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addVehicle(Vehicle vehicle) {
        if (!this.Vehicles.contains(vehicle)) {
            this.Vehicles.add(vehicle);
            System.out.println(vehicle.toString() + " added.");
        }
        else {
            System.out.println(vehicle.toString() + " already in your posession");
        }
    }

    public void subtractMoney(Double money) {
        System.out.println(String.format("Subtracting %.2f from account value %.2f", money, this.Money));
        Money -= money;
        System.out.println(String.format("Current balance: %.2f", Money));
    }

    public String getName() {
        return name;
    }

    public Double getMoney() {
        return Money;
    }

    public static void setupPlayers(GameBoard thisGame, Integer howMany) {
        Scanner scanner = new Scanner(System.in);
        String name;
        for (int i = 0; i < howMany; i++) {
            System.out.println("Your name please:");
            name = scanner.next();
            Player player = new Player(name, GameBoard.startingMoney, i);
            thisGame.players.add(player);
            System.out.println(thisGame.players.get(i).toString());
        }
    }

    public Boolean buyVehicle(Vehicle vehicle) {
        if (vehicle.value <= this.Money) {
            this.addVehicle(vehicle);
            this.subtractMoney(vehicle.value);
            return true;
        }
        else {
            System.out.println("You cannot afford to buy this vehicle.");
            return false;
        }
    }

    public void increaseMoveCount() {
        moveNumber += 1;
    }

    public String listOwnedCars() {
        System.out.println("My cars are:");
        StringBuilder data = new StringBuilder();
        if (this.Vehicles.size() == 0) {
            return "You have no vehicles.";
        }
        for (Vehicle veh : this.Vehicles) {
            data.append(veh.toString());
        }
        return data.toString();
    }

    public String toString() {
        return String.format("Player number: %d. Name: %s. Account balance: %.2f. Move count: %d", this.id, this.name, this.Money, this.moveNumber);
    }
}
