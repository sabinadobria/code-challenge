package com.example.codechallenge.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.example.codechallenge.exceptions.InvalidCoordinatesException;
import com.example.codechallenge.model.IntervalCoordinates;

public class ParseFileUtil {
	private static final Logger logger = LogManager.getLogger(ParseFileUtil.class);
	private static final String END_COORDINATE = "end";
	private static final String START_X_COORDINATE = "startX";
	private static final String START_Y_COORDINATE = "startY";
	private static final String END_X_COORDINATE = "endX";
	private static final String END_Y_COORDINATE = "endY";
	private static final String COMMA = ",";

	private ParseFileUtil() {
	}

	public static IntervalCoordinates getCoordinates(boolean isToggle, String currentLine) {
		String[] line = currentLine.split(COMMA);
		IntervalCoordinates coordinates = new IntervalCoordinates();
		coordinates.setStartX(parseStartX(line, isToggle));
		coordinates.setStartY(parseStartY(line));
		coordinates.setEndX(parseEndX(line));
		coordinates.setEndY(parseEndY(line));

		if(coordinates.isValid()) {
			return coordinates;
		} else {
			throw new InvalidCoordinatesException(END_COORDINATE);
		}
	}

	private static int parseStartX(String[] line, boolean isToggle) {
		try {
			if(isToggle) {
				return Integer.parseInt(line[0].split(" ")[1]);
			} else {
				return Integer.parseInt(line[0].split(" ")[2]);
			}
		} catch (NumberFormatException | IndexOutOfBoundsException ex) {
			logger.error("Could not get start x coordinate from line: {}", line);
			throw new InvalidCoordinatesException(START_X_COORDINATE);
		}
	}

	private static int parseStartY(String[] line) {
		try {
			return Integer.parseInt(line[1].split(" ")[0]);
		} catch (NumberFormatException | IndexOutOfBoundsException ex) {
			logger.error("Could not get start y coordinate from line: {}", line);
			throw new InvalidCoordinatesException(START_Y_COORDINATE);
		}
	}

	private static int parseEndX(String[] line) {
		try {
			return Integer.parseInt(line[1].split(" ")[2]);
		} catch (NumberFormatException | IndexOutOfBoundsException ex) {
			logger.error("Could not get end x coordinate from line: {}, ex: {}", line, ex.getMessage());
			throw new InvalidCoordinatesException(END_X_COORDINATE);
		}
	}

	private static int parseEndY(String[] line) {
		try {
			return Integer.parseInt(line[2]);
		} catch (NumberFormatException | IndexOutOfBoundsException ex) {
			logger.error("Could not get end y coordinate from line: {}", line);
			throw new InvalidCoordinatesException(END_Y_COORDINATE);
		}
	}
}
