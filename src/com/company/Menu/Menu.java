package com.company.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    public Map<Integer, String> optionsDict = new HashMap<Integer, String>();
    ArrayList<String> options = new ArrayList<>();

    public Menu() {
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

    public void selectAction(Integer choice) {
        switch (choice) {
            case 1:
                //listCars2buy
                break;
            case 2:
                //buyCar
                break;
            case 3:
                //listOwnedCars
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
                //CheckAccBalance
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
