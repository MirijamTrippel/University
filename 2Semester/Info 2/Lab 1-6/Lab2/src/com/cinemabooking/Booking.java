package com.cinemabooking;

import java.util.List;
import java.util.UUID;

public class Booking {
    private String ID;
    private List<Seat> seats;
    private User userReference;
    private Show showReference;
    private boolean isCancelled;
    private boolean isPaid;
    private double cost;


    public Booking(List<Seat> seats, User userReference, Show showReference) {
        this.seats = seats;
        this.userReference = userReference;
        this.showReference = showReference;
        this.ID = UUID.randomUUID().toString();
        this.cost = calculateCost(this.seats);
    }


    public void print(){

        System.out.println("Booking ID :" + this.ID+"\n");
        System.out.println("Booking Owner :" + this.userReference.getUserName()+"\n");
        System.out.println("Movie Name :" + this.showReference.getMovieName()+"\n");
        System.out.println("Movie Date :" + this.showReference.getDate()+"\n");
        System.out.println("Movie Showtime :" + this.showReference.getShowTime()+"\n");
        System.out.println("Reserved Seats :\n");
        for (Seat s:seats) {
            System.out.println(s.getNumber()+", ");
        }
        System.out.println("Total Price :" + this.cost+" €\n");

    }

    private double calculateCost(List<Seat> seats) {
        double totalCost = 0;
        for (Seat s : seats) {
            totalCost += generateSeatPrice(s.getRow());
        }
        if (totalCost <= 0) System.out.print(" No seats selected");
        return totalCost;
    }

    public static double generateSeatPrice(int rowNumber) {

        switch (rowNumber) {
            case 1:
                return 15.13;

            case 2:
                return 15.13;

            default:
                return 10.80;
        }
       /* double salePrice = 10.80;
        if ( rowNumber <= 1 ){ return 15.13; }
        if ( rowNumber == 2 ){ return 17.29; }
        if ( rowNumber == 3 ){ return 13.83; }
        if ( rowNumber == 4 ){ return 12.26; }
        if ( rowNumber == 5 ){ return 14.55; }
        if ( rowNumber == 6 ){ return 15.69; }
        else
            return salePrice;*/
    }

    public void update(){
       this.cost = calculateCost(seats);
    }

    public String getID() {
        return ID;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public User getUserReference() {
        return userReference;
    }

    public Show getShowReference() {
        return showReference;
    }

    public boolean isCancelled() {
        return isCancelled;
    }

    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public double getCost() {
        return cost;
    }

}
