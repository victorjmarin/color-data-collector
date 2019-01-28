package edu.rit.nxt.color;

import java.awt.Cursor;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.Queue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Main {

	private static final int FREQ = 50;

	public static void main(String[] args) {

		String slidesPath = args[0];

		String filename = slidesPath.substring(0, slidesPath.indexOf("."));

		Queue<Slide> deck = FileUtils.parseSlides(slidesPath);

		// Log color being visualized for each sample.
		ColorStateLogger logger = new ColorStateLogger(FREQ, deck);
		logger.logStates(filename + "-color-states.csv");

		JFrame screen = getScreen();
		JLabel lbl = new JLabel("Press <ENTER> to start.", SwingConstants.CENTER);
		screen.add(lbl);

		screen.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					screen.remove(lbl);

					// Blank cursor.
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Point hotSpot = new Point(0, 0);
					BufferedImage cursorImage = new BufferedImage(1, 1, BufferedImage.TRANSLUCENT);
					Cursor invisibleCursor = toolkit.createCustomCursor(cursorImage, hotSpot, "InvisibleCursor");
					screen.setCursor(invisibleCursor);

					// Start presentation.
					SlideShow ss = new SlideShow(screen, deck);
					ss.play();
				} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					screen.dispose();
					System.exit(0);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
			}
		});
	}

	private static JFrame getScreen() {
		JFrame frame = new JFrame();
		GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
		gd.setFullScreenWindow(frame);
		return frame;
	}

}
