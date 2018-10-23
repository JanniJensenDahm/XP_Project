package com.adventure;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Product {
    private String category;
    private String name;
    private double price;
    private int inventory;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product(String category, String name, double price, int inventory, int id) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
        this.id = id;
    }

    public Product() {

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public Product getProductById(int id) {
        Product product = null;
        Connection con = AccessDB.getConnection();
        String selectSQL = "SELECT * FROM Products WHERE id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    product = new Product(rs.getString("category"), rs.getString("name"), rs.getDouble("price"), rs.getInt("inventory"), rs.getInt("id"));
                }
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    @SuppressWarnings("Duplicates")
    public static ArrayList<Product> getProducts() {
        Connection con = AccessDB.getConnection();
        String selectSQL = "SELECT * FROM Products";
        ArrayList<Product> productList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    try {
                        productList.add(new Product(
                                rs.getString("category"),
                                rs.getString("name"),
                                rs.getDouble("price"),
                                rs.getInt("inventory"),
                                rs.getInt("id")));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    @Override
    public String toString() {
        return "Product{" +
                "category='" + category + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", inventory=" + inventory +
                ", id=" + id +
                '}';
    }
}
