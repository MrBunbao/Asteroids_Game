package asteroids;

import java.awt.geom.Path2D;
import java.util.Random;

import javafx.scene.shape.*;



/**
 * Provides constants that govern the game.
 */
public class Constants
{
    /**
     * A shared random number generator for general use.
     */
    public final static Random RANDOM = new Random();

    /**
     * The height and width of the game area.
     */
    public final static int SIZE = 750;

    /**
     * Game title
     */
    public final static String TITLE = "CS 1410 Asteroids - by Lincoln Matheson & Andy Dao";

    /**
     * Label on start game button
     */
    public final static String START_LABEL = "Start Game";

    /**
     * Speed beyond which participants may not accelerate
     */
    public final static double SPEED_LIMIT = 15;

    /**
     * Amount of "friction" that can be applied to ships so that they eventually
     * stop. Should be negative.
     */
    public final static double SHIP_FRICTION = -0.05;

    /**
     * Constant of acceleration of ship. Should be positive.s
     */
    public final static double SHIP_ACCELERATION = .65;

    /**
     * The number of milliseconds between the beginnings of frame refreshes
     */
    public final static int FRAME_INTERVAL = 33;
    
    /**
     * The number of milliseconds between beats, initially.
     */
    public final static int INITIAL_BEAT = 900;
    
    /**
     * The fastest beat interval.
     */
    public final static int FASTEST_BEAT = 300;
    
    /**
     * The amount by which the beat interval increases each time there's a beat.
     */
    public final static int BEAT_DELTA = 9;

    /**
     * The number of milliseconds between the end of a life and the display of
     * the next screen.
     */
    public final static int END_DELAY = 2500;

    /**
     * The average offset in pixels from the edges of the screen of newly-placed
     * asteroids.
     */
    public final static int EDGE_OFFSET = 150;

    /**
     * The game over message
     */
    public final static String GAME_OVER = "Game Over";

    
    /**
     * Scaling factors used for asteroids of size 0, 1, and 2.
     */
    public final static double[] ASTEROID_SCALE = { 0.5, 1.0, 2.0 };

    /**
     * Score earned for asteroids of size 0, 1, and 2.
     */
    public final static int[] ASTEROID_SCORE = { 100, 50, 20 };

    /**
     * Scaling factors used for alien ships of size 0 and 1.
     */
    public final static double[] ALIENSHIP_SCALE = { 0.5, 1.0 };
    
    /**
     * Score earned for alien ships of size 0 and 1.
     */
    public final static int[] ALIENSHIP_SPEED = { 6, 4 };
    
    /**
     * Delay after which an alien ship appears.
     */
    public final static int ALIEN_DELAY = 5000;
    
    /**
     * Slow asteroid speed
     */
    public final static int SLOW_ASTEROID_SPEED = 3;
    
    /**
     * Medium asteroid speed
     */
    public final static int MEDIUM_ASTEROID_SPEED = 4;
    
    /**
     * Fast asteroid speed
     */
    public final static int FAST_ASTEROID_SPEED = 5;
    
    /**
     * bullet dimensions.
     */
    public final static int BULLETX = 3;
    public final static int BULLETY = 1;
    
	/**
	 * Duration in milliseconds of a bullet before it disappears.
	 */
    public final static int BULLET_DURATION = 1000;

    /**
     * bullet speed
     */
	public static final double BULLET_SPEED = 15;
	

}