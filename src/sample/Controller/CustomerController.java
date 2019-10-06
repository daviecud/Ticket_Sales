package sample.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.CustomerDAO;
import sample.Model.CustomerModel;

import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class CustomerController {


    @FXML
    public TableView cust_table;
    @FXML
    public TableColumn<CustomerModel, Integer> col_id;
    @FXML
    public TableColumn<CustomerModel, String> col_fname;
    @FXML
    public TableColumn<CustomerModel, String> col_lname;
    @FXML
    public TableColumn<CustomerModel, Date> col_dob;
    @FXML
    public TableColumn<CustomerModel, String> col_addr;
    @FXML
    public TableColumn<CustomerModel, String> col_city;
    @FXML
    public TableColumn<CustomerModel, String> col_country;
    @FXML
    public TableColumn<CustomerModel, String> col_postC;
    @FXML
    public TableColumn<CustomerModel, String> col_email;
    public TextField field_fname;
    public TextField field_lname;
    public TextField field_addr;
    public TextField field_city;
    public TextField field_country;
    public TextField field_postC;
    public TextField field_email;
    public Button updateCustButton;
    public Button deleteCustButton;
    public Button standsButton;
    public Button salesButton;
    public Button dashboardButton;
    public DatePicker dob_picker;
    public TextField field_custId;
    public Button searchButton;
    public Button allCustButton;
    public Button addCustButton;

    @FXML
    private void initialize() throws Exception {

        //TODO fix dob cell
        col_id.setCellValueFactory(cellData -> cellData.getValue().idProperty().asObject());
        col_fname.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        col_lname.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        col_dob.setCellValueFactory(new PropertyValueFactory<>("dob"));
        col_addr.setCellValueFactory(cellData -> cellData.getValue().addressProperty());
        col_city.setCellValueFactory(cellData -> cellData.getValue().cityProperty());
        col_country.setCellValueFactory(cellData -> cellData.getValue().countryProperty());
        col_postC.setCellValueFactory(cellData ->  cellData.getValue().postCodeProperty());
        col_email.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        ObservableList<CustomerModel> list = CustomerDAO.getAllCustomers();

        populateTable(list);

    }

    private void populateTable(ObservableList<CustomerModel> list) {
        cust_table.setItems(list);
    }

    //TODO create methods for update, delete, search and add customer

    @FXML
    private void deleteCustomerById() throws SQLException, ClassNotFoundException {
        try {
            CustomerDAO.deleteCustomerById(Integer.parseInt(field_custId.getText()));

            ObservableList<CustomerModel> custList = CustomerDAO.getAllCustomers();
            populateTable(custList);
            System.out.println("Successful deletion entry from database");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @FXML
    private void insertCustomer() throws SQLException, ClassNotFoundException {
//TODO CHANGE DATE in all classes to sql.Date!! GoodNight

        try {
            CustomerDAO.insertCustomer(field_fname.getText(), field_lname.getText(), Date.valueOf(dob_picker.valueProperty().get()), field_addr.getText(), field_city.getText(),
                    field_country.getText(), field_postC.getText(), field_email.getText());

            //Below will populate the table with a new version of the database after adding a new customer
            ObservableList<CustomerModel> custList = CustomerDAO.getAllCustomers();
            populateTable(custList);
            System.out.println("Successful new entry to database");
        } catch (SQLException e) {
            System.out.println("Error occurred during adding new entry to database" +e);
            e.printStackTrace();
            throw e;
        }
    }

    public void goToStands(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent standsView = FXMLLoader.load(getClass().getResource("../View/stands.fxml"));
        Scene standsScene = new Scene(standsView);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();
        window.setScene(standsScene);
        window.show();
    }

    public void goToDashboard(javafx.event.ActionEvent actionEvent) throws IOException {
        Parent dashboardView = FXMLLoader.load(getClass().getResource("../View/customers.fxml"));
        Scene dashboardScene = new Scene(dashboardView);

        Stage window = (Stage) ((Node)actionEvent.getSource()).getScene().getWindow();

        window.setScene(dashboardScene);
        window.show();
    }
}
