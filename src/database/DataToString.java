package database;

import utils.Categoris;
import utils.Item;

import java.util.List;
import java.util.function.Predicate;

public class DataToString {
    public String itemToString(Item item) {
        // id, name, category, subCategory, imageUrl, price, rating, quantity, description, properties, colors, sizes, sellerName
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(item.id + "\n");
        stringBuilder.append(item.name + "\n");
        stringBuilder.append(item.category + "\n");
        stringBuilder.append(item.subCategory + "\n");
        stringBuilder.append(item.imageUrl + "\n");
        stringBuilder.append(item.price + "\n");
        stringBuilder.append(item.rating + "\n");
        stringBuilder.append(item.quantity + "\n");
        stringBuilder.append(item.description + "\n");
        stringBuilder.append(item.propertiesToString() + "\n");
        stringBuilder.append(item.colorsToString() + "\n");
        stringBuilder.append(item.sizesToString() + "\n");
        stringBuilder.append(item.sellerName + "\n");
        return stringBuilder.toString();
    }

    public String AllItemsToString(Predicate<Item> predicate) {
        DataBase dataBase = DataBase.getInstance();
        List<Item> items = dataBase.getItems().stream().filter(predicate).toList();
        StringBuilder stringBuilder = new StringBuilder();
        for (Item item : items) {
            stringBuilder.append(itemToString(item)).append("***\n");
        }
        //delete the last ***
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());
        }
        return stringBuilder.toString();
    }

    public String categoryToString(Categoris category) {
        // categoryName: [item1, item2, item3]
        return category.toString();
    }

    public String AllCategoriesToString() {
        DataBase dataBase = DataBase.getInstance();
        List<Categoris> categories = dataBase.getCategories();
        StringBuilder stringBuilder = new StringBuilder();
        for (Categoris category : categories) {
            stringBuilder.append(categoryToString(category) + "*** ");
        }
        //delete the last ***
        if (stringBuilder.length() > 0) {
            stringBuilder.delete(stringBuilder.length() - 4, stringBuilder.length());
        }
        return stringBuilder.toString();
    }
}
