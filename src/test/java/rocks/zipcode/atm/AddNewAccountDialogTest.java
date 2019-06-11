package rocks.zipcode.atm;

import org.junit.Test;
import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;

import static org.junit.Assert.*;

public class AddNewAccountDialogTest {

    @Test
    public void newAccount() {
        Bank bank = new Bank();
        CashMachine cashMach = new CashMachine(bank);
        Integer expectedId = 1500;
        String expectedName = "Jane Doe";
        String expectedEmail = "jane@doe.com";
        String expectedAccountType = "Basic";
        Double expectedBalance=4000.25;
        AccountData expected = new AccountData(expectedId,expectedName,expectedEmail,expectedBalance);
        expected.setAccountType(expectedAccountType);
        AddNewAccountDialog newAcctDlg = new AddNewAccountDialog();
        newAcctDlg.insertAcct(cashMach,expectedId,expectedAccountType,expectedName,expectedEmail,expectedBalance);
    }
}