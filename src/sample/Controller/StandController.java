package sample.Controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.Database.DBConnector;
import sample.Model.StandDAO;
import sample.Model.StandModel;

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
    }

    private void populateTable(ObservableList<StandModel> list) {
        table_stands.setItems(list);
    }

    //TODO create insert, delete, update, and search methods
}
