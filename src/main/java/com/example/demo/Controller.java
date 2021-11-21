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

public class Controller implements Initializable {

    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<ShopinList> shopinLists = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Seller> sellers = new ArrayList<>();


    public Label productLabel;
    int kindFlag = 0;
    static int userIduse=-1;
    static int userIdShoplist=-1;

    //login page
    @FXML
    private Label liveStatus;
    @FXML
    private Label welcomeText;
    @FXML
    public PasswordField password;
    @FXML
    private TextField username;
    @FXML
    void login(ActionEvent event) throws IOException {
        if (kindFlag == 1) {
            if (userLogin(username.getText(), password.getText()) != null) {
                userPage();
            } else {
                liveStatus.setTextFill(Color.RED);
                liveStatus.setText("wrong");//problem
            }

        } else if (kindFlag==2){
            if (sellerLogin(username.getText(), password.getText()) != null) {
                sellerPage();
            } else {
                liveStatus.setTextFill(Color.RED);
                liveStatus.setText("wrong");//problem
            }
        }
        else {
            liveStatus.setTextFill(Color.RED);
            liveStatus.setText("firth select your kind");
        }
    }
    @FXML
    void register(ActionEvent event) throws IOException {

        if (kindFlag == 1) {
            userRegister(username.getText(), password.getText());
        }
        else if (kindFlag == 2){
            sellerRegister(username.getText(), password.getText());
        }
        else {
            liveStatus.setTextFill(Color.RED);
            liveStatus.setText("firth select your kind");
        }

    }
    public User userRegister(String username, String password) throws IOException {
        if (username == null || password == null) {
            liveStatus.setTextFill(Color.RED);
            liveStatus.setText("please fill boxes");
            return null;
        }
        User user = new User(username, password);
        users.add(user);
        userIduse=user.id;
        userPage();
        return user;
    }
    public static User userLogin(String username, String password) throws IOException {
        for (User usr : users) {
            if (password.equals(usr.userPass) && username.equals(usr.userName)) {
                System.out.println("welcome!!");
                return usr;
            }
        }

        return null;
    }
    public Seller sellerRegister(String username, String password) throws IOException {
        Seller seller = new Seller(username, password);
        sellers.add(seller);
        sellerPage();
        return seller;
    }
    public static Seller sellerLogin(String username, String password) throws IOException {
        for (Seller sllr : sellers) {
            if (password.equals(sllr.sellerPass) && username.equals(sllr.sellerName)) {
                return sllr;
            }
        }
        return null;
    }
    public void sellerCh(ActionEvent event) {
        kindFlag = 2;
    }
    public void userCh(ActionEvent event) {
        kindFlag = 1;
    }



    //user page things---------------------------------------------------------------------------
    //users param
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
    //users func
    @FXML
    void addProductShoplist(ActionEvent event) {
        for (ShopinList shp:shopinLists) {
            if (shp.id==userIdShoplist){
                shp.listProducts.add(products.get(Integer.parseInt(addProductshoplist.getText())));
                System.out.println("lkj");
            }
        }
    }
    @FXML
    void userRemoveProduct(ActionEvent event){
        for (ShopinList shp:shopinLists) {
            if (shp.id==userIdShoplist){
                shp.listProducts.remove(products.get(Integer.parseInt(addProductshoplist.getText())));
            }
        }
    }
    @FXML
    void createShoplists(ActionEvent event) {
        for (User usr:users) {
            if (userIduse==usr.id){
                ShopinList shopinList = new ShopinList();
                shopinLists.add(shopinList);
                System.out.println("exit:0,add product:1,remove product:2");
                usr.shopinLists.add(shopinList);
                usr.shopinListsId.add(shopinList.id);
                userIdShoplist=shopinList.id;
            }
        }
    }
    @FXML
    void showShoplists(ActionEvent event) {
        ArrayList<String> list = new ArrayList<>();
        for (User usr:users) {
            if (usr.id==userIduse){
                for (ShopinList shp: usr.shopinLists) {
                    list.add(shp.listProducts.toString());
                }
            }
        }
        ObservableList<String> details = FXCollections.observableArrayList(list);
        shoplists.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableViewUser.setItems(details);
    }
    @FXML
    void userShowProduct(ActionEvent event){
        System.out.println(products.toString());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            list.add(products.get(i).toString());
        }
        ObservableList<String> details = FXCollections.observableArrayList(list);
        userShowProduct.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableViewUser1.setItems(details);
    }

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
        System.out.println(products.toString());
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < products.size(); i++) {
            list.add(products.get(i).toString());
        }
        ObservableList<String> details = FXCollections.observableArrayList(list);
        tablecolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableview.setItems(details);
    }
    @FXML
    void showUsers(ActionEvent event) {
        System.out.println(users.toString());
        ArrayList<String> lis = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            lis.add(users.get(i).toString());
        }
        ObservableList<String> detail = FXCollections.observableArrayList(lis);
        tablecolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableview.setItems(detail);
    }
    @FXML
    void showAllShoplists(ActionEvent event){
        ArrayList<String> lis = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            lis.add(shopinLists.get(i).toString());
        }
        ObservableList<String> detail = FXCollections.observableArrayList(lis);
        tablecolumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        tableview.setItems(detail);
    }
    @FXML
    void removeProduct(ActionEvent event) {
        products.removeIf(prod -> Objects.equals(prod.productName, ProductNameRemove.getText()));
    }
    @FXML
    void removeUser(ActionEvent event) {
        for (User usr:users) {
            if (Objects.equals(usr.userName, userRemove.getText())){
                users.remove(usr);
            }
        }
    }
    @FXML
    void setStatus(ActionEvent event){
        for (ShopinList shp:shopinLists) {
            if(shp.id==Integer.parseInt(shoplistID.getText())){
                shp.status = (String) setStatus.getValue();
            }
        }
        System.out.println(setStatus.getValue());
    }




    //pages
    void sellerPage() throws IOException {
        AnchorPane root = FXMLLoader.load(this.getClass().getResource("mainPageSeller.fxml"));
        Stage stage = new Stage();
        stage.setTitle("seller");
        stage.setScene(new Scene(root));
        stage.show();
    }
    void userPage() throws IOException{
        AnchorPane root2 = FXMLLoader.load(this.getClass().getResource("mainPageUser.fxml"));
        Stage stage = new Stage();
        stage.setTitle("user");
        stage.setScene(new Scene(root2));
        stage.show();

    }


    //create and remove


    public static void createProduct(String name, int cost) throws IOException {
        Product product = new Product(name, cost);
        products.add(product);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (flagStatus==0) {
//            liveStatus.setText("status: please enter your information");
//        }
//        else liveStatus.setText("wrong");
    }
}
