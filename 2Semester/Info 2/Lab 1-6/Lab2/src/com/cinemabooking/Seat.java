package com.cinemabooking;

public class Seat {

    private int number;
    private User seatOwner;
    private int row;

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int ID) {
        this.number = ID;
    }

    public User getSeatOwner() {
        return seatOwner;
    }

    public void setSeatOwner(User seatOwner) {
        this.seatOwner = seatOwner;
    }

    public boolean isThisSeatFree(){
        if (seatOwner != null){
            return false;
        }
        else return true;
    }
}
