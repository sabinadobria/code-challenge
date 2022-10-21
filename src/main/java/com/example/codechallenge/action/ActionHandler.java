package com.example.codechallenge.action;

import java.util.Map;

import com.example.codechallenge.model.Light;

public interface ActionHandler {


	LightAction getAction();
	long handle(String currentLine, Map<Light, Integer> lightsMap, long counter);

}
