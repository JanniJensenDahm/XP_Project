package com.adventure;

import java.sql.*;
import java.util.ArrayList;

public class Booking {
    private ArrayList<Activity> activities;
    private User bookingEmployee;
    private User activityEmployee;
    private String date;
    private String time;
    private double price;
    private int peopleCount = 0;


    public Booking(User bookingEmployee, User activityEmployee, String date, Double price, int peopleCount, String customer_name, String customer_phone,
                   String customer_email, String customer_company, ArrayList<Activity> activities) {


    }


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
        String insertBookingSQL = "INSERT INTO Bookings  (bookingEmployee, activityEmployee, Date, Price) VALUES(?,?,?,?,?,?)";

        //Add booking
        try {
            PreparedStatement preparedStatement;
            preparedStatement = con.prepareStatement(insertBookingSQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, booking.getBookingEmployee().getId());
            preparedStatement.setInt(2, booking.getActivityEmployee().getId());
            preparedStatement.setString(3, booking.getDate());
            preparedStatement.setDouble(4, booking.getPrice());
            preparedStatement.setInt(5, booking.getPeopleCount());
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
                preparedStatement.setString(1, activity.getActivity_name());
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

    @SuppressWarnings("Duplicates")
    public ArrayList<Booking> getBookings() {
        Connection con = AccessDB.getConnection();
        String selectSQL = "SELECT * FROM Booking_has_activities" +
                "JOIN Bookings ON (Booking_has_activities.booking_id = Bookings.booking_id);";
        ArrayList<Booking> bookingList = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs != null) {
                while (rs.next()) {
                    try {
                        bookingList.add(new Booking(getUserById(rs.getInt("bookingEmployee")),getUserById(rs.getInt("activityEmployee")),
                                rs.getString("date"),
                                rs.getDouble("price"), rs.getInt("peopleCount"), rs.getString("customer_name"),
                                rs.getString("customer_phone"), rs.getString("customer_email"),
                                rs.getString("customer_company"),getActivitiesByBookingId(rs.getInt("booking_id"))));
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



        return bookingList;
    }

    public User getUserById(int id) {
        User user = null;
        Connection con = AccessDB.getConnection();
        String selectSQL = "SELECT * FROM Users" +
                "JOIN login ON(Users.login_id = login.id) WHERE user_id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    user = new User(rs.getInt("user_id"), rs.getString("name"), rs.getString("phone"), rs.getString("email"),
                    new Login(rs.getString("username"), rs.getString("password"), rs.getInt("accessLevel")));
                }
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public ArrayList<Activity> getActivitiesByBookingId(int id) {
        ArrayList<Activity> activityList = new ArrayList<>();
        Connection con = AccessDB.getConnection();
        String selectSQL = "SELECT * FROM Booking_has_activities" +
                "JOIN Activities ON (Booking_has_activities.activity_id = Activities.activity_id) WHERE Booking_has_activities.booking_id = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(selectSQL);
            preparedStatement.setInt(1,id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    activityList.add(new Activity(rs.getString("activity_name"), rs.getDouble("activity_price"), rs.getInt("equipment"),
                            rs.getString("requirements"), rs.getDouble("activity_duration")));
                }
            }
            preparedStatement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return activityList;
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