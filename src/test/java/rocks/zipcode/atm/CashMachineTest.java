package rocks.zipcode.atm;

import org.junit.Assert;
import org.junit.Test;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import static org.junit.Assert.*;

public class CashMachineTest {

    @Test
    public void testDeposit(){
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.addNewAccount(4500,"Basic" , "Elle", "Elle@email.com", 4500);
        cashMachine1.login(4500);
        cashMachine1.deposit(100);
        AccountData accountData=bank.getAccountById(4500).getData();
        Assert.assertEquals(4600,accountData.getBalance(),0.0009);
    }
    @Test
    public void testDeposit1(){
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.addNewAccount(4500,"Basic" , "Elle", "Elle@email.com", 4500);
        cashMachine1.login(4500);
        cashMachine1.deposit(-100);
        AccountData accountData=bank.getAccountById(4500).getData();
        Assert.assertEquals(4500,accountData.getBalance(),0.0009);
    }

    @Test
    public void testWithdraw(){

        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.addNewAccount(4500,"Basic" , "Elle", "Elle@email.com", 4500);
        cashMachine1.login(4500);
        cashMachine1.withdraw(700);
        AccountData accountData=bank.getAccountById(4500).getData();
        Assert.assertEquals(3800,accountData.getBalance(),0.0009);

    }
    @Test
    public void testWithdraw1(){

        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.addNewAccount(4500,"Basic" , "Elle", "Elle@email.com", 4500);
        cashMachine1.login(4500);
        cashMachine1.withdraw(-500);
        AccountData accountData=bank.getAccountById(4500).getData();
        Assert.assertEquals(4500,accountData.getBalance(),0.0009);

    }

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
    public void addNewAccountpremium() {
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.addNewAccount(5500,"Premium" , "belle", "belle@email.com", 5500);
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
    @Test
    public void testexit(){
        Bank bank=new Bank();
        CashMachine cashMachine1=new CashMachine(bank);
        cashMachine1.exit();
        Assert.assertEquals(false, cashMachine1.isAccountData());
    }
}