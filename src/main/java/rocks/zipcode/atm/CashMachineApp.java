package rocks.zipcode.atm;

import javafx.scene.Node;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.FlowPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());

    private Parent createContent() {

        FlowPane flowpane = new FlowPane();
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();


        Button btnDeposit = new Button("Deposit");
        btnDeposit.setVisible(false);
        btnDeposit.setOnAction(e -> {
            double amount = Double.parseDouble(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setVisible(false);
        btnWithdraw.setOnAction(e -> {
            double amount = Double.parseDouble(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setVisible(false);
        btnExit.setOnAction(e -> {
            cashMachine.exit();
            Node[] toHide={btnExit,btnWithdraw,btnDeposit};
            setVisibleOnExit(toHide);
            areaInfo.setText(cashMachine.toString());
        });


        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);
            Node[] toShow = {btnExit,btnDeposit,btnWithdraw};
            setVisibleOnLogin(toShow);
            areaInfo.setText(cashMachine.toString());
        });

        Button btnNewAcct = new Button("Add New Account");
        btnNewAcct.setOnAction(e ->{
            AddNewAccountDialog newAccDg = new AddNewAccountDialog();
            newAccDg.newAccount(cashMachine);
        });

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
     //   flowpane.getChildren().add(btnNewAcct);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

    public void setVisibleOnLogin(Node[] args){
        if(cashMachine.isAccountData()) {
            for (Node node:args) {
                node.setVisible(true);
            }
        }
    }

    public void setVisibleOnExit(Node[] args){
        for (Node node:args) {
            node.setVisible(false);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
