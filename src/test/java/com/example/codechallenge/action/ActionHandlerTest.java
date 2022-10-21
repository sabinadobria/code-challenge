package com.example.codechallenge.action;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ActionHandlerTest {

	private ActionHandlerFactory actionHandlerFactory;
	@InjectMocks
	private NoOpActionHandler noOpActionHandler;
	@InjectMocks
	private ChangeLightsHandler lightsHandler;
	@InjectMocks
	private ChangeBrightnessHandler brightnessHandler;

	@Test
	public void test_getState() {
		List<ActionHandler> actionHandlers = Arrays.asList(noOpActionHandler,
				lightsHandler, brightnessHandler);
		actionHandlerFactory = new ActionHandlerFactory(actionHandlers, noOpActionHandler);

		ActionHandler actionHandler = actionHandlerFactory.getStateHandler(LightAction.LIGHT_CHANGE);
		Assertions.assertNotNull(actionHandler);
		Assertions.assertTrue(actionHandler instanceof ChangeLightsHandler);

		actionHandler = actionHandlerFactory.getStateHandler(LightAction.BRIGHTNESS_CHANGE);
		Assertions.assertNotNull(actionHandler);
		Assertions.assertTrue(actionHandler instanceof ChangeBrightnessHandler);

		actionHandler = actionHandlerFactory.getStateHandler(LightAction.NO_OP);
		Assertions.assertNotNull(actionHandler);
		Assertions.assertTrue(actionHandler instanceof NoOpActionHandler);

		actionHandlerFactory = new ActionHandlerFactory(Collections.emptyList(), noOpActionHandler);
		actionHandler = actionHandlerFactory.getStateHandler(LightAction.BRIGHTNESS_CHANGE);
		Assertions.assertNotNull(actionHandler);
		Assertions.assertTrue(actionHandler instanceof NoOpActionHandler);
	}

}
