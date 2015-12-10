package asteroids.participants;

import asteroids.Participant;
import asteroids.destroyers.AsteroidDestroyer;
import asteroids.destroyers.ShipDestroyer;
import asteroids.participants.Bullet;

/**
 * This is the ships bullet class, it extends the standard bullet class but destroys asteroids and 
 * alien ships.
 * 
 * @author Andy Dao
 */
public class FriendlyBullet extends Bullet implements AsteroidDestroyer {

	public FriendlyBullet (double x, double y, double direction){
		super(x, y, direction);
	}
	
	@Override
    public void collidedWith (Participant p) {
        if (p instanceof ShipDestroyer || p instanceof Bullet) {
            Participant.expire(this);
        }
    }
	
}
