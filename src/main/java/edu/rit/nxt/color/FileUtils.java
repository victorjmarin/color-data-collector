package edu.rit.nxt.color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class FileUtils {

	public static Queue<Slide> parseSlides(String pathToSlides) {
		Queue<Slide> result = null;

		try {
			List<String> lines = Files.readAllLines(Paths.get(pathToSlides));

			result = lines.stream().map(l -> {

				String[] cols = l.split(",");
				int r = Integer.valueOf(cols[0].trim());
				int g = Integer.valueOf(cols[1].trim());
				int b = Integer.valueOf(cols[2].trim());
				int duration = Integer.valueOf(cols[3].trim());
				return new Slide(r, g, b, duration);

			}).collect(Collectors.toCollection(ArrayDeque::new));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}

	public static void createRandomSlides(int deckSize, int minDuration, int maxDuration, int freq, String saveToPath) {

		double msPerCycle = 1000 / freq;

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(saveToPath))) {
			for (int i = 0; i++ < deckSize;) {
				int r = ThreadLocalRandom.current().nextInt(0, 256);
				int g = ThreadLocalRandom.current().nextInt(0, 256);
				int b = ThreadLocalRandom.current().nextInt(0, 256);
				int duration = ThreadLocalRandom.current().nextInt(minDuration, maxDuration);
				duration -= duration % msPerCycle;
				Slide s = new Slide(r, g, b, duration);
				writer.write(s.toString() + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
