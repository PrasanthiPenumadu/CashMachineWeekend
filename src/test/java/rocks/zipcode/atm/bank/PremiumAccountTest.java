package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PremiumAccountTest {

    @Test
    public void canWithdraw() {
        Integer id = 1500;
        String name = "Jane Doe";
        String email = "jane@doe.com";
        Double balance=4000.25;
        AccountData acctData = new AccountData(id,name,email,balance);
        PremiumAccount acct = new PremiumAccount(acctData);

        Assert.assertTrue(acct.canWithdraw(balance+99));
    }

    @Test
    public void canWithdrawFalse() {
        Integer id = 1500;
        String name = "Jane Doe";
        String email = "jane@doe.com";
        Double balance=4000.25;
        AccountData acctData = new AccountData(id,name,email,balance);
        PremiumAccount acct = new PremiumAccount(acctData);

        Assert.assertFalse(acct.canWithdraw(balance+101));
    }


}