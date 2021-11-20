package model;

public class Seller {
    final int id;
    private static int helpId;
    public String sellerName;
    public String sellerPass;

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", sellerName='" + sellerName + '\'' +
                ", sellerPass='" + sellerPass + '\'' +
                '}';
    }

    public Seller(String sellerName, String sellerPass) {
        this.sellerName = sellerName;
        this.sellerPass = sellerPass;
        id = getNewId();
    }
    private int getNewId() {
        helpId++;
        return helpId;
    }
}
