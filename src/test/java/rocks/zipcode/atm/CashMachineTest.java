package rocks.zipcode.atm;

import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import static org.junit.Assert.*;

public class CashMachineTest {
    @Test
    public void isAccountData() {
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        Assert.assertEquals(false, cashMachine1.isAccountData());
    }

    @Test
    public void addNewAccount() {
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.addNewAccount(4500,"Basic" , "Elle", "Elle@email.com", 4500);
       Integer x= cashMachine1.getAccountIdList().length;
        System.out.println(x);
       Assert.assertEquals(6,cashMachine1.getAccountIdList().length);
    }

    @Test
    public void getAccountIdList() {
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        Assert.assertEquals(5, cashMachine1.getAccountIdList().length);
    }
}