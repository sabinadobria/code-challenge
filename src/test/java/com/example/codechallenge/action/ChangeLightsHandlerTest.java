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
public class ChangeLightsHandlerTest {

	@InjectMocks
	private ChangeLightsHandler changeLightsHandler;

	@Test
	public void test_applyChangesOnLights_Success() {
		Map<Light, Integer> lightsMap = new HashMap<>();
		long counter = 0;
		counter = changeLightsHandler.handle(TURN_ON_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(NUMBER_OF_LIGHTS_ON, counter);

		counter = changeLightsHandler.handle(TURN_OFF_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(0, counter);

		counter = changeLightsHandler.handle(TOGGLE_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(NUMBER_OF_LIGHTS_ON, counter);

		counter = changeLightsHandler.handle(TOGGLE_LIGHTS, lightsMap, counter);
		Assertions.assertEquals(0, counter);
	}

	@Test
	public void test_applyChangesOnLights_throwException() {
		Map<Light, Integer> lightsMap = new HashMap<>();
		long counter = 0;
		InvalidCoordinatesException thrown = Assertions.assertThrows(InvalidCoordinatesException.class, () -> {
			changeLightsHandler.handle(TOGGLE_LIGHTS_INVALID_COORDINATES, lightsMap, counter);
		});
		Assertions.assertTrue(thrown.getMessage().contains(END_COORDINATES));
	}
}
