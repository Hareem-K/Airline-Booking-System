package tests;

import controllers.DatabaseConnectionManager;
import controllers.FlightController;
import Domain.Flight;

import java.util.List;

public class FlightControllerTest {

    public static void main(String[] args) {
        try {
            DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
            FlightController flightController = new FlightController(); // Create an instance of FlightController

            List<Flight> flights = flightController.getAllFlights(); // Retrieve all flights

            if (flights.isEmpty()) {
                System.out.println("No flights found.");
            } else {
                System.out.println("Flights:");
                for (Flight flight : flights) {
                    // Display flight information
                    System.out.println("FlightNumber: " + flight.getFlightNumber());
                    System.out.println("DepartureTime: " + flight.getDepartureTime());
                    System.out.println("ArrivalTime: " + flight.getArrivalTime());
                    System.out.println("Destination: " + flight.getDestination());
                    System.out.println("CrewID: " + flight.getCrewNumber());
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}