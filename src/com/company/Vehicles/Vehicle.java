package com.company.Vehicles;

import com.company.Database.Connector;
import com.company.Game.GameBoard;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Vehicle {
    public Double value;
    public String make;
    public Integer mileage;
    public String colour;
    public String segment;

    public String toString() {
        return String.format("Value: %.2f, brand: %s, mileage: %d, color: %s, segment: %s", this.value, this.make,
                this.mileage, this.colour, this.segment);
    }

    public static void getVehicle(GameBoard thisGame, Integer howMany) throws SQLException {
        Integer allCars = Connector.getNumRows("allvehicles");
        Set<Integer> ids = new HashSet<>();
        while (ids.size() != howMany) {
            ids.add(ThreadLocalRandom.current().nextInt(1, allCars));
        }
        for (Integer id : ids) {
            String sql = String.format("Select * from allvehicles where allvehicles.id = %d", id);
            ResultSet res = Connector.executeQuery(sql);
            res.next();
            String vehType = res.getString("type");
            Double value = res.getDouble("value");
            String make = res.getString("make");
            Integer mileage = res.getInt("mileage");
            String color = res.getString("Color");
            String segment = res.getString("segment");
            Vehicle thisVehicle;
            if (vehType.equals("Car")) {
                thisVehicle = new Car(value, make, mileage, color, segment);
            }
            else if (vehType.equals("Truck")) {
                thisVehicle = new Truck(value, make, mileage, color, segment, ThreadLocalRandom.current().nextDouble(1000.00, 2500.00));
            }
            else {
                thisVehicle = new Motorcycle(value, make, mileage, color, segment);
            }
            thisGame.availableVehicles.add(thisVehicle);
            System.out.println(thisVehicle.toString());
        }
    }
}
