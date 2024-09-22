import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car {
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvailable;

    public Car(String carId, String brand, String model, double basePricePerDay) {
        this.carId = carId;
        this.brand = brand;
        this.model = model;
        this.basePricePerDay = basePricePerDay;
        this.isAvailable = true;
    }

    public String getCarId() {
        return carId;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public double calculatePrice(int rentalDays) {
        return basePricePerDay * rentalDays;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void rent() {
        this.isAvailable = false;
    }

    public void returnCar() {
        this.isAvailable = true;
    }
}

class Customer {
    private String customerId;
    private String name;

    public Customer(String customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }
}

class Rental {
    private Car car;
    private Customer customer;
    private int days;

    public Rental(Car car, Customer customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }

    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public int getDays() {
        return days;
    }
}

class CarRentalSystem {
    private List<Car> cars;
    private List<Customer> customers;
    private List<Rental> rentals;

    public CarRentalSystem() {
        cars = new ArrayList<>();
        customers = new ArrayList<>();
        rentals = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void rentCar(Car car, Customer customer, int days) {
        if (car.isAvailable()) {
            car.rent();
            rentals.add(new Rental(car, customer, days));
            System.out.println("Car rented successfully for " + days + " days.");
        } else {
            System.out.println("Car is not available.");
        }
    }

    public List<Car> getAvailableCars() {
        List<Car> availableCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isAvailable()) {
                availableCars.add(car);
            }
        }
        return availableCars;
    }

    public void returnCar(Car car) {
        car.returnCar();
        System.out.println("Car returned successfully!");
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarRentalSystem system = new CarRentalSystem();

        // Getting input from the user to add cars
        System.out.print("How many cars do you want to add? ");
        int carCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < carCount; i++) {
            System.out.println("Enter details for car " + (i + 1) + ":");

            System.out.print("Enter Car ID: ");
            String carId = scanner.nextLine();

            System.out.print("Enter Brand: ");
            String brand = scanner.nextLine();

            System.out.print("Enter Model: ");
            String model = scanner.nextLine();

            System.out.print("Enter Base Price per Day: ");
            double basePricePerDay = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Car car = new Car(carId, brand, model, basePricePerDay);
            system.addCar(car);
            System.out.println("Car added successfully!\n");
        }

        // Adding customer details
        System.out.print("How many customers do you want to add? ");
        int customerCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        for (int i = 0; i < customerCount; i++) {
            System.out.println("Enter details for customer " + (i + 1) + ":");

            System.out.print("Enter Customer ID: ");
            String customerId = scanner.nextLine();

            System.out.print("Enter Name: ");
            String name = scanner.nextLine();

            Customer customer = new Customer(customerId, name);
            system.addCustomer(customer);
            System.out.println("Customer added successfully!\n");
        }

        // Displaying available cars after input
        System.out.println("Available Cars:");
        List<Car> availableCars = system.getAvailableCars();
        for (Car car : availableCars) {
            System.out.println(car.getCarId() + " - " + car.getBrand() + " " + car.getModel() + " ($" + car.calculatePrice(1) + "/day)");
        }

        // Renting a car (example input from user)
        System.out.print("\nDo you want to rent a car? (yes/no): ");
        String rentCarChoice = scanner.nextLine();

        if (rentCarChoice.equalsIgnoreCase("yes")) {
            System.out.print("Enter Car ID to rent: ");
            String carIdToRent = scanner.nextLine();
            Car carToRent = null;
            for (Car car : availableCars) {
                if (car.getCarId().equals(carIdToRent)) {
                    carToRent = car;
                    break;
                }
            }

            if (carToRent != null) {
                System.out.print("Enter Customer ID: ");
                String customerId = scanner.nextLine();
                Customer rentingCustomer = null;
                for (Customer customer : system.getCustomers()) {
                    if (customer.getCustomerId().equals(customerId)) {
                        rentingCustomer = customer;
                        break;
                    }
                }

                if (rentingCustomer != null) {
                    System.out.print("Enter number of rental days: ");
                    int rentalDays = scanner.nextInt();

                    system.rentCar(carToRent, rentingCustomer, rentalDays);
                } else {
                    System.out.println("Invalid Customer ID.");
                }
            } else {
                System.out.println("Invalid Car ID.");
            }
        }

        scanner.close();
    }
}
