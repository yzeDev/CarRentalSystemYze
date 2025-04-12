package main;

public class rent {
    int id;
    int userId;
    int carId;
    String rentalDate;
    String returnDate;

    // Constructor
    public rent(int id, int userId, int carId, String rentalDate, String returnDate) {
        this.id = id;
        this.userId = userId;
        this.carId = carId;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    // Getters
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

    // Setters
    public void setRentalDate(String rentalDate) {
        this.rentalDate = rentalDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
