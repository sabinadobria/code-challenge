package com.example.codechallenge.exceptions;

public class InvalidCoordinatesException extends IllegalArgumentException {
	public static final String DEFAULT_MESSAGE = "Invalid data for: [ %s ] coordinates";

	public InvalidCoordinatesException(String coordinate){
		super(String.format(DEFAULT_MESSAGE, coordinate));
	}
}
