package utils;

public class ItemInCart extends Item {
    int quantity_in_cart;
    String color;
    String size;
    public ItemInCart(Item item, int quantity, String color, String size) {
        super(item.id, item.name, item.category, item.subCategory, item.imageUrl, item.price, item.rating, item.quantity, item.description, item.properties, item.colors, item.sizes, item.sellerName);
        this.quantity_in_cart = quantity;
        this.color = color;
        this.size = size;
    }
}
