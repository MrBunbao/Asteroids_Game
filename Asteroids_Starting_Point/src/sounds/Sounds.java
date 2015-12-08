package sounds;

import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import javax.sound.sampled.*;
import javax.swing.*;

import asteroids.Constants;
import asteroids.Controller;

/**
 * The sound library. This will also pre create the clips so that there will be
 * no delay when we need to call the audio clip.
 * 
 * @author Andy Dao
 */
public class Sounds {
	/** A Clip that, when played, sounds like a AlienShip hit. */
	private Clip bangAlienShipClip;

	/** A Clip that, when played, sounds like a large range bang. */
	private Clip bangLargeClip;

	/** A Clip that, when played, sounds like a medium range bang. */
	private Clip bangMediumClip;

	/** A Clip that, when played, sounds like a Ship hit. */
	private Clip bangShipClip;

	/** A Clip that, when played, sounds like a small range bang. */
	private Clip bangSmallClip;

	/**
	 * A Clip that, when played, is the first beat played between the two beats
	 * that will alternate.
	 */
	private Clip beat1Clip;

	/**
	 * A Clip that, when played, is the second beat played between the two beats
	 * that will alternate.
	 */
	private Clip beat2Clip;

	/** A Clip that, when played, sounds like bullets being fired. */
	private Clip fireClip;

	/** A Clip that, when played, sounds like a saucer ship flying. */
	private Clip saucerBigClip;

	/** A Clip that, when played, sounds like a small saucer ship flying. */
	private Clip saucerSmallClip;

	/**
	 * A Clip that, when played, sounds like the ship thrusters when the ship is
	 * accelerating.
	 */
	private Clip thrustClip;

	public Sounds() {
		// Creates the clips for the sound files
		bangAlienShipClip = createClip("/sounds/bangAlienShip.wav");
		bangLargeClip = createClip("/sounds/bangLarge.wav");
		bangMediumClip = createClip("/sounds/bangMedium.wav");
		bangSmallClip = createClip("/sounds/bangSmall.wav");
		bangShipClip = createClip("/sounds/bangShip.wav");
		beat1Clip = createClip("/sounds/beat1.wav");
		beat2Clip = createClip("/sounds/beat2.wav");
		fireClip = createClip("/sounds/fire.wav");
		saucerBigClip = createClip("/sounds/saucerBig.wav");
		saucerSmallClip = createClip("/sounds/saucerSmall.wav");
		thrustClip = createClip("/sounds/thrust.wav");
	}

	/**
	 * Creates an audio clip from a sound file.
	 */
	public Clip createClip(String soundFile) {
		// Opening the sound file this way will work no matter how the
		// project is exported. The only restriction is that the
		// sound files must be stored in a package.
		try (BufferedInputStream sound = new BufferedInputStream(getClass().getResourceAsStream(soundFile))) {

			// Create and return a Clip that will play a sound file. There are
			// various reasons that the creation attempt could fail. If it
			// fails, return null.
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			return clip;
		} catch (LineUnavailableException e) {
			return null;
		} catch (IOException e) {
			return null;
		} catch (UnsupportedAudioFileException e) {
			return null;
		}
	}

	public void playBangAlienShipClip() {

		if (bangAlienShipClip != null) {
			if (bangAlienShipClip.isRunning()) {
				bangAlienShipClip.stop();
			}
			bangAlienShipClip.setFramePosition(0);
			bangAlienShipClip.start();
		}
	}

	public void playBangLargeClip() {

		if (bangLargeClip != null) {
			if (bangLargeClip.isRunning()) {
				bangLargeClip.stop();
			}
			bangLargeClip.setFramePosition(0);
			bangLargeClip.start();
		}
	}

	public void playBangMediumClip() {

		if (bangMediumClip != null) {
			if (bangMediumClip.isRunning()) {
				bangMediumClip.stop();
			}
			bangMediumClip.setFramePosition(0);
			bangMediumClip.start();
		}
	}

	public void playBangSmallClip() {

		if (bangSmallClip != null) {
			if (bangSmallClip.isRunning()) {
				bangSmallClip.stop();
			}
			bangSmallClip.setFramePosition(0);
			bangSmallClip.start();
		}
	}

	public void playBangShipClip() {

		if (bangShipClip != null) {
			if (bangShipClip.isRunning()) {
				bangShipClip.stop();
			}
			bangShipClip.setFramePosition(0);
			bangShipClip.start();
		}
	}

	public void playBeat1Clip() {

		if (beat1Clip != null) {
			if (beat1Clip.isRunning()) {
				beat1Clip.stop();
			}
			beat1Clip.setFramePosition(0);
			beat1Clip.start();
		}
	}

	public void playBeat2Clip() {

		if (beat2Clip != null) {
			if (beat2Clip.isRunning()) {
				beat2Clip.stop();
			}
			beat2Clip.setFramePosition(0);
			beat2Clip.start();
		}
	}

	public void playFireClip() {

		if (fireClip != null) {
			if (fireClip.isRunning()) {
				fireClip.stop();
			}
			fireClip.setFramePosition(0);
			fireClip.start();
		}
	}

	// Plays the big saucer clip in a loop
	@SuppressWarnings("static-access")
	public void playSaucerBigClip() {

		if (saucerBigClip != null) {
			if (saucerBigClip.isRunning()) {
				saucerBigClip.stop();
			}
			saucerBigClip.setFramePosition(0);
			saucerBigClip.loop(saucerBigClip.LOOP_CONTINUOUSLY);
		}
	}
	public void stopSaucerBigClip() {
		if (saucerBigClip != null && saucerBigClip.isRunning()) {
			saucerBigClip.stop();
		}
	}


	@SuppressWarnings("static-access")
	public void playSaucerSmallClip() {

		if (saucerSmallClip != null) {
			if (saucerSmallClip.isRunning()) {
				saucerSmallClip.stop();
			}
			saucerSmallClip.setFramePosition(0);
			saucerSmallClip.loop(saucerSmallClip.LOOP_CONTINUOUSLY);
		}
	}
	
	public void stopSaucerSmallClip() {
		if (saucerSmallClip != null && saucerSmallClip.isRunning()) {
			saucerSmallClip.stop();
		}
	}

	public void playThrustClip() {
		if (thrustClip != null) {
			if (thrustClip.isRunning()) {
				thrustClip.stop();
			}
			thrustClip.setFramePosition(0);
			thrustClip.loop(10);
		}
	}

	public void stopThrustClip() {
		if (thrustClip != null && thrustClip.isRunning()) {
			thrustClip.stop();
		}
	}

}
