package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Item {
    public int id;
    public String name;
    public String category;
    public String subCategory;
    public String imageUrl;
    public double price;
    public double rating;
    public int quantity;
    public String description;
    public Map<String, String> properties;
    public ArrayList<String> colors;
    public ArrayList<String> sizes;
    public String sellerName;

    public Item(int id, String name, String category, String subCategory, String imageUrl, double price, double rating, int quantity,
                String description, Map<String, String> properties, ArrayList<String> colors, ArrayList<String> sizes, String sellerName) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.subCategory = subCategory;
        this.imageUrl = imageUrl;
        this.price = price;
        this.rating = rating;
        this.quantity = quantity;
        this.description = description;
        this.properties = properties;
        this.colors = colors;
        this.sizes = sizes;
        this.sellerName = sellerName;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", category='" + category + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                ", quantity=" + quantity +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                ", colors=" + colors +
                ", sizes=" + sizes +
                ", sellerName='" + sellerName + '\'' +
                '}';
    }
    public String propertiesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("properties: {");
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            stringBuilder.append(entry.getKey() + ": " + entry.getValue() + ",");
        }
        //delete last comma
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append("}");
        return stringBuilder.toString();
    }
    public String colorsToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("colors: [");
        for (String color : colors) {
            stringBuilder.append(color + ",");
        }
        //delete last comma
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
    public String sizesToString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("sizes: [");
        for (String size : sizes) {
            stringBuilder.append(size + ",");
        }
        //delete last comma
        stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
}
