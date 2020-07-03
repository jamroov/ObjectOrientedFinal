package com.company;

import com.company.Database.Connector;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;


public class Main {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        System.out.println("Hello World!");
        Connector.connect();
        Connector.getStatement().execute("select * from vehicles");
        ArrayList<String> Colors = new ArrayList<>();
        ArrayList<String> CarMakers = new ArrayList<>();
        Integer startId = 5;
        Colors.add("Red");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Pink");
        Colors.add("Black");
        Colors.add("Silver");
        Colors.add("Red");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Purple");
        Colors.add("Blue");
        Colors.add("Salmon");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Pink");
        Colors.add("Black");
        Colors.add("Silver");
        Colors.add("Red");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Purple");
        Colors.add("Blue");
        Colors.add("Salmon");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Pink");
        Colors.add("Black");
        Colors.add("Silver");
        Colors.add("Red");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Purple");
        Colors.add("Blue");
        Colors.add("Salmon");
        Colors.add("Gold");
        Colors.add("Gray");
        Colors.add("Teal");
        Colors.add("Violet");
        Colors.add("Orange");
        Colors.add("Yellow");
        Colors.add("Pink");
        Colors.add("Black");
        Colors.add("Silver");
        Colors.add("Red");
        Colors.add("Blue");
        Colors.add("White");
        Colors.add("Green");
        Colors.add("Pink");
        Colors.add("Black");
        Colors.add("Silver");
        CarMakers.add("Fiat");
        CarMakers.add("Kia");
        CarMakers.add("Hyundai");
        CarMakers.add("Fiat");
        CarMakers.add("Mercedes");
        CarMakers.add("Ford");
        CarMakers.add("Peugeot");
        CarMakers.add("Renault");
        CarMakers.add("Fiat");
        CarMakers.add("Kia");
        CarMakers.add("Hyundai");
        CarMakers.add("Fiat");
        CarMakers.add("Mercedes");
        CarMakers.add("Ford");
        CarMakers.add("Peugeot");
        CarMakers.add("Renault");
        CarMakers.add("Fiat");
        CarMakers.add("Kia");
        CarMakers.add("Hyundai");
        CarMakers.add("Chrysler");
        CarMakers.add("GMC");
        CarMakers.add("Jeep");
        CarMakers.add("Zastava");
        CarMakers.add("Tavria");
        CarMakers.add("Skoda");
        CarMakers.add("Audi");
        CarMakers.add("Volkswagen");
        CarMakers.add("Nissan");
        CarMakers.add("Subaru");
        CarMakers.add("Toyota");
        CarMakers.add("Honda");
        CarMakers.add("Fiat");
        CarMakers.add("Mercedes");
        CarMakers.add("Ford");
        CarMakers.add("Peugeot");
        CarMakers.add("Renault");
        CarMakers.add("Fiat");
        CarMakers.add("Kia");
        CarMakers.add("Hyundai");
        CarMakers.add("Fiat");
        CarMakers.add("Chrysler");
        CarMakers.add("GMC");
        CarMakers.add("Jeep");
        CarMakers.add("Zastava");
        CarMakers.add("Tavria");
        CarMakers.add("Skoda");
        CarMakers.add("Audi");
        CarMakers.add("Volkswagen");
        CarMakers.add("Nissan");
        CarMakers.add("Subaru");
        CarMakers.add("Toyota");
        CarMakers.add("Honda");

        Integer mileage;
        Double value;

        String typeOfVehicle = "Car";

        for (String make:CarMakers) {
            for (String color:Colors) {
                mileage = ThreadLocalRandom.current().nextInt(1200, 300000 + 1);
                if (mileage < 10000) {
                    value = ThreadLocalRandom.current().nextDouble(85000.00, 150000.00);
                }
                else if (mileage > 10000 && mileage < 50000) {
                    value = ThreadLocalRandom.current().nextDouble(60000.00, 84999.00);
                }
                else if (mileage > 50000 && mileage < 100000) {
                    value = ThreadLocalRandom.current().nextDouble(35000.00, 59999.00);
                }
                else if (mileage > 100000 && mileage < 200000) {
                    value = ThreadLocalRandom.current().nextDouble(15000.00, 34999.00);
                }
                else if (mileage > 200000) {
                    value = ThreadLocalRandom.current().nextDouble(5000.00, 14999.00);
                }
                else {
                    value = ThreadLocalRandom.current().nextDouble(5000.00, 14999.00);
                }

                String sql = String.format(Locale.US, "insert into vehicles (type, value, make, mileage, color, id) values ('%s', %.2f, '%s', %d, '%s', %d)", typeOfVehicle, value, make, mileage, color, startId);
                Connector.executeSQL(sql);
                startId += 1;
            }
        }
    }
}
