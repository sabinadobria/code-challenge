package com.example.codechallenge.utils;

import static com.example.codechallenge.utils.LightsTestContants.END_COORDINATES;
import static com.example.codechallenge.utils.LightsTestContants.END_X_COORDINATE;
import static com.example.codechallenge.utils.LightsTestContants.END_Y_COORDINATE;
import static com.example.codechallenge.utils.LightsTestContants.INVALID_COORDINATES_END_X_ERROR;
import static com.example.codechallenge.utils.LightsTestContants.INVALID_COORDINATES_END_Y_ERROR;
import static com.example.codechallenge.utils.LightsTestContants.INVALID_COORDINATES_START_X_ERROR;
import static com.example.codechallenge.utils.LightsTestContants.INVALID_COORDINATES_START_Y_ERROR;
import static com.example.codechallenge.utils.LightsTestContants.IS_NOT_TOGGLE;
import static com.example.codechallenge.utils.LightsTestContants.IS_TOGGLE;
import static com.example.codechallenge.utils.LightsTestContants.START_X_COORDINATE;
import static com.example.codechallenge.utils.LightsTestContants.START_Y_COORDINATE;
import static com.example.codechallenge.utils.LightsTestContants.TOGGLE_LIGHTS;
import static com.example.codechallenge.utils.LightsTestContants.TOGGLE_LIGHTS_INVALID_COORDINATES;
import static com.example.codechallenge.utils.LightsTestContants.TURN_OFF_LIGHTS;
import static com.example.codechallenge.utils.LightsTestContants.TURN_ON_LIGHTS;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.example.codechallenge.exceptions.InvalidCoordinatesException;
import com.example.codechallenge.model.IntervalCoordinates;

@ExtendWith(SpringExtension.class)
public class ParseFileUtilTest {

	@Test
	public void test_getCoordinates_Success() {
		checkCoordinates(TURN_ON_LIGHTS, IS_NOT_TOGGLE);
		checkCoordinates(TURN_OFF_LIGHTS, IS_NOT_TOGGLE);
		checkCoordinates(TOGGLE_LIGHTS, IS_TOGGLE);
	}

	@Test
	public void test_getCoordinates_MissingStartX_throwException() {
		checkInvalidCoordinates(INVALID_COORDINATES_START_X_ERROR, START_X_COORDINATE);
		checkInvalidCoordinates(INVALID_COORDINATES_START_Y_ERROR, START_Y_COORDINATE);
		checkInvalidCoordinates(INVALID_COORDINATES_END_X_ERROR, END_X_COORDINATE);
		checkInvalidCoordinates(INVALID_COORDINATES_END_Y_ERROR, END_Y_COORDINATE);
	}

	@Test
	public void test_getCoordinates_Invalid_throwException() {
		InvalidCoordinatesException thrown = Assertions.assertThrows(InvalidCoordinatesException.class, () -> {
			ParseFileUtil.getCoordinates(true, TOGGLE_LIGHTS_INVALID_COORDINATES);
		});
		Assertions.assertTrue(thrown.getMessage().contains(END_COORDINATES));

	}

	@Test
	public void test_getCoordinates_TurnOnLightsAndToggleTrue_throwException() {
		InvalidCoordinatesException thrown = Assertions.assertThrows(InvalidCoordinatesException.class, () -> {
			ParseFileUtil.getCoordinates(true, TURN_ON_LIGHTS);
		});
		Assertions.assertTrue(thrown.getMessage().contains(START_X_COORDINATE));
	}

	private void checkCoordinates(String line, boolean isToggle) {
		IntervalCoordinates coordinates = ParseFileUtil.getCoordinates(isToggle, line);

		Assertions.assertTrue(coordinates.isValid());
		Assertions.assertEquals(1, coordinates.getStartX());
		Assertions.assertEquals(1, coordinates.getStartY());
		Assertions.assertEquals(2, coordinates.getEndX());
		Assertions.assertEquals(2, coordinates.getEndY());
	}

	private void checkInvalidCoordinates(String line, String coordinate) {
		InvalidCoordinatesException thrown = Assertions.assertThrows(InvalidCoordinatesException.class, () -> {
			ParseFileUtil.getCoordinates(false, line);
		});
		Assertions.assertTrue(thrown.getMessage().contains(coordinate));
	}
}
