package com.adventure;

import java.sql.*;
import java.util.ArrayList;

public class Booking {
    private ArrayList<Activity> activities;
    User bookingEmployee;
    User activityEmployee;
    String date;
    String time;
    double price;
    private int peopleCount = 0;

    public Booking(User bookingEmployee, User activityEmployee, String date, String time, double price, int peopleCount) {
        this.bookingEmployee = bookingEmployee;
        this.activityEmployee = activityEmployee;
        this.date = date;
        this.time = time;
        this.price = price;
        this.peopleCount = peopleCount;
    }

    public Booking() {

    }

    @SuppressWarnings("Duplicates")
    public void addBooking(Booking booking) {
        int booking_id = 0;
        Connection con = AccessDB.getConnection();
        String insertBookingSQL = "INSERT INTO Bookings  (bookingEmployee, activityEmployee, Date, Time, Price) VALUES(?,?,?,?,?,?)";

        //Add booking
        try {
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(insertBookingSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, booking.getBookingEmployee().getId());
            preparedStatement.setInt(2, booking.getActivityEmployee().getId());
            preparedStatement.setString(3, booking.getDate());
            preparedStatement.setString(4, booking.getTime());
            preparedStatement.setDouble(5, booking.getPrice());
            preparedStatement.setInt(6, booking.getPeopleCount());
            preparedStatement.executeUpdate();


            //Get id of created booking
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                System.out.println(rs.getInt(1));
                booking_id = rs.getInt(1);
            }

            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //add activities

        try {
            String insertActivitySql = "INSERT INTO Activities (name) VALUES(?)";
            PreparedStatement preparedStatement;
            ArrayList<Integer> activityIds = new ArrayList<>();

            //Add all activities
            for (Activity activity : booking.getActivities()) {
                preparedStatement = con.prepareStatement(insertActivitySql, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setString(1, activity.getName());
                preparedStatement.executeUpdate();
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if (rs.next()) {
                    activityIds.add(rs.getInt(1));
                }

            }


            //Connect Activities to booking

            for (Integer activity : activityIds) {
                String combineActivitiesAndBookingsSQL = "INSERT INTO Booking_has_activities (booking_id, activity_id, needed_equipment) VALUES(?,?,?)";
                preparedStatement = preparedStatement = con.prepareStatement(combineActivitiesAndBookingsSQL, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setInt(1, booking_id);
                preparedStatement.setInt(2, activity);
                preparedStatement.setInt(3, booking.getPeopleCount());

            }


            //Get id of created booking


            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        //Connect activities to booking


    }

    private int getPeopleCount() {
        return this.peopleCount;
    }


    public ArrayList<Activity> getActivities() {
        return activities;
    }

    public void setActivities(ArrayList<Activity> activities) {
        this.activities = activities;
    }

    public User getBookingEmployee() {
        return bookingEmployee;
    }

    public void setBookingEmployee(User bookingEmployee) {
        this.bookingEmployee = bookingEmployee;
    }

    public User getActivityEmployee() {
        return activityEmployee;
    }

    public void setActivityEmployee(User activityEmployee) {
        this.activityEmployee = activityEmployee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
//        return "Booking{" +
//                "activity=" + activity +
//                ", bookingEmployee=" + bookingEmployee +
//                ", activityEmployee=" + activityEmployee +
//                ", date='" + date + '\'' +
//                ", time='" + time + '\'' +
//                ", price=" + price +
//                '}';
//    }
        return "Booking";
    }
}