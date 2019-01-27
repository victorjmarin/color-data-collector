package edu.rit.nxt.color;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.util.Queue;

import javax.swing.JFrame;

public class Main {

	private static final int FREQ = 50;

	public static void main(String[] args) {

		String slidesPath = args[0];

		String filename = slidesPath.substring(0, slidesPath.indexOf("."));

		Queue<Slide> deck = FileUtils.parseSlides(slidesPath);

		// Log color being visualized for each sample.
		ColorStateLogger logger = new ColorStateLogger(FREQ, deck);
		logger.logStates(filename + "-color-states.csv");

		// Start presentation.
		JFrame screen = getScreen();
		SlideShow ss = new SlideShow(screen, deck);
		ss.play();
	}

	private static JFrame getScreen() {
		JFrame frame = new JFrame();

		// Blank cursor.
		BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
		Cursor blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
		frame.getContentPane().setCursor(blankCursor);

		// Fullscreen.
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(frame);
		return frame;
	}

}
