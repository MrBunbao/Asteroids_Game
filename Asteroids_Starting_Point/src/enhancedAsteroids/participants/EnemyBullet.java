/**
 * 
 */
package enhancedAsteroids.participants;

import enhancedAsteroids.destroyers.EnemyBulletDestroyer;
import enhancedAsteroids.participants.Bullet;
import enhancedAsteroids.Participant;
import enhancedAsteroids.destroyers.AsteroidDestroyer;
import enhancedAsteroids.destroyers.ShipDestroyer;

/**
 * This is the Alien Ships bullet class, it extends the standard bullet class
 * but destroys asteroids and the players ship.
 * 
 * @author Lincoln Matheson
 *
 */
public class EnemyBullet extends Bullet implements ShipDestroyer, AsteroidDestroyer {
	public EnemyBullet(double x, double y, double direction) {
		super(x, y, direction);
	}

	@Override
	public void collidedWith(Participant p) {
		if (p instanceof EnemyBulletDestroyer) {
			Participant.expire(this);
		}
	}

}
