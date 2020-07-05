package com.company.Transactions;

import com.company.Garages.Garage;
import com.company.Player.Player;
import com.company.Vehicles.Vehicle;

public class Pay extends Transaction {
    public Pay(Player player, Vehicle vehicle) {
        this.type = "Pay";
        this.player = player;
        this.vehicle = vehicle;
    }
}
