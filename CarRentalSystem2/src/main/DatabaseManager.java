package main;

import java.sql.*;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import javax.swing.*;

public class DatabaseManager {
	Connection conn = null;
    public static Connection getConnection() {
    	
        try {
            // Establish the connection
        	Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:car_rental.db");
            System.out.println("Connected to SQlite successfully!");
            return conn;
        } catch (Exception e) {
            System.out.println("Failed to connect to SQlite: " + e.getMessage());
            return null;
        }
    }
    
    // User login method
    public static int userLogin(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                System.out.println("User login success, ID: " + id); // Debug log
                return id;
            } else {
                System.out.println("No matching user found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1; // login failed
    }



    // Admin login method
    public static boolean adminLogin(String username, String password) {
        String query = "SELECT * FROM admins WHERE username = '" + username + "' AND password = '" + password + "'";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                // Admin found
                System.out.println("Admin login successful!");
                return true;
            } else {
                // No admin found with the given username and password
                System.out.println("Invalid username or password!");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error during admin login: " + e.getMessage());
            return false;
        }
    }

 // Method to query and display all cars
    public static void displayCars() {
        String query = "SELECT * FROM cars";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            System.out.println("=== Available Cars ===");
            while (rs.next()) {
                int id = rs.getInt("id");
                String model = rs.getString("model");
                String brand = rs.getString("brand");
                String status = rs.getString("status");
                double pricePerDay = rs.getDouble("price_per_day");

                System.out.println("ID: " + id + ", Model: " + model + ", Brand: " + brand + 
                                   ", Status: " + status + ", Price per Day: $" + pricePerDay);
            }
        } catch (SQLException e) {
            System.out.println("Error while querying cars: " + e.getMessage());
        }
    }
 // Method to insert a car into the database
    public static void insertCar(String model, String brand, String status, double pricePerDay) {
        String query = "INSERT INTO cars (model, brand, status, price_per_day) VALUES ('" + 
                        model + "', '" + brand + "', '" + status + "', " + pricePerDay + ")";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("Car successfully added!");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting car: " + e.getMessage());
        }
    }
    
 // Method to insert a user into the database
    public static void insertUser(String name, String email, String password) {
        String query = "INSERT INTO users (name, email, password) VALUES ('" + 
                        name + "', '" + email + "', '" + password + "')";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            int rowsAffected = stmt.executeUpdate(query);
            if (rowsAffected > 0) {
                System.out.println("User successfully added!");
            }
        } catch (SQLException e) {
            System.out.println("Error while inserting user: " + e.getMessage());
        }
    }
    // Add car
    public static boolean addCar(Car car) {
        String sql = "INSERT INTO cars (brand, model, year, price_per_day, available) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, car.getBrand());
            stmt.setString(2, car.getModel());
            stmt.setInt(3, car.getYear());
            stmt.setDouble(4, car.getPricePerDay());
            stmt.setBoolean(5, car.isAvailable());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        String sql = "SELECT * FROM rentals";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                int userId = rs.getInt("user_id");
                int carId = rs.getInt("car_id");
                String rentalDate = rs.getString("rental_date");
                String returnDate = rs.getString("return_date");

                Rental rental = new Rental(id, userId, carId, rentalDate, returnDate);
                rentals.add(rental);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rentals;
    }
 
    public static List<Car> getAvailableCars() {
        List<Car> cars = new ArrayList<>();
        String sql = "SELECT * FROM cars WHERE available = TRUE";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Car car = new Car(
                    rs.getInt("id"),
                    rs.getString("brand"),
                    rs.getString("model"),
                    rs.getInt("year"),
                    rs.getDouble("price_per_day"),
                    rs.getBoolean("available")
                );
                cars.add(car);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cars;
    }
    public static boolean rentCar(int userId, int carId, String rentalDate) {
        String insertSql = "INSERT INTO rentals (user_id, car_id, rental_date) VALUES (?, ?, ?)";
        String updateSql = "UPDATE cars SET available = FALSE WHERE id = ?";

        try (Connection conn = getConnection()) {
            conn.setAutoCommit(false);

            try (PreparedStatement insertStmt = conn.prepareStatement(insertSql);
                 PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

                insertStmt.setInt(1, userId);
                insertStmt.setInt(2, carId);
                insertStmt.setString(3, rentalDate);
                insertStmt.executeUpdate();

                updateStmt.setInt(1, carId);
                updateStmt.executeUpdate();

                conn.commit();
                return true;
            } catch (SQLException e) {
                conn.rollback();
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


}
