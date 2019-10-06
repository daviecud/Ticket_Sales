package sample.Controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.swing.interop.SwingInterOpUtils;
import sample.Database.DBConnector;
import sample.Model.StandDAO;
import sample.Model.StandModel;

import java.io.IOException;
import java.sql.SQLException;

public class StandController {

    @FXML
    public TableView table_stands;
    @FXML
    public TableColumn<StandModel, Integer> col_id;
    @FXML
    public TableColumn<StandModel, String> col_standName;
    @FXML
    public TableColumn<StandModel, String> col_capacity;
    @FXML
    public TableColumn<StandModel, String> col_ticketPrice;
    public TextField field_standName;
    public TextField field_capacity;
    public TextField field_ticketP;
    public Button newStandButton;
    public TextField newTicket_field;
    public TextField field_stand_id;
    public Button searchButton;
    public Button updateButton;
    public Button deleteButton;
    public Button allButton;
    public Button dashboardButton;
    public Button custButton;

    @FXML
    private void initialize() throws Exception {

        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_standName.setCellValueFactory(new PropertyValueFactory<>("standName"));
        col_capacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        col_ticketPrice.setCellValueFactory(new PropertyValueFactory<>("ticketPrice"));
        ObservableList<StandModel> list = StandDAO.getAllStands();
        populateTable(list);
        setCellValueFromTableToTextfield();
    }

    private void populateTable(ObservableList<StandModel> list) {
        table_stands.setItems(list);
    }

    //TODO create insert, delete, update, and search methods

    private void setCellValueFromTableToTextfield() {
        table_stands.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                StandModel list = (StandModel) table_stands.getItems().get(table_stands.getSelectionModel().getSelectedIndex());
                field_stand_id.setText(String.valueOf(list.getId()));
                field_standName.setText(list.getStandName());
                field_capacity.setText(String.valueOf(list.getCapacity()));
                field_ticketP.setText(String.valueOf(list.getTicketPrice()));
            }
        });
    }
    @FXML
    private void addNewStand() throws SQLException, ClassNotFoundException {
        try {
            StandDAO.addNewStand(field_standName.getText(),Integer.parseInt(field_capacity.getText()),Double.parseDouble(field_ticketP.getText()));

            //Below will populate the table with a new version of the database after adding a new customer
            ObservableList standList = StandDAO.getAllStands();
            populateTable(standList);

            System.out.println("Success in adding new stand data to database");
        } catch (SQLException e) {
            System.out.println("Error occurred during the add new stand process");
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    public void updateStandDetails(ActionEvent actionEvent) throws SQLException, ClassNotFoundException{
        //1. Fill textfields with values selected from table
        //2. update database and table with new details after Update Button is clicked
        try {
            StandDAO.updateStandDetails(Integer.parseInt(field_stand_id.getText()), field_standName.getText(),Integer.parseInt(field_capacity.getText()), Double.parseDouble(field_ticketP.getText()));
            //get new data from database to re-populate the table
            ObservableList<StandModel> standList = StandDAO.getAllStands();
            populateTable(standList);
            System.out.println("Update to database has been completed");
        } catch (SQLException e) {
            System.out.println("Error occurred during update" +e);
            e.printStackTrace();
            throw e;
        }

    }

    @FXML
    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        Parent dashboardView = FXMLLoader.load(getClass().getResource("../View/customers.fxml"));
        Scene dashboardScene = new Scene(dashboardView);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(dashboardScene);
        window.show();
    }

    public void goToCustomers(ActionEvent actionEvent) throws IOException {
        Parent customersView = FXMLLoader.load(getClass().getResource("../View/customers.fxml"));
        Scene customersScene = new Scene(customersView);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(customersScene);
        window.show();
    }
}
