package asteroids;


import java.awt.event.*;
import java.util.Iterator;
import java.util.Random;

import javax.swing.*;

import asteroids.Participant;
import asteroids.ParticipantState;
import asteroids.destroyers.AlienShip;
import asteroids.participants.Asteroid;
import asteroids.participants.Ship;
import static asteroids.Constants.*;

/**
 * Controls a game of Asteroids.
 */
public class Controller implements KeyListener, ActionListener
{
	//the current level
	private int level;
	
	//total score
	private int score;
    
	// The state of all the Participants
    private ParticipantState pstate;
    
    // The ship (if one is active) or null (otherwise)
    private Ship ship;
    
    // When this timer goes off, it is time to refresh the animation
    private Timer refreshTimer;

    // The time at which a transition to a new stage of the game should be made.
    // A transition is scheduled a few seconds in the future to give the user
    // time to see what has happened before doing something like going to a new
    // level or resetting the current level.
    private long transitionTime;
    
    // Number of lives left
    private int lives;

    // calls the alien ship
    private Timer alienShipTimer;
    
    // The game display
    private Display display;
    
    private boolean turnLeft;
    private boolean turnRight;
    private boolean goForward;
    private boolean fireBullet;
    
    private Timer beatTimer;
    
    private int alternateBeat;
    

    /**
     * Constructs a controller to coordinate the game and screen
     */
    public Controller ()
    {
        // Initialize the ParticipantState
        pstate = new ParticipantState();

        // Set up the refresh timer.
        refreshTimer = new Timer(FRAME_INTERVAL, this);

        //sets up the alienShipTimer
        alienShipTimer = new Timer((5 + Constants.RANDOM.nextInt(6)) * 1000, this);
        
        // Clear the transitionTime
        transitionTime = Long.MAX_VALUE;
        
        // Record the display object
        display = new Display(this);

        //Beat Timer
        beatTimer = new Timer(Constants.INITIAL_BEAT, this);
        
        // Bring up the splash screen and start the refresh timer
        splashScreen();
        display.setVisible(true);
        refreshTimer.start();

    }

    /**
     * Returns the ship, or null if there isn't one
     */
    public Ship getShip ()
    {
        return ship;
    }

    /**
     * Configures the game screen to display the splash screen
     */
    private void splashScreen ()
    {
        // Clear the screen, reset the level, and display the legend
        clear();
        level = 1;
        display.setLegend("Asteroids");

        // Place four asteroids near the corners of the screen.
        placeAsteroids(level);
    }

    /**
     * The game is over. Displays a message to that effect.
     */
    private void finalScreen ()
    {
        display.setLegend(GAME_OVER);
        display.removeKeyListener(this);
    }

    /**
     * Place a new ship in the center of the screen. Remove any existing ship
     * first.
     */
    private void placeShip ()
    {
        // Place a new ship
        Participant.expire(ship);
        
    	turnLeft = false;
        turnRight = false;
        goForward = false;
        fireBullet = false;
        ship = new Ship(SIZE / 2, SIZE / 2, -Math.PI / 2, this);
        addParticipant(ship);
        display.setLegend("");
    }

    /**
     * Places asteroids based on level near the corners of the screen. Gives them random
     * velocities and rotations.
     */
    private void placeAsteroids (int level)
    {
        addParticipant(new Asteroid(0, 2, EDGE_OFFSET, EDGE_OFFSET, SLOW_ASTEROID_SPEED, this));
        addParticipant(new Asteroid(1, 2, SIZE - EDGE_OFFSET, EDGE_OFFSET, SLOW_ASTEROID_SPEED, this));
        addParticipant(new Asteroid(2, 2, EDGE_OFFSET, SIZE - EDGE_OFFSET, SLOW_ASTEROID_SPEED, this));
        addParticipant(new Asteroid(3, 2, SIZE - EDGE_OFFSET, SIZE - EDGE_OFFSET, SLOW_ASTEROID_SPEED, this));
        for(int i = 1; i < level; i++){
        	addParticipant(new Asteroid(new Random().nextInt(4), 2, new Random().nextInt(5) * EDGE_OFFSET,  new Random().nextInt(5) * EDGE_OFFSET, SLOW_ASTEROID_SPEED, this));
        }
    }
    /**
     * Clears the screen so that nothing is displayed
     */
    private void clear ()
    {
        pstate.clear();
        display.setLegend("");
        if(ship != null){
        ship.notAccelerating();
        ship = null;
        }
    }

