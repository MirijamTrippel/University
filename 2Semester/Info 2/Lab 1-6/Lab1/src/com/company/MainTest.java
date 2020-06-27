package com.company;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void Main() {
    }

    @org.junit.jupiter.api.Test
    void hello() {
        //given
        //when
        //then
        String result = Main.hello();
        String expected = "Hello";
        assertEquals(result,expected);
    }
}