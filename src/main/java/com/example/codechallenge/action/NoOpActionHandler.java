package com.example.codechallenge.action;

import java.util.Map;

import org.springframework.stereotype.Component;
import com.example.codechallenge.model.Light;

@Component
public class NoOpActionHandler implements ActionHandler {

	@Override
	public LightAction getAction() {
		return LightAction.NO_OP;
	}

	@Override
	public long handle(String currentLine, Map<Light, Integer> lightsMap, long counter) {
		return 0;
	}
}
