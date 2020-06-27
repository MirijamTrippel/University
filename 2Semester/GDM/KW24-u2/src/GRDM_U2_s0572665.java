import ij.IJ;
import ij.ImagePlus;
import ij.WindowManager;
import ij.gui.ImageCanvas;
import ij.gui.ImageWindow;
import ij.plugin.PlugIn;
import ij.process.ImageProcessor;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;

import javax.swing.BorderFactory;
import javax.swing.JSlider;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 Opens an image window and adds a panel below the image
 */

public class GRDM_U2_s0572665 implements PlugIn {

    ImagePlus imp; // ImagePlus object
    private int[] origPixels;
    private int width;
    private int height;

    private double[] kontrast = {0, 0.2, 0.4, 0.6, 0.8, 1, 2, 4, 6, 8, 10};


    public static void main(String args[]) {
        //Path  ||| new ImageJ(); |||
            IJ.open("C:\\Users\\Miri\\Desktop\\GitHub\\University\\2Semester\\KW19\\Picture\\orchid.jpg");
        //Program Instance
        //TODO Aufgabe 1
            GRDM_U2_s0572665 pw = new GRDM_U2_s0572665();
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


    class CustomWindow extends ImageWindow implements ChangeListener {

        private JSlider jSliderBrightness;
        private JSlider jSliderKontrast;
        private JSlider jSliderSaettigung;
        private JSlider jSliderHue;

        private int brightness;
        private double contrast;

        CustomWindow(ImagePlus imp, ImageCanvas ic) {
            super(imp, ic);
            addPanel();
        }

        //TODO Aufgabe 4
        void addPanel() {
            //JPanel panel = new JPanel();
            Panel panel = new Panel();

            panel.setLayout(new GridLayout(4, 1));

            //Panels
                //Helligkeit
                    jSliderBrightness = makeTitledSilder("Helligkeit", -128, 128, 0);
                //Kontrast
                    jSliderKontrast = makeTitledSilder("Kontrast", 0, 10, 5);
                //Saettigung
                //    jSliderSaettigung = makeTitledSildermakeTitledSilderSaettigung("Saettigung", 0, 5, 3);
                //Hue
                //    jSliderHue =        makeTitledSilderHue("Hue", 0, 360, 180);


            // Adds Panels
                panel.add(jSliderBrightness);
                panel.add(jSliderKontrast);
                //panel.add(jSliderSaettigung);
                //panel.add(jSliderHue);
                add(panel);

                pack();
        }
        private JSlider makeTitledSilder(String string, int minVal, int maxVal, int val) {

            JSlider slider = new JSlider(JSlider.HORIZONTAL, minVal, maxVal, val );
            Dimension preferredSize = new Dimension(width, 50);
            slider.setPreferredSize(preferredSize);
            TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(),
                    string, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
                    new Font("Sans", Font.PLAIN, 11));
            slider.setBorder(tb);
            slider.setMajorTickSpacing((maxVal - minVal)/10 );
            slider.setPaintTicks(true);
            slider.addChangeListener(this);

            return slider;
        }

        //Kontrast
        private JSlider makeTitledSilderKontrast(String string, int minVal, int maxVal, int val) {

            JSlider slider = new JSlider(JSlider.HORIZONTAL, minVal, maxVal, val );
            Dimension preferredSize = new Dimension(width, 50);
            slider.setPreferredSize(preferredSize);
            TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(),
                    string, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
                    new Font("Sans", Font.PLAIN, 11));
            slider.setBorder(tb);


            // 0, 0.2, 0.4, 0.6, 0.8, First increments
                if ( val <= 1){
                    // Ich weiß echt nicht wie das so gehen soll, die methode nimmt nur int
                    slider.setMajorTickSpacing((int) 0.2);
                }
            // 2, 4, 6, 8, 10         Second increments
                else
                    slider.setMajorTickSpacing(2);

            slider.setPaintTicks(true);
            slider.addChangeListener(this);

            return slider;
        }

        //Saettigung
        /*private JSlider makeTitledSildermakeTitledSilderSaettigung(String string, int minVal, int maxVal, int val) {

            JSlider slider = new JSlider(JSlider.HORIZONTAL, minVal, maxVal, val );
            Dimension preferredSize = new Dimension(width, 50);
            slider.setPreferredSize(preferredSize);
            TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(),
                    string, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
                    new Font("Sans", Font.PLAIN, 11));
            slider.setBorder(tb);

            if (val <= 1) {
                slider.setMajorTickSpacing(10);
            }
            if () {}




            slider.setPaintTicks(true);
            slider.addChangeListener(this);

            return slider;
        }
         */
        //Hue
        private JSlider makeTitledSilderHue(String string, int minVal, int maxVal, int val) {

            JSlider slider = new JSlider(JSlider.HORIZONTAL, minVal, maxVal, val );
            Dimension preferredSize = new Dimension(width, 50);
            slider.setPreferredSize(preferredSize);
            TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(),
                    string, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
                    new Font("Sans", Font.PLAIN, 11));
            slider.setBorder(tb);
            //Tick pro °
            slider.setMajorTickSpacing(maxVal/360 );

