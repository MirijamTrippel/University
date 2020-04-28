import ij.ImageJ;
import ij.ImagePlus;
import ij.gui.GenericDialog;
import ij.gui.NewImage;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

//erste Uebung (elementare Bilderzeugung)

    public class GDM_U1 implements PlugIn {

        final static String[] choices = {
                "Schwarzes Bild",
                "Gelbes Bild",
                "Belgische Fahne",
                "Schwarz/Weiss Verlauf",
                "Horiz. Schwarz/Rot vert. Schwarz/Blau Verlauf",
                "USA Fahne",
                "Japanische Fahne"
        };

        private String choice;

        public static void main(String args[]) {
            ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
            ij.exitWhenQuitting(true);

            GDM_U1 imageGeneration = new GDM_U1();
            imageGeneration.run("");
        }

        public void run(String arg) {

            int width  = 566;  // Breite
            int height = 400;  // Hoehe

            // RGB-Bild erzeugen
            ImagePlus imagePlus = NewImage.createRGBImage("GLDM_U1", width, height, 1, NewImage.FILL_BLACK);
            ImageProcessor ip = imagePlus.getProcessor();

            // Arrays fuer den Zugriff auf die Pixelwerte
            int[] pixels = (int[])ip.getPixels();

            dialog();

            ////////////////////////////////////////////////////////////////
            // Hier bitte Ihre Aenderungen / Erweiterungen

            if ( choice.equals("Schwarzes Bild") ) {
                generateBlackImage(width, height, pixels);
            }
            if ( choice.equals("Gelbes Bild") ) {
                generateYellowImage(width, height, pixels);
            }
            if ( choice.equals("Belgische Fahne") ) {
                generateBelgianFlag(width, height, pixels);
            }
            if ( choice.equals("USA Fahne") ) {
                generateAmericanFlag(width, height, pixels);
            }
            if ( choice.equals("Schwarz/Weiss Verlauf") ) {
                generateBWGradient(width, height, pixels);
            }



            ////////////////////////////////////////////////////////////////////

            // neues Bild anzeigen
            imagePlus.show();
            imagePlus.updateAndDraw();
        }

        private void generateBlackImage(int width, int height, int[] pixels) {
            // Schleife ueber die y-Werte
            for (int y=0; y<height; y++) {
                // Schleife ueber die x-Werte
                for (int x=0; x<width; x++) {
                    int pos = y*width + x; // Arrayposition bestimmen

                    int r = 0;
                    int g = 0;
                    int b = 0;

                    // Werte zurueckschreiben
                    pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                }
            }
        }

        private void generateYellowImage(int width, int height, int[] pixels) {
            // Schleife ueber die y-Werte
            for (int y=0; y<height; y++) {
                // Schleife ueber die x-Werte
                for (int x=0; x<width; x++) {
                    int pos = y*width + x; // Arrayposition bestimmen

                    int r = 255;
                    int g = 255;
                    int b = 0;

                    // Werte zurueckschreiben
                    pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                }
            }
        }

        private void generateBelgianFlag(int width, int height, int[] pixels) {
            // Schleife ueber die y-Werte
            for (int y=0; y<height; y++) {
                // Schleife ueber die x-Werte
                for (int x=0; x<width; x++) {
                    // Arrayposition bestimmen
                    int pos = y*width + x;

                    int oneThird = width/3;

                    if (x < oneThird) {
                        int r = 0;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (x > oneThird && x < oneThird*2 ) {
                        int r = 255;
                        int g = 255;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (x > oneThird*2) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                }
            }
        }

        private void generateAmericanFlag(int width, int height, int[] pixels) {
            // Schleife ueber die y-Werte
            for (int y=0; y<height; y++) {
                // Schleife ueber die x-Werte
                int streifen = 13;
                int oneRow = height/streifen;

                for (int x=0; x<width; x++) {
                    int pos = y*width + x; // Arrayposition bestimmen

                    if (y <= oneRow) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow && y <= oneRow *2 ) {
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *2 && y < oneRow *3 ) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *3 && y <= oneRow *4 ) {
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *4 && y <= oneRow *5 ) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *5 && y <= oneRow *6 ) {
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *6 && y <= oneRow *7 ) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *7 && y <= oneRow *8 ) {
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *8 && y <= oneRow *9 ) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *9 && y <= oneRow *10 ) {
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *10 && y <= oneRow *11 ) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *11 && y <= oneRow *12 ) {
                        int r = 255;
                        int g = 255;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *12 && y <= oneRow *13 ) {
                        int r = 255;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    if (y >= oneRow *13) {
                        int r = 0;
                        int g = 0;
                        int b = 0;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                    // Werte zurueckschreiben

                }
                //Boxie Blau
                for (int x=0; x<width; x++) {
                    int pos = y*width + x; // Arrayposition bestimmen

                    // ES STÖRT MICH DAS WIR NICHT auf 40% gehen können weil das seiten verhältnis nicht
                    //für flaggen gemacht wurde
                    if ( x < oneRow*9 && y < oneRow*7 ) {
                        int r = 0;
                        int g = 0;
                        int b = 255;
                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) |  b;
                    }
                }
            }
        }

        private void generateBWGradient(int width, int height, int[] pixels) {
            // Schleife ueber die y-Werte


            //aufteilen in 255 werten
            for (int i=0; i<255;i++) {

                // Schleife ueber die y-Werte
                for (int y = 0; y < height; y++) {

                    // Schleife ueber die x-Werte
                    for (int x = i * width / 255; x < width; x++) {

                        // Arrayposition bestimmen
                        int pos = y * width + x;

                        int r = 0 + i;
                        int g = 0 + i;
                        int b = 0 + i;

                        pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;

                    }
                }
            }
        }


        private void dialog() {
            // Dialog fuer Auswahl der Bilderzeugung
            GenericDialog gd = new GenericDialog("Bildart");

            gd.addChoice("Bildtyp", choices, choices[0]);


            gd.showDialog();	// generiere Eingabefenster

            choice = gd.getNextChoice(); // Auswahl uebernehmen

            if (gd.wasCanceled())
                System.exit(0);

        }

       /*
       //Wieso funktioniert das nicht?
       private void generateAmericanFlag(int width, int height, int[] pixels) {
            //StreifenAnzahl
            int stripes = 13;
            //StreifenGröße
            int stripeSize = width / stripes;

            //Schleife = für jeden streifen
            for (int i=0; i<= stripes; i++) {

                // Schleife über die y-Werte
                for (int y = 0; y < height; y++) {

                    // Schleife über die x-Werte
                    for (int x = 0; x < width; x++) {

                        int pos = y * width + x; // Arrayposition bestimmen

                        //Färbe ungerade streifen Rot
                        if ( i%2==1 ) {
                            int r = 255;
                            int g = 0;
                            int b = 0;
                            pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
                        }

                        //Färbe gerade streifen Weiß
                        if ( i%2==0 ) {
                            int r = 255;
                            int g = 255;
                            int b = 255;
                            pixels[pos] = 0xFF000000 | (r << 16) | (g << 8) | b;
                        }


                        // teile das bild in 13 teile,färbe jeden teil anders,mach ein blauen kasten
                    }
                }
            }
        }
        */
    }
