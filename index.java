import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car
{
    private String carId;
    private String brand;
    private String model;
    private double basePricePerDay;
    private boolean isAvilable;

    public Car (String carId, String brand, String model, double basePricePerDay)
    {
        this.carId=carId;
        this.brand=brand;
        this.model=model;
        this.basePricePerDay=basePricePerDay;
        this.isAvilable=true;
    }


    public String getcarId()
    {
        return carId;

    }

    public String getBrand()
    {
        return brand;
    }

    public String getModel()
    {
        return model;
    }
    public double calculatePrice(int rentalDays)
    {
        return basePricePerDay * rentalDays;
    }
    public boolean isAvilable()
    {
        return isAvilable;
    }

  
    
}
class Customer
{
    private String customerId;
    private String name;    

    public String getCoustomerId()
    {
        return customerId;
    }

    public String getName()
    {
        return name;
    }
}
class Rental 
{
    private Car car;
    private Customer customer; 
    private int days;

    // Constructor for Rental
    public Rental(Car car, Customer customer, int days) 
    {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }
}    



class CarRentalSystem
{
    private List<Car> cars;
    private List<Rental> rentals;

    public CarRentalSystem()
    {
        cars=new ArrayList<>();
        customers =new ArrayList<>();
        rentals =new ArrayList<>();

    }

    public void addCar(Car car)
    {
        cars.add(car);
    }

    public void addCustomer(Customer coustomer)
    {
        coustomer.add(coustomer);
    }

    public void rentCar(Car car, Coustomer coustomer, int days)
    {
        if(Car.isAvilable())
        {
            car.rent();
            rentals.add(new Rental)
        }
    }

}