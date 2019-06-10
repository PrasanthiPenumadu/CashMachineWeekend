package rocks.zipcode.atm;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
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
import javafx.application.Application;
import javafx.event.ActionEvent;
import  javafx.event.EventHandler;
import  javafx.scene.layout.StackPane;

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    //TextField field = new TextField();
    //field.setPromptText("password")
    //private TextField field2 = new TextField();

    private CashMachine cashMachine = new CashMachine(new Bank());


    private Parent createContent() {
        /*GridPane grids = new GridPane();
        grids.setAlignment(Pos.TOP_LEFT);
        grids.setPadding(new Insets(10,10,10,10));
        grids.setVgap(8);
        grids.setHgap(10);*/

        TextField field = new TextField();
        field.setPromptText("Enter Account ID Here ");
        field.setMaxSize(200,200);

        // Enter amount here
        TextField field2 = new TextField();
        field2.setPromptText("Enter Amount Here ");
        field2.setMaxSize(200,200);

        VBox vbox = new VBox(60);
        vbox.setPrefSize(600, 600);

        TextArea areaInfo = new TextArea();
        areaInfo.setMaxSize(600 - 5,600);

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            int id = Integer.parseInt(field.getText());
            cashMachine.login(id);



            //areaInfo.setText(cashMachine.toString());
        });

         Button btnDeposit = new Button("Deposit");
        btnDeposit.setVisible(field.getText().equals("2000") | field.getText().equals("1000"));
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field2.getText());
            cashMachine.deposit(amount);


            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field2.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });


        GridPane flowpane2 = new GridPane();
        //Set Account ID Button
        flowpane2.setConstraints(btnSubmit,1,1);
        flowpane2.getChildren().add(btnSubmit);

        GridPane flowpane = new GridPane();
        //flowpane.setAlignment(Pos.CENTER);
        flowpane.setPadding(new Insets(10,10,10,10));
        flowpane.setVgap(8);
        flowpane.setHgap(10);

        //Set Account ID Button
        //flowpane.setConstraints(btnSubmit,0,1);
        //flowpane.getChildren().add(btnSubmit);

        //Deposit Button
        flowpane.setConstraints(btnDeposit,0,2);
        flowpane.getChildren().add(btnDeposit);

        //Withdraw Button
        flowpane.setConstraints(btnWithdraw,0,3);
        flowpane.getChildren().add(btnWithdraw);

        //Exit Button
        flowpane.setConstraints(btnExit,0,4);
        flowpane.getChildren().add(btnExit);

        vbox.getChildren().addAll(field,field2,flowpane2,flowpane, areaInfo);
        flowpane.setConstraints(field,0,0);
        return vbox;
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        stage.show();
        /*
        stage.setTitle("Zip Code Banking");
        button = new Button();
        button.setText("Hey now");

        StackPane layout = new StackPane();
        layout.getChildren().add(button);

        //Scene scene = new Scene(layout, 300, 250);
        //stage.setScene(scene);
        //stage.show();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Login");
        GridPane.setConstraints(nameLabel,0,0);

        TextField nameInput = new TextField("Williamreyes28");
        GridPane.setConstraints(nameInput,1,0);

        Label passLabel = new Label("Password");
        GridPane.setConstraints(passLabel,0,1);

        //Password Input
        TextField passINput = new TextField();
        passINput.setPromptText("password");
        GridPane.setConstraints(passINput,1,1);

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1,2);

        grid.getChildren().addAll(nameLabel,nameInput, passINput,passLabel,loginButton);

        Scene scene = new Scene(grid,300,300);
        stage.setScene(scene);
        stage.show();*/


    }
Button button;
    public static void main(String[] args) {
       launch(args);
    }
}
