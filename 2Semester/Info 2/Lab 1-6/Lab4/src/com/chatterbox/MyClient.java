package com.chatterbox;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class MyClient {

    private static Socket mySocket;

    private static BufferedReader inStream;
    private static PrintWriter out;
    private static Scanner in;

    public static void main(String[] args) {

        if (args.length != 2) {
            System.err.println("Please specify <host name> <port number>");
            System.exit(1);
        }

        String hostName = args[0];
        int portNumber = Integer.parseInt(args[1]);

        try {
            // Instanziieren des ServerSockets
            Socket mySocket = new Socket(hostName, portNumber);
            // Writer zu server
            out = new PrintWriter(mySocket.getOutputStream(), true);
            // reader von Server
            inStream = new BufferedReader(new InputStreamReader(mySocket.getInputStream()));
            in = new Scanner(mySocket.getInputStream());
            System.out.println("starting threads");

            new Thread() {
                public void run() {
                    while (this.isAlive()) MyClient.handleMsg();
                }
            }.start();

            new Thread() {
                public void run() {
                    while (this.isAlive()) MyClient.handleWrite();
                }
            }.start();
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    public static void handleMsg() {
        System.out.println("handler runnning");
        try {
            out.println("CLIENT CONNECTED");
            while (true) {
                String name = inStream.readLine();
                if (name != null) {
                    System.out.println("Server : " + name);
                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }

    public static void handleWrite() {
        System.out.println("Writer runnning");
        try {
            System.out.println("please write Message");
            BufferedReader cmdInput = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String message = cmdInput.readLine();
                if (message != null) {
                    out.println(message);
                }
            }
        } catch (Exception e) {
            System.out.println(e);

        }
    }
}




