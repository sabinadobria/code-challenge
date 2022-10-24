package com.example.codechallenge.action;

import static com.example.codechallenge.utils.ParseFileUtil.getCoordinates;

import java.util.Map;

import org.springframework.stereotype.Component;

import com.example.codechallenge.model.IntervalCoordinates;
import com.example.codechallenge.model.Light;

@Component
public class ChangeBrightnessHandler implements ActionHandler {
	private static final String TURN_ON_LIGHT = "turn on";
	private static final String TURN_OFF_LIGHT = "turn off";
	private static final String TOGGLE_LIGHT = "toggle";
	@Override
	public LightAction getAction() {
		return LightAction.BRIGHTNESS_CHANGE;
	}

	@Override
	public long handle(String currentLine, Map<Light, Integer> lightsMap, long counter) {
		return applyBrightnessOnLights(currentLine, lightsMap, counter);
	}

	private static Long applyBrightnessOnLights(String currentLine, Map<Light, Integer> lightsMap, Long counter) {
		if (currentLine.contains(TURN_ON_LIGHT)) {
			IntervalCoordinates intervalCoordinates = getCoordinates(false, currentLine);
			counter = increaseBrightness(lightsMap, intervalCoordinates, counter);
		}
		else if (currentLine.contains(TURN_OFF_LIGHT)) {
			IntervalCoordinates intervalCoordinates = getCoordinates(false, currentLine);
			counter = decreaseBrightness(lightsMap, intervalCoordinates, counter);
		}
		else if (currentLine.contains(TOGGLE_LIGHT)) {
			IntervalCoordinates intervalCoordinates = getCoordinates(true, currentLine);
			counter = toggleBrightness(lightsMap, intervalCoordinates, counter);
		}
		return counter;
	}
	private static Long increaseBrightness(Map<Light, Integer> lightsMap, IntervalCoordinates coordinates, Long counter){
		for(int x = coordinates.getStartX(); x<=coordinates.getEndX(); x++) {
			for (int y = coordinates.getStartY(); y <= coordinates.getEndY(); y++) {
				Light light = new Light(x, y);
				lightsMap.computeIfPresent(light, (key,val) -> val + 1);
				if(!lightsMap.containsKey(light)) {
					lightsMap.put(light, 1);
				}
				counter++;
			}
		}
		return counter;
	}

	private static Long decreaseBrightness(Map<Light, Integer> lightsMap, IntervalCoordinates coordinates, Long counter){
		for(int x = coordinates.getStartX(); x<=coordinates.getEndX(); x++) {
			for (int y = coordinates.getStartY(); y <= coordinates.getEndY(); y++) {
				Light light = new Light(x, y);
				if(lightsMap.containsKey(light)) {
					Integer brightnessLvl = lightsMap.get(light);
					if(brightnessLvl - 1 == 0) {
						lightsMap.remove(light);
					} else {
						lightsMap.put(light, brightnessLvl - 1);
					}
					counter--;
				}
			}
		}
		return counter;
	}

	private static Long toggleBrightness(Map<Light, Integer> lightsMap, IntervalCoordinates coordinates, Long counter){
		for(int x = coordinates.getStartX(); x<=coordinates.getEndX(); x++) {
			for (int y = coordinates.getStartY(); y <= coordinates.getEndY(); y++) {
				Light light = new Light(x, y);
				lightsMap.computeIfPresent(light, (k,v) -> v + 2);
				if(!lightsMap.containsKey(light)) {
					lightsMap.put(light, 2);
				}
				counter = counter + 2;
			}
		}
		return counter;
	}

}
