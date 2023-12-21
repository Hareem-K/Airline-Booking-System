package Domain;

public class CurrentInfoManager {
    private static CurrentInfoManager instance;
    private RegisteredUsers currentUser;
    private Flight userFlight;

    private Seat userSeat;


    private boolean isAttendant;
    // Add more fields as needed for other entities

    public CurrentInfoManager() {
        // Private constructor to prevent direct instantiation
    }

    public static synchronized CurrentInfoManager getInstance() {
        if (instance == null) {
            instance = new CurrentInfoManager();
        }
        return instance;
    }

    // Setters for different entities
    public void setCurrentUser(RegisteredUsers user) {
        this.currentUser = user;
    }

    public void setCurrentFlight(Flight flight) {
        this.userFlight = flight;
    }


    // Add more setters as needed for other entities

    // Getters for different entities
    public RegisteredUsers getCurrentUser() {
        return currentUser;
    }

    public Flight getCurrentFlight() {
        return userFlight;
    }

    public Seat getUserSeat() {
        return userSeat;
    }

    public void setUserSeat(Seat userSeat) {
        this.userSeat = userSeat;
    }

    public void setIsAttendant(boolean isAttendant) {
        this.isAttendant = isAttendant;
    }

    public boolean getIsAttendant() {
        return isAttendant;
    }
    // Add more getters as needed for other entities
}
