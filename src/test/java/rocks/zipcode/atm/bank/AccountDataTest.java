package rocks.zipcode.atm.bank;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDataTest {

    @Test
    public void getId() {
        AccountData accountData1=new AccountData(12345,"George","george@gmail.com" , 12500);

        Assert.assertEquals(accountData1.getId(),12345);
    }

    @Test
    public void getName() {
        AccountData accountData1=new AccountData(12345,"George","george@gmail.com" , 12500);

        Assert.assertEquals(accountData1.getName(),"George");
    }

    @Test
    public void getEmail() {
        AccountData accountData1=new AccountData(12345,"George","george@gmail.com" , 12500);

        Assert.assertEquals(accountData1.getEmail(),"george@gmail.com" );
    }

    @Test
    public void getBalance() {
        AccountData accountData1=new AccountData(12345,"George","george@gmail.com" , 12500);

        Assert.assertEquals(accountData1.getBalance(),12500,0.0009);
    }


}