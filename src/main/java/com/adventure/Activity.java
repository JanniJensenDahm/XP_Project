package com.adventure;

import java.sql.*;

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

    public static void addNewActivity (Activity activity) {
        Connection con = AccessDB.getConnection();
        String insertActivitySQL = "INSERT INTO Activities  (activity_name, activity_price, equipment, requirements, activity_duration) VALUES(?,?,?,?,?)";

        //Add booking
        try {
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(insertActivitySQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, activity.getActivity_name());
            preparedStatement.setDouble(2, activity.getActivity_price());
            preparedStatement.setInt(3, activity.getEquipment());
            preparedStatement.setString(4, activity.getRequirements());
            preparedStatement.setDouble(5, activity.getActivity_duration());
            preparedStatement.executeUpdate();

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return activity_name;

    }
}
