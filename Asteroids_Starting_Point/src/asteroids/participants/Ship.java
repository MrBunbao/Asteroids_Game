package asteroids.participants;

import java.awt.Shape;
import java.awt.geom.*;

import asteroids.Constants;
import asteroids.Controller;
import asteroids.Participant;
import asteroids.destroyers.*;
import asteroids.participants.Debris;

/**
 * Represents ships
 */
public class Ship extends Participant implements ShipDestroyer, AlienShipDestroyer, AsteroidDestroyer {
	// Game controller
	private Controller controller;

	// The flame of the ship
	private Shape flameOffOutline;
	private Shape flameOnOutline;
	private boolean flameAppears;
	private boolean isAccelerating;

	// Constructs a ship at the specified coordinates
	// that is pointed in the given direction.
	public Ship(int x, int y, double direction, Controller controller) {
		this.controller = controller;
		this.setPosition(x, y);
		this.setRotation(direction);
		this.flameAppears = true;
		this.isAccelerating = false;

		Path2D.Double poly = new Path2D.Double();
		poly.moveTo(20, 0);
		poly.lineTo(-20, 12);
		poly.lineTo(-13, 10);
		poly.lineTo(-13, -10);
		poly.lineTo(-20, -12);
		poly.closePath();

		this.flameOffOutline = poly;
		poly = new Path2D.Double();
		poly.moveTo(20, 0);
		poly.lineTo(-20.0, 12.0);
		poly.lineTo(-13.0, 10.0);
		poly.lineTo(-13.0, -5.0);
		poly.lineTo(-25.0, 0.0);
		poly.lineTo(-13.0, 5.0);
		poly.lineTo(-13.0, -10.0);
		poly.lineTo(-20.0, -12.0);
		poly.closePath();
		this.flameOnOutline = poly;
	}

	/**
	 * Returns the x-coordinate of the point on the screen where the ship's nose
	 * is located.
	 */
	public double getXNose() {
		Point2D.Double point = new Point2D.Double(20, 0);
		transformPoint(point);
		return point.getX();
	}

	/**
	 * Returns the x-coordinate of the point on the screen where the ship's nose
	 * is located.
	 */
	public double getYNose() {
		Point2D.Double point = new Point2D.Double(20, 0);
		transformPoint(point);
		return point.getY();
	}

	@Override
	protected Shape getOutline() {
		if (isAccelerating) {
			if (flameAppears) {
				return flameOnOutline;
			}
			return flameOffOutline;
		}
		return flameOffOutline;
	}

	/**
	 * Customizes the base move method by imposing friction
	 */
	@Override
	public void move() {
		applyFriction(Constants.SHIP_FRICTION);
		super.move();
	}

	/**
	 * Turns right by Pi/16 radians
	 */
	public void turnRight() {
		rotate(Math.PI / 16);
	}

	/**
	 * Turns left by Pi/16 radians
	 */
	public void turnLeft() {
		rotate(-Math.PI / 16);
	}

	/**
	 * Accelerates by SHIP_ACCELERATION
	 */
	public void accelerate() {
		accelerate(Constants.SHIP_ACCELERATION);

		if (isAccelerating == true) {

			Ship.getSounds().playThrustClip();
		}
		// This will change the boolean isAccelerating to true when accelerate
		// is called
		isAccelerating = true;
	}

	/**
	 * Will stop the "thrust.wav" file to stop looping when not accelerating.
	 */
	public void notAccelerating() {
		if (isAccelerating == false) {
			Ship.getSounds().stopThrustClip();
		}
		// This will change the boolean isAccelerating to false when
		// notAccelerating is called
		isAccelerating = false;
	}

	/**
	 * When a Ship collides with a ShipKiller, it expires.
	 */
	@Override
	public void collidedWith(Participant p) {
		if (p instanceof ShipDestroyer) {
			// Expire the ship from the game
			expire(this);

			// Plays "bangShip.wav"
			Ship.getSounds().playBangShipClip();

			// Will create the debris when collided with a destroyer
			controller.addParticipant(new Debris(this.getX(), this.getY(), 15));
			controller.addParticipant(new Debris(this.getX(), this.getY(), 13));
			controller.addParticipant(new Debris(this.getX(), this.getY(), 7));
			controller.addParticipant(new Debris(this.getX(), this.getY(), 10));
			controller.addParticipant(new Debris(this.getX(), this.getY(), 6));

			// Tell the controller the ship was destroyed
			controller.shipDestroyed();
		}
	}

	/**
	 * This method is invoked when a ParticipantCountdownTimer completes its
	 * countdown.
	 */
	@Override
	public void countdownComplete(Object payload) {
	}

	public void fire() {
		if (controller.bulletLimit(8) == false) {
			FriendlyBullet bullet = new FriendlyBullet(this.getXNose(), this.getYNose(), this.getRotation());
			controller.addParticipant(bullet);
			Ship.getSounds().playFireClip();
		}
	}
}
