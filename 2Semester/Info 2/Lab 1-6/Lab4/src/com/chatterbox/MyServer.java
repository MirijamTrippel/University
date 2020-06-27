package com.chatterbox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer {

    public static int portNo;
    private static ServerSocket serverSocket;
    private static Socket socket;
    private static BufferedReader inStream;
    private static PrintWriter out;


    public static void main(String[] args) throws Exception {


        if (args.length != 1) {
            System.err.println("Please specify Port number");
            System.exit(1);
        }

        portNo = Integer.parseInt(args[0]);

        System.out.println("The chat server is running on port " + portNo);

        /// PROBLEME :: Verwenden von runnable interface Klassen wie ein Message handler hat nicht funktioniert,
        // wenn beide threads in der Main funktion gestarted wurden, wurde nur einer ausgeführt

        try {
            // server gestartet
            serverSocket = new ServerSocket(portNo);
            // client socket
            socket = serverSocket.accept();
            // reader & writer
            inStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("starting threads");

         new Thread() {
             // diese funktion wird ausgeführt
                public void run() {
                    while (this.isAlive()) MyServer.handleMsg();
                }
            }.start();

            new Thread() {
                // diese funktion wird ausgeführt
                public void run() {
                    while (this.isAlive()) MyServer.handleWrite();
                }
            }.start();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void handleMsg() {
        System.out.println("handler runnning");
        try {
            out.println("SEND MESSAGE TO SERVER");
            while (true) {

                String message = inStream.readLine();
                if (message != null) {
                    System.out.println("Client : " + message);
                }
                if (message.equals(("Bye")))break;
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
                if (message.equals(("Bye")))break;
            }
        } catch (Exception e) {
            System.out.println(e);

        }

    }
}











