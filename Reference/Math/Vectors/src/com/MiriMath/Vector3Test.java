package com.MiriMath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector3Test {

    Vector3 vector1;
    Vector3 vector2;
    Vector3 vector3;

    @BeforeEach
    void setUp() {
        vector1 = new Vector3(1,2,3);
        vector2 = new Vector3(-1,-2,-3);
        vector3 = new Vector3(0,0,0);
    }
    //X
    @Test
    void getXPositive() {
        double expected = 1;
        double result = vector1.getX();
        assertEquals(expected,result);
    }
    @Test
    void getXNegative() {
        double expected = -1;
        double result = vector2.getX();
        assertEquals(expected,result);
    }
    @Test
    void getXZero() {
        double expected = 0;
        double result = vector3.getX();
        assertEquals(expected,result);
    }
    //Y
    @Test
    void getYPositive() {
        double expected = 2;
        double result = vector1.getY();
        assertEquals(expected,result);
    }
    @Test
    void getYNegative() {
        double expected = -2;
        double result = vector2.getY();
        assertEquals(expected,result);
    }
    @Test
    void getYZero() {
        double expected = 0;
        double result = vector3.getY();
        assertEquals(expected,result);
    }
    //Z
    @Test
    void getZPositive() {
        double expected = 3;
        double result = vector1.getZ();
        assertEquals(expected,result);
    }
    @Test
    void getZNegative() {
        double expected = -3;
        double result = vector2.getZ();
        assertEquals(expected,result);
    }
    @Test
    void getZZero() {
        double expected = 0;
        double result = vector3.getZ();
        assertEquals(expected,result);
    }
    //Specific
    @Test
    void getSameVector() {
        boolean expected = true;
        boolean result = vector1.equals(vector1.getVector());
        // Write a Test that compares the object
        assertEquals(expected,result);
    }



}