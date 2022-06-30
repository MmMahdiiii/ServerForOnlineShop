package utils;

import java.util.Date;
import java.util.ArrayList;

public class Order {

    public String id;
    User user;
    ArrayList<ItemInCart> products;


    public Order(User user, ArrayList<ItemInCart> products) {
        this.user = user;
        this.products = products;
        // generate id
        this.id = user.id + "-" + new Date().getTime();
    }

    public Order(String id, User user, ArrayList<ItemInCart> products) {
        this.id = id;
        this.user = user;
        this.products = products;
    }

    String getId() {
        return id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\n");
        sb.append(user.id).append("\n");
        for (ItemInCart item : products) {
            // [id, quantity_in_cart, color, size]
            sb.append("[").append(item.id).append(',' + item.quantity_in_cart).append(',').append(item.color).
                    append(',').append(item.size).append("]\n");
        }
        return sb.toString();
    }


}