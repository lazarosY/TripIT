import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class SetupDatabase {

    public static void dbSetup() {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "tripit1234";

        String dbName = "tripit";
        String tableName = "user_credentials";

        String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS " + dbName;
        String useDatabaseSQL = "USE " + dbName;
        String createTableSQL = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
                + "user_id INT AUTO_INCREMENT PRIMARY KEY,"
                + "email VARCHAR(255) UNIQUE NOT NULL,"
                + "salt VARCHAR(255),"
                + "hashed_password VARCHAR(255)"
                + ");";

        try (Connection connection = DriverManager.getConnection(url, user, password);
                Statement statement = connection.createStatement()) {

            statement.executeUpdate(createDatabaseSQL);
            statement.executeUpdate(useDatabaseSQL);
            statement.executeUpdate(createTableSQL);

        } catch (SQLException e) {
            handleException(e);
        }
    }

    private static void handleException(SQLException e) {
        System.err.println("Error: " + e.getMessage());
        System.err.println("SQL State: " + e.getSQLState());
        System.err.println("Error Code: " + e.getErrorCode());
    }
}
