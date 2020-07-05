package com.company.Player;

import com.company.Customer.Customer;
import com.company.Game.GameBoard;
import com.company.Garages.Garage;
import com.company.Transactions.Buy;
import com.company.Transactions.Pay;
import com.company.Transactions.Sell;
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
        }
    }

    public Boolean buyVehicle(Vehicle vehicle) {
        Buy thisTransaction = new Buy(this, vehicle);
        Double totalPrice = vehicle.value + (vehicle.value * 0.02);
        System.out.println(String.format("Vehicle price is: %.2f, you will also pay tax: %.2f", vehicle.value, vehicle.value * 0.02));
        System.out.println(String.format("For a total price of: %.2f", totalPrice));
        if (totalPrice <= this.Money) {
            this.addVehicle(vehicle);
            this.subtractMoney(totalPrice);
            thisTransaction.setTransaction(true, totalPrice, "Succesfull purchase");
            this.addTransaction(thisTransaction);
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

    public Vehicle getVehicleById(Integer id) {
        for (Vehicle veh : this.Vehicles) {
            if (veh.id.equals(id)) {
                return veh;
            }
        }
        System.out.println("You don't won a vehicle with ID: " + id);
        return null;
    }

    public void addTransaction(Transaction thisTransaction) {
        this.Transactions.add(thisTransaction);
    }

    public String getTransactionHistory() {
        StringBuilder data = new StringBuilder();
        for (Transaction trans : this.Transactions) {
            data.append(trans.toString()).append("\n");
        }
        return data.toString();
    }
}
