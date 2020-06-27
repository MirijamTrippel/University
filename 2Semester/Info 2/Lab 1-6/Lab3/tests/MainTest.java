import java.io.*;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @org.junit.jupiter.api.Test
    void correctCount() throws IOException {
        HashMap<Character, Integer> expected = new HashMap<>();
        expected.put('a', 1);
        expected.put('A', 1);
        expected.put('D', 1);
        expected.put('d', 1);
        expected.put('e', 5);
        expected.put('f', 1);
        expected.put('g', 1);
        expected.put('i', 4);
        expected.put('m', 1);
        expected.put('n', 3);
        expected.put('r', 1);
        expected.put('s', 6);
        expected.put('t', 4);
        expected.put('T', 2);
        expected.put('x', 1);

        assertEquals(expected, Main.frequencies("Lab3\\src\\TestText.txt"));
    }
}
