import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;

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

    public void drawFrame(){
        myCanvas.drawLine(20,20, 580, 20);
        myCanvas.drawLine(580, 20, 580, 480);
        myCanvas.drawLine(580, 480, 20, 480);
        myCanvas.drawLine(20, 480, 20, 20);
    }
    
    public void drawAdaptiveFrame(int x){
        int currentWidth = myCanvas.getWidth();
        int currentHeight = myCanvas.getHeight();
        
        myCanvas.drawLine(x,x,myCanvas.getWidth()-x , x);
        myCanvas.drawLine(x,x, currentWidth-x, x);
        myCanvas.drawLine(currentWidth-x, x, currentWidth-x, currentHeight-x);
        myCanvas.drawLine(currentWidth-x, currentHeight-x, x, currentHeight-x);
        myCanvas.drawLine(x, currentHeight-x,x,x);
    }
    
    /**
     * Simulate two bouncing balls
     */
    public void bounce()
    {
        int ground = 400;   // position of the ground line

        myCanvas.setVisible(true);

        // draw the ground
        myCanvas.setForegroundColor(Color.BLACK);
        if (LEAVE_TRACES_ON_GROUND)
            myCanvas.drawLine(50, ground, 550, ground);
        else
            myCanvas.drawLine(50, ground+1, 550, ground+1);
        // create and show the balls
        BouncingBall ball = new BouncingBall(50, 50, 16, Color.BLUE, ground, myCanvas);
        ball.draw();
        BouncingBall ball2 = new BouncingBall(70, 80, 20, Color.RED, ground, myCanvas);
        ball2.draw();

        // make them bounce
        boolean finished =  false;
        while (!finished) {
            myCanvas.wait(50);           // small delay
            ball.move();
            ball2.move();
            // stop once ball has travelled a certain distance on x axis
            if(ball.getXPosition() >= 550 || ball2.getXPosition() >= 550) {
                finished = true;
            }
        }
    }
     /**
     * Demonstrate some of the drawing operations that are
     * available on a Canvas object.
     */
    public void drawDemo()
    {
        myCanvas.setFont(new Font("helvetica", Font.BOLD, 10));
        myCanvas.setForegroundColor(Color.red);

        myCanvas.drawString("We can draw text, ...", 200, 30);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.black);
        myCanvas.drawString("...draw lines...", 60, 60);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.RED);
        myCanvas.drawLine(200,200, 200,400);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.blue);
        myCanvas.drawLine(220, 100, 370, 40);
        myCanvas.wait(500);
        myCanvas.setForegroundColor(Color.green);
        myCanvas.drawLine(290, 10, 320, 120);
        myCanvas.wait(1000);

        myCanvas.setForegroundColor(Color.gray);
        myCanvas.drawString("...and shapes!", 110, 1000);

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

}
