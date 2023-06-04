/* This is just a placeholder. */
package ocr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OCRTest {

	@Test
	void testEmptyString() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("", "", "");


		assertEquals("", translation);
	}	
	@Test
	void testEmptyStringOnlySpaces() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("   ", "   ", "   ");


		assertEquals("", translation);
	}
	@Test
	void testNotSameLength() { 

		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					translator.translate("  _|", "     ", " ");
				}
				);

		assertEquals("The strings are not the same length or are null", exception.getMessage());
	}
	
	@Test
	void testTwoSameLength() { 

		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					translator.translate("  ", "  ", " ");
				}
				);

		assertEquals("The strings are not the same length or are null", exception.getMessage());
	}
	
	@Test
	void testAllNullInput() {
		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					translator.translate(null,null,null);
				}
				);
		
		
		assertEquals("The strings are not the same length or are null", exception.getMessage());
	}
	@Test
	void testOneNullInput() {
		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					translator.translate("__","_",null);
				}
				);
		
		
		assertEquals("The strings are not the same length or are null", exception.getMessage());
	}
	
	@Test
	void testMiddleNull() { //for coverage
		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					translator.translate("   ",null ,"");
				}
				);
		
		
		assertEquals("The strings are not the same length or are null", exception.getMessage());
	}
	
	@Test
	void testZero() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _ ", 
												  "| |", 
												  "|_|");


		assertEquals("0", translation);
	}
	@Test
	void testOne() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" ", 
												  "|", 
												  "|");


		assertEquals("1", translation);
	}
	
	@Test
	void testTwo() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _ ", 
												  " _|", 
												  "|_ ");


		assertEquals("2", translation);
	}
	
	@Test
	void testThree() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("_ ", 
											 	  "_|", 
											 	  "_|");


		assertEquals("3", translation);
	}
	
	@Test
	void testFour() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("   ", 
												  "|_|", 
												  "  |");


		assertEquals("4", translation);
	}
	@Test
	void testFive() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _ ", 
												  "|_ ", 
												  " _|");


		assertEquals("5", translation);
	}
	
	@Test
	void testSix() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _ ", 
												  "|_ ", 
												  "|_|");


		assertEquals("6", translation);
	}
	
	@Test
	void testSeven() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("_ ", 
												  " |", 
												  " |");


		assertEquals("7", translation);
	}
	@Test
	void testEight() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _ ", 
												  "|_|", 
												  "|_|");


		assertEquals("8", translation);
	}
	
	@Test
	void testNine() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _ ", 
												  "|_|", 
												  "  |");


		assertEquals("9", translation);
	}
	@Test
	void testTwoOnesOneSpace() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("   ", 
												  "| |", 
												  "| |");

		assertEquals("11", translation);
	}
	
	@Test
	void testTwoDifferentNumbersOneSpace() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("_   ", 
												  "_| |", 
												  "_| |");

		assertEquals("31", translation);
	}
	
	@Test
	void testTwoDifferentNumbersOneSpaceAfter() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate(" _      _  ", 
												  " _|  | |_| ", 
												  " _|  | |_| ");

		assertEquals("318", translation);
	}
	@Test
	void testAllDigitsIncludingSpaces() { 

		OCRTranslator translator = new OCRTranslator();
		String translation = translator.translate("  _     _     _        _     _   _     _     _ ", 
												  " | | |  _|    _|  |_| |_    |_    |   |_|   |_|", 
												  " |_| | |_     _|    |  _|   |_|   |   |_|     |");

		assertEquals("0123456789", translation);
	}
	
	@Test
	void testDoesntTranslate() { 
		
		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					String translation = translator.translate("  _        _  ", 
							  "  _|  |_  |_| ", 
							  "  _|  |   |_| "); }
				);


		assertEquals("Invalid", exception.getMessage());
	}
	
	@Test
	void testInvalidCharacters() { 
		
		Throwable exception = assertThrows(
				OCRException.class, () -> {
					OCRTranslator translator = new OCRTranslator();
					String translation = translator.translate("  _        _  ", 
							  "  _|  |_  |_| ", 
							  "  aa  |   |_| "); }
				);


		assertEquals("Invalid", exception.getMessage());
	}
}
