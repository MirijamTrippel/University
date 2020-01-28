 import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * Class BallDemo - a short demonstration showing animation with the 
 * Canvas class. 
 *
 * @author Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class BallDemo   
{
    public static final boolean LEAVE_TRACES_ON_GROUND = false;
    public static final boolean LEAVE_TRACE = false;
    private Canvas myCanvas;
    

    /**
     * Create a BallDemo object. Creates a fresh canvas and makes it visible.
     */
    public BallDemo()
    {
        myCanvas = new Canvas("Ball Demo", 600, 500,LEAVE_TRACE);
    }

    /**
     * Simulate two bouncing balls
     */
    public void bounce(int NumberOfBallz)
    {
        int ground = 400;   // position of the ground line
        myCanvas.setVisible(true);
        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        if (LEAVE_TRACES_ON_GROUND)
            myCanvas.drawLine(50, ground, 550, ground);
        else
            myCanvas.drawLine(50, ground+1, 550, ground+1);
        // create NumberOfBallz Balls
        BouncingBall[] ballz = new BouncingBall[NumberOfBallz];
        int xPos = 20;
        int yPos = 30;
        for (int i=0; i<NumberOfBallz; i++){
            ballz[i] = new BouncingBall(xPos, yPos, 20, Color.BLUE, ground, myCanvas);
            xPos = xPos +30;
            if(xPos >= myCanvas.getWidth()-50){
                xPos = 20;
                yPos = yPos +30;
            }
            ballz[i].draw();
        }
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for (BouncingBall element : ballz){
                element.move();
                if(ballz[0].getXPosition() >= 550) {
                    finished = true;
                }
            }
        }
    }
    
    public void bounceAtRandom(int NumberOfBallz)
    {
        int ground = 400;   // position of the ground line
        myCanvas.setVisible(true);
        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        if (LEAVE_TRACES_ON_GROUND)
            myCanvas.drawLine(50, ground, 550, ground);
        else
            myCanvas.drawLine(50, ground+1, 550, ground+1);
        // create NumberOfBallz Balls
        BouncingBall[] ballz = new BouncingBall[NumberOfBallz];
        Random random = new Random();
        int xPosFirstBall = myCanvas.getWidth() +100; // variable to find ball with smallest xPos (has to be unreachable)
        int indexFirstBall = 0;
        int xPos;
        int yPos;
        for (int i=0; i<NumberOfBallz; i++){
            xPos = random.nextInt(myCanvas.getWidth()-50)+20;
            yPos = random.nextInt(myCanvas.getHeight()/2)+30;
            ballz[i] = new BouncingBall(xPos, yPos, 20, Color.BLUE, ground, myCanvas);
            ballz[i].draw();
            if(xPos<=xPosFirstBall){
                xPosFirstBall = xPos;
                indexFirstBall = i;
            }
        }
        /*alternative way to find ball with smallest xPos (works as well):
        if(ballz.length > 2){
            for(int j=1; j<NumberOfBallz; j++){
                int xBall1 = ballz[j-1].getXPosition();
                int xBall2 = ballz[j].getXPosition();
                if(xBall1 <= xBall2){
                    indexFirstBall = j-1;
                }
            }
        }
        else{
            if(ballz[1].getXPosition() < ballz[0].getXPosition()){
                indexFirstBall = 1;
            }
        } */
        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            for (BouncingBall element : ballz){
                element.move();
                if(ballz[indexFirstBall].getXPosition() >= 550) {
                    finished = true;
                }
            }
        }
    }
    
     /**
     * Demonstrate some of the drawing operations that are
     * available on a Canvas object.
     */
    public void drawDemo()
    {
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 14));
        myCanvas.setForegroundColor(Color.red);

        myCanvas.drawString("We can draw text, ...", 20, 30);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.black);
        myCanvas.drawString("...draw lines...", 60, 60);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawLine(200, 20, 300, 50);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawLine(220, 100, 370, 40);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.green);
        myCanvas.drawLine(290, 10, 320, 120);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawString("...and shapes!", 110, 90);

        myCanvas.setForegroundColor(Color.red);

        // the shape to draw and move
        int xPos = 10;
        Rectangle rect = new Rectangle(xPos, 150, 30, 20);

        // move the rectangle across the screen
        for(int i = 0; i < 200; i ++) {
            myCanvas.fill(rect);
            myCanvas.wait(10);
            myCanvas.erase(rect);
            xPos++;
            rect.setLocation(xPos, 150);
        }
        // at the end of the move, draw once more so that it remains visible
        myCanvas.fill(rect);
    }
    
    public void drawFrame(){
        Rectangle frame = new Rectangle (20, 20, 560, 460);
        myCanvas.draw(frame);
    }
    
    public void drawFrameLines(int x)// x ist Rahmenbreite
    {
        myCanvas.drawLine(x,x,myCanvas.getWidth()-x , x);
        myCanvas.drawLine(x,x, myCanvas.getWidth()-x, x);
        myCanvas.drawLine(myCanvas.getWidth()-x, x, myCanvas.getWidth()-x, myCanvas.getHeight()-x);
        myCanvas.drawLine(myCanvas.getWidth()-x, myCanvas.getHeight()-x, x, myCanvas.getHeight()-x);
        myCanvas.drawLine(x, myCanvas.getHeight()-x,x,x);
    }
    
    public void drawFrameWide(int x){
        myCanvas.setForegroundColor(Color.black);
        int height = myCanvas.getHeight()-(2*x);
        int width = myCanvas.getWidth()-(2*x);
        Rectangle frame1 = new Rectangle(0, 0, myCanvas.getWidth(), myCanvas.getHeight());
        myCanvas.fill(frame1);
        myCanvas.setForegroundColor(Color.white);
        Rectangle frame2 = new Rectangle(x, x, width, height);
        myCanvas.fill(frame2);
    }
    
    public void drawFrameThin(int x){
        myCanvas.setForegroundColor(Color.black);
        Rectangle frame = new Rectangle(x, x, myCanvas.getWidth()-(2*x), myCanvas.getHeight()-(2*x));
        myCanvas.draw(frame);
    }
    
    public void drawFrameWideCustom(int x, int y) // x-y= Rahmendicke,
    {
        if(x <= y) // default
        {
            drawFrameThin(x);
        }
        else{
            myCanvas.setForegroundColor(Color.black);
            int height = myCanvas.getHeight()-(2*x);
            int width = myCanvas.getWidth()-(2*x);
            Rectangle frame1 = new Rectangle(y, y, myCanvas.getWidth()-(2*y), myCanvas.getHeight()-(2*y));
            myCanvas.fill(frame1);
            myCanvas.setForegroundColor(Color.white);
            Rectangle frame2 = new Rectangle(x, x, width, height);
            myCanvas.fill(frame2);
        }
    }
    
    
}
