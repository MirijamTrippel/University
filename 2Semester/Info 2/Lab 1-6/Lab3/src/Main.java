
import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    static int charPosition = 0;

    public static void main(String[] args) throws IOException {
        //quick method test//////////////////////////////////////////////////
        readNextFromFile("Lab3\\files\\TestText.txt"); //1.
        saveStringToFile("Lab3\\files\\StringTest.txt"); //2.
        saveIntegerToFile("Lab3\\files\\IntegerTest.txt"); //2.
        saveIntToFile("Lab3\\files\\IntTest.txt"); //2.
        frequenciesInTextFile("Lab3\\files\\TestText.txt"); //3.
        mostFrequent("Lab3\\files\\TestText.txt"); //5.
        histogram("Lab3\\files\\TestText.txt"); //6.
        /////////////////////////////////////////////////////////////////////
    }

    // Assignment 1
    static void readNextFromFile(String filename) {
        Charset charset = Charset.forName("US-ASCII");
        File file = new File(filename);

        try (BufferedReader reader =
                     Files.newBufferedReader(file.toPath(), charset)) {
            String allLetters = "";
            String line = null;
            while ((line = reader.readLine()) != null) {
                allLetters += line;
            }
            if (charPosition >= allLetters.length()) charPosition = allLetters.length() - 1;
            char currentChar = allLetters.charAt(charPosition);
            if (currentChar == '\n') {
                charPosition++;
                readNextFromFile(filename);
                return;
            }
            System.out.println(currentChar);
            charPosition++;
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }

    // Assignment 2
    static void saveStringToFile(String targetFileName) {
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFileName))) {
            String test = "STRING WRITE TEST";

            writer.write(test);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void saveIntegerToFile(String targetFileName) {
        Charset charset = Charset.forName("US-ASCII");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(targetFileName))) {
            Integer testInt = 1234568790;

            writer.write(testInt.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    static void saveIntToFile(String targetFileName) {

        // Convert the string to a
        // byte array.
        int s = 90000;

        Path p = Paths.get(targetFileName);
        try (OutputStream out = new BufferedOutputStream(
                Files.newOutputStream(p))) {
            out.write(s);

        } catch (IOException x) {
            System.err.println(x);
        }

    }

    //general method for counting frequencies
    public static HashMap frequencies(String filename) throws IOException {
        HashMap<Character, Integer> map = new HashMap<>();

        BufferedReader reader = new BufferedReader(new FileReader(filename));

        while (true) {
            String line = reader.readLine();
            if (line == null) {
                break;
            }
            line = line.replaceAll("[^a-zA-Z]", "");
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                Integer value = map.get(c);
                if (value != null) {
                    map.put(c, value + 1);
                } else {
                    map.put(c, 1);
                }
            }
        }
        reader.close();
        return map;
    }

    //Assignment 3
    public static void frequenciesInTextFile(String filename) throws IOException {
        HashMap<Character, Integer> map = frequencies(filename);
        File frequency = new File("Lab3\\files\\frequency.txt");
        FileWriter writer = new FileWriter("Lab3\\files\\frequency.txt");
        for (char key : map.keySet()) {
            writer.write(key + ": " + map.get(key) + "\n");
        }
        writer.close();
    }

    //Assignment 5
    public static char mostFrequent(String filename) throws IOException {
        HashMap<Character, Integer> map = frequencies(filename);
        int highest = 0;
        char mostFrequentChar = '\u0000'; //default char
        for (char key : map.keySet()) {
            if (map.get(key) > highest) {
                mostFrequentChar = key;
                highest = map.get(key);
            }
        }
        return mostFrequentChar;
    }

    //Assignment 6
    public static void histogram(String filename) throws IOException {
        HashMap<Character, Integer> map = frequencies(filename);
        for (char key : map.keySet()) {
            System.out.print(key + ": ");
            for (int i = 0; i < map.get(key); i++) {
                System.out.print("*");
            }
            System.out.print("\n");
        }
    }
}