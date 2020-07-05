package com.company.Menu;

import com.company.Components.Component;
import com.company.Game.GameBoard;
import com.company.Garages.Garage;
import com.company.Player.Player;
import com.company.Transactions.Pay;
import com.company.Transactions.Transaction;
import com.company.Vehicles.Vehicle;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private GameBoard game;
    public Map<Integer, String> optionsDict = new HashMap<Integer, String>();
    ArrayList<String> options = new ArrayList<>();

    public Menu(GameBoard game) {
        this.game = game;
        this.BuildMenuOptions();
    }

    public void PrintMenu() {
        for (Map.Entry<Integer, String> menuEntry : this.optionsDict.entrySet()) {
            System.out.println(menuEntry.getKey()+menuEntry.getValue());
        }
    }

    public void BuildMenuOptions() {
        this.options.add(": List the cars you can buy");
        this.options.add(": Buy a car");
        this.options.add(": List owned cars");
        this.options.add(": Repair the car");
        this.options.add(": View the potential clients");
        this.options.add(": Sell a car to potential client");
        this.options.add(": Buy an advertising");
        this.options.add(": Check your account balance");
        this.options.add(": Check transactions history");
        this.options.add(": Check car repair history");
        this.options.add(": Check how much you spent to fix and clean a car.");
        for (int key = 1; key < 12; key++) {
            this.optionsDict.put(key, this.options.get(key - 1));
        }
    }

    public void getEnterKey() throws IOException {
        System.in.read();
    }

    public Integer getChoice() {
        Integer choice = 0;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("\nChoose what you would like to do:");
                choice = scanner.nextInt();
                scanner.nextLine();
            }
            catch (java.util.InputMismatchException e) {
                System.out.println("Wrong input.");
                scanner.nextLine();
                continue;
            }
            if (choice < 1 || choice > 9) {
                System.out.println("Wrong  input.");
                continue;
            }
            return choice;
        }
    }

    public Boolean selectAction(Integer choice) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = game.getCurrentPlayer();
        Boolean finishTurn = false;
        switch (choice) {
            case 1:
                System.out.println("All available vehicles: \n");
                game.listAllVehicles();
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                finishTurn = false;
                break;
            case 2:
                System.out.println("ID of a vehicle to buy:");
                Vehicle vehToBuy = this.userInputGetFromAllVeh();
                if (vehToBuy == null) {
                    System.out.println("Press enter to continue.");
                    this.getEnterKey();
                    finishTurn = false;
                    break;
                }
                if (currentPlayer.buyVehicle(vehToBuy)) {
                    game.availableVehicles.remove(vehToBuy);
                }
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                finishTurn = true;
                break;
            case 3:
                System.out.println("These are all your cars:");
                System.out.println(currentPlayer.listOwnedCars());
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                finishTurn = false;
                break;
            case 4:
                System.out.println(game.listWorkshops());
                Vehicle thisVeh = this.userInputGetPlayersVeh(currentPlayer);
                if (thisVeh == null) {
                    System.out.println("Failed to get vehicle");
                    finishTurn = false;
                    break;
                }
                Component thisComp = this.userInputGetVehComponent(thisVeh);
                if (thisComp == null) {
                    System.out.println("Failed to get component");
                    finishTurn = false;
                    break;
                }
                Garage thisGar = this.userInputSelectGarage();
                if (thisGar == null) {
                    System.out.println("Failed to get garage");
                    finishTurn = false;
                    break;
                }
                if (thisGar.fixThis(thisComp, thisVeh, currentPlayer)) {
                    System.out.println("Success");
                } else {
                    System.out.println("Failure");
                }
                finishTurn = true;
                break;
            case 5:
                System.out.println("These are potential buyers:");
                System.out.println(game.listCustomers());
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                finishTurn = false;
                break;
            case 6:
                //sellCar2Client
                finishTurn = true;
                break;
            case 7:
                //buyAdvert
                finishTurn = true;
                break;
            case 8:
                System.out.println("Your account balance.");
                System.out.println(game.players.get(game.getCurrentPlayerId()).getMoney());
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                finishTurn = false;
                break;
            case 9:
                //CheckTransHist
                finishTurn = false;
                break;
            case 10:
                //CheckRepairHist
                finishTurn = false;
                break;
            case 11:
                //calculateRepairCleanCost
                finishTurn = false;
                break;
        }
        return finishTurn;

    }

    public Vehicle userInputGetPlayersVeh(Player player) {
        System.out.println("Which car? Provide id.");
        Scanner scanner = new Scanner(System.in);
        return player.getVehicleById(scanner.nextInt());
    }

    public Vehicle userInputGetFromAllVeh() {
        System.out.println("Which car? Provide id.");
        Scanner scanner = new Scanner(System.in);
        return game.getVehicleById(scanner.nextInt());
    }

    public Component userInputGetVehComponent(Vehicle veh) {
        System.out.println("Which component (Body, Brakes, Dampers, Engine or Gearbox)?");
        Scanner scanner = new Scanner(System.in);
        return veh.getComponentByName(scanner.next());
    }

    public Garage userInputSelectGarage() {
        System.out.println("Which workshop [cheap|medium|expensive]?");
        Scanner scanner = new Scanner(System.in);
        return game.getGarageByType(scanner.next());
    }
}
