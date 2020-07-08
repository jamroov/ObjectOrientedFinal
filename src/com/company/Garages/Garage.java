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
    public String name;
    public static Map<String, Double> priceMap = new HashMap<String, Double>();
    private Double budgetPriceModifier;
    private Double standardPriceModifier;
    private Double premiumPriceModifier;

    public abstract String getPricing(Vehicle veh);
    public abstract boolean fixThis(Component component, Vehicle vehicle, Player player);
    public abstract Double getPricingForComponent(Vehicle vehicle, Component component);

    public static void setDefaultPrices(Double body, Double brakes, Double dampers, Double engine, Double gearbox) {
        priceMap.put("Body", body);
        priceMap.put("Brakes", brakes);
        priceMap.put("Dampers", dampers);
        priceMap.put("Engine", engine);
        priceMap.put("Gearbox", gearbox);
    }

    public String componentRepairInfo(String componentType, Double price) {
        return String.format("%s repair: component - %s, price - %.2f", this.name, componentType, price);
    }

    public String toString() {
        return String.format("Garage type: %s, name: %s", this.typeOfGarage, this.name);
    }
}