    /**
     * Sets things up and begins a new game.
     */
    private void initialScreen ()
    {
    	beatTimer.stop();
		beatTimer.setDelay(Constants.INITIAL_BEAT);
		beatTimer.start();
    	
        // Clear the screen
        clear();

        // Place four asteroids
        placeAsteroids(1);

        // Place the ship
        placeShip();

        // Reset statistics
        lives = 3;
        level = 1;
        score = 0;
        
        // Settings the labels for lives, level, score
        display.setLives(lives);
        display.setLevel(level);
        display.setScore(score);
        
        // Will help with turning
        turnLeft = false;
        turnRight = false;
        goForward = false;
        fireBullet = false;
        
        // Starts beat 1
        alternateBeat = 2;
        
        // Start listening to events (but don't listen twice)
        display.removeKeyListener(this);
        display.addKeyListener(this);

        // Give focus to the game screen
        display.requestFocusInWindow();
    }

    /**
     * Adds a new Participant
     */
    public void addParticipant (Participant p)
    {
        pstate.addParticipant(p);
    }


    /**
     * The ship has been destroyed
     */
    public void shipDestroyed ()
    {
        // Null out the ship
        ship = null;
        
        //alien ship wont appear at a transition
        if(alienShipTimer.isRunning())
        {
        alienShipTimer.stop();
        }
        
        // Display a legend
        display.setLegend("Loser");

        // Decrement lives
        lives--;
        
        // Changes the display of lives
        display.setLives(lives);
        
        // Since the ship was destroyed, schedule a transition
        scheduleTransition(END_DELAY);
    }

    /**
     * An asteroid of the given size has been destroyed
     */
    @SuppressWarnings("static-access")
    public void asteroidDestroyed (int size)
    {
    	//100 for small
    	//50 for med
    	//20 for big
    	switch(size){
    	case -1:
    		ship.getSounds().playBangSmallClip();
    		scoreAdder(50);
    	case 0:
    		ship.getSounds().playBangMediumClip();
    		scoreAdder(30);
    	case 1:
    		ship.getSounds().playBangLargeClip();
    		scoreAdder(20);
    	}
        // If all the asteroids are gone, schedule a transition
        if (pstate.countAsteroids() == 0)
        {
        	beatTimer.stop();
        	display.setLegend("Level " + (1 + level));
            scheduleTransition(END_DELAY);
        }
    }


    /**
     * Schedules a transition m msecs in the future
     */
    private void scheduleTransition (int m)
    {
        transitionTime = System.currentTimeMillis() + m;
    }

    /**
     * This method will be invoked because of button presses and timer events.
     */
    @Override
    public void actionPerformed (ActionEvent e)
    {
        // The start button has been pressed. Stop whatever we're doing
        // and bring up the initial screen
        if (e.getSource() instanceof JButton)
        {
            initialScreen();
        }
        
        else if(e.getSource() == beatTimer && alternateBeat == 1)
        {
        	Participant.getSounds().playBeat1Clip();
        	beatTimer.setDelay(Math.max(Constants.FASTEST_BEAT, beatTimer.getDelay() - Constants.BEAT_DELTA));
        	alternateBeat = 2;
        }
        
        else if(e.getSource() == beatTimer && alternateBeat == 2)
        {
        	Participant.getSounds().playBeat2Clip();
        	beatTimer.setDelay(Math.max(Constants.FASTEST_BEAT, beatTimer.getDelay() - Constants.BEAT_DELTA));
        	alternateBeat = 1;
        }
        
        // Time to refresh the screen and deal with keyboard input
        else if (e.getSource() == refreshTimer)
        {
        	
            // It may be time to make a game transition
            performTransition();

            // Move the participants to their new locations
            pstate.moveParticipants();
            
            if(turnLeft && ship != null){
            	ship.turnLeft();
            }
            if(turnRight && ship != null){
            	ship.turnRight();
            }
            if(goForward && ship != null){
            	ship.accelerate();
            }
            	else if(ship != null)
            	{
            		ship.notAccelerating();
            	}
            if(fireBullet && ship != null){
            	ship.fire();
            	fireBullet = false;
            }
            
            // Refresh screen
            display.refresh();
        }
	        if(e.getSource() == alienShipTimer )
	        {
	        	if(!pstate.isAlienShip())
	        	{
	        	callAlienShip(level);
	        	alienShipTimer.stop();
	        	}
	        }
    }



