/**
 * 
 */
package enhancedAsteroids.participants;

import enhancedAsteroids.Participant;
import enhancedAsteroids.destroyers.AlienShip;
import enhancedAsteroids.destroyers.AsteroidDestroyer;
import enhancedAsteroids.destroyers.ShipDestroyer;

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
