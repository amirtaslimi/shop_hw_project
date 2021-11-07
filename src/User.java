import java.util.ArrayList;

public class User {
    final int id;
    private static int helpId;
    String userName;
    String userPass;


    public User(String userName, String userPass) {
        this.userName = userName;
        this.userPass = userPass;
        id = getNewId();
    }
    ArrayList<Integer> shopinListsId = new ArrayList<Integer>();
    ArrayList<ShopinList> shopinLists = new ArrayList<>();

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
