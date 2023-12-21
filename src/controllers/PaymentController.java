package controllers;

import Domain.Payment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentController extends DatabaseConnectionManager {

    public PaymentController() throws SQLException {super(); }


    // Method to retrieve payments by card number
    public List<Payment> getPaymentsByCardNumber(String cardNumber) {
        List<Payment> payments = new ArrayList<>();

        try {
            String query = "SELECT * FROM Payments WHERE CardNumber = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, cardNumber);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming Payment class has appropriate constructor
                Payment payment = new Payment(
                        resultSet.getString("CardNumber"),
                        resultSet.getString("PaymentType"),
                        resultSet.getInt("FlightID"),
                        resultSet.getDouble("Price")
                        // Add more attributes as needed
                );

                payments.add(payment);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving payments from the database");
            e.printStackTrace();
        }

        return payments;
    }

    public boolean addPayment(Payment payment) {
        boolean isSuccess = false;

        try {
            String query = "INSERT INTO Payments (CardNumber, PaymentType, FlightID, Price) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);

            preparedStatement.setString(1, payment.getCardNumber());
            preparedStatement.setString(2, payment.getPaymentType());
            preparedStatement.setInt(3, payment.getFlightID());
            preparedStatement.setDouble(4, payment.getPrice());

            int rowsInserted = preparedStatement.executeUpdate();
            isSuccess = rowsInserted > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while adding a payment to the database");
            e.printStackTrace();
        }

        return isSuccess;
    }


    public void cancelPayment(Payment payment) {
        try {
            int flightID = payment.getFlightID();


            String query = "DELETE FROM Payments WHERE FlightID = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, flightID);


            preparedStatement.executeUpdate();

            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
