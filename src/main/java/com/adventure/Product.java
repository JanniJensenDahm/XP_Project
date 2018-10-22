package com.adventure;

import java.sql.*;

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
    public Product(String category, String name, double price, int inventory) {
        this.category = category;
        this.name = name;
        this.price = price;
        this.inventory = inventory;
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
    public static void addProduct (Product product) {
        Connection con = AccessDB.getConnection();
        String insertProductSQL = "INSERT INTO Products  (category, name, price, inventory) VALUES(?,?,?,?)";

        try {
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(insertProductSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, product.getCategory());
            preparedStatement.setString(2, product.getName());
            preparedStatement.setDouble(3, product.getPrice());
            preparedStatement.setInt(4, product.getInventory());
            preparedStatement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
