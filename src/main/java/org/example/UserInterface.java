package org.example;

import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;
    private Scanner scanner = new Scanner(System.in);

    public void display(){
        init();
        int choice;

        do {
            displayMenu();
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice){
                case 1:
                    processGetByPriceRequest();
                    break;

                case 2:
                    processGetByMakeModelRequest();
                    break;

                case 3:
                    processGetByYearRequest();
                    break;

                case 4:
                    processGetByColorRequest();
                    break;

                case 5:
                    processGetByMileageRequest();
                    break;

                case 6:
                    processGetByVehicleTypeRequest();
                    break;

                case 7:
                    processGetAllVehiclesRequest();
                    break;

                case 8:
                    processAddVehicleRequest();
                    break;

                case 9:
                    processRemoveVehicleRequest();
                    break;

                case 99:
                    System.out.println("Thank you for using the dealership app!");
                    break;

                default:
                    System.out.println("Invalid option");
            }
        }
        while (choice != 99);
    }
    private void init(){
        DealershipFileManager fileManager = new DealershipFileManager();
        dealership = fileManager.getDealership();
    }

    private void displayMenu(){
        System.out.println("\n======== DEALERSHIP MENU =========");

        System.out.println("1 - Find vehicles within a price range");
        System.out.println("2 - Find vehicles by make / model");
        System.out.println("3 - Find vehicles by year range");
        System.out.println("4 - Find vehicles by color");
        System.out.println("5 - Find vehicles by mileage range");
        System.out.println("6 - Find vehicles by type (car, truck, SUV, van)");
        System.out.println("7 - List ALL vehicles");
        System.out.println("8 - Add a vehicle");
        System.out.println("9 - Remove a vehicle");
        System.out.println("99 - Quit");

        System.out.println("Enter your choice: ");
    }

    private void displayVehicles(List<Vehicle> vehicles){
        if (vehicles.isEmpty()){
            System.out.println("No vehicle found.");
            return;
        }
        for (Vehicle vehicle : vehicles){
            System.out.println(vehicle);
        }
    }

    public void processGetByPriceRequest(){
        System.out.println("Enter minimum price: ");
        double min = scanner.nextDouble();

        System.out.println("Enter maximum price: ");
        double max = scanner.nextDouble();

        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByPrice(min, max);

        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest(){
        System.out.println("Enter make: ");
        String make = scanner.nextLine();

        System.out.println("Enter model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByMakeModel(make, model);

        displayVehicles(vehicles);
    }

    public void processGetByYearRequest(){
        System.out.println("Enter minimum year: ");
        int min = scanner.nextInt();

        System.out.println("Enter maximum year: ");
        int max = scanner.nextInt();

        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByYear(min, max);

        displayVehicles(vehicles);
    }

    public void processGetByColorRequest(){
        System.out.println("Enter color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByColor(color);

        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest(){
        System.out.println("Enter minimum mileage: ");
        int min = scanner.nextInt();

        System.out.println("Enter maximum mileage: ");
        int max = scanner.nextInt();

        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByMileage(min, max);

        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest(){
        System.out.println("Enter vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehicleByType(type);

        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest(){
        List<Vehicle> vehicles = dealership.getAllVehicle();

        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest(){
        System.out.println("Enter VIN: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter make: ");
        String make = scanner.nextLine();

        System.out.println("Enter model");
        String model = scanner.nextLine();

        System.out.println("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

        System.out.println("Enter color: ");
        String color = scanner.nextLine();

        System.out.println("Enter mileage: ");
        int mileage = scanner.nextInt();

        System.out.println("Enter price: ");
        double price = scanner.nextDouble();

        scanner.nextLine();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, mileage, price);

        dealership.addVehicle(vehicle);

        DealershipFileManager fileManager = new DealershipFileManager();

        System.out.println("Vehicle added successfully");
    }

    public void processRemoveVehicleRequest(){
        System.out.println("Enter Vin of vehicle to remove: ");
        int vin = scanner.nextInt();

        scanner.nextLine();

        Vehicle vehicleToRemove = null;

        for (Vehicle vehicle : dealership.getAllVehicle()){
            if (vehicle.getVin() == vin){
                vehicleToRemove = vehicle;
                break;
            }
        }
        if (vehicleToRemove != null){
            dealership.removeVehicle(vehicleToRemove);

            DealershipFileManager fileManager = new DealershipFileManager();

            fileManager.saveDealership(dealership);

            System.out.println("Vehicle removed successfully.");
        }
        else {
            System.out.println("Vehicle not found.");
        }
    }
}
