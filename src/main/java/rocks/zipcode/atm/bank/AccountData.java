package rocks.zipcode.atm.bank;

import java.util.Objects;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;

    private final double balance;

    private String accountType;

    AccountData(int id, String name, String email, double balance) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.balance = balance;
        this.accountType = "Basic";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        if(balance<0){ return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                String.format("Balance: %1.2f",balance)+"\n"+"Alert: Your Account is OverDraft";}
else
        return "Account id: " + id + '\n' +
                "Name: " + name + '\n' +
                "Email: " + email + '\n' +
                String.format("Balance: %1.2f",balance);
        //   "Balance: " + balance;
    }




    public String toDataString() {
        return "(01)" + id + "(02)" + name + "(03)" + email + "(04)" + balance + "(05)" + accountType + "(06)";
    }




    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountData that = (AccountData) o;
        return id == that.id &&
                Double.compare(that.balance, balance) == 0 &&
                Objects.equals(name, that.name) &&
                Objects.equals(email, that.email) &&
                Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, balance, accountType);
    }
}
