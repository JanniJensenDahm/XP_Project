package com.adventure;

import java.util.ArrayList;

public class Activity {
    private String name;
    private String duration;
    private double price;
    private ArrayList equipment;
    private ArrayList requirements;

    public Activity() {
    }

    public Activity(String name, String duration, double price, ArrayList equipment, ArrayList requirements) {
        this.name = name;
        this.duration = duration;
        this.price = price;
        this.equipment = equipment;
        this.requirements = requirements;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public ArrayList getEquipment() {
        return equipment;
    }

    public void setEquipment(ArrayList equipment) {
        this.equipment = equipment;
    }

    public ArrayList getRequirements() {
        return requirements;
    }

    public void setRequirements(ArrayList requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "name='" + name + '\'' +
                ", duration='" + duration + '\'' +
                ", price=" + price +
                ", equipment=" + equipment +
                ", requirements=" + requirements +
                '}';
    }
}
