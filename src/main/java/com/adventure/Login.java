package com.adventure;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Jesper Tang Petersen, 09-10-2018 14:46
 */

public class Login {
    //Fields
    private String username;
    private String password;
    private int accessLevel;

    /**
     * This method creates a login for the user
     *
     * @param username The users username
     * @param password The users password
     */
    public Login(String username, String password) {
        this.username = username;
        this.password = password;
    }


    //Methods

    /**
     * This method verifies the user and checks if both username and password match up
     * @return Boolean Returns true if info is correct or false if incorrect
     */
    public Boolean verifyUser() {
        Connection con = AccessDB.getConnection();
        String getSQL = "SELECT username, password, accessLevel FROM login WHERE username = ?";
        try {
            PreparedStatement preparedStatement = con.prepareStatement(getSQL);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs != null) {
                while (rs.next()) {
                    try {
                        if (username.equals(rs.getString("username")) && password.equals(rs.getString("password"))) {
                            accessLevel = rs.getInt("accessLevel");
                            return true;
                        } else {
                            accessLevel = 0;
                            return false;
                        }

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
        return false;
    }

    /**
     * This method redirects a user baded upon accessLevel
     *
     * @return String Returns a String used to redirect user
     */
    public String redirect() {
        String redirectPage = "";
        switch (accessLevel) {
            //Owner
            case 1:
                redirectPage = "owner_page";
                break;
            //Booking employee
            case 2:
                redirectPage = "booking";
                break;
            //Other employee
            case 3:
                redirectPage = "activityHelper_page";
                break;
            //Wrong login or No access to the system
            default:
                redirectPage = "index";
                break;

        }
        return redirectPage;
    }


    /*Getters and setters*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }


}
