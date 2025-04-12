package main;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private double pricePerDay;
    private boolean available;

    // Constructor without ID (for inserting a new car)
    public Car(String brand, String model, int year, double pricePerDay, boolean available) {
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    // Constructor with ID (optional, useful when fetching from DB)
    public Car(int id, String brand, String model, int year, double pricePerDay, boolean available) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    // Optional toString for testing/logging
    @Override
    public String toString() {
        return brand + " " + model + " (" + year + ")";
    }
}
