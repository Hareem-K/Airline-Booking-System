package controllers;
import Domain.RegisteredUsers;
import Domain.CreditCard;
import Domain.Payment;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegisteredUsersController extends DatabaseConnectionManager {

    public RegisteredUsersController() throws SQLException {
        super();
    }

    public List<RegisteredUsers> getAllRegisteredUsers() {
        List<RegisteredUsers> registeredUsers = new ArrayList<>();

        try {
            String query = "SELECT R.FullName, R.Password, R.Email, C.CardNumber, C.ExpiryDate, C.CardholderName " +
                    "FROM RegisteredUsers R LEFT JOIN CreditCard C " +
                    "ON R.FullName = C.CardholderName";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String cardNumber = resultSet.getString("CardNumber");
                String expiryDate = resultSet.getString("ExpiryDate");
                String cardholderName = resultSet.getString("CardholderName");

                // Assuming RegisteredUser class has appropriate constructor
                RegisteredUsers user;

                if (cardNumber != null && expiryDate != null && cardholderName != null) {
                    user = new RegisteredUsers(
                            resultSet.getString("FullName"),
                            resultSet.getString("Password"),
                            resultSet.getString("Email"),
                            new CreditCard(cardNumber, expiryDate, cardholderName)
                            // Add more attributes as needed
                    );
                } else {
                    user = new RegisteredUsers(
                            resultSet.getString("FullName"),
                            resultSet.getString("Password"),
                            resultSet.getString("Email"),
                            null
                            // Add more attributes as needed
                    );
                }

                registeredUsers.add(user);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving registered users from the database");
            e.printStackTrace();
        }

        return registeredUsers;
    }

    public boolean validateRegisteredUser(String email, String password) {
        boolean isValid = false;

        try {
            String query = "SELECT * FROM RegisteredUsers WHERE Email = ? AND Password = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();

            isValid = resultSet.next(); // If the resultSet has at least one row, the user is validated

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while validating registered user in the database");
            e.printStackTrace();
        }

        return isValid;
    }

    public boolean addRegisteredUser(String fullName, String email, String password) {
        boolean isSuccess = false;

        try {
            String query = "INSERT INTO RegisteredUsers (FullName, Email, Password) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, fullName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);

            int rowsInserted = preparedStatement.executeUpdate();
            isSuccess = rowsInserted > 0;

            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while adding a registered user to the database");
            e.printStackTrace();
        }
        return isSuccess;
    }

    public RegisteredUsers getRegisteredUserByEmail(String email) {
        RegisteredUsers user = null;

        try {
            String query = "SELECT R.FullName, R.Password, R.Email, C.CardNumber, C.ExpiryDate, C.CardholderName, P.PaymentType, P.FlightID, P.Price " +
                    "FROM RegisteredUsers R LEFT JOIN CreditCard C " +
                    "ON R.FullName = C.CardholderName " +
                    "LEFT JOIN Payments P " +
                    "ON C.CardNumber = P.CardNumber " +
                    "WHERE R.Email = ?";

            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                String cardNumber = resultSet.getString("CardNumber");
                String expiryDate = resultSet.getString("ExpiryDate");
                String cardholderName = resultSet.getString("CardholderName");

                if (cardNumber != null && expiryDate != null && cardholderName != null) {
                    user = new RegisteredUsers(
                            resultSet.getString("FullName"),
                            resultSet.getString("Password"),
                            resultSet.getString("Email"),
                            new CreditCard(cardNumber, expiryDate, cardholderName)
                    );

                    // Add payments to the CreditCard
                    CreditCard creditCard = user.getCreditCard();
                    String paymentType = resultSet.getString("PaymentType");

                    if (paymentType != null) {
                        Payment payment = new Payment(
                                cardNumber,
                                paymentType,
                                resultSet.getInt("FlightID"),
                                resultSet.getDouble("Price")
                        );
                        creditCard.addPayment(payment);
                    }

                } else {
                    user = new RegisteredUsers(
                            resultSet.getString("FullName"),
                            resultSet.getString("Password"),
                            resultSet.getString("Email"),
                            null
                    );
                }
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving the registered user from the database");
            e.printStackTrace();
        }

        return user;
    }

}
