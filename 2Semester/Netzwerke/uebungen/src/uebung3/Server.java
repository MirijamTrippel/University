package uebung3;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URI;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import static java.lang.System.out;

public class Server {



    public static void main ( String[] args ) throws IOException {

        int port = Integer.parseInt ( args [0] );

        if (args.length > 0) {
            ServerSocket serverSocket = new ServerSocket ( port );

            while (true) {
                new Server.HTTPConnection( serverSocket.accept() );
            }
        }
    }

    static class HTTPConnection extends Thread {
        Socket socket;

        HTTPConnection( Socket socket ) {
            this.socket = socket;
            setPriority( NORM_PRIORITY - 1 );
            start();
        }

        public void run () {
            try {
                // Input
                InputStream httpIn = socket.getInputStream ();
                BufferedReader httpReader = new BufferedReader ( new InputStreamReader ( httpIn ) );
                // Output
                OutputStream httpOut = socket.getOutputStream ();
                PrintWriter httpWriter = new PrintWriter ( httpOut );

                try {
                    String requestLine = httpReader.readLine ();
                    out.println ( "HTTP: " + requestLine );
                    // Splits the requestLine into pieces
                    StringTokenizer token = new StringTokenizer ( requestLine );
                    String method = token.nextToken();

                    /*
                    ->
                    GET / HTTP/1.0

                    <-
                    HTTP/1.1 400 Bad Request
                    Date: Fri, 24 Apr 2020 11:25:11 GMT
                    Content-Type: text/html
                    Content-length: 155
                    Connection: close
                    Server: cloudflare
                     */




                    if (method.equals( "GET" ) )/* || method.equals("POST")) */ {

                        // hole Request-URI
                        String URL = token.nextToken();	// "/index.html"
                        String URLparams = "";
                        String HTTP_version = token.nextToken();	// "HTTP/1.0"

                        //          https://www.youtube.com/?gl=DE
                        //          https   :   //  www youtube com /   ?gl=DE
                        //          https   :           youtube de  /
                        //          http    :
                        //          http, https, ftp, mailto, file, data, irc.















                        int tocolon;
                        do {
                            request = httpreader.readLine ();
                            tocolon = request.indexOf (':');
                            if (tocolon > 0) {
                            }
                        }
                        while (tocolon > 0);
                        // TODO if (has_content || has_body) {
                    }
                    if (URI.contains (".")) {
                        httpwriter.println ("HTTP/1.0 200 OK");
                        httpwriter.println ("Content-Type: ...");
                        httpwriter.println ("");
                        httpwriter.flush ();
                        httpout.write (data);
                        httpout.close ();
                    }
                }
                    else {
                    if (URI.equals ("uhrzeit")) {

                    }
                }

                if(true){

                }
                else {
                    httpwriter.println ("HTTP/1.0 405 Method Not Allowed");
                    out.println ("HTTP Method abgelehnt: " + method);
                }
            } catch (NoSuchElementException e) {
                // keine Tokens (z.B. URI) im Request
                httpwriter.println ("HTTP/1.0 400 Bad Request");
                out.println ("Bad Request");
            } catch (IOException e) {
                httpwriter.println ("HTTP/1.0 500 Internal Server Error");
                out.println ("I/O error " + e);
            }

            sock.close ();
        } catch (IOException e) {
        }
    }


    }

    // Input
    InputStream httpin = sock.getInputStream ();
    BufferedReader httpreader = new BufferedReader (new InputStreamReader(httpin));
    // Output
    OutputStream httpout = sock.getOutputStream ();
    PrintWriter httpwriter = new PrintWriter (httpout);



}
