/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Copyright ©2016-2017 Gary F. Pollice
 *******************************************************************************/
package ocr;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * This class has a single method that will translate OCR digits to a string of
 * text digits that correspond to the OCR digits.
 * 
 * @version Feb 2, 2023
 */
public class OCRTranslator
{
	//hashmap used for translation
	private HashMap<String, String> OCRtoStringMap;
	/**
	 * The construction will each digit and their corresponding translation into the map
	 */
	public OCRTranslator()
	{
		OCRtoStringMap = new HashMap<String, String>();
		OCRtoStringMap.put(" _ "
				+   "| |"
				+   "|_|", "0");

		OCRtoStringMap.put(" "
				+   "|"
				+	"|", "1");
		OCRtoStringMap.put(" _ "
				+ " _|"
				+ "|_ ", "2");

		OCRtoStringMap.put(    "_ "
				+   "_|"
				+   "_|", "3");
		OCRtoStringMap.put("   "
				+ "|_| "
				+  " |", "4");
		OCRtoStringMap.put(" _ "
				+ "|_ "
				+ " _|", "5");
		OCRtoStringMap.put(" _ "
				+ "|_ "
				+ "|_|", "6");
		OCRtoStringMap.put("_ "
				+  " |"
				+  " |", "7");
		OCRtoStringMap.put(" _ "
				+ "|_|"
				+ "|_|", "8");
		OCRtoStringMap.put(  " _ "
				+ "|_|"
				+ "  |", "9");

	}

	/**
	 * Translate a string of OCR digits to a corresponding string of text
	 * digits. OCR digits are represented as three rows of characters (|, _, and space).
	 * @param top the top row of the OCR input
	 * @param middle the middle row of the OCR input
	 * @param bottom the third row of the OCR input
	 * @return a String containing the digits corresponding to the OCR input
	 * @throws an OCRException on error as noted in the specification
	 */
	public String translate(String top, String middle, String bottom) {

		//checks input
		if (!validInput(top, middle, bottom)) { 
			throw new OCRException("The strings are not the same length or are null");
		}

		String result = "";
		//get list of OCR numbers
		List<String> OCRDigits = getDigits(top, middle, bottom); 


		for (String singleOCRDigit : OCRDigits) {
			//check if blank
			if (singleOCRDigit.isBlank()) result += "";

			else {
				//get OCR to String translation using map
				String digitTranslation = this.OCRtoStringMap.get(singleOCRDigit);

				//if can't be translated throw error
				if (digitTranslation == null) { 
					throw new OCRException("Invalid");
				}
				else {
					result += digitTranslation;
				}
			}

		}

		return result;
	}

	/**
	 * validInput
	 * Checks if the three input strings are all valid for OCR translation
	 * Valid input consists of all non-null strings and all strings being the same length
	 * @param top the top row of the OCR input
	 * @param middle the middle row of the OCR input
	 * @param bottom the third row of the OCR input
	 * @return true if valid input false if invalid 
	 */
	public Boolean validInput(String top, String middle, String bottom) {
		//check if null or all same length
		if (top==null || middle == null || bottom == null || top.length() != middle.length() || middle.length() != bottom.length()) {
			return false;
		}

		return true;

	}


	/**
	 * allSpaces
	 * Checks if all three inputed strings have spaces at the given index
	 * @param index the character to check in the strings 
	 * @param top the top row of the OCR input
	 * @param middle the middle row of the OCR input
	 * @param bottom the third row of the OCR input
	 * @return
	 */
	public Boolean allSpaces (int index, String top, String middle, String bottom) {
		if ((top.charAt(index) == ' ') && (middle.charAt(index) == ' ') && bottom.charAt(index) == ' ') 
			return true;  
		return false;


	}



	/**
	 * Seperates each OCR digit by removing spaces and creates a list 
	 *  @param top the top row of the OCR input
	 * @param middle the middle row of the OCR input
	 * @param bottom the third row of the OCR input
	 * @return Array List of OCR digits 
	 */
	public List<String> getDigits(String top, String middle, String bottom) {
		//array list to hold digits
		List<String> OCRdigits = new ArrayList<String>();

		int index =0;
		int startOfDigit =0;
		while (index < top.length()) {
			//check if space between digits, move index
			if (allSpaces(index, top, middle, bottom)) {
				index++; //check next element
			}

			//else, must be beginning of digit
			startOfDigit = index;

			//still in bounds and not a space
			while ((index<top.length())&& !(allSpaces(index, top, middle, bottom))) {
				index++;
			}
			//end of string or a space, add the digit concatenation
			OCRdigits.add(top.substring(startOfDigit, index).concat(middle.substring(startOfDigit, index)).concat(bottom.substring(startOfDigit, index)));
		}
		return OCRdigits;


	}
}

