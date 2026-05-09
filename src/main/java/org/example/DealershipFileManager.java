package org.example;

import java.io.*;
import java.util.List;

public class DealershipFileManager {

    public Dealership getDealership(){
        Dealership dealership = null ;

        try{
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String dealershipLine = bufferedReader.readLine();

            String[] dealershipData = dealershipLine.split("\\|");

            String name = dealershipData[0];
            String address = dealershipData[1];
            String phone = dealershipData[2];

            dealership = new Dealership(name, address, phone);

            String line;

            while ((line = bufferedReader.readLine()) != null) {
                String[] csvRow = line.split("\\|");
                int vin = Integer.parseInt(csvRow[0]);
                int year = Integer.parseInt(csvRow[1]);
                String make = csvRow[2];
                String model = csvRow[3];
                String vehicleType = csvRow[4];
                String color = csvRow[5];
                int odometer = Integer.parseInt(csvRow[6]);
                double price = Double.parseDouble(csvRow[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model,
                        vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);

            }
            bufferedReader.close();

        }
        catch (IOException e){
            System.out.println("Error loading dealership.");
        }
        return dealership;
    }

    public static void saveDealership(Dealership dealership){

        try{
            File file = new File("src/main/resources/inventory.csv");
            FileWriter fileWriter = new FileWriter(file, true);

            fileWriter.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());

            List<Vehicle> vehicles = dealership.getAllVehicle();
            for (Vehicle vehicle : vehicles){
                fileWriter.write(System.lineSeparator());

             fileWriter.write(String.format("%d|%d|%s|%s|%s|%s|%d|%.2f", vehicle.getVin(), vehicle.getYear(),
                     vehicle.getMake(), vehicle.getModel(), vehicle.getVehicleType(), vehicle.getColor(),
                     vehicle.getOdometer(),vehicle.getPrice()));
            }
            fileWriter.close();

        }
        catch(IOException ex){
            System.out.println("Error writing to file.");
        }
    }
}
