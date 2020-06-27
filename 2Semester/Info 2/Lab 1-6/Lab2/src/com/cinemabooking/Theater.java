package com.cinemabooking;

import java.util.ArrayList;

public class Theater {


    //Class Variables
    private Seat[] allSeats;
    private int numSeats = 12;
    private int numRows = 6;


    public Seat getSeatByNumber(int seatNumber){
        try{ return allSeats[seatNumber];}
        catch(ArrayIndexOutOfBoundsException e) { return null;}
    }

    public Theater() {
        generateSeats();
    }

    private void generateSeats() {
        // array initialisieren in rows * seats
        allSeats = new Seat[numRows * numSeats];

        for (int r = 0; r < numRows; r++) {
            for (int s = 0; s < numSeats; s++) {
                Seat seat = new Seat();
                seat.setRow(r);
                seat.setNumber(r * numSeats + s);
                allSeats[r * numSeats + s] = seat;
            }
        }

    }

    private Seat findSeat(int seatNumber, int row) {

        for (Seat s : allSeats) {
            if (s.getRow() == row && s.getNumber() == seatNumber) {
                return s;
            }
        }
        return null;
    }

    public Seat[] getAllSeats() {
        return allSeats;
    }


    public int getNumSeats() {
        return numSeats;
    }

    public int getNumRows() {
        return numRows;

    }
}
