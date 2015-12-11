package enhancedAsteroids;

import java.awt.*;
import java.util.Iterator;
import javax.swing.*;
import static enhancedAsteroids.Constants.*;

/**
 * The area of the display in which the game takes place.
 */
@SuppressWarnings("serial")
public class ScreenE extends JPanel {
	// Legend that is displayed across the screen
	private String legend;

	// font of the legend
	private Font legendFont;

	// the name of the power up in play currently.
	private String pwrUpName;

	// level of score multiplier
	private String multiplierLabel;

	// Game controller
	private EnhancedController controller;

	/**
	 * Creates an empty screen
	 */
	public ScreenE(EnhancedController controller) {
		pwrUpName = "";
		setMultiplier(controller.getMultiplier());
		this.controller = controller;
		legend = "";
		setPreferredSize(new Dimension(SIZE, SIZE));
		setMinimumSize(new Dimension(SIZE, SIZE));
		setBackground(Color.black);
		setForeground(Color.white);
		legendFont = new Font(Font.SANS_SERIF, Font.PLAIN, 120);
		setFont(legendFont);
		setFocusable(true);

	}

	/**
	 * Set the legend
	 */
	public void setLegend(String legend) {
		this.legend = legend;
	}

	/**
	 * @param multiplier,
	 *            the multiplier to set
	 */
	public void setMultiplier(int multiplier) {
		multiplierLabel = "Score X" + multiplier;
	}

	/**
	 * sets the label for the power-up panel
	 */
	public void setPowerUpLabel(String text) {
		pwrUpName = text;
	}

	/**
	 * Paint the participants onto this panel
	 */
	@Override
	public void paintComponent(Graphics g) {
		// Do the default painting
		super.paintComponent(g);

		// Draw each participant in its proper place
		Iterator<Participant> iter = controller.getParticipants();
		while (iter.hasNext()) {
			iter.next().draw((Graphics2D) g);
		}

		// Draw the legend across the middle of the panel
		int size = g.getFontMetrics().stringWidth(legend);
		g.drawString(legend, (SIZE - size) / 2, SIZE / 2);
		g.setFont(new Font(getFont().getFontName(), Font.BOLD, 15));
		g.drawString(multiplierLabel, 0, SIZE);
		size = g.getFontMetrics().stringWidth(pwrUpName);
		g.drawString(pwrUpName, SIZE - size, SIZE);
		g.setFont(legendFont);

	}

}
