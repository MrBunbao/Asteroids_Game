package enhancedAsteroids.participants;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import enhancedAsteroids.Constants;
import enhancedAsteroids.EnhancedController;
import enhancedAsteroids.Participant;

public class PowerUp extends Participant {

	private Shape outline;

	private EnhancedController controller;

	private String discription;

	private int index;

	private int duration;
	

	public PowerUp(EnhancedController ec, int index) {
		this.setPosition(Constants.RANDOM.nextInt(Constants.SIZE), Constants.RANDOM.nextInt(Constants.SIZE));
		controller = ec;
		this.index = index;
		switch (index) {
		case 0:
			duration = 4000;
			setDiscription("Rapid fire");
			break;
		case 1:
			duration = 0;
			setDiscription("Extra Life");
			break;
		case 2:
			duration = 4000;
			setDiscription("Max Multiplier");
			break;
		case 3:
			duration = 0;
			setDiscription("Hot Shot Ready");
			break;
		case 4:
			duration = 4000;
			setDiscription("Invincibillity");
			break;
		}
		outline = new Rectangle2D.Double(0, 0, 15, 15);
	}

	private void setDiscription(String text) {
		this.discription = text;
	}

	@Override
	public void collidedWith(Participant p) {
		if (p instanceof Ship) {
			controller.powerUpEngage(this);
			expire(this);
		}
	}
	
	/**
	 * @return the duration in m/s
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @return the description
	 */
	public String getDiscription() {
		return discription;
	}

	@Override
	protected Shape getOutline() {
		return outline;
	}
}
