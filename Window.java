package game;

import game.Game.STATE;
import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

/**
 *
 * @author Zachary Bianucci
 */
public class Window extends Canvas {

    private static final long serialVersionUID = 1L;
    JFrame frame;
    Game game;

    public Window(int width, int height, String title, Game game) {
        //frame of the window
        this.frame = new JFrame(title);
        this.game = game;
        //set the size
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMaximumSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(width, height));

        //makes sure the x button works
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //doesn't allow user to resize
        frame.setResizable(false);
        //puts window in the middle of the screen
        frame.setLocationRelativeTo(null);
        //adds the game class into the frame
        frame.add(game);
        //makes sure you can see the frame
        frame.setVisible(true);
        //runs the start method in the game class
        game.start();

    }

    public void blankCursor() {
        // Transparent 16 x 16 pixel cursor image.
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);

        // Create a new blank cursor.
        Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(
                cursorImg, new Point(0, 0), "blank cursor");

        // Set the blank cursor to the JFrame.
        frame.getContentPane().setCursor(blankCursor);

    }

    public void defaultCursor() {
        frame.getContentPane().setCursor(Cursor.getDefaultCursor());
    }
}
