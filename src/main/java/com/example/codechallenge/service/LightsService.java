package com.example.codechallenge.service;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import com.example.codechallenge.action.LightAction;
import com.example.codechallenge.action.ActionHandlerFactory;
import com.example.codechallenge.utils.LightsFileReader;
import com.example.codechallenge.exceptions.InvalidCoordinatesException;
import com.example.codechallenge.model.Light;

@Service
public class LightsService {
	private static final Logger logger = LogManager.getLogger(LightsService.class);

	private ActionHandlerFactory actionHandlerFactory;

	public LightsService(ActionHandlerFactory actionHandlerFactory) {
		this.actionHandlerFactory = actionHandlerFactory;
	}

	public long readNumberOfLightFromFile(LightAction lightsAction)  {
		long counter = 0;

		try(BufferedReader reader = new BufferedReader(LightsFileReader.getFileReader())) {
			String currentLine;
			Map<Light, Integer> lightsMap = new HashMap<>();

			while ((currentLine = reader.readLine()) != null) {
				counter = computeNumberOfLightsOn(counter, currentLine, lightsAction, lightsMap);
			}

		}  catch (FileNotFoundException f) {
			logger.error("Could not find file at given path");
			return 0;
		} catch (IOException e) {
			logger.error("An exception occurred while reading the file, ex: {}", e.getMessage());
			return 0;
		}
		return counter;
	}

	private long computeNumberOfLightsOn(long counter, String currentLine, LightAction lightsAction, Map<Light, Integer> lightsMap) {
		try {
			counter = actionHandlerFactory.getStateHandler(lightsAction)
					.handle(currentLine, lightsMap, counter);
		} catch (InvalidCoordinatesException ex) {
			logger.error("Invalid coordinates, moving to next line: {}", ex.getMessage());
		}
		return counter;
	}
}
