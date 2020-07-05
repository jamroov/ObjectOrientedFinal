package com.company.Garages;

import com.company.Components.Body;
import com.company.Vehicles.Vehicle;

import java.util.HashMap;
import java.util.Map;

public class CheapJanuszPol extends Garage {
    public Map<String, Double> prices = new HashMap<String, Double>(Garage.priceMap);

    public static final Integer riskOfTotaling = 50;
    public CheapJanuszPol(String typeOfGarage, Integer riskOfFailure) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;
        updatePriceMap();
    }

    public void updatePriceMap() {
        for (Map.Entry<String, Double> entry : this.prices.entrySet()) {
            String key = entry.getKey();
            double defaultPriceModifier = 0.75;
            entry.setValue(entry.getValue() * defaultPriceModifier);
        }
    }

    public String getPricing(Vehicle veh) {
        return null;
    }
}
