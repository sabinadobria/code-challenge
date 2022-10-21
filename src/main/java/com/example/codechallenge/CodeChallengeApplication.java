package com.example.codechallenge;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.codechallenge.action.ActionHandler;
import com.example.codechallenge.action.ActionHandlerFactory;
import com.example.codechallenge.action.ChangeBrightnessHandler;
import com.example.codechallenge.action.ChangeLightsHandler;
import com.example.codechallenge.action.LightAction;
import com.example.codechallenge.action.NoOpActionHandler;
import com.example.codechallenge.service.LightsService;

@SpringBootApplication
public class CodeChallengeApplication {


	public static void main(String[] args) {

		SpringApplication.run(CodeChallengeApplication.class, args);

		LightsService lightsService = new LightsService(createStateHandlerFactory());
		long numberOfLights = lightsService.readNumberOfLightFromFile(LightAction.LIGHT_CHANGE);
		System.out.println("Number of lights on: " + numberOfLights);
		numberOfLights = lightsService.readNumberOfLightFromFile(LightAction.BRIGHTNESS_CHANGE);
		System.out.println("Number of lights on after brightness changes: " + numberOfLights);

	}
	private static ActionHandlerFactory createStateHandlerFactory() {
		List<ActionHandler> list = new ArrayList<>();
		list.add(new ChangeLightsHandler());
		list.add(new ChangeBrightnessHandler());
		NoOpActionHandler handler = new NoOpActionHandler();
		return new ActionHandlerFactory(list, handler);
	}
}
