package edu.rit.nxt.color;

import java.awt.Color;

public class Slide {

	// Background color.
	private Color color;

	// Duration in ms.
	private int duration;

	public Slide(int r, int g, int b, int duration) {
		color = new Color(r, g, b);
		this.duration = duration;
	}

	public Color getColor() {
		return color;
	}

	public int getDuration() {
		return duration;
	}

	public String toString() {
		return String.format("%d,%d,%d,%d", color.getRed(), color.getGreen(), color.getBlue(), duration);
	}

}
