package com.adventure;

public class Activity {
    private String activity_name;
    private Double activity_price;
    private int equipment;
    private String requirements;
    private double activity_duration;

    public Activity() {

    }

    public Activity(String activity_name, Double activity_price, int equipment, String requirements, Double activity_duration) {
        this.activity_name = activity_name;
        this.activity_price = activity_price;
        this.equipment = equipment;
        this.requirements = requirements;
        this.activity_duration = activity_duration;
    }

    public String getActivity_name() {
        return activity_name;
    }

    public void setActivity_name(String activity_name) {
        this.activity_name = activity_name;
    }

    public Double getActivity_price() {
        return activity_price;
    }

    public void setActivity_price(Double activity_price) {
        this.activity_price = activity_price;
    }

    public int getEquipment() {
        return equipment;
    }

    public void setEquipment(int equipment) {
        this.equipment = equipment;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public Double getActivity_duration() {
        return activity_duration;
    }

    public void setActivity_duration(Double activity_duration) {
        this.activity_duration = activity_duration;
    }
}
