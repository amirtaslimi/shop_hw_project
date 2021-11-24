package com.example.demo;
import java.net.URL;
import java.util.ArrayList;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainPageSellerController {




    //sellers param
    @FXML
    private TableColumn<String, String> tablecolumn;
    @FXML
    private TableColumn<String, String> tablecolumn1;
    @FXML
    private TableView<String> tableview;
    @FXML
    private TextField userRemove;
    @FXML
    private TextField ProductNameRemove;
    @FXML
    private TextField createProductCost;
    @FXML
    private TextField createProductName;
    @FXML
    private TextField shoplistID;
    @FXML
    private ChoiceBox<?> setStatus;
    //seller func
    @FXML
    void createProductPage(ActionEvent event) throws IOException {
        createProduct(createProductName.getText(), Integer.parseInt(createProductCost.getText()));
    }
    @FXML
    void showProduct(ActionEvent event) {
        System.out.println(Data.products.toString());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Data.products.size(); i++) {
            list.add(Data.products.get(i).toString());
        }
        ObservableList<String> details = FXCollections.observableArrayList(list);
        tablecolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableview.setItems(details);
    }
    @FXML
    void showUsers(ActionEvent event) {
        System.out.println(Data.users.toString());
        ArrayList<String> lis = new ArrayList<>();
        for (int i = 0; i < Data.users.size(); i++) {
            lis.add(Data.users.get(i).toString());
        }
        ObservableList<String> detail = FXCollections.observableArrayList(lis);
        tablecolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableview.setItems(detail);
    }
    @FXML
    void showAllShoplists(ActionEvent event){
        ArrayList<String> lis = new ArrayList<>();
        for (int i = 0; i < Data.shopinLists.size(); i++) {
            System.out.println(Data.shopinLists.get(i).toString());
            lis.add(Data.shopinLists.get(i).toString());
        }
        ObservableList<String> detail = FXCollections.observableArrayList(lis);
        tablecolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableview.setItems(detail);
    }
    @FXML
    void removeProduct(ActionEvent event) {
        Data.products.removeIf(prod -> Objects.equals(prod.productName, ProductNameRemove.getText()));
    }
    @FXML
    void removeUser(ActionEvent event) {
        for (User usr:Data.users) {
            if (Objects.equals(usr.userName, userRemove.getText())){
                Data.users.remove(usr);
            }
        }
    }
    @FXML
    void setStatus(ActionEvent event){
        for (ShopinList shp:Data.shopinLists) {
            if(shp.id==Integer.parseInt(shoplistID.getText())){
                shp.status = (String) setStatus.getValue();
            }
        }
        System.out.println(setStatus.getValue());
    }
    public void createProduct(String name, int cost) throws IOException {
        Product product = new Product(name, cost);
        Data.products.add(product);
    }


}
