package view;

import controllers.FlightController;
import Domain.CurrentInfoManager;
import Domain.Flight;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class BrowseFlightsGUI {
    private JFrame frame;
    private JPanel flightPanel;

    public BrowseFlightsGUI() throws SQLException {
        frame = new JFrame("Browse Flights");
        flightPanel = new JPanel(new GridLayout(0, 1));

        displayFlights();

        frame.add(flightPanel);
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Set location to the center of the screen
        frame.setVisible(true);
    }

    private void displayFlights() throws SQLException {
        FlightController flightController = new FlightController();
        List<Flight> flights = flightController.getAllFlights();

        for (Flight flight : flights) {
            StringBuilder flightInfo = new StringBuilder();
            flightInfo.append("<html>Flight Number: ").append(flight.getFlightNumber()).append("<br/>");
            flightInfo.append("Departure Time: ").append(flight.getDepartureTime()).append("<br/>");
            flightInfo.append("Arrival Time: ").append(flight.getArrivalTime()).append("<br/>");
            flightInfo.append("Destination: ").append(flight.getDestination()).append("</html>");

            JButton button = new JButton(flightInfo.toString());
            button.setHorizontalAlignment(SwingConstants.LEFT);

            button.addActionListener(e -> {
                int flightNumber = flight.getFlightNumber();
                CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
                currentInfoManager.setCurrentFlight(flight);

                if (currentInfoManager.getIsAttendant()) {
                    try {
                        Driver.openPassengerListGUI(flightNumber);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    try {
                        Driver.openFlightSeatingGUI(flightNumber);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                frame.dispose(); // Closes the current window
            });

            flightPanel.add(button);
        }
    }

}
