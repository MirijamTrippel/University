import ij.IJ;
import ij.ImageJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageCanvas;
import ij.gui.ImageWindow;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

import java.awt.Panel;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;

/**
 Opens an image window and adds a panel below the image
 */
public class GRDM_U3_s0572665 implements PlugIn {

    ImagePlus imp; // ImagePlus object
    private int[] origPixels;
    private int width;
    private int height;

    String[] items = {"Original", "Rot-Kanal","Negativ", "Graustufen","Binärbild_S/W","Binärbild_S/W_5",
                      "Binärbild_S/W_10", "HorizontaleFehlerdiffusion","Sepia","Binärbild_VF_6"};


    public static void main(String args[]) {

        IJ.open("C:\\Users\\Miri\\Desktop\\GitHub\\University\\2Semester\\KW21 GDM\\Bear.jpg");
        //IJ.open("Z:/Pictures/Beispielbilder/orchid.jpg");

        GRDM_U3_s0572665 pw = new GRDM_U3_s0572665();
        pw.imp = IJ.getImage();
        pw.run("");
    }

    public void run(String arg) {
        if (imp==null)
            imp = WindowManager.getCurrentImage();
        if (imp==null) {
            return;
        }
        CustomCanvas cc = new CustomCanvas(imp);

        storePixelValues(imp.getProcessor());

        new CustomWindow(imp, cc);
    }


    private void storePixelValues(ImageProcessor ip) {
        width = ip.getWidth();
        height = ip.getHeight();

        origPixels = ((int []) ip.getPixels()).clone();
    }


    class CustomCanvas extends ImageCanvas {

        CustomCanvas(ImagePlus imp) {
            super(imp);
        }

    } // CustomCanvas inner class


    class CustomWindow extends ImageWindow implements ItemListener {

        private String method;

        CustomWindow(ImagePlus imp, ImageCanvas ic) {
            super(imp, ic);
            addPanel();
        }

        void addPanel() {
            //JPanel panel = new JPanel();
            Panel panel = new Panel();

            JComboBox cb = new JComboBox(items);
            panel.add(cb);
            cb.addItemListener(this);

            add(panel);
            pack();
        }

        public void itemStateChanged(ItemEvent evt) {

            // Get the affected item
            Object item = evt.getItem();

            if (evt.getStateChange() == ItemEvent.SELECTED) {
                System.out.println("Selected: " + item.toString());
                method = item.toString();
                changePixelValues(imp.getProcessor());
                imp.updateAndDraw();
            }

        }


        private void changePixelValues(ImageProcessor ip) {

            // Array zum Zurückschreiben der Pixelwerte
            int[] pixels = (int[])ip.getPixels();

            if (method.equals("Original")) {

                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;

                        pixels[pos] = origPixels[pos];
                    }
                }
            }

