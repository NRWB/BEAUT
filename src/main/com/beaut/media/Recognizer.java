package com.beaut.media;

import java.nio.file.Path;

import com.beaut.media.types.MediaType;

/**
 * Simple test class to test a input Path to see if it matches the preset media.
 * 
 * @author Nick
 */
public class Recognizer {

	/**	
	 * Tests for recognized media. The media is preset by media.types in the Media.java enum.
	 * 
	 * @param p The path to test against
	 * @return true for contained in the preset list, otherwise false.
	 */
	public static boolean isRecognize(Path p) {
		String s = p.toString();
		String ext = s.substring(s.lastIndexOf('.') + 1, s.length());
		for (Media m : Media.values())
			for (MediaType mt : m.getSubTypes()) {
				String a = mt.getTypeName().toLowerCase();
				String b = ext.toLowerCase();
				if (a.equals(b))
					return true;
			}
		return false;
	}

	/*
	 * Simple test
	public static void main(String[] args) throws IOException {
		boolean b = Recognizer.recognize(Paths.get("testFileExt.png"));
		System.out.println("found? (Y/N): " + (b ? "Y-Yes" : "N-No"));
	}
	 */
}
