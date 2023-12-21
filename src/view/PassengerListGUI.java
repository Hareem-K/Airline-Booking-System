package view;

import Domain.Flight;
import Domain.Seat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class PassengerListGUI extends JFrame {
    public PassengerListGUI(int flightNumber) throws SQLException {
        Flight flight = Driver.getFlight(flightNumber); // Assuming Driver.getFlight retrieves the flight

        setTitle("Passenger List - Flight " + flightNumber);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(0, 1)); // One column for passenger list

        for (Seat seat : flight.getSeats()) {
            if (seat.getPassengerName() != null) {
                JLabel passengerLabel = new JLabel("Seat " + seat.getSeatID() + ": " + seat.getPassengerName());
                panel.add(passengerLabel);
            }
        }

        JScrollPane scrollPane = new JScrollPane(panel); // Add scroll functionality
        getContentPane().add(scrollPane);

        JButton backButton = new JButton("Back to Browsing");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Driver.openBrowseFlightsGUI();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                dispose(); // Close the current window
            }
        });

        getContentPane().add(backButton, BorderLayout.SOUTH); // Add the button at the bottom

        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }

}