            if (method.equals("Rot-Kanal")) {

                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;

                        int rn = r;
                        int gn = 0;
                        int bn = 0;

                        if (rn >= 255){
                            rn = 255;
                        }
                        if (gn >= 255){
                            rn = 255;
                        }
                        if (bn >= 255){
                            rn = 255;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Negativ")) {
                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int rn = 255-r;
                        int gn = 255-g;
                        int bn = 255-b;

                        if (rn >= 255){
                            rn = 255;
                        }
                        if (gn >= 255){
                            rn = 255;
                        }
                        if (bn >= 255){
                            rn = 255;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Graustufen")) {
                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int rn = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                        int gn = (int) (0.299 * r + 0.587 * g + 0.114 * b);
                        int bn = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                        if (rn >= 255){
                            rn = 255;
                        }
                        if (gn >= 255){
                            rn = 255;
                        }
                        if (bn >= 255){
                            rn = 255;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Binärbild_S/W")) {
                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int rn = r;
                        int gn = g;
                        int bn = b;

                        if (r >= 255){
                            r = 255;
                        }
                        if (g >= 255){
                            r = 255;
                        }
                        if (b >= 255){
                            r = 255;
                        }

                        int grau = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                        if (grau <= 127){
                            rn = 0;
                            gn = 0;
                            bn = 0;
                        }
                        if (grau >= 127){
                            rn = 255;
                            gn = 255;
                            bn = 255;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Binärbild_S/W_5")) {
                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int rn = r;
                        int gn = g;
                        int bn = b;

                        if (r >= 255){
                            r = 255;
                        }
                        if (g >= 255){
                            r = 255;
                        }
                        if (b >= 255){
                            r = 255;
                        }

                        int grau = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                        if (grau >= 0 & grau <= 50){
                            rn = 0;
                            gn = 0;
                            bn = 0;
                        }
                        if (grau >= 51 & grau <= 101){
                            rn = 64;
                            gn = 64;
                            bn = 64;
                        }
                        if (grau >= 102 & grau <= 152){
                            rn = 128;
                            gn = 128;
                            bn = 128;
                        }
                        if (grau >= 153 & grau <= 203){
                            rn = 192;
                            gn = 192;
                            bn = 192;
                        }
                        if (grau >= 204 & grau >= 255){
                            rn = 255;
                            gn = 255;
                            bn = 255;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Binärbild_S/W_10")) {
                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int rn = r;
                        int gn = g;
                        int bn = b;

                        if (r >= 255){
                            r = 255;
                        }
                        if (g >= 255){
                            r = 255;
                        }
                        if (b >= 255){
                            r = 255;
                        }

                        int grau = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                        if (grau >= 0 & grau <= 30){
                            rn = 0;
                            gn = 0;
                            bn = 0;
                        }
                        if (grau >= 31 & grau <= 55){
                            rn = 28;
                            gn = 28;
                            bn = 28;
                        }
                        if (grau >= 56 & grau <= 80){
                            rn = 56;
                            gn = 56;
                            bn = 56;
                        }
                        if (grau >= 81 & grau <= 105){
                            rn = 84;
                            gn = 84;
                            bn = 84;
                        }
                        if (grau >= 106 & grau <= 130){
                            rn = 112;
                            gn = 112;
                            bn = 112;
                        }
                        if (grau >= 131 & grau <= 155){
                            rn = 140;
                            gn = 140;
                            bn = 140;
                        }
                        if (grau >= 156 & grau <= 180){
                            rn = 168;
                            gn = 168;
                            bn = 168;
                        }
                        if (grau >= 181 & grau <= 205){
                            rn = 196;
                            gn = 196;
                            bn = 196;
                        }
                        if (grau >= 206 & grau <= 230){
                            rn = 224;
                            gn = 224;
                            bn = 224;
                        }
                        if (grau >= 231 & grau <= 255){
                            rn = 255;
                            gn = 255;
                            bn = 255;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("HorizontaleFehlerdiffusion")) {
                for (int y=0; y<height; y++) {
                    int fehlerKorrektur = 0;
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int grau = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                        int schwellenwert = grau + fehlerKorrektur;
                        int diffusion = 0;

                        if (schwellenwert >= 127) {
                            fehlerKorrektur = (-1) * (255-schwellenwert);
                            diffusion = 255;
                        }
                        if (schwellenwert <= 128) {
                            fehlerKorrektur = 128 + schwellenwert;
                            diffusion = 0;
                        }

                        int rn = diffusion;
                        int gn = diffusion;
                        int bn = diffusion;

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Sepia")) {
                for (int y=0; y<height; y++) {
                    for (int x=0; x<width; x++) {
                        int pos = y*width + x;
                        int argb = origPixels[pos];

                        int r = (argb >> 16) & 0xff;
                        int g = (argb >> 8) & 0xff;
                        int b = argb & 0xff;

                        int grau = (int) (0.299 * r + 0.587 * g + 0.114 * b);

                        int rn = grau +55;
                        int gn = grau +20;
                        int bn = grau -10;


                        if (rn >= 255){
                            rn = 255;
                        }
                        if (rn <= 0){
                            rn = 0;
                        }
                        if (gn >= 255){
                            gn = 255;
                        }
                        if (gn <= 0){
                            gn = 0;
                        }
                        if (bn >= 255){
                            bn = 255;
                        }
                        if (bn <= 0){
                            bn = 0;
                        }

                        pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;

                    }
                }
            }

            if (method.equals("Binärbild_VF_6")) {
                /// KEINE AHNUNG BLEP
            }



        }



    } // CustomWindow inner class
}