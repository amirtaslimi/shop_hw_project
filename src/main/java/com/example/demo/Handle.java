package com.example.demo;

import model.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Handle {
    static Scanner scan = new Scanner(System.in);
    public static ArrayList<Product> products = new ArrayList<>();
    public static ArrayList<ShopinList> shopinLists = new ArrayList<>();
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Seller> sellers =new ArrayList<>();











    public static void mainn(String[] args) throws IOException {
        int selctor=0;
        while (selctor!=1){
            System.out.println("choose");
            System.out.println("exit:1");
            System.out.println("user register:2");
            System.out.println("user login:3");
            System.out.println("seller register:4");
            System.out.println("seller login:5");
            selctor = Integer.parseInt(scan.nextLine());
            switch (selctor){
                case 1:
                    System.out.println("exit.");
                    break;
                case 2://user
                    userHandel(userRegister());
                    break;
                case 3:
                    userHandel(userLogin());
                    break;
                case 4:
                    sellerHandel(sellerRegister());
                    break;
                case 5:
                    sellerHandel(sellerLogin());
                    break;
                default:
                    System.out.println("wrong number!!");
            }

        }
    }

    //control
    public static void userHandel(User user) throws IOException {
        if (user != null) {
            int selector = 0;
            while (selector != 1) {
                System.out.println("choose for handle user");
                System.out.println("createShopinList:2");
                System.out.println("addProduct:3");
                System.out.println("showShopList:4");
                System.out.println("createProduct:5");
                System.out.println("");
                selector = Integer.parseInt(scan.nextLine());
                switch (selector) {
                    case 1:
                        System.out.println("exit");
                        break;
                    case 2:
                        createShopinList(user);
                        break;
                    case 3:
                        addProduct(user);
                        break;
                    case 4:
                        showShopList(user);
                        break;
                    case 5:
                        createProduct(scan.nextLine(), Integer.parseInt(scan.nextLine()));
                        break;
                    default:
                        System.out.println("wrong number!!");
                }
            }
        }
    }
    public static void sellerHandel (Seller seller)throws IOException{
        if (seller != null) {
            int selector = 0;
            while (selector != 1) {
                System.out.println("choose for handle seller");
                System.out.println("showProducts:2");
                System.out.println("showUsers:3");
                System.out.println("removeProduct:4");
                System.out.println("createProduct:5");
                System.out.println("removeUser:6");
                System.out.println("setStatus:7");
                System.out.println("");
                selector = Integer.parseInt(scan.nextLine());
                switch (selector) {
                    case 1:
                        System.out.println("exit");
                        break;
                    case 2:
                        showProducts();
                        break;
                    case 3:
                        showUsers();
                        break;
                    case 4:
                        removeProduct();
                        break;
                    case 5:
                        createProduct(scan.nextLine(), Integer.parseInt(scan.nextLine()));
                        break;
                    case 6:
                        removeUser();
                        break;
                    default:
                        System.out.println("wrong number!!");
                }
            }
        }
    }

    //information
    public static User userRegister()throws IOException {
        System.out.println("pls enter username password");
        User user = new User(scan.nextLine(), scan.nextLine());
        users.add(user);
        return user;
    }
    public static User userLogin()throws IOException {
        String username;
        String password;
        System.out.println("pls enter your username");
        username = scan.nextLine();
        System.out.println("pls enter your password");
        password = scan.nextLine();
        for(User usr:users){
            if (password.equals(usr.userPass) && username.equals(usr.userName)){
                System.out.println("welcome!!");
                return usr;
            }
        }

        System.out.println("not found!!");
        return null;
    }
    public static Seller sellerRegister()throws IOException{
        System.out.println("pls enter username password");
        Seller seller = new Seller(scan.nextLine(), scan.nextLine());
        sellers.add(seller);
        return seller;
    }
    public static Seller sellerLogin() throws IOException{
        String username;
        String password;
        System.out.println("pls enter your username");
        username = scan.nextLine();
        System.out.println("pls enter your password");
        password = scan.nextLine();
        for (Seller sllr : sellers) {
            if (password.equals(sllr.sellerPass) && username.equals(sllr.sellerName)) {
                return sllr;
            }
        }
        return null;
    }

    //create and remove
    public static void createShopinList(User user)throws IOException{
        ShopinList shopinList = new ShopinList();
        int num =1;
        int numProduct=0;
        while (num!=0){
            System.out.println("exit:0,add product:1,remove product:2");
            switch (num){
                case 1:
                    System.out.println("add product 1,"+products.size());
                    numProduct = Integer.parseInt(scan.nextLine());
                    shopinList.listProducts.add(products.get(numProduct));
                case 2:
                    System.out.println("remove product 1,"+user.shopinLists.size());
                    numProduct = Integer.parseInt(scan.nextLine());
                    user.shopinLists.remove(numProduct);
            }
        }
        user.shopinLists.add(shopinList);
        user.shopinListsId.add(shopinList.id);
    }
    public static void createProduct (String name,int cost)throws IOException{
        Product product = new Product(name, cost);
        products.add(product);
    }
    public static void addProduct(User user)throws IOException{
        System.out.println("add product 0,"+products.size());
        int num = Integer.parseInt(scan.nextLine());

    }
    public static void removeUser(){}
    public static void removeProduct(){
        String name;
        System.out.println("pls enter product name to delete");
        name = scan.nextLine();
        for(Product product:products){
            if (name == product.productName){
                System.out.println("it is done!");
            }
        }
        System.out.println("found nothing!!");
    }
    //show things
    public static void showShopList(User user)throws IOException{
        user.shopinLists.forEach(System.out::println);
    }
    public static void showProducts(){
        products.forEach(System.out::println);
    }
    public static void showUsers(){
        users.forEach(System.out::println);

    }

}
