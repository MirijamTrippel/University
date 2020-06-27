


package uebung3;

import java.net.*;
import java.io.*;
import java.util.*;
import static java.lang.System.out;

public class HTTPServer {

    /* TODO
    • Der Webserver soll parallele Requests bearbeiten können (nutze Thread) XXX CHECK

    • Nach Extraktion der URL aus der ersten Zeile des Requests („Request Line“)
      soll die entsprechende Ressource (Datei) geladen werden,
      die relativ zum aktuellen Verzeichnis aufgesucht wird,
      z.B. URL „/ab/cd.html“ → Datei „./ab/cd.html“;
      der Dateiinhalt soll als Body der HTTP-Response abgeliefert werden

    • Unterscheiden der URL nach Typen, z.B. anhand des Suffix;
      passend zum Typ muss in der Response der Content-Type gesetzt werden
    • Fehlerbehandlung; wird eine Datei nicht gefunden bzw. ist nicht lesbar,
      soll eine geeignete Fehler-Response geliefert werden (HTTP-Code 404)
     */


    //------------------------------------------------------------------------------------------------Threads
//                               in config Port 80
        public static void main (String argv []) throws IOException {
            if (argv.length > 0) {
                ServerSocket ss = new ServerSocket (Integer.parseInt (argv [0]));

                while (true) {
                    // alle einkommende anfragen werden akzeptiert und als neuen thread gestartet.
                    new HTTPConnection (ss.accept ());
                }
            }
        }

    static class HTTPConnection extends Thread {
        Socket sock;

        HTTPConnection (Socket s) {
            sock = s;
            setPriority (NORM_PRIORITY - 1);
            start (); // starte run() als neuen Thread
        }
    //------------------------------------------------------------------------------------------------Threads
        public void run () {
            try {
                // Input
                InputStream httpin = sock.getInputStream ();
                BufferedReader httpreader = new BufferedReader (new InputStreamReader (httpin));
                // Output
                OutputStream httpout = sock.getOutputStream ();
                PrintWriter httpwriter = new PrintWriter (httpout);

                try {
                    // Format: "GET /xy.html HTTP/1.0"
                    String request = httpreader.readLine (); // lies die erste Zeile des Requests ("Request-Line")
                    out.println ("HTTP: " + request);

                    StringTokenizer tokens = new StringTokenizer (request); // zerlege Request-Zeile in Worte
                    // z.B. "GET", "/index.html", "HTTP/1.0"
                    String method = tokens.nextToken();

                    // bearbeite akzeptierte Request "Methods"
                    if (method.equals("GET") || method.equals("POST")) {
                        // hole Request-URI
                        String URI = tokens.nextToken();	// "/index.html"
                        String URIparams = "";
                        String HTTP_version = tokens.nextToken();	// "HTTP/1.0"

                    //          https://www.youtube.com/?gl=DE
                    //          https   :   //  www youtube com /   ?gl=DE
                    //          https   :           youtube de  /
                    //          http    :
                    //          http, https, ftp, mailto, file, data, irc.
                    //

                        //... URL verarbeiten
                        // "/index.html"
                        // "/bla/arg.html"
                        // "/img.jpg"
                        // "/uhrzeit"
                        // "/uhrzeit?format=am/pm"

                        // Header-Zeilen lesen
                        int tocolon;
                        do {
                            request = httpreader.readLine ();
                            tocolon = request.indexOf (':');
                            if (tocolon > 0) {

                                //... Header-Zeile verarbeiten	// <- optional

                            }
                        }
                        while (tocolon > 0);

                        // lies Body, falls vorhanden;	// <- optional
                        // Indizien sind Headerzeilen Content-Type oder Transfer-Encoding

                       // TODO if (has_content || has_body) {
                            // lies Body (kann z.B. bei POST Parameter enthalten)
                            ///...
                        }


                        // bearbeite den Request

                        // unterscheide nach einem Server-spezifischen Kriterium
                        // verschiedene Ressourcentypen, hier:
                        //	– Dateien (z.B. HTML-Seiten), wenn URI "." enthält
                        //	– dynamische Server-Funktionen
                        if (URI.contains (".")) {		// Ressourcen-Typ wird angefragt
                            // z.B. .html, .jpg

                            // bestimme Dateityp anhand des Suffix des Dateinamens
                            //...

                            // lies die Datei
                            //...


                            // liefere Response zurück
                            httpwriter.println ("HTTP/1.0 200 OK");
                            // setze Content-Type entsprechend des Dateityps
                            httpwriter.println ("Content-Type: ...");
                            // text/html
                            // text/plain
                            // image/jpeg
                            // image/gif
                            //...

                            // Ende der Header-Zeilen
                            httpwriter.println ("");
                            httpwriter.flush ();

                            // schreibe Dateiinhalt in Response-Body
                            // (binär, daher nicht über den Writer,
                            // sondern den darunterliegenden OutputStream,
                            // deshalb vorher flush erforderlich)
                            httpout.write (data);
                            httpout.close ();
                        }
                    }
                    else {
                        if (URI.equals ("uhrzeit")) {
                            //...
                        }
                    }

                    if(true){
                        //...
                        // optional: eigene Methoden oder Request-Formate
                        // (eigenes Anwendungsprotokoll statt HTTP)
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
                // falls hier schon eine Exception auftrat, besteht keine
                // Möglichkeit, eine Antwort zu übergeben
                // out.println ("I/O error am Anfang " + e);
            }
        }
    }



}