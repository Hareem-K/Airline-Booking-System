package controllers;

import Domain.CreditCard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CreditCardController extends DatabaseConnectionManager {

    public CreditCardController() throws SQLException {
        super();
    }

    public boolean addCreditCard(String cardholderName, String cardNumber, String expiryDate) {
        boolean isSuccess = false;

        try {
            String queryCheck = "SELECT CardNumber FROM CreditCard WHERE CardNumber = ?";
            PreparedStatement checkStatement = dbConnection.prepareStatement(queryCheck);
            checkStatement.setString(1, cardNumber);
            ResultSet resultSet = checkStatement.executeQuery();

            if (!resultSet.next()) {
                String query = "INSERT INTO CreditCard (CardholderName, CardNumber, ExpiryDate) VALUES (?, ?, ?)";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
                preparedStatement.setString(1, cardholderName);
                preparedStatement.setString(2, cardNumber);
                preparedStatement.setString(3, expiryDate);

                int rowsInserted = preparedStatement.executeUpdate();
                isSuccess = rowsInserted > 0;

                preparedStatement.close();
            } else {
                // Card number already exists
                isSuccess = true; // Consider it a success as it didn't perform a new insertion
            }

            checkStatement.close();
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while adding a credit card to the database");
            e.printStackTrace();
        }
        return isSuccess;
    }

    public CreditCard getCreditCard(String username) {
        CreditCard creditCard = null;
        try {
            String query = "SELECT * FROM CreditCard WHERE CardholderName = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, username);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String cardholderName = resultSet.getString("CardholderName");
                String cardNumber = resultSet.getString("CardNumber");
                String expiryDate = resultSet.getString("ExpiryDate");

                // Create the CreditCard object
                creditCard = new CreditCard(cardNumber, cardholderName, expiryDate);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving the credit card information for user: " + username);
            e.printStackTrace();
        }
        return creditCard;
    }


}