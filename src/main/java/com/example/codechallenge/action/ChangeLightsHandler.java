package com.example.codechallenge.action;

import static com.example.codechallenge.utils.ParseFileUtil.getCoordinates;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.codechallenge.model.IntervalCoordinates;
import com.example.codechallenge.model.Light;

@Component
public class ChangeLightsHandler implements ActionHandler {
	private static final Integer LIGHT_ON = 1;
	private static final Integer LIGHT_OFF = 0;
	private static final String TURN_ON_LIGHT = "turn on";
	private static final String TURN_OFF_LIGHT = "turn off";
	private static final String TOGGLE_LIGHT = "toggle";

	@Override
	public LightAction getAction() {
		return LightAction.LIGHT_CHANGE;
	}

	@Override
	public long handle(String currentLine, Map<Light, Integer> lightsMap, long counter) {
		return applyChangesOnLights(currentLine, lightsMap, counter);
	}

	public static long applyChangesOnLights(String currentLine, Map<Light, Integer> lightsMap, long counter) {
		if (currentLine.contains(TURN_ON_LIGHT)) {
			IntervalCoordinates intervalCoordinates = getCoordinates(false, currentLine);
			counter = turnOnLights(lightsMap, intervalCoordinates, counter);
		}
		else if (currentLine.contains(TURN_OFF_LIGHT)) {
			IntervalCoordinates intervalCoordinates = getCoordinates(false, currentLine);
			counter = turnOffLights(lightsMap, intervalCoordinates, counter);
		}
		else if (currentLine.contains(TOGGLE_LIGHT)) {
			IntervalCoordinates intervalCoordinates = getCoordinates(true, currentLine);
			counter = toggleLights(lightsMap, intervalCoordinates, counter);
		}
		return counter;
	}

	private static long turnOnLights(Map<Light, Integer> lightsMap, IntervalCoordinates coordinates, long counter){
		for(int x = coordinates.getStartX(); x<=coordinates.getEndX(); x++) {
			for (int y = coordinates.getStartY(); y <= coordinates.getEndY(); y++) {
				Light light = new Light(x, y);
				if (!isLightOn(lightsMap, light)) {
					counter = turnOn(lightsMap, light, counter);
				}
			}
		}
		return counter;
	}

	private static long turnOffLights(Map<Light, Integer> lightsMap, IntervalCoordinates coordinates, long counter){
		for(int x = coordinates.getStartX(); x<=coordinates.getEndX(); x++) {
			for (int y = coordinates.getStartY(); y <= coordinates.getEndY(); y++) {
				Light light = new Light(x, y);
				if(isLightOn(lightsMap, light)) {
					counter = turnOff(lightsMap, light, counter);
				}
			}
		}
		return counter;
	}
	private static long toggleLights(Map<Light, Integer> lightsMap, IntervalCoordinates coordinates, long counter){
		for(int x = coordinates.getStartX(); x<=coordinates.getEndX(); x++) {
			for (int y = coordinates.getStartY(); y <= coordinates.getEndY(); y++) {
				Light light = new Light(x, y);
				if(isLightOn(lightsMap, light)) {
					counter = turnOff(lightsMap, light, counter);
				} else {
					counter = turnOn(lightsMap, light, counter);
				}
			}
		}
		return counter;
	}

	private static long turnOn(Map<Light, Integer> lightsMap, Light light, long counter) {
		lightsMap.put(light, LIGHT_ON);
		return ++counter;
	}

	private static long turnOff(Map<Light, Integer> lightsMap, Light light, long counter) {
		lightsMap.put(light, LIGHT_OFF);
		return --counter;
	}
	private static boolean isLightOn(Map<Light, Integer> lightsMap, Light light) {
		return lightsMap.containsKey(light) && lightsMap.get(light).equals(1);
	}

}
