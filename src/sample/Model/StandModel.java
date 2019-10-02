package sample.Model;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.text.SimpleDateFormat;

public class StandModel {

    private SimpleIntegerProperty id;
    private SimpleStringProperty standName;
    private SimpleIntegerProperty capacity;
    private SimpleDoubleProperty ticketPrice;

    public StandModel() {
        this.id = new SimpleIntegerProperty();
        this.standName = new SimpleStringProperty();
        this.capacity = new SimpleIntegerProperty();
        this.ticketPrice = new SimpleDoubleProperty();
    }

    public int getId() {
        return id.get();
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getStandName() {
        return standName.get();
    }

    public SimpleStringProperty standNameProperty() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName.set(standName);
    }

    public int getCapacity() {
        return capacity.get();
    }

    public SimpleIntegerProperty capacityProperty() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity.set(capacity);
    }

    public double getTicketPrice() {
        return ticketPrice.get();
    }

    public SimpleDoubleProperty ticketPriceProperty() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice.set(ticketPrice);
    }

    @Override
    public String toString() {
        return "StandModel{" +
                "id=" + id +
                ", standName=" + standName +
                ", capacity=" + capacity +
                ", ticketPrice=" + ticketPrice +
                '}';
    }
}
