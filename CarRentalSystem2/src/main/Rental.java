package main;

public class Rental {
    private int id;
    private int userId;
    private int carId;
    private String rentalDate;
    private String returnDate;

    public Rental(int id, int userId, int carId, String rentalDate, String returnDate) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public int getCarId() {
        return carId;
    }

    public String getRentalDate() {
        return rentalDate;
    }

    public String getReturnDate() {
        return returnDate;
    }
}
