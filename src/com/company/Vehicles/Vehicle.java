package com.company.Vehicles;

import com.company.Components.*;
import com.company.Database.Connector;
import com.company.Game.GameBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Vehicle {
    public Integer id;
    public Double value;
    public String make;
    public Integer mileage;
    public String colour;
    public String segment;
    public Set<Component> components = new HashSet<>();

    public String toString() {
        StringBuilder message = new StringBuilder(String.format("ID: %d, Value: %.2f, brand: %s, mileage: %d, color: %s, segment: %s", this.id, this.value, this.make,
                this.mileage, this.colour, this.segment) + "\n");
        message.append("Components:\n");
        for (Component comp : this.components) {
            message.append(comp.toString()).append("\n");
        }
        return message.toString();
    }

    public static void setupVehicles(GameBoard thisGame, Integer howMany) throws SQLException {
        Integer allCars = Connector.getNumRows("allvehicles");
        Set<Integer> ids = new HashSet<>();
        while (ids.size() != howMany) {
            ids.add(ThreadLocalRandom.current().nextInt(1, allCars));
        }
        for (Integer id : ids) {
            String sql = String.format("Select * from allvehicles where allvehicles.id = %d", id);
            ResultSet res = Connector.executeQuery(sql);
            res.next();
            Integer vehId = res.getInt("id");
            String vehType = res.getString("type");
            Double value = res.getDouble("value");
            String make = res.getString("make");
            Integer mileage = res.getInt("mileage");
            String color = res.getString("Color");
            String segment = res.getString("segment");
            Vehicle thisVehicle;
            if (vehType.equals("Car")) {
                thisVehicle = new Car(vehId, value, make, mileage, color, segment);
            }
            else if (vehType.equals("Truck")) {
                thisVehicle = new Truck(vehId, value, make, mileage, color, segment, ThreadLocalRandom.current().nextDouble(1000.00, 2500.00));
            }
            else {
                thisVehicle = new Motorcycle(vehId, value, make, mileage, color, segment);
            }

            //Add components
            Component.addComponents(thisVehicle);
            thisGame.availableVehicles.add(thisVehicle);
        }
    }

    public static void totalThisVehicle(Vehicle veh) {
        for (Component comp : veh.components) {
            comp.health = 0.50;
            comp.damaged = true;
        }
        veh.value = 0.0;
    }
}
