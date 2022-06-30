package utils;

import java.util.Date;
import java.util.ArrayList;

public class Order {

    public String id;
    User user;
    ArrayList<ItemInCart> products;
    Date date;
    OrderStatus status;

    public Order(User user, ArrayList<ItemInCart> products) {
        this.user = user;
        this.products = products;
        this.date = new Date();
        // generate id
        this.id = user.id + "-" + date.getTime();
    }
    String getId() {
        return id;
    }
}