package controllers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionManager {

    //private static final String DB_URL = "jdbc:mysql://localhost/AirlineReservationSystem";
    private final String TEST_DB_URL = "jdbc:mysql://localhost/ARS";

    //Usernames and passwords for the database
    private final String TEST_DB_USERNAME = "root";
    private final String TEST_DB_PASSWORD = "root";

    public Connection dbConnection;

    private static DatabaseConnectionManager connectionInstance = null;

    public DatabaseConnectionManager() throws SQLException {

        try {
            dbConnection = DriverManager.getConnection(this.TEST_DB_URL, this.TEST_DB_USERNAME, this.TEST_DB_PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public DatabaseConnectionManager getInstance() {
        if(connectionInstance == null){
            try {
				connectionInstance = new DatabaseConnectionManager();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
        }
        return connectionInstance;
    }


}