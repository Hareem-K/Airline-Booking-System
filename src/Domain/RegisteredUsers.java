package Domain;

public class RegisteredUsers {

    private String Name;
    private String Email;
    private CreditCard CreditCard;
    private String Password;

    // Constructor
    public RegisteredUsers(String Username, String Email, String Password, CreditCard CreditCard ) {
        this.Name = Username;
        this.Email = Email;
        this.Password = Password;
        this.CreditCard = CreditCard;
    }

    //Getters


    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public CreditCard getCreditCard(){return CreditCard;}

    //Setters
    public void setCreditCard(CreditCard CreditCard) {
        this.CreditCard = CreditCard;
    }
    
}
