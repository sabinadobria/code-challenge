package com.example.codechallenge.service;

import static com.example.codechallenge.action.LightAction.BRIGHTNESS_CHANGE;
import static com.example.codechallenge.action.LightAction.LIGHT_CHANGE;

import static com.example.codechallenge.utils.LightsTestContants.FILE_PATH;
import static com.example.codechallenge.utils.LightsTestContants.FILE_PATH_EXAMPLE;
import static com.example.codechallenge.utils.LightsTestContants.PATH_WRONG_FILE;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.codechallenge.action.ActionHandlerFactory;
import com.example.codechallenge.action.ChangeBrightnessHandler;
import com.example.codechallenge.action.ChangeLightsHandler;
import com.example.codechallenge.action.LightAction;
import com.example.codechallenge.utils.LightsFileReader;

@ExtendWith(SpringExtension.class)
public class LightsServiceTest {
	LightsService lightsService;
	@Mock
	ActionHandlerFactory actionHandlerFactory;
	private static MockedStatic<LightsFileReader> mockedSettings;

	@BeforeEach
	public void init() {
		mockedSettings = Mockito.mockStatic(LightsFileReader.class);
		lightsService = new LightsService(actionHandlerFactory);
		Mockito.when(actionHandlerFactory.getStateHandler(LIGHT_CHANGE)).thenReturn(new ChangeLightsHandler());
		Mockito.when(actionHandlerFactory.getStateHandler(BRIGHTNESS_CHANGE)).thenReturn(new ChangeBrightnessHandler());

	}
	@AfterEach
	public void close() {
		mockedSettings.close();
	}

	@Test
	public void test_numberOfLightsOn_Success() throws FileNotFoundException {
		Mockito.when(LightsFileReader.getFileReader()).thenReturn(getFileReader(FILE_PATH));

		long counter = lightsService.readNumberOfLightFromFile(LIGHT_CHANGE);
		Assertions.assertEquals(6, counter);

		Mockito.when(LightsFileReader.getFileReader()).thenReturn(getFileReader(FILE_PATH));
		counter = lightsService.readNumberOfLightFromFile(LightAction.BRIGHTNESS_CHANGE);
		Assertions.assertEquals(19, counter);
	}

	@Test
	public void test_numberOfLightsOn_Example_Success() throws FileNotFoundException {
		Mockito.when(LightsFileReader.getFileReader()).thenReturn(getFileReader(FILE_PATH_EXAMPLE));

		long counter = lightsService.readNumberOfLightFromFile(LIGHT_CHANGE);
		Assertions.assertEquals(998004, counter);

		Mockito.when(LightsFileReader.getFileReader()).thenReturn(getFileReader(FILE_PATH_EXAMPLE));
		counter = lightsService.readNumberOfLightFromFile(LightAction.BRIGHTNESS_CHANGE);
		Assertions.assertEquals(1000000, counter);
	}

	@Test
	public void test_numberOfLightsOn_FileNotFoundException() throws FileNotFoundException {
		Mockito.when(LightsFileReader.getFileReader()).thenThrow(FileNotFoundException.class);
		long counter = lightsService.readNumberOfLightFromFile(LIGHT_CHANGE);
		Assertions.assertEquals(0, counter);
	}

	@Test
	public void test_numberOfLightsOn_InvalidCoordinates() throws FileNotFoundException {
		Mockito.when(LightsFileReader.getFileReader()).thenReturn(getFileReader(PATH_WRONG_FILE));
		long counter = lightsService.readNumberOfLightFromFile(LIGHT_CHANGE);
		Assertions.assertEquals(0, counter);
	}

	private FileReader getFileReader(String path) throws FileNotFoundException {
		return new FileReader(path);
	}
}
