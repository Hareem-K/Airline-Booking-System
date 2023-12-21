package Domain;

public class Seat {
    //variables
    // Seat numbers
    private String SeatID;
    private int FlightNumber;
    private String SeatType;
    private String PassengerName;

    //constructor
        public Seat(int FlightNumber, String SeatID, String SeatType, String PassengerName) {
            this.SeatID = SeatID;
            this.FlightNumber = FlightNumber;
            this.SeatType = SeatType;
            this.PassengerName = PassengerName;

    }

    // Getters

    public int getFlightNumber() {
        return FlightNumber;
    }

    public String getSeatType() {
        return SeatType;
    }

    public String getPassengerName() {
        return PassengerName;
    }

    public String getSeatID() {return SeatID;}

    public void setPassengerName(String PassengerName) {
        this.PassengerName = PassengerName;
    }

    public void setSeatType(String SeatType) {
        this.SeatType = SeatType;
    }

    public void setSeatID(String SeatID) {
        this.SeatID = SeatID;
    }


    // Seat numbers
    //private String[][] seats;


    // // Constructor
    // public Seat() {
    //     // Initialize seat numbers A1, A2, B1, B2, C1, C2, D1, D2, E1, E2 (10 seats total)
    //     seats = new String[5][2];
    //     for (int row = 0; row < 5; row++) {
    //         for (int column = 0; column < 2; column++) {
    //             seats[row][column] = (char) ('A' + row) + Integer.toString(column + 1);
    //         }
    //     }
    // }

    // //getters
    // public String[][] getSeats() {
    //     return seats;
    // }

    // public String getSeat(int row, int column) {
    //     return seats[row][column];
    // }

    // public String getSeatClass(int row) {
    //     if (row == 0) {
    //         return "First Class";

    //     } else if (row == 1) {
    //         return "Business Class";

    //     } else {
    //         return "Economy Class"; //rows C-E
    //     }

    // }



}