package com.example.codechallenge.action;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

@Component
public class ActionHandlerFactory {
	private NoOpActionHandler noOpStateHandler;
	private final Map<LightAction, ActionHandler> stateHandlerMap;

	public ActionHandlerFactory(List<ActionHandler> actionHandlers,
	                            NoOpActionHandler noOpStateHandler) {
		this.noOpStateHandler = noOpStateHandler;
		stateHandlerMap = actionHandlers.stream()
				.collect(Collectors.toUnmodifiableMap(ActionHandler::getAction, Function.identity()));
	}

	public ActionHandler getStateHandler(LightAction lightAction) {
		return Optional.ofNullable(stateHandlerMap)
				.map(stateHandlerMap -> stateHandlerMap.get(lightAction))
				.orElse(noOpStateHandler);
	}
}
