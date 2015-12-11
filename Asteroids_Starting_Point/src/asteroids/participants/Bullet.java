package asteroids.participants;

import asteroids.Participant;
import asteroids.ParticipantCountdownTimer;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import asteroids.Constants;

/**
 * The physics of the bullet. The bullet will expire after the value of
 * Constants.BULLET_DURATION.
 */
public abstract class Bullet extends Participant {

	private Shape outline;

	// after 1000 msecs bullet expires.
	@SuppressWarnings("unused")
	private ParticipantCountdownTimer duration;

	public Bullet(double x, double y, double direction) {
		this.setPosition(x, y);
		this.setVelocity(Constants.BULLET_SPEED, direction);
		this.outline = new Ellipse2D.Double(0, 0, 1, 1);
		duration = new ParticipantCountdownTimer(this, Constants.BULLET_DURATION);
	}

	@Override
	protected Shape getOutline() {
		return this.outline;
	}

	@Override
	public void countdownComplete(Object payload) {
		Participant.expire(this);
	}
}
