import java.sql.*;

public class DataBaseConnectivity {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/tripit";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    public static void insertData(String email, String salt, String hashedPassword) {
        if (emailExists(email)) {
            System.out.println("Duplicate email entry. The email entered already exists.");
            return;
        }

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(
                     "INSERT INTO user_credentials (email, salt, hashed_password) VALUES (?, ?, ?)")) {

            preparedStatement.setString(1, email);
            preparedStatement.setString(2, salt);
            preparedStatement.setString(3, hashedPassword);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("User data inserted successfully.");
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
    }

    private static boolean emailExists(String email) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement(
                     "SELECT COUNT(*) FROM user_credentials WHERE email = ?")) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        
        return true;
    }

    public static boolean authentication(String email, String enteredPassword) {
        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement preparedStatement = con.prepareStatement("SELECT salt, hashed_password FROM user_credentials WHERE email = ?")) {

            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String storedSalt = resultSet.getString("salt");
                    String storedHashedPassword = resultSet.getString("hashed_password");
                    return Hash.verifyPassword(enteredPassword, storedSalt, storedHashedPassword);
                }
            }

        } catch (SQLException e) {
            handleSQLException(e);
        }
        return false;
    }


    private static void handleSQLException(SQLException e) {
        System.err.println("Database error occurred. Please contact support.");
        e.printStackTrace();
    }
}
