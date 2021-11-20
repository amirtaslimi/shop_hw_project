package model;

import java.util.ArrayList;

public class User {
    public final int id;
    private static int helpId;
    public String userName;
    public String userPass;


    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
        id = getNewId();
    }
    public ArrayList<Integer> shopinListsId = new ArrayList<Integer>();
    public ArrayList<ShopinList> shopinLists = new ArrayList<>();

    private int getNewId() {
        helpId++;
        return helpId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", userPass='" + userPass + '\'' +
                ", shopinListsId=" + shopinListsId +
                ", shopinLists=" + shopinLists +
                '}';
    }
}
