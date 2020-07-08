package com.company.Menu;

import com.company.Advert.Advert;
import com.company.Advert.InternetAd;
import com.company.Advert.LocalPaperAd;
import com.company.Components.Component;
import com.company.Customer.Customer;
import com.company.Game.GameBoard;
import com.company.Garages.Garage;
import com.company.Player.Player;
import com.company.Transactions.Pay;
import com.company.Transactions.Transaction;
import com.company.Vehicles.Vehicle;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

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
        this.options.add(": Check how much you will spend to fix a car.");
        this.options.add(": Skip turn.");
        for (int key = 1; key < 13; key++) {
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
            if (choice < 1 || choice > 13) {
                System.out.println("Wrong  input.");
                continue;
            }
            return choice;
        }
    }

    public Boolean selectAction(Integer choice) throws IOException, SQLException {
        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = game.getCurrentPlayer();
        Vehicle playerVehicle;
        Garage thisGarage;
        Customer thisCustomer;
        Component thisComponent;
        Boolean finishTurn = false;
        switch (choice) {
            case 1:
                System.out.println("All available vehicles: \n");
                game.listAllVehicles();
                System.out.println("Press enter to continue.");
                finishTurn = false;
                break;
            case 2:
                game.printAllVehsShort();
                playerVehicle = this.userInputGetFromAllVeh();
                if (playerVehicle == null) {
                    System.out.println("Press enter to continue.");
                    finishTurn = false;
                    break;
                }
                if (currentPlayer.buyVehicle(playerVehicle)) {
                    game.availableVehicles.remove(playerVehicle);
                    System.out.println("Press enter to continue.");
                    finishTurn = true;
                    break;
                }
                finishTurn = false;
                break;
            case 3:
                System.out.println("These are all your cars:");
                System.out.println(currentPlayer.listOwnedCars());
                System.out.println("Press enter to continue.");
                finishTurn = false;
                break;
            case 4:
                System.out.println(game.listWorkshops());
                currentPlayer.printVehiclesShort();
                playerVehicle = this.userInputGetPlayersVeh(currentPlayer);
                if (playerVehicle == null) {
                    System.out.println("Failed to get vehicle");
                    finishTurn = false;
                    break;
                }
                thisComponent = this.userInputGetVehComponent(playerVehicle);
                if (thisComponent == null) {
                    System.out.println("Failed to get component");
                    finishTurn = false;
                    break;
                }
                for (Garage gar : game.availableGarages) {
                    String msg = String.format("%s, garage type %s, to fix %s will cost %.2f", gar.name, gar.typeOfGarage,
                            thisComponent.type, gar.getPricingForComponent(playerVehicle, thisComponent));
                    System.out.println(msg);
                }
                thisGarage = this.userInputSelectGarage();
                if (thisGarage == null) {
                    System.out.println("Failed to get garage");
                    finishTurn = false;
                    break;
                }
                if (thisGarage.fixThis(thisComponent, playerVehicle, currentPlayer)) {
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
                finishTurn = false;
                break;
            case 6:
                currentPlayer.printVehiclesShort();
                playerVehicle = this.userInputGetPlayersVeh(currentPlayer);
                if (playerVehicle == null) {
                    System.out.println("Failed to get vehicle");
                    finishTurn = false;
                    break;
                }
                System.out.println(game.listCustomers());
                thisCustomer = this.userInputGetCustomer();
                if (thisCustomer == null) {
                    System.out.println("Failed to get customer");
                    finishTurn = false;
                    break;
                }
                finishTurn = currentPlayer.sellVehicle(playerVehicle, thisCustomer);
                break;
            case 7:
                //buyAdvert
                Advert thisAd = userInputGetAd();
                if (thisAd == null) {
                    finishTurn = false;
                    break;
                }
                if (!thisAd.buyAnAd(currentPlayer)) {
                    finishTurn = false;
                    break;
                }
                System.out.println(thisAd.CustomerIncrease + " new customers are available thanks to this campaign.");
                Customer.setupCustomers(game, thisAd.CustomerIncrease);
                finishTurn = true;
                break;
            case 8:
                System.out.println(String.format("Your account balance\n%.2f", currentPlayer.getMoney()));
                System.out.println("Press enter to continue.");
                finishTurn = false;
                break;
            case 9:
                System.out.println(currentPlayer.getTransactionHistory());
                finishTurn = false;
                break;
            case 10:
                //CheckRepairHist
                currentPlayer.printVehiclesShort();
                Vehicle veh = userInputGetPlayersVeh(currentPlayer);
                if (veh == null) {
                    System.out.println("You don't own this vehicle");
                    finishTurn = false;
                    break;
                }
                System.out.println(veh.getRepairHistory());
                finishTurn = false;
                break;
            case 11:
                thisGarage = userInputSelectGarage();
                currentPlayer.printVehiclesShort();
                playerVehicle = userInputGetPlayersVeh(currentPlayer);
                String msg = thisGarage.getPricing(playerVehicle);
                System.out.println(msg);
                finishTurn = false;
                break;
            case 12:
                finishTurn = true;
                break;
        }
        this.getEnterKey();
        return finishTurn;
    }

    public Advert userInputGetAd() {
        System.out.println("What type of ad? Local paper can increase customer count by up to 6 for 5000.\n" +
                "Internet ad only adds one customer for 1000. \n['Local paper' or 'Internet']");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("Local paper")) {
            return new LocalPaperAd();
        }
        else if (choice.equals("Internet")) {
            return new InternetAd();
        }
        else {
            return null;
        }
    }

    public Customer userInputGetCustomer() {
        System.out.println("Which customer? Name or ID please");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        try {
            return game.getCustomerById(Integer.parseInt(choice));
        } catch (NumberFormatException e) {
            return game.getCustomerByName(choice);
        }
    }

    public Vehicle userInputGetPlayersVeh(Player player) {
        System.out.println("Which car? Provide id.");
        Scanner scanner = new Scanner(System.in);
        try {
            return player.getVehicleById(scanner.nextInt());
        }
        catch (InputMismatchException e) {
            return null;
        }

    }

    public Vehicle userInputGetFromAllVeh() {
        System.out.println("Which car? Provide id.");
        Scanner scanner = new Scanner(System.in);
        try {
            return game.getVehicleById(scanner.nextInt());
        }
        catch (InputMismatchException e) {
            return null;
        }

    }

    public Component userInputGetVehComponent(Vehicle veh) {
        System.out.println(veh.toString());
        System.out.println("Which component (Body, Brakes, Dampers, Engine or Gearbox)?");
        Scanner scanner = new Scanner(System.in);
        return veh.getComponentByName(scanner.next());
    }

    public Garage userInputSelectGarage() {
        System.out.println("Which workshop [Cheap|Medium|Expensive]?");
        Scanner scanner = new Scanner(System.in);
        return game.getGarageByType(scanner.next());
    }
}
