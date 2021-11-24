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

public class LoginController implements Initializable {


    public Label productLabel;
    int kindFlag = 0;


    //login page param
    @FXML
    private Label liveStatus;
    @FXML
    private Label welcomeText;
    @FXML
    public PasswordField password;
    @FXML
    private TextField username;


    //login page func

    @FXML
    void login(ActionEvent event) throws IOException {
        if (kindFlag == 1) {
            if (userLogin(username.getText(), password.getText()) != null) {
                userPage();
            } else {
                liveStatus.setTextFill(Color.RED);
                liveStatus.setText("wrong information");//problem
            }

        } else if (kindFlag==2){
            if (sellerLogin(username.getText(), password.getText()) != null) {
                sellerPage();
            } else {
                liveStatus.setTextFill(Color.RED);
                liveStatus.setText("wrong information");//problem
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
            if (userRegister(username.getText(), password.getText()) != null) {
                userPage();
            }
        }
        else if (kindFlag == 2){
            if (sellerRegister(username.getText(), password.getText()) != null) {
                sellerPage();
            }
        }
        else {
            liveStatus.setTextFill(Color.RED);
            liveStatus.setText("firth select your kind");
        }

    }
    public User userRegister(String username, String password) throws IOException {
        if (username.isEmpty()|| password.isEmpty()) {
            liveStatus.setTextFill(Color.RED);
            liveStatus.setText("please fill boxes");
            System.out.println(username);
            return null;
        } else{
            for (User usr:Data.users) {
                if (username.equals(usr.userName)){
                    liveStatus.setTextFill(Color.RED);
                    liveStatus.setText("this username already exist");
                    return null;
                }
            }
        }
        User user = new User(username, password);
        Data.users.add(user);
        Data.userIduse=user.id;
        return user;
    }
    public User userLogin(String username, String password) throws IOException {
        for (User usr : Data.users) {
            if (password.equals(usr.userPass) && username.equals(usr.userName)) {
                liveStatus.setTextFill(Color.GREEN);
                liveStatus.setText("welcome!!");
                return usr;
            }
        }

        return null;
    }
    public Seller sellerRegister(String username, String password) throws IOException {
        if (username.isEmpty()|| password.isEmpty()) {
            liveStatus.setTextFill(Color.RED);
            liveStatus.setText("please fill boxes");
            System.out.println(username);
            return null;
        } else{
            for (Seller slr:Data.sellers) {
                if (username.equals(slr.sellerName)){
                    liveStatus.setTextFill(Color.RED);
                    liveStatus.setText("this username already exist");
                    return null;
                }
            }
        }
        Seller seller = new Seller(username, password);
        Data.sellers.add(seller);
        return seller;
    }
    public Seller sellerLogin(String username, String password) throws IOException {
        for (Seller sllr : Data.sellers) {
            if (password.equals(sllr.sellerPass) && username.equals(sllr.sellerName)) {
                liveStatus.setTextFill(Color.GREEN);
                liveStatus.setText("welcome!!");
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        if (flagStatus==0) {
//            liveStatus.setText("status: please enter your information");
//        }
//        else liveStatus.setText("wrong");
    }
}
