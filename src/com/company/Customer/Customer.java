package com.company.Customer;

import com.company.Database.Connector;
import com.company.Game.GameBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public class Customer {
    public String name;
    public Double cash;
    public String wantedType;
    public Boolean willBuyDamaged;

    public Customer(String name, Double cash, String wantedType, Boolean willBuyDamaged) {
        this.name = name;
        this.cash = cash;
        this.wantedType = wantedType;
        this.willBuyDamaged = willBuyDamaged;
    }

    public static void setupCustomers(GameBoard thisGame, Integer howMany) throws SQLException {
        Integer allClients = Connector.getNumRows("clients");
        Set<Integer> ids = new HashSet<>();
        while (ids.size() != howMany) {
            ids.add(ThreadLocalRandom.current().nextInt(1, allClients));
        }
        for (Integer id : ids) {
            String sql = String.format("Select * from clients where clients.id = %d", id);
            ResultSet res = Connector.executeQuery(sql);
            res.next();
            String name = res.getString("name");
            Double cash = res.getDouble("cash");
            String wantedType = res.getString("wantedType");
            Boolean buyDamaged = res.getBoolean("willBuyDamaged");

            Customer thisCustomer = new Customer(name, cash, wantedType, buyDamaged);
            thisGame.availableCustomers.add(thisCustomer);
        }
    }

    public String toString() {
        return String.format("I am a potential buyer. My name is %s, I have %.2f$ to spend on a %s. " +
                "It is %b that I will buy a damaged vehicle.", this.name, this.cash, this.wantedType, this.willBuyDamaged);
    }
}
