package rocks.zipcode.atm.bank;

import rocks.zipcode.atm.ActionResult;

import java.util.*;

import static javafx.scene.input.KeyCode.T;

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
        accounts.put(2500, new PremiumAccount(new AccountData(
                2500, "Example 3", "example3@gmail.com", 2500
        )));
        accounts.put(2100, new PremiumAccount(new AccountData(
                2100, "Example 4", "example4@gmail.com", 500
        )));
        accounts.put(1800, new BasicAccount(new AccountData(
                1800, "Example 5", "example5@gmail.com", 1500
        )));

    }

    public Integer[] getAccountIdList() {
    List<Integer> keysArrList = new ArrayList<>(Arrays.asList(accounts.keySet().toArray(new Integer[0])));
        Collections.sort(keysArrList);
        Integer [] integerarr=new Integer[keysArrList.size()];
        for(int i=0;i<keysArrList.size();i++){
            integerarr[i]=keysArrList.get(i);
        }
        return integerarr;
    }

    public ActionResult<AccountData> getAccountById(int id) {
        Account account = accounts.get(id);

        if (account != null) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("No account with ID: " + id + "\nTry another account ID");
        }
    }

    public ActionResult<AccountData> deposit(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        account.deposit(amount);

        return ActionResult.success(account.getAccountData());
    }

    public ActionResult<AccountData> withdraw(AccountData accountData, int amount) {
        Account account = accounts.get(accountData.getId());
        boolean ok = account.withdraw(amount);

        if (ok) {
            return ActionResult.success(account.getAccountData());
        } else {
            return ActionResult.fail("Withdraw failed: " + amount + ". Account has: " + account.getBalance());
        }
    }
}