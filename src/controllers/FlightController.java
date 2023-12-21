package controllers;
import Domain.Flight;
import Domain.Seat;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlightController extends DatabaseConnectionManager {

    //controller - allow connection to database
    public FlightController() throws SQLException {
        super();
    }



    public List<Flight> getAllFlights() {
        List<Flight> flights = new ArrayList<>();

        try {
            String query = "SELECT * FROM Flights";
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                // Assuming Flight class has appropriate constructor
                Flight flight = new Flight(
                        resultSet.getInt("FlightNumber"),
                        resultSet.getTimestamp("DepartureTime"),
                        resultSet.getTimestamp("ArrivalTime"),
                        resultSet.getString("Destination"),
                        resultSet.getInt("CrewID")
                        // Add more attributes as needed
                );
                flights.add(flight);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving flights from the database");
            e.printStackTrace();
        }

        return flights;
    }

    public Flight getFlightByFlightNumber(int flightNumber) {
        Flight flight = null;

        try {
            String flightQuery = "SELECT * FROM Flights WHERE FlightNumber = ?";
            PreparedStatement flightStatement = dbConnection.prepareStatement(flightQuery);
            flightStatement.setInt(1, flightNumber);
            ResultSet flightResult = flightStatement.executeQuery();

            if (flightResult.next()) {
                flight = new Flight(
                        flightResult.getInt("FlightNumber"),
                        flightResult.getTimestamp("DepartureTime"),
                        flightResult.getTimestamp("ArrivalTime"),
                        flightResult.getString("Destination"),
                        flightResult.getInt("CrewID")
                        // Add more attributes as needed
                );

                // Fetch corresponding seats for the flight
                String seatsQuery = "SELECT * FROM Seats WHERE FlightNumber = ?";
                PreparedStatement seatsStatement = dbConnection.prepareStatement(seatsQuery);
                seatsStatement.setInt(1, flightNumber);
                ResultSet seatsResult = seatsStatement.executeQuery();

                while (seatsResult.next()) {
                    // Assuming Seat class has appropriate constructor

                    Seat seat = new Seat(
                            seatsResult.getInt("FlightNumber"),
                            seatsResult.getString("SeatID"),
                            seatsResult.getString("SeatType"),
                            seatsResult.getString("PassengerName")
                            // Add more attributes as needed
                    );

                    flight.addSeat(seat); // Add the seat to the flight
                }

                seatsResult.close();
                seatsStatement.close();
            }

            flightResult.close();
            flightStatement.close();
        } catch (SQLException e) {
            System.err.println("Error occurred while retrieving flight with seats from the database");
            e.printStackTrace();
        }


        return flight;
    }

    public Flight getFlightBySeat(Seat seat) throws SQLException {
        Flight flight = null;
        if (seat != null) {
            try {
                String query = "SELECT * FROM Flights F JOIN Seats S ON F.FlightNumber = S.FlightNumber WHERE S.FlightNumber = ? AND S.PassengerName = ?";
                PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
                preparedStatement.setInt(1, seat.getFlightNumber());
                preparedStatement.setString(2, seat.getPassengerName());

                ResultSet resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {

                    // Retrieve flight information from the query result
                    int flightNumber = resultSet.getInt("FlightNumber");
                    Timestamp departureTime = resultSet.getTimestamp("DepartureTime");
                    Timestamp arrivalTime = resultSet.getTimestamp("ArrivalTime");
                    String destination = resultSet.getString("Destination");
                    int crewID = resultSet.getInt("CrewID");
                    // Other flight details...

                    // Create the Flight object
                    flight = new Flight(flightNumber, departureTime, arrivalTime, destination, crewID);
                }

                resultSet.close();
                preparedStatement.close();
            } catch (SQLException e) {
                System.err.println("Error occurred while retrieving the flight by seat from the database");
                e.printStackTrace();
                throw e;
            }
        }
        return flight;
    }

}