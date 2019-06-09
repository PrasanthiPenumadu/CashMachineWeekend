package rocks.zipcode.atm;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import rocks.zipcode.atm.bank.Bank;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
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

        VBox vbox = new VBox(10);
        vbox.setPrefSize(600, 600);
        TextArea areaInfo = new TextArea();
        Integer [] acctIdList = cashMachine.getAccountIdList();
        ObservableList<Integer> options = FXCollections.observableArrayList();
        for (int i = 0; i <acctIdList.length ; i++) {
            options.add(acctIdList[i]);
        }
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

        Button btnSubmit = new Button("Set Account ID");
        btnSubmit.setOnAction(e -> {
          //  int id = Integer.parseInt(field.getText());
            int id = comboBox.getValue();
            cashMachine.login(id);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnDeposit = new Button("Deposit");
        btnDeposit.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.deposit(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnWithdraw = new Button("Withdraw");
        btnWithdraw.setOnAction(e -> {
            int amount = Integer.parseInt(field.getText());
            cashMachine.withdraw(amount);

            areaInfo.setText(cashMachine.toString());
        });

        Button btnExit = new Button("Exit");
        btnExit.setOnAction(e -> {
            cashMachine.exit();

            areaInfo.setText(cashMachine.toString());
        });

        FlowPane flowpane = new FlowPane();
          //  flowpane.getChildren().add(vb1);
        flowpane.getChildren().add(btnSubmit);
        flowpane.getChildren().add(comboBox);
        flowpane.getChildren().add(btnDeposit);
        flowpane.getChildren().add(btnWithdraw);
        flowpane.getChildren().add(btnExit);
        vbox.getChildren().addAll(field, flowpane, areaInfo);


        return vbox;
    }

    private boolean isInt(String text) {
        try{
            int id=Integer.parseInt(text);
        }catch (NumberFormatException e){
            System.out.println("Enter a number");
        }return true;
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