	/**
     * Returns an iterator over the active participants
     */
    public Iterator<Participant> getParticipants ()
    {
        return pstate.getParticipants();
    }

    /**
     * If the transition time has been reached, transition to a new state
     */
    private void performTransition ()
    {
        // Do something only if the time has been reached
        if (transitionTime <= System.currentTimeMillis())
        {
            // Clear the transition time
            transitionTime = Long.MAX_VALUE;

            // If there are no lives left, the game is over. Show the final
            // screen.
            if (lives <= 0)
            {
                finalScreen();
            }

            // If the ship was destroyed, place a new one and continue
            else if (ship == null)
            {
                placeShip();
            }
            
            // Moves to next level
            if(pstate.countAsteroids() == 0){
            	
            	level++;
            	newLevel(level);
            }
        }
    }
    /**
     * sets up the game for a new level.
     */
    private void newLevel(int level) {
    	display.setLevel(level);
    	display.setLegend("");
    	clear();
    	placeAsteroids(level);
        placeShip();
    	alienShipTimer.start();
    	beatTimer.stop();
    	beatTimer.start();
	}
	/**
	 * summons an alien ship based on level
	 */
	public void callAlienShip(int level){
		AlienShip aShip;
		if(level > 2){
			aShip = new AlienShip(Constants.RANDOM.nextBoolean(),this);
		}else{
			aShip = new AlienShip(false, this);
		}
		this.addParticipant(aShip);
	}

    /**
     * If a key of interest is pressed, record that it is down, start a timer that rotates the ship smoothly.
     */
    @Override
    public void keyPressed (KeyEvent e)
    {
    	
    	int keyCode = e.getKeyCode();
        if((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) && ship != null)
        {
            turnLeft = true;
            
        }
        if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT ) && ship != null)
        {
            turnRight = true;
        }
        if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP ) && ship != null)
        {
        	goForward = true;
        }
        if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_SPACE) && ship != null)
        {
        	fireBullet = true;
        }
        if (keyCode == KeyEvent.VK_1)
        {
        	level++;
            beatTimer.stop();
           	display.setLegend("Level " + (1 + level));
            scheduleTransition(END_DELAY);
            newLevel(level);
        }
    }

    /**
     * Ignore these events.
     */
    @Override
    public void keyTyped (KeyEvent e)
    {
    }

    /**
     * stops the timer that rotates the ship.
     */
    @Override
    public void keyReleased (KeyEvent e)
    {      
    	int keyCode = e.getKeyCode();
        if((keyCode == KeyEvent.VK_A || keyCode == KeyEvent.VK_LEFT) && ship != null)
        {
            turnLeft = false;
        }
        if ((keyCode == KeyEvent.VK_D || keyCode == KeyEvent.VK_RIGHT ) && ship != null)
        {
        	turnRight = false;
        }
        if ((keyCode == KeyEvent.VK_W || keyCode == KeyEvent.VK_UP ) && ship != null)
        {
        	goForward = false;
        }
        if ((keyCode == KeyEvent.VK_S || keyCode == KeyEvent.VK_SPACE) && ship != null)
        {
        	fireBullet = false;
        }
    }
    
    // Keeps checking if there are more than 8 bullets on the screen.
    // if so, it'll expire 1 bullet, keeping only a maximum of 8 bullets
    // on screen.
    public boolean bulletLimit (int bulletAmount)
    {
    	if(pstate.countBullets() >= bulletAmount)
    	{
    		return true;
    	}
    	return false;
    }
    /**
     * increments score
     */
    public void scoreAdder (int n)
    {
    	score += n;
    	display.setScore(score);
    }

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
/**
 * adds to the score if you destroy the ship. no matter what destroys the ship restarts the call ship timer.
 */
	public void etGoneHome(int worth) {
		scoreAdder(worth);
		alienShipTimer.start();
	}
}