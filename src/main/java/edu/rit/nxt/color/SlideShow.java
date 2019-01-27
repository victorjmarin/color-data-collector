package edu.rit.nxt.color;

import java.awt.Color;
import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;

public class SlideShow {

	private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	private JFrame screen;
	private Queue<Slide> deck;

	public SlideShow(JFrame screen, Queue<Slide> deck) {
		this.screen = screen;
		this.deck = deck;
	}

	public void play() {

		if (deck == null || deck.isEmpty()) {
			screen.dispose();
			System.exit(0);
			return;
		}

		Slide currentSlide = deck.poll();

		showSlide(currentSlide);

		long duration = currentSlide.getDuration();

		scheduler.schedule(this::play, duration, TimeUnit.MILLISECONDS);
	}

	private void showSlide(Slide slide) {
		Color color = slide.getColor();
		screen.getContentPane().setBackground(color);
	}

}
