package utils;

import java.util.ArrayList;
import java.util.List;

public class User {
    public String name;
    public int id;
    public String email;
    public String password;
    public String phone;
    String address = "";
    String city;
    String country;
    List<Item> wishlist;
    List<ItemInCart> cart;
    List<Order> orders = new ArrayList<>();

    public User(int id, String name, String email, String password, String phone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setWishlist(List<Item> wishlist) {
        this.wishlist = wishlist;
    }

    public void setCart(List<ItemInCart> cart) {
        this.cart = cart;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
