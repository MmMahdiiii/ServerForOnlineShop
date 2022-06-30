package database;

import utils.Categoris;
import utils.Item;
import utils.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

public class ReadFromFile {
    public ArrayList<Item> readItems(String path) {
        // read from file
        // items separated by ***
        // id, name, category, subCategory, imageUrl, price, rating, quantity, description, properties, colors, sizes, sellerName
        ArrayList<Item> items = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(new File(path).toPath());
            for (int i = 0; i < lines.size(); i++) {
                ArrayList<String> itemInfo = new ArrayList<>();
                // add lines until "***" is found
                while (!lines.get(i).equals("***")) {
                    itemInfo.add(lines.get(i));
                    i++;
                }
                // create item
                Item item = parseItem(itemInfo);
                items.add(item);
            }
        } catch (IOException e) {
            System.out.println("Could not read from file" + path);
        }
        return items;
    }

    public Item parseItem(ArrayList<String> lines) {
        // parse item from lines
        // id, name, category, subCategory, imageUrl, price, rating, quantity, description, properties, colors, sizes, sellerName
        int id = Integer.parseInt(lines.get(0));
//        System.out.println(id);
        String name = lines.get(1);
//        System.out.println(name);
        String category = lines.get(2);
        String subCategory = lines.get(3);
        String imageUrl = lines.get(4);
        double price = Double.parseDouble(lines.get(5));
        double rating = Double.parseDouble(lines.get(6));
        int quantity = Integer.parseInt(lines.get(7));
        String description = lines.get(8);
        Map<String, String> properties = readAMap("properties", lines.get(9));
        ArrayList<String> colors = readAList("colors", lines.get(10));
        ArrayList<String> sizes = readAList("sizes", lines.get(11));
        String sellerName = lines.get(12);
        return new Item(id, name, category, subCategory, imageUrl, price, rating, quantity, description, properties, colors, sizes, sellerName);
    }

    String readFile(String path) {
        try {
            Scanner scanner = new Scanner(new File(path));
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNextLine()) {
                stringBuilder.append(scanner.nextLine());
            }
            return stringBuilder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("File not found " + path);
        }
        return null;
    }

    String readASubject(String title, String Data) {
        // find title
        // separated by :
        // read until next ;
        // return the subject
        int start = Data.indexOf(title);
        while (Data.charAt(start) != ':') {
            start++;
        }
        StringBuilder subject = new StringBuilder();
        while (Data.charAt(start) != ';') {
            subject.append(Data.charAt(start));
            start++;
        }
        return subject.toString();
    }

    public ArrayList<String> readAList(String title, String Data) {
        // find title
        // read the list [
        // separated by ,
        // read until next ];
        // return the list
        int start = Data.indexOf(title);
        while (Data.charAt(start) != '[' && start != -1) {
            start++;
        }
        if (start == -1) {
            return null;
        }
        int end = Data.indexOf(']', start);
        String list = Data.substring(start + 1, end);
        String[] items = list.split(",");
        ArrayList<String> listItems = new ArrayList<>();
        Collections.addAll(listItems, items);
        return listItems;
    }
    public ArrayList<String> readANoTileList(String Data) {
        // read the list [
        // separated by ,
        // read until next ];
        // return the list
        int start = Data.indexOf('[');
        int end = Data.indexOf(']', start);
        String list = Data.substring(start + 1, end);
        String[] items = list.split(",");
        ArrayList<String> listItems = new ArrayList<>();
        Collections.addAll(listItems, items);
        return listItems;
    }

    Map<String, String> readAMap(String title, String Data) {
        // find title
        // read the list {
        // separated by ,
        // read until next };
        // return the list
        int start = Data.indexOf(title);
        while (Data.charAt(start) != '{' && start != -1) {
            start++;
        }
        if (start == -1) {
            return null;
        }
        int end = Data.indexOf('}', start);
        String map = Data.substring(start + 1, end);
        String[] items = map.split(",");
        Map<String, String> mapItems = new HashMap<>();
        for (String item : items) {
            String[] keyValue = item.split(":");
            mapItems.put(keyValue[0], keyValue[1]);
        }
        return mapItems;
    }

    public List<Categoris> readCategories(String path) {
        try {
            List<String> lines = Files.readAllLines(new File(path).toPath());
            return lines.stream().map(line -> {
                String[] parts = line.split(":");
                return new Categoris(parts[0], readAList(parts[0], line));
            }).toList();

        } catch (IOException e) {
            System.out.println("Could not read from file" + path);
        }
        return null;
    }

    public Map<Integer, User> readAllUsers(String path) {
        //necessary fields: id, name, phone, email, password
        //make new user and add to map
        //optional fields: address, city, country, wishlist, cart, orders
        //Users separated by ***
        Map<Integer, User> users = new HashMap<>();
        try {
            List<String> lines = Files.readAllLines(new File(path).toPath());
            for (int i = 0; i < lines.size(); i++) {

                // add lines until "***" is found
                int id = Integer.parseInt(lines.get(i));
                i++;
                System.out.println(id);
                String name = lines.get(i);
                i++;
                System.out.println(name);
                String phone = lines.get(i);
                i++;
                System.out.println(phone);
                String email = lines.get(i);
                i++;
                System.out.println(email);
                String password = lines.get(i);
                i++;
                System.out.println(password);
                User user = new User(id, name, phone, email, password);
                while (!lines.get(i).equals("***")) {
                    Scanner scanner = new Scanner(lines.get(i));
                    switch (scanner.next()) {
                        case "address:":
                            user.setAddress(scanner.next());
                            break;
                        case "city:":
                            user.setCity(scanner.next());
                            break;
                        case "country:":
                            user.setCountry(scanner.next());
                            break;
                        case "wishlist:":
                            ArrayList<Integer> wishlist = readAList("wishlist:", lines.get(i)).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
                            //get items from items map
                            DataBase db = DataBase.getInstance();
                            ArrayList<Item> items = wishlist.stream().map((babash) -> db.items.get(babash)).collect(Collectors.toCollection(ArrayList::new));
                            user.setWishlist(items);
                            break;
//                        case "orders":
//                            ArrayList<Integer> orders = readAList("orders", lines.get(i + 1)).stream().map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
//                            i++;
//                            break;
                    }
                    i++;
                }
                users.put(id, user);
            }
        } catch (IOException e) {
            System.out.println("Could not read from file" + path);
        }
        System.out.println(users.size());
        return users;
    }
}
