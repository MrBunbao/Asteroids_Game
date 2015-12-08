/**
 * 
 */
package asteroids.participants;

import asteroids.Participant;
import asteroids.destroyers.AlienShip;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.ShipDestroyer;

/**
 * @author Lincoln Matheson
 *
 */
public class EnemyBullet extends Bullet implements ShipDestroyer, AsteroidDestroyer {
	public EnemyBullet (double x, double y, double direction){
		super(x, y, direction);
	}
	@Override
	public void collidedWith(Participant p) {
		if (!p.isExpired() && !(p instanceof AlienShip)) {
            Participant.expire(this);
        }
	}

}
