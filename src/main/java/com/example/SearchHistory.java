package com.example;

import java.sql.*;
import java.time.LocalDate;

public class SearchHistory {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tripit";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static void saveSearch(int user_id, String destination, LocalDate dep_date, LocalDate arrival_date,
            String trip_preferences, double budget) {

        try (Connection con = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                PreparedStatement preparedStatement = con.prepareStatement(
                        "INSERT INTO user_history (user_id, destination, dep_date, arrival_date, trip_preferences, budget, search_time) VALUES (?, ?, ?, ?, ?, ?, NOW())")) {

            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, destination);
            preparedStatement.setDate(3, java.sql.Date.valueOf(dep_date));
            preparedStatement.setDate(4, java.sql.Date.valueOf(arrival_date));
            preparedStatement.setString(5, trip_preferences);
            preparedStatement.setDouble(6, budget);

            preparedStatement.executeUpdate();

            System.out.println("User history inserted successfully.");

        } catch (SQLException e) {
            handleSQLException(e);
        } catch (Exception e) {
            handleGenericException(e);
        }
    }

    private static void handleSQLException(SQLException e) {
        System.err.println("Database error occurred. Please contact support.");
        e.printStackTrace();
    }

    private static void handleGenericException(Exception e) {
        System.err.println("An unexpected error occurred. Please contact support.");
        e.printStackTrace();
    }
}
