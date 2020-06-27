package com.cinemabooking;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class BookingTest {
    Seat testing = new Seat();
    String userName = "Max Mustermann";
    static Show dummyShow0;
    int testSeatNo = 60;

    @org.junit.jupiter.api.BeforeAll
    static void setUp() {
       // testing.setRow(2);
      //  testing.setNumber(13);
        Integer date0 = 15042020;
        Integer time0 = 2000;
        Theater theater0 = new Theater();
        dummyShow0 = new Show("Batman", date0, time0, theater0);
        CinemaBookingSystem.currentShows.add(dummyShow0);
    }

    @org.junit.jupiter.api.Test
    void generateSeatPrice() {
        double expected = 17.29;
        double result = Booking.generateSeatPrice(testing.getRow());
        assertEquals(result,expected);
    }
    @org.junit.jupiter.api.Test
    void createUserTest(){
        User testUser = CinemaBookingSystem.createUser(userName);
        assertNotNull(testUser);
    }
    @org.junit.jupiter.api.Test
    void printMovieTest(){
        String testNames = CinemaBookingSystem.printMovies();
        String expectedResult = dummyShow0.getMovieName()+"\n";
        assertEquals(expectedResult,testNames);
    }
    @org.junit.jupiter.api.Test
    void printDatesTest(){
        String testDates = CinemaBookingSystem.printDates(dummyShow0.getMovieName());
        String expectedResult = dummyShow0.getDate()+"\n";
        assertEquals(expectedResult,testDates);
    }
    @org.junit.jupiter.api.Test
    void printShowTimesTest(){
        String testTimes = CinemaBookingSystem.printShowTimes(((Integer)dummyShow0.getDate()).toString(),dummyShow0.getMovieName());
        String expectedResult = dummyShow0.getShowTime()+"\n";
        assertEquals(expectedResult,testTimes);
    }
    @org.junit.jupiter.api.Test
    void searchShowByTimeTest() {
        Show testShow = CinemaBookingSystem.getShowByTime(((Integer) dummyShow0.getShowTime()).toString());
        assertEquals(testShow,dummyShow0);
    }

    }