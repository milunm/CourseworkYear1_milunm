

public class Numerical_Conversion extends Main_Class {
	
	public static int GetNumber(String DecodedText) {
		String[] ValueList = DecodedText.split(":"); //re-used code from QR validation
		try {
			int NumberVal = Integer.parseInt(ValueList[0]); //parse the first value as an integer,
			return NumberVal; //then return its value
		}catch(Exception e) {
			e.printStackTrace();
			return -1; //if any exceptions occur, return -1
		}		
	}
	public static int decimalToOctal(int DecimalVal) { //method to convert a decimal value to its octal form
		int temp; //temp value to be used during calculation
		int OctalValue = 0; //initialize variable to store the complete conversion
		int PlaceValue = 1; //this is used to add 'weighting' to each digit. as in octal conversion, 
		//the first division is the smallest weighting, then for each subsequent division the remainder becomes 10* that of the previous
		while (DecimalVal != 0) {
			int remainder = DecimalVal % 8; //calculate remainder
			temp = remainder * PlaceValue; //store the remainder, multiplied by its placevalue weighting in a temp variable
			OctalValue += temp; //add the value to the octal total
			DecimalVal /= 8; //divide by 8 to progress to the next iteration
			PlaceValue *= 10; //multiply placevalue by 10 to progress to next iteration
			}
		return OctalValue; //return the octal value 
}
	public static String decimalToHex(int DecimalVal) {
		String hexValue = "";
		char[] hexDigits = "0123456789ABCDEF".toCharArray(); //store all possible hex values in a char array
		
		while (DecimalVal != 0) {
			int remainder = DecimalVal % 16; //get the remainder of the decimal val / 16 ; this is how hex values are calculated
			hexValue = hexDigits[remainder] + hexValue; //get the hex digit at the index of the remainder, and add it to the hexValue string
			DecimalVal /= 16;	//divide the decimal value by 16 and progress to the next iteration (as long as the val is still > 0)					
		}
		return hexValue; //at the end, return the calculated hex value
		
	}

	public static String decimalToBinary(int DecimalVal) {
		String binaryValue = ""; //create a string to store the binary val
		while (DecimalVal != 0) { //until the value reaches 0
			int remainder = DecimalVal % 2; //calculate the remainder of the decimal value divided by 2 
			binaryValue = remainder + binaryValue; //add on the remainder (either 0 or 1) to the binaryValue string
			DecimalVal /= 2; //divide the decimal value by 2 to progress to the next iteration
			
		}
		return binaryValue; //return the string at the end of calculations
	}
	
	
	
	
	
	
}
	
