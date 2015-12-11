package enhancedAsteroids.participants;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;

import javax.swing.Timer;

import enhancedAsteroids.Participant;
import enhancedAsteroids.destroyers.FriendlyBulletDestroyer;

public class BigGun extends FriendlyBullet implements ActionListener {

	private Shape outline;

	private Timer expirer;

	private Timer refreshSize;

	private double currentSize;

	private int phase;

	public BigGun(double x, double y, double direction) {
		super(x, y, direction);
		setSpeed(7);
		expirer = new Timer(3000, this);
		refreshSize = new Timer(100, this);
		expirer.start();
		refreshSize.start();
		this.outline = new Ellipse2D.Double(0, 0, 1, 1);
		currentSize = 1;
		phase = 0;
	}

	@Override
	public void collidedWith(Participant p) {

	}

	@Override
	public void countdownComplete(Object payload) {

	}

	@Override
	protected Shape getOutline() {
		return outline;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == expirer) {
			expire(this);
		} else if (e.getSource() == refreshSize) {
			phase++;
			currentSize = (1 + (80 * Math.pow(Math.abs(Math.sin((phase * Math.PI) / 30)), 2)));
			outline = new Ellipse2D.Double(0, 0, currentSize, currentSize);
		}
	}
}