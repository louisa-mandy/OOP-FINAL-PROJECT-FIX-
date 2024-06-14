
package Parking_Ticket;

public class PremiumUser extends User implements UserActions {
    public PremiumUser(String email, String password, String username, double balance) {
        super(email, password, username, balance, "Premiumuser");
    }

    @Override
    public void checkBalance() {
        System.out.println("Balance: $" + getBalance());
    }

    @Override
    public void topUp(double amount) {
        setBalance(getBalance() + amount * 1.1); // Premium users get a 10% bonus
        System.out.println("New Balance with bonus: $" + getBalance());
    }
}
