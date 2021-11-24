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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

import java.io.IOException;
import java.util.Objects;
import java.util.ResourceBundle;
import java.util.Scanner;

public class MainPageUserController {


    @FXML
    private MenuButton shipingStatus;
    @FXML
    private TableView<String> tableViewUser;
    @FXML
    private TableView<String> tableViewUser1;
    @FXML
    private TableColumn<String, String> shoplists;
    @FXML
    private TableColumn<String, String> userShowProduct;
    @FXML
    private TextField addProductshoplist;
    @FXML
    private TextField userRemoveProduct;
    @FXML
    private TextField shoplistID;
    @FXML
    private Text shoplistWithIDShow;
    //users func
    @FXML
    void addProductShoplist(ActionEvent event) {
        for (ShopinList shp:Data.shopinLists) {
            if (shp.id==Data.userIdShoplist){
                for (Product prod:Data.products) {
                    if (Objects.equals(prod.productName, addProductshoplist.getText())){
                        shp.listProducts.add(prod);
                    }
                }
            }
        }
    }
    @FXML
    void userRemoveProduct(ActionEvent event){
        for (ShopinList shp:Data.shopinLists) {
            if (shp.id==Data.userIdShoplist){
                for (Product prod:Data.products) {
                    if (Objects.equals(prod.productName, addProductshoplist.getText())){
                        shp.listProducts.remove(prod);
                    }
                }
            }
        }
    }
    @FXML
    void createShoplists(ActionEvent event) {
        for (User usr:Data.users) {
            if (Data.userIduse==usr.id){
                ShopinList shopinList = new ShopinList();
                Data.shopinLists.add(shopinList);
                System.out.println("exit:0,add product:1,remove product:2");
                usr.shopinLists.add(shopinList);
                usr.shopinListsId.add(shopinList.id);
                Data.userIdShoplist=shopinList.id;
            }
        }
    }
    @FXML
    void showShoplists(ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        for (User usr: Data.users) {
            if (usr.id==Data.userIduse){
                for (ShopinList shp: usr.shopinLists) {
                    list.add(shp.toString());
                }
            }
        }
        ObservableList<String> details = FXCollections.observableArrayList(list);
        shoplists.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableViewUser.setItems(details);
    }
    @FXML
    void userShowProduct(ActionEvent event){
        System.out.println(Data.products.toString());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < Data.products.size(); i++) {
            list.add(Data.products.get(i).toString());
        }
        ObservableList<String> details = FXCollections.observableArrayList(list);
        userShowProduct.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableViewUser1.setItems(details);
    }
    @FXML
    void shoplistWithID(ActionEvent event) {
        for (ShopinList shp:Data.shopinLists) {
            if (shp.id==Integer.parseInt(shoplistID.getText())){
                shoplistWithIDShow.setText(shp.toString());
            }
        }

    }

}
