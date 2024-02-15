package com.example.lab1_lavanya;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public TableView<Employee> tableView;
    @FXML
    public TableColumn<Employee, Integer> tableId;
    @FXML
    public TableColumn<Employee, String> tableFirstName;
    @FXML
    public TableColumn<Employee, String> tableLastName;
    @FXML
    public TableColumn<Employee, String> tablePosition;

    ObservableList<Employee> employeeList = FXCollections.observableArrayList();

    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableId.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
        tableFirstName.setCellValueFactory(new PropertyValueFactory<Employee, String>("firstName"));
        tableLastName.setCellValueFactory(new PropertyValueFactory<Employee, String>("lastName"));
        tablePosition.setCellValueFactory(new PropertyValueFactory<Employee, String>("position"));
        tableView.setItems(employeeList);
    }

    private static final String url = "jdbc:mysql://localhost:3306/employeeFXLab1";
    private static final String username = "root";
    private static final String password = "Oreo@2004";


    @FXML
    protected void showDatabaseData() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.cj.sql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            String query = "SELECT * FROM employee";
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                String position = resultSet.getString("position");
                System.out.println(id + firstName + lastName + position);
                tableView.getItems().add(new Employee(id,firstName,lastName,position));


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
