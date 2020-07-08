package com.company.Transactions;

import com.company.Garages.Garage;
import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

public class Buy extends Transaction {
    public Buy(Player player) {
        this.type = "Buy";
        this.player = player;
    }
}
