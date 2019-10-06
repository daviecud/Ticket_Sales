package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Controller.CustomerController;
import sample.Controller.StandController;
import sample.Database.DBConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Database.DBConnector.dbExecuteQuery;

public class StandDAO {

    public static void updateStandDetails(int id, String standName, int capacity, double ticketPrice) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE stands SET stand_name = '"+standName+"', capacity = '"+capacity+"', ticket_price = '"+ticketPrice+"' WHERE id = '"+id+"' ";

        try {
            dbExecuteQuery(sql);
        }catch (SQLException e) {
            System.out.println("Error occurred during update of the record to database");
            e.printStackTrace();
        }
    }

    public static void addNewStand(String standName, int capacity, double ticketPrice) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO stands (stand_name, capacity, ticket_price) VALUES ('"+standName+"','"+capacity+"','"+ticketPrice+"')";
        try {
            dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error occurred during add new stand to database"+e);
            e.printStackTrace();
            throw e;
        }
    }

    public static ObservableList<StandModel> getAllStands() throws SQLException, ClassNotFoundException {
        String sql = "select * from stands";

        try {
            ResultSet rst = DBConnector.dbExecute(sql);
            ObservableList<StandModel> list = getStandObject(rst);
            return list;
        } catch (SQLException e) {
            System.out.println("Error occurred during getting data"+e);
            e.printStackTrace();
            throw e;
        }
    }

    private static ObservableList<StandModel> getStandObject(ResultSet rst) throws SQLException {

        ObservableList<StandModel> standList = FXCollections.observableArrayList();
        try {
            while (rst.next()) {
                StandModel stand = new StandModel();
                stand.setId(rst.getInt("id"));
                stand.setStandName(rst.getString("stand_name"));
                stand.setCapacity(rst.getInt("capacity"));
                stand.setTicketPrice(rst.getDouble("ticket_price"));

                standList.add(stand);
            }
            return standList;
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
