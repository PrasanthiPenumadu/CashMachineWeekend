package rocks.zipcode.atm;

import rocks.zipcode.atm.bank.AccountData;
import rocks.zipcode.atm.bank.Bank;
import rocks.zipcode.atm.bank.PremiumAccount;

import java.io.IOException;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author ZipCodeWilmington
 */
public class CashMachine {

    private final Bank bank;
    private AccountData accountData = null;
    PremiumAccount pa=new PremiumAccount(accountData);
 //   public String errorMessage = "";
    public CashMachine(Bank bank) {
        this.bank = bank;
    }

    private Consumer<AccountData> update = data -> {
        accountData = data;
    };

    public void login(int id) {
        try {

            // String s=String.valueOf(id);
            //  if(s.matches("[0-9]")){
            // id=Integer.parseInt(s);
            tryCall(
                    () -> bank.getAccountById(id),
                    update
            );

        }catch (NumberFormatException e){
            System.out.println("Enter a valid account");
        }
    }

    public void deposit(int amount) {
        if (accountData != null&&amount>0) {
            tryCall(
                    () -> bank.deposit(accountData, amount),
                    update
            );
        }
    }

    public void withdraw(int amount) {
        if (accountData != null&&amount>0) {
            tryCall(
                    () -> bank.withdraw(accountData, amount),
                    update
            );
        }
    }

    public void exit() {
        if (accountData != null) {
            accountData = null;
        }
    }

    @Override
    public String toString() {
int amount=0;
//        if(pa.withdraw(amount)==false)
//            return accountData != null ? accountData.toString() : "Withdraw failed";
//        else
        return accountData != null ? accountData.toString() : "Try another account and click Set Account Id.";
//        if (accountData!=null){
//            if (!(errorMessage.isEmpty())) return accountData.toString(); else return errorMessage;
//
//        }
//        else return "Try another account and click Set Account Id.";
 //if(pa.withdraw(amount)==false)
      //return accountData != null ? accountData.toString() : "Try another account and click submit.";
    }

    private <T> void tryCall(Supplier<ActionResult<T> > action, Consumer<T> postAction) {
        try {
            ActionResult<T> result = action.get();
            if (result.isSuccess()) {
                T data = result.getData();
                postAction.accept(data);
            } else {
                String errorMessage = result.getErrorMessage();
              T data = result.getData();
               postAction.accept(data);
        //       accountData.toString( "your account is in overdraft");
                throw new RuntimeException(errorMessage);
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    public Integer[] getAccountIdList(){
        return bank.getAccountIdList();
    }
}
