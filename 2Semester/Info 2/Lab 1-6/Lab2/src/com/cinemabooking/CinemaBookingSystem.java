package com.cinemabooking;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaBookingSystem {

    public static List<Show> currentShows = new ArrayList<>();
    private static List<Show> searchShows = new ArrayList<>();
    private static User sessionOwner;
    private static Booking sessionBooking;
    private static String sessionMovie;
    private static String searchDate;
    private static String seatNumber;
    private static String searchTime;
    private static Show sessionShow;
    private static String addSeats;


    public static void main(String[] args) {
        init();
        boolean finished = false;


        Scanner scanner = new Scanner(System.in);
        System.out.print("Hello, welcome to the CinemaBookingSystem. Please enter your Username :\n");
        String username = scanner.next();
        sessionOwner = createUser(username);
        System.out.print("Created User Account " + sessionOwner.getUserName() + " with ID : " + sessionOwner.getUserID() + "\n");
        while (!finished) {
            clear();

            while (!checkMovies()) {
                System.out.print("What movie would you like to see? \n");
                String movieNames = printMovies();
                System.out.println(movieNames);
                System.out.print("Please enter the name of the desired movie \n");
                sessionMovie = scanner.next();
            }

            while (!checkDates()) {
                String movieDates = printDates(sessionMovie);
                System.out.println(movieDates);
                System.out.print("Please enter the date of desired Show (dd/mm/yyyy) : \n");
                searchDate = scanner.next();
            }

            while (!checkTimes()) {
                String showTimes = printShowTimes(searchDate, sessionMovie);
                System.out.println(showTimes);
                System.out.print("Please enter the show Time of desired Show (HHMM) : \n");
                searchTime = scanner.next();

            }

            sessionShow = getShowByTime(searchTime);
            System.out.print("Following seats are available: \n");
            printSeats(sessionShow.getTheater());
            System.out.print("Please enter your desired seat number : \n");
            seatNumber = scanner.next();
            sessionBooking = createBooking(Integer.parseInt(seatNumber));
            System.out.print("Would you like to reserve neighbouring seats ? (y/n) : \n");
            if (scanner.next().contentEquals("y")) {
                System.out.print("How many additional seats ?: \n");
                addSeats = scanner.next();
                bookAdditional(Integer.parseInt(addSeats));
            }
            sessionBooking.print();
            System.out.print("Would you like to confirm the order (y/n) ?: \n");
            if (scanner.next().contentEquals("y")) finished = true;
            else {
                System.out.print("Would you like to delete the booking and restart the booking process(y/n) ?: \n");
                if (scanner.next().contentEquals("y")) finished = false;
                else finished = true;

            }
        }
        System.out.print("Confirmed Booking No. " + sessionBooking.getID() + "\n");

    }

    private static void init() {
        createDummyData();
    }

    public static void clear() {
        sessionBooking = null;
        searchShows.clear();
        sessionShow = null;
        seatNumber = null;
        searchDate = null;
        sessionMovie = null;
        searchTime = null;

    }

    public static void createDummyData() {
        Integer date0 = 15042020;
        Integer time0 = 2000;
        Theater theater0 = new Theater();
        Show dummyShow0 = new Show("Batman", date0, time0, theater0);
        currentShows.add(dummyShow0);
        date0 = 16042020;
        time0 = 2100;
        Theater theater3 = new Theater();
        Show dummyShow3 = new Show("Batman", date0, time0, theater3);
        currentShows.add(dummyShow3);

        date0 = 15042020;
        time0 = 2100;
        Theater theater4 = new Theater();
        Show dummyShow4 = new Show("Batman", date0, time0, theater4);
        currentShows.add(dummyShow4);
        date0 = 15042020;
        time0 = 2100;
        Theater theater1 = new Theater();
        Show dummyShow1 = new Show("Superman", date0, time0, theater1);
        currentShows.add(dummyShow1);
        date0 = 16042020;
        time0 = 2100;
        Theater theater2 = new Theater();
        Show dummyShow2 = new Show("Spiderman", date0, time0, theater2);
        currentShows.add(dummyShow2);
    }

    public static User createUser(String userName) {
        return new User(userName);
    }

    public static String printMovies() {
        List<String> movieNames = new ArrayList();
        // get all names
        for (Show S : currentShows) {
            String movieName = S.getMovieName();
            if (movieName != "") if (!movieNames.contains(movieName)) movieNames.add(S.getMovieName());
        }
        // print
        String Names = "";
        //for (String name : movieNames) System.out.println(name + "\n");
        for (String name : movieNames) Names += name + "\n";
        return Names;
    }

    public static boolean checkMovies() {
        if (sessionMovie == null) return false;
        boolean check = false;
        for (Show S : currentShows)
            if (S.getMovieName().contains(sessionMovie)) {
                searchShows.add(S);
                check = true;
            }

        if (!check) System.out.println("INVALID MOVIE");
        return check;
    }

    public static boolean checkDates() {
        if (searchDate == null) return false;
        boolean check = false;
        List<Show> tempShows = searchShows;
        for (Show S : tempShows)
            if (((Integer) S.getDate()).toString().contains(searchDate)) check = true;

        if (!check) System.out.println("INVALID DATE");
        return check;
    }

    public static String printDates(String movieName) {
        List<Integer> movieDates = new ArrayList();
        // get all names
        for (Show S : currentShows)
            if (S.getMovieName().contains(movieName))
                if (!movieDates.contains(S.getDate())) movieDates.add(S.getDate());

        System.out.println("Following Dates are available for " + movieName + " \n");
        // print
        String Dates = "";
        for (Integer Date : movieDates) {

            String input = Date.toString();
            StringBuffer newString
                    = new StringBuffer(input);

            // Insert the strings to be inserted
            // using insert() method
            newString.insert(1 + 1, ".");
            newString.insert(4 + 1, ".");

            Dates += newString.toString() + "\n";
        }
        return Dates;

    }

    public static String printShowTimes(String searchDate, String sessionMovie) {


        System.out.println("Following Showtimes are available for " + sessionMovie + " on " + searchDate + "\n");
        List<Integer> showTimes = new ArrayList();
        // get all names
        for (Show S : currentShows)
            if (S.getDate() == Integer.parseInt(searchDate) && S.getMovieName().contentEquals(sessionMovie))
                showTimes.add(S.getShowTime());
        // print
        String showT = "";
        for (Integer S : showTimes) {
            String input = S.toString();

            StringBuffer newString = new StringBuffer(input);
            newString.insert(1 + 1, ":");
            showT += newString.toString() + "\n";
        }
        return showT;
    }

    public static boolean checkTimes() {
        if (searchTime == null) return false;
        boolean check = false;
        List<Show> tempShows = searchShows;
        for (Show S : tempShows)
            if (((Integer) S.getShowTime()).toString().contains(searchTime)) check = true;

        if (!check) System.out.println("INVALID DATE");
        return check;
    }

    public static Show getShowByTime(String time) {
        for (Show S : currentShows) if (S.getShowTime() == Integer.parseInt(time)) return S;
        return null;
    }

    public static void printSeats(Theater theater) {
        for (int r = 0; r < theater.getNumRows(); r++) {
            System.out.print("Row: " + r + " ");
            for (int s = 0; s < theater.getNumSeats(); s++) {
                if (theater.getAllSeats()[r * theater.getNumSeats() + s].isThisSeatFree() == true)
                    System.out.print("[ ]");
                else
                    System.out.print("[x]");
            }
            System.out.println();
            System.out.print("        ");
            for (int sitznummer = 0; sitznummer < theater.getNumSeats(); sitznummer++) {
                if (r * theater.getNumSeats() + sitznummer < 10)
                    System.out.print((r * theater.getNumSeats() + sitznummer) + "  ");
                else
                    System.out.print((r * theater.getNumSeats() + sitznummer) + " ");
            }
            System.out.println();
        }
    }

    public static Booking createBooking(int seatNumber) {
        List<Seat> bookedSeats = new ArrayList<>();
        Theater T = sessionShow.getTheater();
        Seat seat = T.getSeatByNumber(seatNumber);
        bookedSeats.add(seat);
        Booking booking = new Booking(bookedSeats, sessionOwner, sessionShow);
        return booking;
    }

    public static void bookAdditional(int howMany) {

        int originalSeatIndex = sessionBooking.getSeats().get(0).getNumber();
        for (int i = 1; i <= howMany; i++) {
            sessionBooking.getSeats().add(sessionShow.getTheater().getSeatByNumber(originalSeatIndex + i));
        }
        sessionBooking.update();
    }
}
