package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Database.DBConnector;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import static sample.Database.DBConnector.dbExecuteQuery;

public class CustomerDAO {

    public static void insertCustomer(String firstName, String lastName, Date dob, String address, String city,
                                      String country, String postCode, String email) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO customer(first_name, last_name, dob, address, city, post_code, email) VALUES ('"+firstName+"', '"+lastName+"', '"+dob+"', '"+address+"', '"+city+"', '"+country+"', '"+postCode+"', '"+email+"',)";
        try {
            dbExecuteQuery(sql);
        } catch (SQLException e) {
            System.out.println("Error during insert to database");
            e.printStackTrace();
            throw e;
        }


    }

    public static ObservableList<CustomerModel> getAllCustomers() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM customer";

        try {
            ResultSet rst = DBConnector.dbExecute(sql);
            ObservableList<CustomerModel> custList = getCustomersObject(rst);
            return custList;

        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    //Returns ObservableArrayList of customers, using a while loop to go through each customer and set values
    private static ObservableList<CustomerModel> getCustomersObject(ResultSet res) throws SQLException {
        ObservableList<CustomerModel> custList = FXCollections.observableArrayList();

        try {
            //while each customer from CustomerModel, do set the values to column name
            while (res.next()) {
                CustomerModel customer = new CustomerModel();
                customer.setId(res.getInt("id"));
                customer.setFirstName(res.getString("first_name"));
                customer.setLastName(res.getString("last_name"));
                customer.setDob(res.getDate("dob").toLocalDate());
                customer.setAddress(res.getString("address"));
                customer.setCity(res.getString("city"));
                customer.setCountry(res.getString("country"));
                customer.setPostCode(res.getString("post_code"));
                customer.setEmail(res.getString("email"));

                custList.add(customer);

            }
            return custList;
        } catch (SQLException e) {
            System.out.println("Error during reading of customer data");
            e.printStackTrace();
            throw e;
        }
    }
}
