package rocks.zipcode.atm.bank;

/**
 * @author ZipCodeWilmington
 */
public final class AccountData {

    private final int id;
    private final String name;
    private final String email;

    private final int balance;

    private String accountType;

    AccountData(int id, String name, String email, int balance) {
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

    public int getBalance() {
        return balance;
    }


        @Override
        public String toString() {
            return "Account id: " + id + '\n' +
                    "Name: " + name + '\n' +
                    "Email: " + email + '\n' +
                    "Balance: " + balance;
        }


/*


       @Override
        public String toString() {
            return "(01)" + id + "(02)" + name + "(03)" + email + "(04)" + balance + "(05)" + accountType + "(06)";
        }


    */
    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

}
