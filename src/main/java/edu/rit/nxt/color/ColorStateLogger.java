package edu.rit.nxt.color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;

public class ColorStateLogger {

	// Frequency in Hz.
	private int freq;
	private Collection<Slide> deck;

	public ColorStateLogger(int freq, Collection<Slide> deck) {
		this.freq = freq;
		this.deck = deck;
	}

	public void logStates(String saveToPath) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveToPath))) {
			double msPerCycle = 1000 / freq;
			for (Slide s : deck) {
				int duration = s.getDuration();
				int measurements = (int) Math.round(duration / msPerCycle);
				int red = s.getColor().getRed();
				int green = s.getColor().getGreen();
				int blue = s.getColor().getBlue();
				while (measurements-- > 0)
					writer.write(String.format("%d,%d,%d", red, green, blue) + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
