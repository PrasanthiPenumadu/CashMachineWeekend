package rocks.zipcode.atm;
//import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
//import javafx.stage.Stage;

/**
 * @see http://stackoverflow.com/q/44147595/230513
 * @see http://www.javaworld.com/article/2991463/
 */
public class AddNewAccountDialog {

    private static enum AccountType {Basic, Premium}

 //   @Override
 //   public void start(Stage primaryStage) {
   public void newAccount(CashMachine cashMachine){
        Dialog<Integer> dialog = new Dialog<>();
        dialog.setTitle("Add New Account");
        dialog.setHeaderText("Please specify…");
        DialogPane dialogPane = dialog.getDialogPane();
        dialogPane.getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        TextField textField1 = new TextField("ID");
        TextField textField = new TextField("Name");
        TextField textField2 = new TextField("E-mail");
        TextField textField3 = new TextField("Opening balance");
        ObservableList<AccountType> options =
                FXCollections.observableArrayList(AccountType.values());
        ComboBox<AccountType> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
        dialogPane.setContent(new VBox(8,textField1, textField, textField2,textField3, comboBox));
        Platform.runLater(textField::requestFocus);
        dialog.show();
        dialog.setResultConverter((ButtonType button) -> {
            if (button == ButtonType.OK) {
              /*  return new Results(Integer.parseInt(textField1.getText()), textField.getText(),
                        textField2.getText(),Double.parseDouble(textField3.getText()),comboBox.getValue());
                        */
                    Integer id = Integer.parseInt(textField1.getText());
                    cashMachine.addNewAccount(id, comboBox.getValue().toString(), textField.getText(),
                            textField2.getText(),Double.parseDouble(textField3.getText()));
                    return id;
            }
            return null;
        });
 /*       Optional<Results> optionalResult = dialog.showAndWait();
        optionalResult.ifPresent((Results results) -> {
            System.out.println(
                    results.name + " " + results.accountType);
        });*/

    }

 /*   private static class Results {
        int id;
        String name;
        String email;
        double balance;
        AccountType accountType;

        public Results(int id, String name, String email, double balance, AccountType accountType) {
            this.id = id;
            this.name = name;
            this.email=email;
            this.balance=balance;
            this.accountType = accountType;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }*/
}