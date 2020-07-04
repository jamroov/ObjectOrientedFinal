package com.company.Menu;

import com.company.Game.GameBoard;
import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

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

    public void selectAction(Integer choice) throws IOException {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = game.getCurrentPlayer();
        switch (choice) {
            case 1:
                System.out.println("All available vehicles: \n");
                game.listAllVehicles();
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                break;
            case 2:
                System.out.println("ID of a vehicle to buy:");
                Integer ID = scanner.nextInt();
                Vehicle vehToBuy = game.getVehicleById(ID);
                if (vehToBuy == null) {
                    System.out.println("Press enter to continue.");
                    this.getEnterKey();
                    break;
                }
                if (currentPlayer.buyVehicle(vehToBuy)) {
                    game.availableVehicles.remove(vehToBuy);
                }
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                break;
            case 3:
                System.out.println("These are all your cars:");
                System.out.println(currentPlayer.listOwnedCars());
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                break;
            case 4:
                //carRepair
                break;
            case 5:
                //viewClientelle
                break;
            case 6:
                //sellCar2Client
                break;
            case 7:
                //buyAdvert
                break;
            case 8:
                System.out.println("Your account balance.");
                System.out.println(game.players.get(game.getCurrentPlayerId()).getMoney());
                System.out.println("Press enter to continue.");
                this.getEnterKey();
                break;
            case 9:
                //CheckTransHist
                break;
            case 10:
                //CheckRepairHist
                break;
            case 11:
                //calculateRepairCleanCost
                break;
        }
    }
}
