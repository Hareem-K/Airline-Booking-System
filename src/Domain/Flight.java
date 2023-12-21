package Domain;

import java.sql.Timestamp;

public class Flight {
    private int flightNumber;
    private Timestamp departureTime;
    private Timestamp arrivalTime;
    private String destination;
    private int crewNumber;

    private Seat[] seats;

    // Add more attributes as needed

    // Constructor
    public Flight(int flightNumber, Timestamp departureTime, Timestamp arrivalTime,
                  String destination, int crewNumber) {
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.destination = destination;
        this.crewNumber = crewNumber;

        // Initialize seats array with 10 seats
        this.seats = new Seat[10];
        char[] seatRows = {'A', 'A', 'A', 'A', 'B', 'B', 'B', 'B', 'C', 'C'};
        int[] seatNumbers = {1, 2, 3, 4, 1, 2, 3, 4, 1, 2};
        String[] seatTypes = {"First Class", "First Class", "First Class", "First Class",
                "Business Class", "Business Class", "Business Class", "Business Class",
                "Economy", "Economy"};

        for (int i = 0; i < 10; i++) {
            String seatId = String.valueOf(seatRows[i]) + seatNumbers[i];
            this.seats[i] = new Seat(flightNumber, seatId, seatTypes[i], null);
        }
    }

        // Getters
    public int getFlightNumber() {
        return flightNumber;
    }

    public Timestamp getDepartureTime() {
        return departureTime;
    }

    public Timestamp getArrivalTime() {
        return arrivalTime;
    }

    public String getDestination() {
        return destination;
    }

    public int getCrewNumber() {
        return crewNumber;
    }

    public Seat[] getSeats() {
        return seats;
    }


    public void addSeat(Seat seat) {
        boolean seatExists = false;

        // Check if the seat already exists in the array
        for (Seat existingSeat : seats) {
            if (existingSeat != null && existingSeat.getSeatID().equals(seat.getSeatID())) {
                existingSeat.setPassengerName(seat.getPassengerName()); // Update passenger name if seat exists
                seatExists = true;
                break;
            }
        }

        // If the seat doesn't exist, add it to the array
        if (!seatExists) {
            if (seats == null) {
                seats = new Seat[]{seat};
            } else {
                Seat[] newSeats = new Seat[seats.length + 1];
                System.arraycopy(seats, 0, newSeats, 0, seats.length);
                newSeats[seats.length] = seat;
                seats = newSeats;
            }
        }
    }
}