            slider.setPaintTicks(true);
            slider.addChangeListener(this);

            return slider;
        }


        private void setSliderTitle(JSlider slider, String str) {
            TitledBorder tb = new TitledBorder(BorderFactory.createEtchedBorder(),
                    str, TitledBorder.LEFT, TitledBorder.ABOVE_BOTTOM,
                    new Font("Sans", Font.PLAIN, 11));
            slider.setBorder(tb);
        }

        public void stateChanged( ChangeEvent e ){
            JSlider slider = (JSlider)e.getSource();

            //Helligkeit
            if (slider == jSliderBrightness) {
                brightness = slider.getValue();
                String str = "Helligkeit " + brightness;
                setSliderTitle(jSliderBrightness, str);
            }

            if (slider == jSliderKontrast) {
                contrast = kontrast[slider.getValue()];
                String str = "Kontrast" + contrast;
                setSliderTitle(jSliderKontrast, str);
            }

            /*
            if (slider == jSliderSaettigung) {
                int saettigung = slider.getValue();
                String str = "Saettigung" + saettigung;
                setSliderTitle(jSliderSaettigung, str);
            }
            if (slider == jSliderHue) {
                int hue = slider.getValue();
                String str = "Hue" + hue;
                setSliderTitle(jSliderHue, str);
            }
            */

            //Process
            changePixelValues(imp.getProcessor());
            imp.updateAndDraw();
        }


        //TODO Aufgabe 3, Farbtransformation von RGB nach YUV
        private void changePixelValues(ImageProcessor ip) {

            // Array fuer den Zugriff auf die Pixelwerte
            int[] pixels = (int[])ip.getPixels();

            for (int y=0; y<height; y++) {
                for (int x=0; x<width; x++) {
                    int pos = y*width + x;
                    int argb = origPixels[pos];  // Lesen der Originalwerte

                    //Variablen   PixelShift Maske
                    int r = (argb >> 16) & 0xff;
                    int g = (argb >>  8) & 0xff;
                    int b =  argb        & 0xff;


                    //Transformation zu YUV
                    int Y = (int) ( 0.299 * r + 0.587 * g + 0.114 * b ) ;
                    int U = (int) ( (b-Y)*0.493 );
                    int V = (int) ( (r-Y)*0.877 );




                    //Kontrast f(j) = k*(j-127,5)+127,5 +h
                    // Pixelintensität j, Helligkeitsanderung h, kontraständerung k
                    // Helligkeit       , Values aus dem Slider, Values aus dem Slider
                    //Y = (int) (Y /* * (contrast-127.5)+-127.5 */ + brightness);
                    Y = Y + brightness;

                    //Check if contrast works = Something is not right and i think its the start values but its so :S

                    // Brightness works = Check




                    //Transformation zurück zu rgb
                    int R = (int) ( Y + V/0.877);
                    int G = (int) ( 1/0.587 * Y - 0.299/0.587*r - 0.114/0.587 * b );
                    int B = (int) ( Y + U/0.493 );

                    int rn = R;
                    int gn = G;
                    int bn = B;

                    //TODO Aufgabe 2
                    //Limits für Byte Shift
                    if (rn<0){
                        rn=0;
                    }
                    if (gn<0){
                        gn=0;
                    }
                    if (bn<0){
                        bn=0;
                    }
                    if (rn>255){
                        rn=255;
                    }
                    if (gn>255){
                        gn=255;
                    }
                    if (bn>255){
                        bn=255;
                    }

                    //Bau der Byte Reihenfolge mit oder "|"
                    pixels[pos] = (0xFF<<24) | (rn<<16) | (gn<<8) | bn;
                }
            }
        }

    } // CustomWindow inner class

    //Testing convertion
    public static int convert(int r, int g, int b){
        //Transformation zu YUV
        int Y = (int) ( 0.299 * r + 0.587 * g + 0.114 * b );
        int U = (int) ( (b-Y)*0.493 );
        int V = (int) ( (r-Y)*0.877 );
        //Transformation zurück zu rgb
        int R = (int) ( Y + V/0.877);
        int G = (int) ( 1/0.587 * Y - 0.299/0.587*r - 0.114/0.587 * b );
        int B = (int) ( Y + U/0.493 );
        return R + G + B;

    }


}