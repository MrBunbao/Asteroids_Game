package enhancedAsteroids;

import javax.swing.*;

import enhancedAsteroids.EnhancedController;

import java.awt.*;

import static enhancedAsteroids.Constants.*;

/**
 * Defines the top-level appearance of an Asteroids game.
 */
@SuppressWarnings("serial")
public class Display extends JFrame {
	// The area where the action takes place
	private ScreenE screen;

	// Label for score
	private JLabel scoreLabel;

	// Label for lives
	private JLabel livesLabel;

	// Label for level
	private JLabel levelLabel;

	/**
	 * Lays out the game and creates the controller
	 */
	public Display(EnhancedController controller) {
		// Title at the top
		setTitle(TITLE);

		// Default behavior on closing
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// make the window un-re-size-able
		setMaximumSize(new Dimension(SIZE, SIZE + 50));
		setMinimumSize(new Dimension(SIZE, SIZE + 50));

		// The main playing area and the controller
		screen = new ScreenE(controller);

		// This panel contains the screen to prevent the screen from being
		// resized
		JPanel screenPanel = new JPanel();
		screenPanel.setLayout(new GridBagLayout());
		screenPanel.add(screen);

		// This panel contains buttons and labels
		JPanel controls = new JPanel();
		controls.setLayout(new GridLayout(1, 2));

		JPanel leftTopSide = new JPanel();
		JPanel rightTopSide = new JPanel();

		controls.add(leftTopSide);
		controls.add(rightTopSide);

		// The button that starts the game
		JButton startGame = new JButton(START_LABEL);
		leftTopSide.add(startGame);

		levelLabel = new JLabel();
		rightTopSide.add(levelLabel);

		livesLabel = new JLabel();
		rightTopSide.add(livesLabel);

		scoreLabel = new JLabel();
		rightTopSide.add(scoreLabel);

		// Organize everything
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add((Component) screenPanel, "Center");
		mainPanel.add((Component) controls, "North");
		setContentPane(mainPanel);
		pack();

		// Connect the controller to the start button
		startGame.addActionListener(controller);
	}

	/**
	 * Called when it is time to update the screen display. This is what drives
	 * the animation.
	 */
	public void refresh() {
		screen.repaint();
	}

	public void setScore(int score) {
		this.scoreLabel.setText(" Score: " + score);
	}

	public void setLevel(int level) {
		this.levelLabel.setText(" Level: " + level);
	}

	public void setLives(int lives) {
		this.livesLabel.setText(" Lives: " + lives);
	}

	/**
	 * Sets the large legend
	 */
	public void setLegend(String s) {
		screen.setLegend(s);
	}

	/**
	 * sets the multiplier
	 */
	public void setMultiplier(int multi) {
		screen.setMultiplier(multi);
	}

	/**
	 * sets the powerup Label
	 */
	public void setPowerUpLabel(String discription, int secLeft) {
		if (secLeft == 0) {
			screen.setPowerUpLabel(discription);
		} else {
			screen.setPowerUpLabel(discription + " " + secLeft);
		}
	}

}
