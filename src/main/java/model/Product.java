package model;

public class Product {
    public String productName;
    public int cost;


    public Product(String name, int cost) {
        this.productName = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + productName + '\'' +
                ", cost=" + cost +
                '}';
    }
}
