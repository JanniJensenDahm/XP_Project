package com.adventure;

public class Booking {
    Activity activity;
    User bookingEmployee;
    User activityEmployee;
    String date;
    String time;
    double price;

    public Booking(Activity activity, User bookingEmployee, User activityEmployee, String date, String time, double price) {
        this.activity = activity;
        this.bookingEmployee = bookingEmployee;
        this.activityEmployee = activityEmployee;
        this.date = date;
        this.time = time;
        this.price = price;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
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
        return "Booking{" +
                "activity=" + activity +
                ", bookingEmployee=" + bookingEmployee +
                ", activityEmployee=" + activityEmployee +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", price=" + price +
                '}';
    }
}