package com.example.codechallenge.utils;

public class LightsTestContants {
	public static final String TURN_ON_LIGHTS = "turn on 1,1 through 2,2";
	public static final String TURN_OFF_LIGHTS = "turn off 1,1 through 2,2";
	public static final String TOGGLE_LIGHTS = "toggle 1,1 through 2,2";
	public static final String TOGGLE_LIGHTS_INVALID_COORDINATES = "toggle 2,1 through 1,2";
	public static final String END_COORDINATES = "end";
	public static final int NUMBER_OF_LIGHTS_ON = 4;
	public static final String INVALID_COORDINATES_START_X_ERROR = "turn on ,1 through 2,2";
	public static final String INVALID_COORDINATES_START_Y_ERROR = "turn off 1, through 2,2";
	public static final String INVALID_COORDINATES_END_X_ERROR = "turn off 1,1 through ,2";
	public static final String INVALID_COORDINATES_END_Y_ERROR = "turn on 1,1 through 2,";
	public static final String START_X_COORDINATE = "startX";
	public static final String START_Y_COORDINATE = "startY";
	public static final String END_X_COORDINATE = "endX";
	public static final String END_Y_COORDINATE = "endY";
	public static final boolean IS_TOGGLE = true;
	public static final boolean IS_NOT_TOGGLE = false;
	public static final String FILE_PATH = "src/test/resources/coding_challenge_input.txt";
	public static final String FILE_PATH_EXAMPLE = "src/test/resources/coding_challenge_input_example.txt";
	public static final String PATH_WRONG_FILE = "src/test/resources/coding_challenge_input_error.txt";
}
