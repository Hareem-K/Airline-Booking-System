package view;


import Domain.Flight;
import Domain.Seat;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class FlightSeatingGUI {
    private JFrame frame;
    private JPanel seatPanel;

    public FlightSeatingGUI(int flightNumber) throws SQLException {
        frame = new JFrame("Available Seats");
        seatPanel = new JPanel(new GridLayout(0, 4)); // Assuming 4 seats per row

        displayAvailableSeats(flightNumber);

        frame.add(seatPanel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set location to the center of the screen
        frame.setVisible(true);
    }

    private void displayAvailableSeats(int flightNumber) throws SQLException {
        try {
            Flight flight = Driver.getFlight(flightNumber); // Assuming this method retrieves Flight by flightNumber
            Seat[] seats = flight.getSeats(); // Assuming Flight class has a method to get available seats

            int seatsPerRow = 2; // Assuming 2 seats per row
            int rowCount = seats.length / seatsPerRow;

            seatPanel.setLayout(new GridLayout(0, 1)); // Set layout to vertical

            for (int i = 0; i < rowCount; i++) {
                JPanel rowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Row panel for each row of seats

                for (int j = i * seatsPerRow; j < (i * seatsPerRow) + seatsPerRow; j++) {
                    Seat seat = seats[j];

                    JButton button = new JButton(seat.getSeatID());

                    // Customize button properties or add ActionListener here
                    String seatType = seat.getSeatType();
                    switch (seatType) {
                        case "First Class":
                            button.setBackground(Color.GREEN); // Light green for First Class
                            break;
                        case "Business Class":
                            button.setBackground(Color.YELLOW); // Light yellow for Business Class
                            break;
                        // For Economy, no specific color
                        default:
                            break;
                    }

                    if (seat.getPassengerName() != null) {
                        button.setEnabled(false); // Make the button non-pressable
                        button.setBackground(Color.RED); // Set background color to red for taken seats
                    } else {
                        button.addActionListener(e -> {
                            Driver.openCreditCardGUI(seat);
                            frame.dispose(); // Close the current window
                        });
                    }

                    rowPanel.add(button);
                }

                seatPanel.add(rowPanel);
            }

                frame.add(seatPanel);
                frame.setSize(600, 400);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                JPanel legendPanel = new JPanel(new GridLayout(0, 2));
                JLabel firstClassLabel = new JLabel("First Class");
                JLabel businessClassLabel = new JLabel("Business Class");
                JLabel economyClassLabel = new JLabel("Economy");
                JLabel takenLabel = new JLabel("Taken");

                // Customize label colors
                firstClassLabel.setOpaque(true);
                firstClassLabel.setBackground(Color.GREEN);
                businessClassLabel.setOpaque(true);
                businessClassLabel.setBackground(Color.YELLOW);
                takenLabel.setOpaque(true);
                takenLabel.setBackground(Color.RED);

                // Add labels to the legend panel
                legendPanel.add(firstClassLabel);
                legendPanel.add(businessClassLabel);
                legendPanel.add(economyClassLabel);
                legendPanel.add(takenLabel);

                frame.add(legendPanel, BorderLayout.SOUTH); // Add legend panel at the bottom
                frame.add(seatPanel, BorderLayout.CENTER); // Add seating panel in the center

                frame.setSize(600, 500); // Increase frame height for the legend
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            } catch(SQLException e){
                e.printStackTrace();
            }

        }
}
