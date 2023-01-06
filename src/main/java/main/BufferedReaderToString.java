package main;

import java.io.BufferedReader;
import java.io.IOException;

public class BufferedReaderToString {
	public static String convert(BufferedReader buffereReader) throws IOException {
		String response, jsonToString = "";
			while ((response = buffereReader.readLine()) != null) {
				jsonToString += response;
			}
		return jsonToString;
	}
}
