/**
 * 
 */
package asteroids.participants;

import asteroids.Participant;
import asteroids.destroyers.AlienShip;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.EnemyBulletDestroyer;
import asteroids.destroyers.ShipDestroyer;

/**
 * This is the Alien Ships bullet class, it extends the standard bullet class but destroys asteroids and 
 * the players ship.
 * 
 * @author Lincoln Matheson
 *
 */
public class EnemyBullet extends Bullet implements ShipDestroyer, AsteroidDestroyer {
	public EnemyBullet (double x, double y, double direction){
		super(x, y, direction);
	}
	@Override
	public void collidedWith(Participant p) {
		if (p instanceof EnemyBulletDestroyer) {
            Participant.expire(this);
        }
	}

}
