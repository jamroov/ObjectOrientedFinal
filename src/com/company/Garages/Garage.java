package com.company.Garages;

import com.company.Components.Component;
import com.company.Player.Player;
import com.company.Transactions.Pay;
import com.company.Transactions.Transaction;
import com.company.Vehicles.Vehicle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Garage {
    public String typeOfGarage; //Either cheap, medium or expensive
    public Integer riskOfFailure; //0, 10%, 20%
    public static Map<String, Double> priceMap = new HashMap<String, Double>();

    public boolean fixThis(Component component, Vehicle vehicle, Player player) {
        Boolean fixFail = false;
        Boolean carTotalled = false;
        Pay thisTransaction = new Pay(player, vehicle);
        if (!vehicle.components.contains(component)) {
            System.out.println("This is not this vehicle's component");
            thisTransaction.setTransaction(false, 0.00, "Incorrect component.");
            player.addTransaction(thisTransaction);
            return false;
        }
        if (!component.damaged) {
            System.out.println(component.type + " is not damaged. No repairs needed.");
            thisTransaction.setTransaction(false, 0.00, "Component repair not required.");
            player.addTransaction(thisTransaction);
            return false;
        }
        Double price = this.priceMap.get(component.type);
        if (player.getMoney() < price) {
            System.out.println(player.getName() + " not enough money for fix");
            thisTransaction.setTransaction(false, 0.00, "Insufficient funds.");
            player.addTransaction(thisTransaction);
            return false;
        }
        //Calculate risk of failure
        if (ThreadLocalRandom.current().nextInt(this.riskOfFailure) == 1) {
            fixFail = true;
            System.out.println("That was not supposed to happen...");
        }
        if (this.getClass() == CheapJanuszPol.class) {
            carTotalled = ThreadLocalRandom.current().nextInt(CheapJanuszPol.riskOfTotaling) == 1;

        }
        if (carTotalled) {
            System.out.println("Oh shit...");
            System.out.println("Sorry, your car is ruined. But you still pay.");
            Vehicle.totalThisVehicle(vehicle);
            thisTransaction.setTransaction(false, price, "Car totalled.");
            player.subtractMoney(price);
            player.addTransaction(thisTransaction);
            return false;
        }
        if (fixFail) {
            System.out.println("Sorry, we failed to fix it. But you still pay");
            player.subtractMoney(price);
            thisTransaction.setTransaction(false, price, "Car totalled.");
            player.addTransaction(thisTransaction);
            return false;
        }
        System.out.println("We will fix that for you.");
        player.subtractMoney(price);
        component.damaged = false;
        component.health = 100.00;
        vehicle.value += vehicle.value * component.priceIncrease;
        thisTransaction.setTransaction(true, price, "Repair successful.");
        player.addTransaction(thisTransaction);
        return true;
    }

    public static void setDefaultPrices(Double body, Double brakes, Double dampers, Double engine, Double gearbox) {
        priceMap.put("Body", body);
        priceMap.put("Brakes", brakes);
        priceMap.put("Dampers", dampers);
        priceMap.put("Engine", engine);
        priceMap.put("Gearbox", gearbox);
    }
}
