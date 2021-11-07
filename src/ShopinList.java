import java.util.ArrayList;

public class ShopinList {
    final int id;
    private static int helpId;
    Status status;
    ArrayList<Product> listProducts = new ArrayList<>();
    private int numberOfProduct = listProducts.size();
    //private int costs = listProducts.get(0).cost;

    public ShopinList(Status status, int numberOfProduct) {
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

