package uebung4;

import ij.*;
import ij.io.*;
import ij.process.*;
import ij.gui.*;
import ij.plugin.filter.*;


public class GRDM_U4_s0572665 implements PlugInFilter {

    protected ImagePlus imp;
    final static String[] choices = {"Wischen", "Weiche Blende", "Overlay AB", "Overlay BA", "Schieb Blende", "Chroma Key", "Überblendung"};

    public int setup(String arg, ImagePlus imp) {
        this.imp = imp;
        return DOES_RGB+STACK_REQUIRED;
    }

    public static void main(String args[]) {
        ImageJ ij = new ImageJ(); // neue ImageJ Instanz starten und anzeigen
        ij.exitWhenQuitting(true);

        IJ.open("C:\\Users\\Miri\\Desktop\\GitHub\\University\\2Semester\\GDM\\files\\StackB.zip");

        GRDM_U4_s0572665 sd = new GRDM_U4_s0572665();
        sd.imp = IJ.getImage();
        ImageProcessor B_ip = sd.imp.getProcessor();
        sd.run(B_ip);
    }

    public void run(ImageProcessor B_ip) {
        // Film B wird uebergeben
        ImageStack stack_B = imp.getStack();

        int length = stack_B.getSize();
        int width  = B_ip.getWidth();
        int height = B_ip.getHeight();

        // ermoeglicht das Laden eines Bildes / Films
        Opener o = new Opener();
        /*
        OpenDialog od_A = new OpenDialog("Auswählen des 2. Filmes ...",  "");

        // Film A wird dazugeladen
        String dateiA = od_A.getFileName();
        if (dateiA == null) return; // Abbruch
        String pfadA = od_A.getDirectory();

         */
        ImagePlus A = o.openImage("C:\\Users\\Miri\\Desktop\\GitHub\\University\\2Semester\\GDM\\files\\StackA.zip");
        if (A == null) return; // Abbruch

        ImageProcessor A_ip = A.getProcessor();
        ImageStack stack_A  = A.getStack();

        if (A_ip.getWidth() != width || A_ip.getHeight() != height)
        {
            IJ.showMessage("Fehler", "Bildgrößen passen nicht zusammen");
            return;
        }

        // Neuen Film (Stack) "Erg" mit der kleineren Laenge von beiden erzeugen
        length = Math.min(length,stack_A.getSize());

        ImagePlus Erg = NewImage.createRGBImage("Ergebnis", width, height, length, NewImage.FILL_BLACK);
        ImageStack stack_Erg  = Erg.getStack();

        // Dialog fuer Auswahl des Ueberlagerungsmodus
        GenericDialog gd = new GenericDialog("Überlagerung");
        gd.addChoice("Methode",choices,"");
        gd.showDialog();

        int methode = 0;
        String s = gd.getNextChoice();
        if (s.equals("Wischen")) methode = 1;
        if (s.equals("Weiche Blende")) methode = 2;
        if (s.equals("Overlay AB")) methode = 3;
        if (s.equals("Overlay BA")) methode = 4;
        if (s.equals("Schieb Blende")) methode = 5;
        if (s.equals("Chroma Key")) methode = 6;
        if (s.equals("Überblendung")) methode = 7;

        // Arrays fuer die einzelnen Bilder
        int[] pixels_B;
        int[] pixels_A;
        int[] pixels_Erg;

        // Schleife ueber alle Bilder
        for (int z=1; z<=length; z++)
        {
            pixels_B   = (int[]) stack_B.getPixels(z);
            pixels_A   = (int[]) stack_A.getPixels(z);
            pixels_Erg = (int[]) stack_Erg.getPixels(z);

            int pos = 0;
            for (int y=0; y<height; y++)
                for (int x=0; x<width; x++, pos++)
                {
                    int cA = pixels_A[pos];
                    int rA = (cA & 0xff0000) >> 16;
                    int gA = (cA & 0x00ff00) >> 8;
                    int bA = (cA & 0x0000ff);

                    int cB = pixels_B[pos];
                    int rB = (cB & 0xff0000) >> 16;
                    int gB = (cB & 0x00ff00) >> 8;
                    int bB = (cB & 0x0000ff);

                    if (methode == 1) // Wischen check
                    {
                        if (y+1 < (z-1)*(double)height/(length-2))
                            pixels_Erg[pos] = pixels_B[pos];
                        else
                            pixels_Erg[pos] = pixels_A[pos];
                    }

					if (methode == 2) // Weiche Blende
					{

					    int alphaMax = 95;
					    int alpha = z;


					int rN = ( alpha * rA + ( alphaMax - alpha ) * rB ) / alphaMax;
					int gN = ( alpha * gA + ( alphaMax - alpha ) * gB ) / alphaMax;
					int bN = ( alpha * bA + ( alphaMax - alpha ) * bB ) / alphaMax;

					pixels_Erg[pos] = 0xFF000000 + ((rN & 0xff) << 16) + ((gN & 0xff) << 8) + ( bN & 0xff);
					}

					if (methode == 3) // Overlay AB
					{

                        int rN = 128;
                        int gN = 128;
                        int bN = 128;


                        if (rA <= 128){
                            rN = rA*rB /128;
                        }
                        if (rA > 128) {
                            rN = 255 - ( ( 255 - rA) * (255 - rB) / 128);
                        }
                        if (gA <= 128){
                            gN =  gA*gB /128;
                        }
                        if (gA > 128) {
                            gN = 255 - ( ( 255 - gA) * (255 - gB) / 128);
                        }
                        if (bA <= 128){
                            bN =  bA*bB /128;
                        }
                        if (bA > 128) {
                            bN = 255 - ( ( 255 - bA) * (255 - bB) / 128);
                        }


					pixels_Erg[pos] = 0xFF000000 + ((rN & 0xff) << 16) + ((gN & 0xff) << 8) + ( bN & 0xff);
					}

                    if (methode == 4) // Overlay BA
					{

                        int rN = 128;
                        int gN = 128;
                        int bN = 128;


                        if (rB <= 128){
                            rN = rB*rA /128;
                        }
                        if (rB > 128) {
                            rN = 255 - ( ( 255 - rB) * (255 - rA) / 128);
                        }
                        if (gB <= 128){
                            gN =  gB*gA /128;
                        }
                        if (gB > 128) {
                            gN = 255 - ( ( 255 - gB) * (255 - gA) / 128);
                        }
                        if (bB <= 128){
                            bN =  bB*bA /128;
                        }
                        if (bB > 128) {
                            bN = 255 - ( ( 255 - bB) * (255 - bA) / 128);
                        }



					pixels_Erg[pos] = 0xFF000000 + ((rN & 0xff) << 16) + ((gN & 0xff) << 8) + ( bN & 0xff);
					}

					if (methode == 5) // Schieb Blende
                    {
                        if (x+1 > (z-1)*(double)width/(length-2)) {
                            pixels_Erg[pos] = pixels_A[ (int) ( pos-((z-1)*(double)width/(length-2)))];
                        }
                        else
                            pixels_Erg[pos] = pixels_B[pos];

                    }

					if (methode == 6) // Chromakey
					{

                        int rKey= 216;
                        int gKey= 157;
                        int bKey= 56;

                        int keyRange = 20; // 25 ist besser sauberer für orange

                        if( rKey-keyRange < rA && rA < rKey + keyRange ){
                            pixels_Erg[pos] = pixels_B[pos];
                        }
                        if( gKey-keyRange < gA && gA < gKey + keyRange ){
                            pixels_Erg[pos] = pixels_B[pos];
                        }
                        // Das blau ist das problem und ich bekomme es nicht besser hin
                        if( bKey-keyRange < bA && bA < rKey + keyRange ){
                            pixels_Erg[pos] = pixels_B[pos];
                        }
                        else
                            pixels_Erg[pos] = pixels_A[pos];



					}

					if (methode == 7) // Überblendung
					{

                        int xMittelPunk = width/2;
                        int yMittelPunkt = height/2;
                        int radius = 80;
                             // Lol multiply with itself is an easy way to square in java
                        if ( ( x - xMittelPunk )*( x - xMittelPunk ) + ( y - yMittelPunkt )*( y - yMittelPunkt ) >= radius*radius ) {
                            pixels_Erg[pos] = pixels_B[pos];
                        }
                        else
                            //Normal
                            //pixels_Erg[pos] = pixels_A[pos];
                            //Abgespaced
                            pixels_Erg[pos] = cA-cB;



					}




                }
        }
        // neues Bild anzeigen
        Erg.show();
        Erg.updateAndDraw();
    }
}
