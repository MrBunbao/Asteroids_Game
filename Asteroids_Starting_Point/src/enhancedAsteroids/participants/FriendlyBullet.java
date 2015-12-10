package enhancedAsteroids.participants;

import enhancedAsteroids.Participant;
import enhancedAsteroids.destroyers.AlienShipDestroyer;
import enhancedAsteroids.destroyers.AsteroidDestroyer;
import enhancedAsteroids.destroyers.FriendlyBulletDestroyer;
import enhancedAsteroids.participants.Bullet;

/**
 * This is the ships bullet class, it extendes the standard bullet class but destroys asteroids and 
 * alien ships.
 */
public class FriendlyBullet extends Bullet implements AsteroidDestroyer, AlienShipDestroyer {

	public FriendlyBullet (double x, double y, double direction){
		super(x, y, direction);
	}
	
	@Override
    public void collidedWith (Participant p) {
        if (p instanceof FriendlyBulletDestroyer) {
            Participant.expire(this);
        }
    }
	
}
