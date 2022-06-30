package database;

import utils.Categoris;
import utils.Item;
import utils.Order;
import utils.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

// singleton class
public class DataBase {
    static DataBase database;
    ReadFromFile readFromFile = new ReadFromFile();
    WriteToFile writeToFile = new WriteToFile();
    DataToString dataToString = new DataToString();
    public Map<Integer, Item> items = new HashMap<Integer, Item>();
    public Map<Integer, User> users;
    public Map<String, Order> orders;
    List<Categoris> categories;


    private DataBase() {
        for (Item i : readFromFile.readItems("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Items.txt")) {
            items.put(i.id, i);
        }
        categories = readFromFile.readCategories("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Categories.txt");
        users = readFromFile.readAllUsers("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Users.txt");
        orders = readFromFile.readAllOrders("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Orders.txt");
    }

    public static DataBase getInstance() {
        if (database == null) {
            database = new DataBase();
        }
        return database;
    }

    List<Item> getItems() {
        return new ArrayList<>(items.values());
    }

    List<Categoris> getCategories() {
        if (categories == null) {
            return categories = readFromFile.readCategories("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Categories.txt");
        }
        return categories;
    }

    public static void main(String[] args) {
        database = DataBase.getInstance();
        //test users
    }

    public List<User> getUsers() {
        return new ArrayList<>(users.values());
    }

    public List<Order> getOrders() {
        return new ArrayList<>(orders.values());
    }

    public void save() {
        writeToFile.writeItems("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Items.txt", getItems());
        writeToFile.writeCategories("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Categories.txt", categories);
        writeToFile.writeUsers("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Users.txt", getUsers());
        writeToFile.writeOrders("E:\\Aria\\T2\\AP\\ServerForOnlineShop\\src\\data\\Orders.txt", getOrders());
    }
}

