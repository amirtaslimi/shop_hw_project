package model;

import java.util.ArrayList;

public class ShopinList {
    public final int id;
    private static int helpId;
    public String status;
    public ArrayList<Product> listProducts = new ArrayList<>();
    private int numberOfProduct = listProducts.size();
    //private int costs = listProducts.get(0).cost;

    public ShopinList(String  status, int numberOfProduct) {
        this.status = status;
        this.listProducts = listProducts;
        this.numberOfProduct = numberOfProduct;
        id = getNewId();
    }
    public ShopinList(){
        this.listProducts = listProducts;
        this.numberOfProduct = numberOfProduct;
        id = getNewId();
    }




    @Override
    public String toString() {
        return "ShopinList{" +
                "id=" + id +
                ", status=" + status +
                ", listProducts=" + listProducts +
                ", numberOfProduct=" + numberOfProduct +
          //      ", costs=" + costs +
                '}';
    }

    private int getNewId() {
        helpId++;
        return helpId;
    }
}

