package com.example.codechallenge.utils;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class LightsFileReader {
	private static final String FILE_PATH = "src/main/resources/files/coding_challenge_input.txt";

	public static FileReader getFileReader() throws FileNotFoundException {
		return new FileReader(FILE_PATH);
	}
}
