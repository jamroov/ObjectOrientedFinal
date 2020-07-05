package com.company.Transactions;

import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

public class Sell extends Transaction {
    public Sell(Player player, Vehicle vehicle) {
        this.type = "Sell";
        this.player = player;
        this.vehicle = vehicle;
    }
}
