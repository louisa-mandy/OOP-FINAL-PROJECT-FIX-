
package Parking_Ticket;

public class RegularUser extends User implements UserActions {
    public RegularUser(String email, String password, String username, double balance) {
        super(email, password, username, balance, "RegularUser");
    }

    @Override
    public void checkBalance() {
        System.out.println("Balance: $" + getBalance());
    }

    @Override
    public void topUp(double amount) {
        setBalance(getBalance() + amount);
        System.out.println("New Balance: $" + getBalance());
    }
}
