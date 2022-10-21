package com.example.codechallenge.action;

import static com.example.codechallenge.utils.LightsTestContants.END_COORDINATES;
import static com.example.codechallenge.utils.LightsTestContants.NUMBER_OF_LIGHTS_ON;
import static com.example.codechallenge.utils.LightsTestContants.TOGGLE_LIGHTS;
import static com.example.codechallenge.utils.LightsTestContants.TOGGLE_LIGHTS_INVALID_COORDINATES;
import static com.example.codechallenge.utils.LightsTestContants.TURN_OFF_LIGHTS;
import static com.example.codechallenge.utils.LightsTestContants.TURN_ON_LIGHTS;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.codechallenge.exceptions.InvalidCoordinatesException;
import com.example.codechallenge.model.Light;

@ExtendWith(SpringExtension.class)
public class ChangeBrightnessHandlerTest {
	@InjectMocks
	ChangeBrightnessHandler brightnessHandler;

	@Test
	public void test_applyBrightnessOnLights_Success() {
		Map<Light, Integer> lightsMap = new HashMap<>();
		long counter = 0;
		// apply lights on -> brightness lvl = 1
		counter = brightnessHandler.handle(TURN_ON_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(NUMBER_OF_LIGHTS_ON, counter);
		lightsMap.forEach((key, value) -> Assertions.assertEquals(1, value));

		// apply lights off -> brightness lvl = 0
		counter = brightnessHandler.handle(TURN_OFF_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(0, counter);

		// apply lights toggle -> brightness lvl = 2
		counter = brightnessHandler.handle(TOGGLE_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(NUMBER_OF_LIGHTS_ON, counter);
		lightsMap.forEach((key, value) -> Assertions.assertEquals(2, value));

		// apply lights off -> brightness lvl = 1
		counter = brightnessHandler.handle(TURN_OFF_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(NUMBER_OF_LIGHTS_ON, counter);
		lightsMap.forEach((key, value) -> Assertions.assertEquals(1, value));

		// apply lights off -> brightness lvl = 0
		counter = brightnessHandler.handle(TURN_OFF_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(0, counter);

		// apply brightness off on off lights -> brightness level = 0
		counter = brightnessHandler.handle(TURN_OFF_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(0, counter);
		lightsMap.forEach((key, value) -> Assertions.assertEquals(0, value));

		// apply lights on -> brightness lvl = 1
		counter = brightnessHandler.handle(TURN_ON_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(NUMBER_OF_LIGHTS_ON, counter);
		lightsMap.forEach((key, value) -> Assertions.assertEquals(1, value));
	}

	@Test
	public void test_applyChangesOnLights_throwException() {
		Map<Light, Integer> lightsMap = new HashMap<>();
		long counter = 0;
		InvalidCoordinatesException thrown = Assertions.assertThrows(InvalidCoordinatesException.class, () -> {
			brightnessHandler.handle(TOGGLE_LIGHTS_INVALID_COORDINATES, lightsMap, counter);
		});
		Assertions.assertTrue(thrown.getMessage().contains(END_COORDINATES));
	}
}
