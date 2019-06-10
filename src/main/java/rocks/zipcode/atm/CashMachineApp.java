package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import rocks.zipcode.atm.bank.AccountData;
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

import javafx.scene.control.Label;  // Alex added
import javafx.scene.layout.Pane;  // Alex added
import javafx.scene.paint.Color;  // Alex added

import java.util.ArrayList;
//import javafx.scene.shape.Circle;  // Alex added
//import javafx.scene.shape.Line;  // Alex added                                    // Alex added
//import javafx.scene.shape.Rectangle;  // Alex added

/**
 * @author ZipCodeWilmington
 */
public class CashMachineApp extends Application {

    private TextField field = new TextField();
    private CashMachine cashMachine = new CashMachine(new Bank());
    private ObservableList<Integer> options = FXCollections.observableArrayList();

    private Parent createContent() {

        FlowPane flowpane = new FlowPane();
        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);
        TextArea areaInfo = new TextArea();
        updateAccountListSelector(options);
        ComboBox<Integer> comboBox = new ComboBox<>(options);
        comboBox.getSelectionModel().selectFirst();
//      Menu m=new Menu("Accounts")  ;
//      Integer [] acctIdList = cashMachine.getAccountIdList();
//      MenuItem[] menuItems = new MenuItem[acctIdList.length];
//        Label l = new Label("\t" + "  select");
//      EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
//            public void handle(ActionEvent e)
//            {
//                l.setText(((MenuItem)e.getSource()).getText());
//            }};
//        for (int i = 0; i < acctIdList.length ; i++) {
//            menuItems[i]=new MenuItem(String.format("%10d",acctIdList[i]));
//          //  m.getItems().add(menuItems[i]);
//            event = new EventHandler<ActionEvent>() {
//                public void handle(ActionEvent e)
//                {
//                   // field.setText(acctIdList[i].toString());
//                }};
//            (menuItems[i]).setOnAction(event);
//        }
//        MenuItem m1 = new MenuItem("        1000");
//        MenuItem m2 = new MenuItem("        2000");
//        MenuItem m3 = new MenuItem("        1800");
//        MenuItem m4 = new MenuItem("        2500");
//        MenuItem m5 = new MenuItem("        2100");
//
//        m.getItems().add(m1);
//        m.getItems().add(m2);
//        m.getItems().add(m3);
//        m.getItems().add(m4);
//        m.getItems().add(m5);



//        m1.setOnAction(event);
//        m2.setOnAction(event);
//        m3.setOnAction(event);
//        m4.setOnAction(event);
//        m5.setOnAction(event);
//        MenuBar mb=new MenuBar();
//        mb.getMenus().add(m);
//       // VBox vb1=new VBox(mb,l);
//        VBox vb1=new VBox(mb);
//m.setOnAction(e -> isInt(field.getText()));
//        //Scene sc=new Scene(vb1);
////Stage.setScene(sc);
//        try{
//        m.setOnAction(e -> {
//            int id = Integer.parseInt(field.getText());
//            cashMachine.login(id);
//            areaInfo.setText(cashMachine.toString());
//        });



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
            comboBox.getSelectionModel().selectFirst();
            areaInfo.setText(cashMachine.toString());
        });

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
            //  int id = Integer.parseInt(field.getText());
            int id = comboBox.getValue();
            cashMachine.login(id);
            Node[] toShow = {btnExit,btnDeposit,btnWithdraw};
            setVisibleOnLogin(toShow);
            comboBox.getSelectionModel().selectFirst();
            areaInfo.setText(cashMachine.toString());
        });

        Button btnNewAcct = new Button("Add New Account");
        btnNewAcct.setOnAction(e ->{
            AddNewAccountDialog newAccDg = new AddNewAccountDialog();
            newAccDg.newAccount(cashMachine);
            updateAccountListSelector(options);
            comboBox.getSelectionModel().selectFirst();
        });

        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(comboBox);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        flowpane.getChildren().add(btnNewAcct);
        vbox.getChildren().addAll(field, flowpane, areaInfo);
        return vbox;
    }

    public void setVisibleOnLogin(Node[] args){
        if(cashMachine.isAccountData()) {
            for (Node node:args) {
                node.setVisible(true);
            }
        }
        updateAccountListSelector(options);
    }

    public void setVisibleOnExit(Node[] args){
        for (Node node:args) {
            node.setVisible(false);
        }
        updateAccountListSelector(options);
    }

    private boolean isInt(String text) {
        try{
            int id=Integer.parseInt(text);
        }catch (NumberFormatException e){
            System.out.println("Enter a number");
        }return true;
    }

    private void updateAccountListSelector(ObservableList<Integer> options){
        Integer [] acctIdList = cashMachine.getAccountIdList();
        options.clear();
        for (int i = 0; i <acctIdList.length ; i++) {
            options.add(acctIdList[i]);
        }
    }


    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent()));
        initUI(stage);                                  // Alex added
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }



 //       public Parent initUI() {
       public Parent initUI(Stage stage) {

        Pane root = new Pane();
        TextArea areaInfo = new TextArea();
        Label accountIdLabel1 = new Label("Account Number: ");
        Label accountTypeLabel1 = new Label("Account Type: ");
           Label clientNameLabel1 = new Label("Client Name: ");
           Label clientEmailLabel1 = new Label("Client Email: ");
           Label accountBalanceLabel1 = new Label("Account Balance: ");
           Label accountIdLabel2 = new Label("Account Number: ");
           Label accountTypeLabel2 = new Label("Account Type: ");
           Label clientNameLabel2 = new Label("Client Name: ");
           Label clientEmailLabel2 = new Label("Client Email: ");
           Label accountBalanceLabel2 = new Label("Account Balance: ");
       //    Label creditScoreLabel = new Label("Your Credit Score is: ");
      //     Label splashLabel = new Label("Welcome");
           Label accountLabel = new Label("Enter your account number");
           Button btnDeposit = new Button("Deposit");
           Button btnWithdraw = new Button("Withdraw");
           Button btnExit = new Button("Logout");
           Button btnSubmit = new Button("Open Account");
           Button btnNewAcct = new Button("Add New Account");




           Node[] allControls = {
                   btnSubmit,
                   btnDeposit,
                   btnWithdraw,
                   btnExit,
                   accountLabel,
                   field,
                   accountIdLabel1,
                   accountTypeLabel1,
                   clientNameLabel1,
                   clientEmailLabel1,
                   accountBalanceLabel1,
                   accountIdLabel2,
                   accountTypeLabel2,
                   clientNameLabel2,
                   clientEmailLabel2,
                   btnNewAcct,
                   accountBalanceLabel2
           };

           areaInfo.setLayoutX(0);
            areaInfo.setLayoutY(375);
            areaInfo.setVisible(true);



           accountIdLabel1.relocate(100, 50);
           accountIdLabel1.setDisable(false);
           accountIdLabel1.setVisible(false);



           accountTypeLabel1.relocate(100, 100);
           accountTypeLabel1.setDisable(false);
           accountTypeLabel1.setVisible(false);


              // Alex added
           clientNameLabel1.relocate(100, 150);
           clientNameLabel1.setDisable(false);
           clientNameLabel1.setVisible(false);


              // Alex added
           clientEmailLabel1.relocate(100, 200);
           clientEmailLabel1.setDisable(false);
           clientEmailLabel1.setVisible(false);



           accountBalanceLabel1.relocate(100, 250);
           accountBalanceLabel1.setDisable(false);
           accountBalanceLabel1.setVisible(false);



               // Alex added
           accountIdLabel2.relocate(300, 50);
           accountIdLabel2.setDisable(false);
           accountIdLabel2.setVisible(false);


               // Alex added
           accountTypeLabel2.relocate(300, 100);
           accountTypeLabel2.setDisable(false);
           accountTypeLabel2.setVisible(false);


               // Alex added
           clientNameLabel2.relocate(300, 150);
           clientNameLabel2.setDisable(false);
           clientNameLabel2.setVisible(false);


               // Alex added
           clientEmailLabel2.relocate(300, 200);
           clientEmailLabel2.setDisable(false);
           clientEmailLabel2.setVisible(false);


              // Alex added
           accountBalanceLabel2.relocate(300, 250);
           accountBalanceLabel2.setDisable(false);
           accountBalanceLabel2.setVisible(false);









/*
           int creditScore = (int) (548 * Math.random()) + 301;
           // Alex added
        creditScoreLabel.relocate(50.0, 20.0);
        creditScoreLabel.setDisable(false);
        creditScoreLabel.setVisible(false);



           // Alex added
        splashLabel.relocate(40, 15);
        splashLabel.setScaleX(2.0);
        splashLabel.setScaleY(2.0);
        splashLabel.setDisable(false);
        splashLabel.setVisible(false);

*/







           // Alex added
        accountLabel.relocate(160, 125);

        field.relocate(160, 150);

        btnNewAcct.relocate(300, 300);





           btnNewAcct.setOnAction(e ->{
               AddNewAccountDialog newAccDg = new AddNewAccountDialog();
               newAccDg.newAccount(cashMachine);
           });




           Double w = 200.0;


           btnDeposit.setLayoutX(w);
           btnDeposit.setLayoutY(330);
           btnDeposit.setOnAction(e -> {
               int amount = Integer.parseInt(field.getText());
               cashMachine.deposit(amount);

               areaInfo.setText(cashMachine.toString());
           });


           w = btnDeposit.getWidth() + 30.0;


           btnWithdraw.setLayoutX(300);
           btnWithdraw.setLayoutY(330);
           btnWithdraw.setOnAction(e -> {
               int amount = Integer.parseInt(field.getText());
               cashMachine.withdraw(amount);

               areaInfo.setText(cashMachine.toString());
           });

           w = btnWithdraw.getWidth() + 30.0;


           final Node[] toShowOnExit = new Node[]{
                   field,
                   accountLabel,
                   btnNewAcct,
                   btnSubmit
           };


           btnNewAcct.setOnAction(e ->{
               AddNewAccountDialog newAccDg = new AddNewAccountDialog();
               newAccDg.newAccount(cashMachine);
           });


           btnExit.setLayoutX(400);
           btnExit.setLayoutY(330);

           btnExit.setOnAction(e -> {
               cashMachine.exit();



               showAndHide(allControls,toShowOnExit);



               areaInfo.setText(cashMachine.toString());
           });





           final Node[] toShowOnLogin = new Node[]{
                   btnDeposit,
                   btnWithdraw,
                   btnExit,
                   accountIdLabel1,
                   accountTypeLabel1,
                   clientNameLabel1,
                   clientEmailLabel1,
                   accountBalanceLabel1,
                   accountIdLabel2,
                   accountTypeLabel2,
                   clientNameLabel2,
                   clientEmailLabel2,
                   accountBalanceLabel2
           };
          btnSubmit.setLayoutX(220);
          btnSubmit.setLayoutY(210);
          //accountBalanceLabel1.setText(areaInfo.getText());
           btnSubmit.setOnAction(e -> {
               int id = Integer.parseInt(field.getText());
               cashMachine.login(id);



           //    areaInfo.setText(cashMachine.toString());


            showAndHide(allControls,toShowOnLogin);


              /*  btnSubmit.setVisible(false);
               accountLabel.setVisible(false);
               field.setVisible(false);

               accountIdLabel1.setVisible(true);
               accountTypeLabel1.setVisible(true);
               clientNameLabel1.setVisible(true);
               clientEmailLabel1.setVisible(true);
               accountBalanceLabel1.setVisible(true);


               accountIdLabel2.setVisible(true);
               accountTypeLabel2.setVisible(true);
               clientNameLabel2.setVisible(true);
               clientEmailLabel2.setVisible(true);
               accountBalanceLabel2.setVisible(true);

               btnDeposit.setVisible(true);
               btnWithdraw.setVisible(true);
               btnExit.setVisible(true);
*/



                String parts = cashMachine.toString();

                Integer i1 = parts.indexOf("(01)");
               Integer i2 = parts.indexOf("(02)");
               Integer i3 = parts.indexOf("(03)");
               Integer i4 = parts.indexOf("(04)");
               Integer i5 = parts.indexOf("(05)");
               Integer i6 = parts.indexOf("(06)");




               accountIdLabel2.setText(parts.substring(i1+4, i2));
               clientNameLabel2.setText(parts.substring(i2+4, i3));
               clientEmailLabel2.setText(parts.substring(i3+4, i4));
               accountBalanceLabel2.setText("$" + parts.substring(i4+4, i5));
               accountTypeLabel2.setText(parts.substring(i5+4, i6));





           });




            btnDeposit.setVisible(false);
            btnWithdraw.setVisible(false);
            btnExit.setVisible(false);



           root.getChildren().addAll(accountIdLabel1, accountTypeLabel1, accountBalanceLabel1, clientNameLabel1, clientEmailLabel1);
           root.getChildren().addAll(accountIdLabel2, accountTypeLabel2, accountBalanceLabel2, clientNameLabel2, clientEmailLabel2);
           root.getChildren().addAll(accountLabel, field, btnSubmit, areaInfo);
           root.getChildren().addAll(btnDeposit, btnWithdraw, btnExit, btnNewAcct);


        Scene scene = new Scene(root, 500, 400, Color.WHITESMOKE);

            stage.setTitle("Welcome to ZipCloudBank");
            stage.setScene(scene);
            stage.show();

        return root;







    }
    public void showAndHide(Node[] toHide, Node[] toShow) {

        for (Node node : toHide) {
            node.setVisible(false);
        }

        for (Node node : toShow) {
            node.setVisible(true);
        }
    }






}
