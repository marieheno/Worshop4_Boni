package org.example;

import java.util.ArrayList;
import java.util.List;

public class Dealership {

    private String name;
    private String address;
    private String phone;

    private ArrayList<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;

        inventory = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void removeVehicle(Vehicle vehicle){
        inventory.remove(vehicle);
    }

    public List<Vehicle> getAllVehicle(){
        return inventory;
    }

    public List<Vehicle> getVehicleByPrice(double min, double max){
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getPrice() <= max){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehicleByMakeModel(String make, String model){
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getMake().equalsIgnoreCase(make) && vehicle.getModel()
                    .equalsIgnoreCase(model)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehicleByYear(int min, int max){
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getYear() >= min && vehicle.getYear() <= max){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehicleByColor(String color){
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getColor().equalsIgnoreCase(color)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehicleByMileage(int min, int max){
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getOdometer() >= min && vehicle.getOdometer() <= max){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public List<Vehicle> getVehicleByType(String vehicleType){
        List<Vehicle> matchingVehicles = new ArrayList<>();
        for (Vehicle vehicle : inventory){
            if (vehicle.getVehicleType().equalsIgnoreCase(vehicleType)){
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    public void addVehicle(Vehicle vehicle) {
        inventory.add(vehicle);
    }
}
