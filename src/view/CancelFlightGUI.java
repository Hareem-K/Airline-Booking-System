package view;

import controllers.FlightController;
import controllers.SeatController;
import Domain.*;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CancelFlightGUI extends JFrame {
    public CancelFlightGUI() throws SQLException {
        setTitle("Cancel Flight");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1)); // One column for flight list

        CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
        RegisteredUsers currentUser = currentInfoManager.getCurrentUser();
        SeatController seatController = new SeatController();
        FlightController flightController = new FlightController();

        List<Seat> passengerSeats = seatController.getSeatsByUser(currentUser.getName()); // Get seats for the current user
        if (passengerSeats != null && !passengerSeats.isEmpty()) {
            for (Seat seat : passengerSeats) {

                Flight flight = flightController.getFlightBySeat(seat); // Get flight details using the seat

                if (flight != null) {
                    JButton cancelButton = new JButton("Cancel Flight " + flight.getFlightNumber() + ", Seat " + seat.getSeatID());
                    cancelButton.addActionListener(e -> {
                        int confirmDialogResult = JOptionPane.showConfirmDialog(
                                null,
                                "Are you sure you want to cancel Flight " + flight.getFlightNumber() + " with a $20 cancellation charge?",
                                "Confirmation",
                                JOptionPane.YES_NO_OPTION
                        );

                        if (confirmDialogResult == JOptionPane.YES_OPTION) {
                            try {
                                // Perform cancellation logic here
                                int flightNumber = flight.getFlightNumber();
                                String seatID = seat.getSeatID();
                                // Apply the $20 charge logic here...
                                // Example: ChargeController.applyCancellationCharge(currentUser.getName(), 20.0);
                                Driver.cancelFlight(flightNumber, seatID); // Cancel the seat in the database
                                JOptionPane.showMessageDialog(null, "Flight " + flightNumber + ", Seat " + seatID + " canceled.");
                                dispose(); // Close the current window after canceling
                            } catch (SQLException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    });
                    panel.add(cancelButton);
                }
            }
        } else {
            JLabel noFlightsLabel = new JLabel("No flights found for cancellation.");
            panel.add(noFlightsLabel);
        }

        JScrollPane scrollPane = new JScrollPane(panel); // Add scroll functionality
        getContentPane().add(scrollPane);

        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }
}