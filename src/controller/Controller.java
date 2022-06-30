package controller;

import database.DataBase;
import database.DataToString;
import database.ReadFromFile;
import utils.Item;
import utils.ItemInCart;
import utils.Order;
import utils.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {
    DataBase dataBase = DataBase.getInstance();
    ReadFromFile readFromFile = new ReadFromFile();
    DataToString dataToString = new DataToString();

    public String run(String message) {
        Scanner scanner = new Scanner(message);
        String command = scanner.next();
        if (command.equals("get")) {
            String next = scanner.next();
            if (next.startsWith("categories")) {
                {
                    System.out.println(dataToString.AllCategoriesToString());
                    return dataToString.AllCategoriesToString();
                }
            } else if (next.startsWith("items")) {
                String subCategory = scanner.next();
                return dataToString.AllItemsToString((i) -> i.subCategory.equals(subCategory));
            } else if (next.startsWith("users")) {
                return "users";
            } else {
                return "error";
            }
        } else if (command.equals("test"))
            return "yes";
        else if (command.equals("signUp")) {
            return signUp(scanner);
            // make a new user
        } else if (command.equals("order")) {
            return order(scanner);
        } else if (command.equals("newItem"))
            return newItem(scanner);
        else if (command.equals("logIn"))
            return logIn(scanner);
        return "error";
    }

    private String logIn(Scanner scanner) {
        //email, password
        String email = scanner.next();
        String password = scanner.next();
        User user = dataBase.getUsers().stream().filter(u -> u.email.equals(email) && u.password.equals(password)).findFirst().orElse(null);
        if (user == null)
            return "error";
        //
        return user.id + "\n" + user.name + "\n" + user.email + "\n" + user.password + "\n" + user.phone;
    }

    private String newItem(Scanner scanner) {
        ArrayList<String> information = new ArrayList<>();
        while (scanner.hasNextLine()) {
            information.add(scanner.nextLine());
        }
        String sellerName = dataBase.users.get(Integer.parseInt(information.get(information.size() - 1))).name;
        information.remove(information.size() - 1);
        information.add(sellerName);
        Item item = readFromFile.parseItem(information);
        item.id = dataBase.items.size() + 1;
        dataBase.items.put(item.id, item);
        return "" + item.id;
    }

    private String order(Scanner scanner) {
        scanner.next();
        int userId = scanner.nextInt();
        scanner.next();
        ArrayList<ItemInCart> items = new ArrayList<>();
        while (scanner.hasNext()) {
            //itemId, quantity_in_cart, color, size
            ArrayList<String> list = readFromFile.readANoTileList(scanner.next());
            int itemId = Integer.parseInt(list.get(0));
            int quantity = Integer.parseInt(list.get(1));
            String color = list.get(2);
            String size = list.get(3);
            Item item = dataBase.items.get(itemId);
            ItemInCart itemInCart = new ItemInCart(item, quantity, color, size);
            items.add(itemInCart);
        }
        User user = dataBase.users.get(userId);
        Order order = new Order(user, items);
        return order.id;
    }

    String signUp(Scanner scanner) {
        String info = scanner.next();
        List<String> list = readFromFile.readAList("information", info);
        //name, phone, email, password
        // check if the user already exists (email, phone)
        if (dataBase.getUsers().stream().filter((u) -> u.email.equals(list.get(2))).findFirst().isPresent()) {
            return "error email";
        }
        if (dataBase.getUsers().stream().filter((u) -> u.phone.equals(list.get(1))).findFirst().isPresent()) {
            return "error phone";
        } else {
            //generate id
            int id = dataBase.getUsers().size() + 1;
            dataBase.getUsers().add(new User(id, list.get(0), list.get(1), list.get(2), list.get(3)));
            return "success " + id;
        }
    }

    private String sendCategories() {
        // send the categories to the client from data\categories.txt
        return "";
    }
}
