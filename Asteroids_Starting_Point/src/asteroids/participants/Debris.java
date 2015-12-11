package asteroids.participants;

import asteroids.Constants;
import asteroids.Participant;
import java.awt.Shape;
import java.awt.geom.Path2D;
import asteroids.ParticipantCountdownTimer;

/**
 * With certain participants, such as the Ship, AlienShip, etc... When collided
 * to a destroyer, it will make random lines as an effect for the debris.
 * 
 * @author Andy Dao
 */
public class Debris extends Participant {

	private Shape outline;

	public Debris(double x, double y, double length) {

		Path2D.Double line = new Path2D.Double();
		line.moveTo(0.0, (-length) / 2.0);
		line.lineTo(0.0, length / 2.0);
		this.setRotation(Constants.RANDOM.nextDouble() * 5);
		this.setVelocity(Constants.RANDOM.nextDouble(), Constants.RANDOM.nextDouble() * 4);
		this.setPosition(x, y);
		outline = line;
		new ParticipantCountdownTimer(this, (int) (Constants.RANDOM.nextDouble() * 3500));

	}

	@Override
	protected Shape getOutline() {
		return outline;
	}

	@Override
	public void collidedWith(Participant p) {
	}

	@Override
	public void countdownComplete(Object payload) {
		Participant.expire(this);
	}

}
