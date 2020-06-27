package com.cinemabooking;

public class User {
    private String userName;
    private long phoneNumber;
    //Miri
    private int userID = 0;
    //Miri

    public User(String userName) {
        this.userName = userName;
        this.userID = (int) (Math.random()*1000000f);
    }


    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }
}
