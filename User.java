package Parking_Ticket;



public abstract class   User {

    private String email;
    private String password;
    private String username;
    private double balance;
    private String userType;

    public User(String email, String password, String username, double balance, String userType) {
        this.email = email;
        this.password = password;
        this.username = username;
        this.balance = balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getUserType() {
        return userType;
    }

}




