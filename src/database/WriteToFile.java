package database;

import utils.Categoris;
import utils.Item;
import utils.Order;
import utils.User;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Formatter;
import java.util.List;

public class WriteToFile {
    public void writeItems(String path, List<Item> items) {
        // write to file
        // items separated by ***
        // id, name, category, subCategory, imageUrl, price, rating, quantity, description, properties, colors, sizes, sellerName
        try {
            Formatter formatter = new Formatter(path);
            for (Item item : items) {
                formatter.format("%d\n", item.id);
                formatter.format("%s\n", item.name);
                formatter.format("%s\n", item.category);
                formatter.format("%s\n", item.subCategory);
                formatter.format("%s\n", item.imageUrl);
                formatter.format("%f\n", item.price);
                formatter.format("%f\n", item.rating);
                formatter.format("%d\n", item.quantity);
                formatter.format("%s\n", item.description);
                formatter.format("%s\n", item.propertiesToString());
                formatter.format("%s\n", item.colorsToString());
                formatter.format("%s\n", item.sizesToString());
                formatter.format("%s\n", item.sellerName);
                formatter.format("***\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeCategories(String path, List<Categoris> categories) {
        try {
            Formatter formatter = new Formatter(path);
            for (Categoris category : categories) {
                formatter.format("%s\n", category.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeUsers(String path, List<User> users) {
        try {
            Formatter formatter = new Formatter(path);
            for (User user : users) {
                formatter.format("%s\n", user.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void writeOrders(String path, List<Order> orders) {
        try {
            Formatter formatter = new Formatter(path);
            for (Order order : orders) {
                formatter.format("%s\n", order.toString());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
