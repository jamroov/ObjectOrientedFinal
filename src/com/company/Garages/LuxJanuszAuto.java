package com.company.Garages;

import java.util.HashMap;
import java.util.Map;

public class LuxJanuszAuto extends Garage {
    public Map<String, Double> prices = new HashMap<String, Double>(Garage.priceMap);

    public LuxJanuszAuto(String typeOfGarage, Integer riskOfFailure) {
        this.typeOfGarage = typeOfGarage;
        this.riskOfFailure = riskOfFailure;
        updatePriceMap();
    }
    public void updatePriceMap() {
        for (Map.Entry<String, Double> entry : this.prices.entrySet()) {
            String key = entry.getKey();
            double defaultPriceModifier = 2.5;
            entry.setValue(entry.getValue() * defaultPriceModifier);
        }
    }
}
