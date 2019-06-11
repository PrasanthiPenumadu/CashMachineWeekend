package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {

    @Test
    public void getAccountIdList() {

        Bank bank=new Bank();
        Integer[] expected = {1000,1800,2000,2100,2500};
        Integer[] actual = bank.getAccountIdList();

        Assert.assertArrayEquals(expected,actual);
    }

    @Test
    public void getAccountById() {
        Bank bank = new Bank();
        Integer expectedId = 1800;
        AccountData expected = new AccountData(expectedId,"Example 5", "example5@gmail.com", 1500);
        AccountData actual =  bank.getAccountById(expectedId).getData();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getAccountByIdFail() {
        Bank bank = new Bank();
        Integer id = 1500;
        AccountData expected = null;
        AccountData actual =  bank.getAccountById(id).getData();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void getAcctListPrompt() {
        Bank bank = new Bank();
        String actual = bank.getAcctListPrompt();
        String expected="1000, 1800, 2000, 2100 or 2500";
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void addNewAccount() {
        Bank bank=new Bank();
        Integer expectedId = 1500;
        String expectedName = "Jane Doe";
        String expectedEmail = "jane@doe.com";
        String expectedAccountType = "Basic";
        Double expectedBalance=4000.25;
        AccountData expected = new AccountData(expectedId,expectedName,expectedEmail,expectedBalance);
        bank.addNewAccount(expectedId,expectedAccountType,expectedName,expectedEmail,expectedBalance);
        AccountData actual = bank.getAccountById(expectedId).getData();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void addNewPremiumAccount() {
        Bank bank=new Bank();
        Integer expectedId = 1500;
        String expectedName = "Jane Doe";
        String expectedEmail = "jane@doe.com";
        String expectedAccountType = "Premium";
        Double expectedBalance=4000.25;
        AccountData expected = new AccountData(expectedId,expectedName,expectedEmail,expectedBalance);
        bank.addNewAccount(expectedId,expectedAccountType,expectedName,expectedEmail,expectedBalance);
        expected.setAccountType(expectedAccountType);
        AccountData actual = bank.getAccountById(expectedId).getData();

        Assert.assertEquals(expected,actual);
    }

    @Test
    public void deposit() {
        Bank bank=new Bank();
        Integer id = 1800;
        AccountData before = bank.getAccountById(id).getData();
        Double depositAmt = 300.30;
        Double expected = before.getBalance()+depositAmt;
        bank.deposit(before,depositAmt);
        AccountData after = bank.getAccountById(id).getData();
        Double actual = after.getBalance();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void withdraw() {
        Bank bank=new Bank();
        Integer id = 1800;
        AccountData before = bank.getAccountById(id).getData();
        Double withdrawAmt = 300.30;
        Double expected = before.getBalance()-withdrawAmt;
        bank.withdraw(before,withdrawAmt);
        AccountData after = bank.getAccountById(id).getData();
        Double actual = after.getBalance();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void withdrawOver() {
        Bank bank=new Bank();
        Integer id = 1800;
        AccountData before = bank.getAccountById(id).getData();
        Double withdrawAmt = 3000.30;
        String expected = "Withdraw failed: " + withdrawAmt + ". Account has: " + before.getBalance();
        bank.withdraw(before,withdrawAmt);
        AccountData after = bank.getAccountById(id).getData();
        Double actual = after.getBalance();
        Assert.assertEquals(expected,actual);
    }
}