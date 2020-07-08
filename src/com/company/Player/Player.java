package com.company.Player;

import com.company.Components.Component;
import com.company.Customer.Customer;
import com.company.Game.GameBoard;
import com.company.Transactions.Buy;
import com.company.Transactions.Sell;
import com.company.Transactions.Transaction;
import com.company.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    public Integer installmentsLeft = 0;
    public Double installmentRate = 0.0;
    public int id;
    private String name;
    private ArrayList<Vehicle> Vehicles = new ArrayList<>();
    private ArrayList<Transaction> Transactions = new ArrayList<>();

    public ArrayList<Transaction> getTransactions() {
        return Transactions;
    }

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

    public void setupInstallments(Double value) {
        this.installmentsLeft = 10;
        this.installmentRate = value / 10;
    }

    public void getInstallmentMoney() {
        if (this.installmentsLeft > 0) {
            this.installmentsLeft -= 1;
            System.out.println(String.format("Installment no: %d out of 10. Value: %.2f", 10 - this.installmentsLeft, this.installmentRate));
            this.addMoney(this.installmentRate);
        }
    }

    public void subtractMoney(Double money) {
        System.out.println(String.format("Subtracting %.2f from account value %.2f", money, this.Money));
        this.Money -= money;
        System.out.println(String.format("Current balance: %.2f", Money));
    }

    public void addMoney(Double money) {
        System.out.println(String.format("Adding %.2f to account value %.2f", money, this.Money));
        this.Money += money;
        System.out.println(String.format("Current balance: %.2f", this.Money));
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
            System.out.println(String.format("Player number: %d. Your name please:", i+1));
            name = scanner.next();
            Player player = new Player(name, GameBoard.startingMoney, i);
            thisGame.players.add(player);
        }
    }

    public Boolean sellVehicle(Vehicle vehicle, Customer cust) {
        Sell thisTransaction = new Sell(this, vehicle);
        Double sellTax = vehicle.value * 0.02;
        if (!cust.wantedType.equals(vehicle.type)) {
            System.out.println(String.format("Customer doesnt want %s, they want %s", vehicle.type, cust.wantedType));
            return false;
        }
        for (Component comp : vehicle.components) {
            if (comp.damaged && !cust.willBuyDamaged) {
                System.out.println("Customer will not buy a vehicle with damaged parts.");
                return false;
            }
        }
        if (vehicle.value <= cust.cash) {
            this.Vehicles.remove(vehicle);
            thisTransaction.setTransaction(true, vehicle.value + sellTax, "Vehicle sold to " + cust.name);
            this.addTransaction(thisTransaction);
            this.addMoney(vehicle.value - sellTax);
            return true;
        }
        else {
            System.out.println("Customer cannot afford that. They will buy this vehicle in 10 installments");
            this.setupInstallments(vehicle.value);
            this.subtractMoney(sellTax);
            thisTransaction.setTransaction(true, vehicle.value + sellTax, "Vehicle sold in 10 installments to " + cust.name);
            this.Vehicles.remove(vehicle);
            return true;
        }
    }

    public Boolean buyVehicle(Vehicle vehicle) {
        Buy thisTransaction = new Buy(this);
        Double totalPrice = vehicle.value + (vehicle.value * 0.02);
        System.out.println(String.format("Vehicle price is: %.2f, you will also pay tax: %.2f", vehicle.value, vehicle.value * 0.02));
        System.out.println(String.format("For a total price of: %.2f", totalPrice));
        if (totalPrice <= this.Money) {
            this.addVehicle(vehicle);
            this.subtractMoney(totalPrice);
            thisTransaction.setVehicle(vehicle);
            thisTransaction.setTransaction(true, totalPrice, "Successful purchase");
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
        return String.format("Player number: %d. Name: %s. Account balance: %.2f. Move count: %d", this.id+1, this.name, this.Money, this.moveNumber);
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

    public void printVehiclesShort() {
        for (Vehicle veh : this.Vehicles) {
            System.out.println(veh.toStringShort());
        }
    }
}
