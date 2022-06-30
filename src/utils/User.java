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

    @Override
    public String toString() {
        //necessary fields: id, name, phone, email, password
        //make new user and add to map
        //optional fields: address, city, country, wishlist, cart, orders
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\n");
        sb.append(name).append("\n");
        sb.append(phone).append("\n");
        sb.append(email).append("\n");
        sb.append(password).append("\n");
        if (address != "") {
            sb.append("address:").append(address).append("\n");
        }
        if (city != null && city != "") {
            sb.append("city:").append(city).append("\n");
        }
        if (country != null && country != "") {
            sb.append("country:").append(country).append("\n");
        }
        if (wishlist != null) {
            sb.append("wishlist:").append(wishlist.toString()).append("\n");
        }
        if (cart != null) {
            sb.append("cart:").append(cart.toString()).append("\n");
        }
        if (orders != null) {
            sb.append("orders:").append(orders.toString()).append("\n");
        }
        return sb.toString();
    }
}
