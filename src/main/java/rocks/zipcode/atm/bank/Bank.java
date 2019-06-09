package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.*;

/**
 * @author ZipCodeWilmington
 */
public class Bank {

    private Map<Integer, Account> accounts = new HashMap<>();

    public Bank() {
        accounts.put(1000, new BasicAccount(new AccountData(
                1000, "Example 1", "example1@gmail.com", 500
        )));


        accounts.put(2000, new PremiumAccount(new AccountData(
                2000, "Example 2", "example2@gmail.com", 200
        )));

        accounts.put(3000, new PremiumAccount(new AccountData(
                3000, "Example 3", "example3@gmail.com", 200
        )));

    }

    public ActionResult<AccountData> addNewAccount(int id, String accountType, String name, String email, double balance){
        Account account = accounts.get(id);

        if(account==null) {
            return ActionResult.success(this.addAccount(id, accountType, name, email, balance));
        } else {
            String acctListPrompt = getAcctListPrompt();
            return ActionResult.fail("Unable to create account, ID " + id + " associated with existing account.\n" +
                    "Please try again with an ID other than "+acctListPrompt);
        }
    }

    private AccountData addAccount(int id, String accountType, String name, String email, double balance){
        switch (accountType){
            case "Premium":{
                accounts.put(id, new PremiumAccount(new AccountData(id,name,email,balance)));
                break;
            }
            default: {
                accounts.put(id, new BasicAccount(new AccountData(id,name,email,balance)));
                break;
            }
        }
        return accounts.get(id).getAccountData();
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            String acctListPrompt = getAcctListPrompt();
            return ActionResult.fail("No account with id: " + id + "\nTry account "+acctListPrompt);
        }
    }

    public String getAcctListPrompt() {
        StringBuilder builder = new StringBuilder();
        List<Integer> keysArrList = new ArrayList<>(Arrays.asList(accounts.keySet().toArray(new Integer[0])));
        Collections.sort(keysArrList);
        for (int i = 0; i < accounts.size()-2; i++) {
            builder.append(keysArrList.get(i)).append(", ");
        }
        if(accounts.size()>1) builder.append(keysArrList.get(accounts.size()-2)).append(" or ");
        builder.append(keysArrList.get(accounts.size()-1));
        return builder.toString();
    }

    public ActionResult<AccountData> deposit(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, double amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}
