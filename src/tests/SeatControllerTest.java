package tests;

import controllers.DatabaseConnectionManager;
import controllers.SeatController;
import Domain.Seat;

import java.util.List;

public class SeatControllerTest {

    public static void main(String[] args) {
        try {
            DatabaseConnectionManager dbcm = new DatabaseConnectionManager();
            SeatController seatController = new SeatController(); // Create an instance of SeatController

            List<Seat> seats = seatController.getAllSeats(); // Retrieve all seats

            if (seats.isEmpty()) {
                System.out.println("No seats found.");
            } else {
                System.out.println("Seats:");
                for (Seat seat : seats) {
                    // Display seat information
                    System.out.println("FlightNumber: " + seat.getFlightNumber());
                    System.out.println("SeatID: " + seat.getSeatID());
                    System.out.println("SeatType: " + seat.getSeatType());
                    System.out.println("PassengerName: " + seat.getPassengerName());
                    System.out.println("-----------------------------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}