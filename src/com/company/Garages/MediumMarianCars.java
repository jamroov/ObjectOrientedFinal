package com.company.Garages;

import com.company.Components.Component;
import com.company.Player.Player;
import com.company.Transactions.Pay;
import com.company.Vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class MediumMarianCars extends Garage {
    public Map<String, Double> prices = new HashMap<String, Double>(Garage.priceMap);
    private Double budgetPriceModifier = 1.0;
    private Double standardPriceModifier = 1.2;
    private Double premiumPriceModifier = 1.6;

    public MediumMarianCars(String typeOfGarage, Integer riskOfFailure, String name) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;
        this.name = name;
        updatePriceMap();
    }

    public void updatePriceMap() {
        for (Map.Entry<String, Double> entry : this.prices.entrySet()) {
            String key = entry.getKey();
            double defaultPriceModifier = 1.5;
            entry.setValue(entry.getValue() * defaultPriceModifier);
        }
    }

    public boolean fixThis(Component component, Vehicle vehicle, Player player) {
        Boolean fixFail = false;
        Double priceModifier = this.standardPriceModifier;
        Pay thisTransaction = new Pay(player, vehicle);
        if (vehicle.segment.equals("budget"))
            priceModifier = this.budgetPriceModifier;
        else if (vehicle.segment.equals("premium"))
            priceModifier = this.premiumPriceModifier;
        Double price = this.prices.get(component.type) * priceModifier;
        if (vehicle.type.equals("Motorcycle")) {
            price *= 0.9;
        }
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
        vehicle.repairHistory.add(this.componentRepairInfo(component.type, price));
        player.addTransaction(thisTransaction);
        return true;
    }

    public String getPricing(Vehicle veh) {
        Double totalPrice = 0.0;
        StringBuilder msg = new StringBuilder();
        msg.append("This repair will cost:\n");
        Double priceModifier = this.standardPriceModifier;
        if (veh.segment.equals("budget"))
            priceModifier = this.budgetPriceModifier;
        else if (veh.segment.equals("premium"))
            priceModifier = this.premiumPriceModifier;
        for (Component comp : veh.components) {
            if (comp.damaged) {
                msg.append(String.format("To fix: %s - %.2f.\n", comp.type, this.getPricingForComponent(veh, comp)));
                totalPrice += this.getPricingForComponent(veh, comp);
            }
        }
        msg.append(String.format("Total is: %.2f\n", totalPrice));
        return msg.toString();
    }

    public Double getPricingForComponent(Vehicle vehicle, Component component) {
        Double basePrice = this.prices.get(component.type);
        if (vehicle.segment.equals("budget"))
           basePrice = basePrice * this.budgetPriceModifier;
        else if (vehicle.segment.equals("premium"))
            basePrice = basePrice * this.premiumPriceModifier;
        if (vehicle.type.equals("Motorcycle")) {
            basePrice = basePrice * 0.9;
        }
        return basePrice;
    }
}
