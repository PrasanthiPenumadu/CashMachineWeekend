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
    public void addNewAccount() {
    }

    @Test
    public void getAccountById() {
    }

    @Test
    public void getAcctListPrompt() {
    }

    @Test
    public void deposit() {
    }

    @Test
    public void withdraw() {
    }
}