package view;


import controllers.*;
import Domain.*;

import java.sql.SQLException;
import java.util.List;

public class Driver {
    // Other methods and code...

    public static void openPickUserGUI() {
        PickUserGUI pickUserGUI = new PickUserGUI();
        pickUserGUI.setVisible(true); // Make the GUI visible
    }

    //adds a user to the database
    public static boolean addUser(String fullName, String email, String password) throws SQLException {
        RegisteredUsersController rc = new RegisteredUsersController();
        return rc.addRegisteredUser(fullName, email, password);
    }

    //opens the user hub gui
    public static void openUserHubGUI(){
        UserHubGUI userHubGUI = new UserHubGUI();
    }

    //authenticates the login
    public static boolean authenticateLogin(String email, String password) throws SQLException {
        RegisteredUsersController rc = new RegisteredUsersController();
        Boolean val = rc.validateRegisteredUser(email, password);
        CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
        if (val == true){
            RegisteredUsers user = rc.getRegisteredUserByEmail(email);
            currentInfoManager.setCurrentUser(user);
        }

        return val;
    }

    //opens the user sign in gui
    public static void openUserSignInGUI(){UserSignInGUI userSignInGUI = new UserSignInGUI();}

    //opens the guest registration gui
    public static void openGuestRegistrationGUI(){new GuestRegistrationGUI();}

    public static void openBrowseFlightsGUI() throws SQLException {new BrowseFlightsGUI();}

    public static void openFlightSeatingGUI(int flight) throws SQLException { new FlightSeatingGUI(flight); }

    public static Flight getFlight(int flightNumber) throws SQLException {
        FlightController fc = new FlightController();
        Seat[] seats = fc.getFlightByFlightNumber(flightNumber).getSeats();

        return fc.getFlightByFlightNumber(flightNumber);
    }

    public static void openCreditCardGUI(Seat seat){
        CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
        currentInfoManager.setUserSeat(seat);

        String full_name = currentInfoManager.getCurrentUser().getName();
        new CreditCardGUI(full_name);}

    public static boolean setCreditCard(String fullName, String cardNumber, String expiryDate) throws SQLException {
        CreditCardController cc = new CreditCardController();
        boolean isSuccess = cc.addCreditCard(fullName, cardNumber, expiryDate);
        if (isSuccess){
            CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
            RegisteredUsers user = currentInfoManager.getCurrentUser();
            user.setCreditCard(new CreditCard(cardNumber, expiryDate, fullName));
            currentInfoManager.setCurrentUser(user);
        }
        return isSuccess;
    }

    public static void openPaymentGUI(){
        CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
        Seat seat = currentInfoManager.getUserSeat();
        String cardNum = currentInfoManager.getCurrentUser().getCreditCard().getCardNumber();
        Flight flight = currentInfoManager.getCurrentFlight();
        new PaymentGUI(seat,cardNum,flight);
    }

    public static void confirmPayment(Seat seat, String cardNum, int flightNum, double price) throws SQLException {
        Payment payment = new Payment(cardNum, "Flight Purchase", flightNum, price);
        CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
        currentInfoManager.getCurrentUser().getCreditCard().addPayment(payment);

        String userName = currentInfoManager.getCurrentUser().getName();

        SeatController sc = new SeatController();
        sc.updateSeat(seat, userName);

        PaymentController pc = new PaymentController();
        pc.addPayment(payment);

        Flight flight = currentInfoManager.getCurrentFlight();
        new PaymentNotificationGUI(seat, flight, price, cardNum);
        new UserHubGUI();
    }

    public static void attendant() throws SQLException {
        CurrentInfoManager currentInfoManager = CurrentInfoManager.getInstance();
        currentInfoManager.setIsAttendant(true);
        new BrowseFlightsGUI();
    }

    public static void openPassengerListGUI(int flightNumber) throws SQLException {
        new PassengerListGUI(flightNumber);
    }

    public static void openCancelFlightGUI() throws SQLException {
        new CancelFlightGUI();
    }

    public static void cancelFlight(int flightNumber, String seatID) throws SQLException {
        SeatController sc = new SeatController();
        sc.cancelSeat(flightNumber, seatID);

        List<Payment> payments = CurrentInfoManager.getInstance().getCurrentUser().getCreditCard().getPayments();
        for (Payment payment : payments){
            
            if (payment.getFlightID() == flightNumber){
                PaymentController pc = new PaymentController();
                pc.cancelPayment(payment);
                Payment charge = new Payment(payment.getCardNumber(), "Flight Cancellation", flightNumber, 20.00);
                pc.addPayment(charge);
            }
        }
        new UserHubGUI();
    }

    public static void openViewPaymentsGUI() throws SQLException {
        new ViewPaymentsGUI(CurrentInfoManager.getInstance().getCurrentUser().getName());
    }
}