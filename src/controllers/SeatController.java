package controllers;
import Domain.Seat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SeatController extends DatabaseConnectionManager{


    public SeatController() throws SQLException {
        super();
    }

    public List<Seat> getAllSeats() {
        List<Seat> seats = new ArrayList<>();

        try {
            String query = "SELECT * FROM Seats";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming Seat class has appropriate constructor
                Seat seat = new Seat(
                        resultSet.getInt("FlightNumber"),
                        resultSet.getString("SeatID"),
                        resultSet.getString("SeatType"),
                        resultSet.getString("PassengerName")
                        // Add more attributes as needed
                );
                seats.add(seat);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving seats from the database");
            e.printStackTrace();
        }

        return seats;
    }

    public boolean updateSeat(Seat seat, String passengerName) {
        if (seat != null && passengerName != null && !passengerName.isEmpty()) {
            try {
                String query = "UPDATE Seats SET PassengerName = ? WHERE FlightNumber = ? AND SeatID = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
                preparedStatement.setString(1, passengerName);
                preparedStatement.setInt(2, seat.getFlightNumber());
                preparedStatement.setString(3, seat.getSeatID());

                int rowsUpdated = preparedStatement.executeUpdate();

                preparedStatement.close();

                // Check if the update was successful
                return rowsUpdated > 0;
            } catch (SQLException e) {
                System.err.println("Error occurred while updating the seat in the database");
                e.printStackTrace();
            }
        }
        return false; // Update failed due to invalid input
    }

    public List<Seat> getSeatsByUser(String passengerName) {
        List<Seat> seatsList = new ArrayList<>();

        if (passengerName != null && !passengerName.isEmpty()) {
            try {
                String query = "SELECT FlightNumber, SeatID, SeatType, PassengerName FROM Seats WHERE PassengerName = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
                preparedStatement.setString(1, passengerName);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int flightNumber = resultSet.getInt("FlightNumber");
                    String seatID = resultSet.getString("SeatID");
                    String seatType = resultSet.getString("SeatType");
                    String passengerNameFromDB = resultSet.getString("PassengerName");

                    Seat seat = new Seat(flightNumber, seatID, seatType, passengerNameFromDB);
                    seatsList.add(seat);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println("Error occurred while retrieving seats from the database for the passenger: " + passengerName);
                e.printStackTrace();
            }
        }

        return seatsList;
    }

    public boolean cancelSeat(int flightNumber, String seatID) {
        try {
            String query = "UPDATE Seats SET PassengerName = NULL WHERE FlightNumber = ? AND SeatID = ?";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.setInt(1, flightNumber);
            preparedStatement.setString(2, seatID);

            int rowsUpdated = preparedStatement.executeUpdate();
            preparedStatement.close();

            // Check if the update was successful
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.err.println("Error occurred while canceling the seat in the database");
            e.printStackTrace();
        }
        return false; // Update failed
    }
}
