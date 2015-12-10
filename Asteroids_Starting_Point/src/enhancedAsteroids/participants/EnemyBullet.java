/**
 * 
 */
package enhancedAsteroids.participants;

import enhancedAsteroids.destroyers.EnemyBulletDestroyer;
import enhancedAsteroids.Participant;
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
		if (p instanceof EnemyBulletDestroyer) {
            Participant.expire(this);
        }
	}

}
