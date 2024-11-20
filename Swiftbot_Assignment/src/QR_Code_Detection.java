import java.awt.image.BufferedImage;
import swiftbot.*;
public class QR_Code_Detection extends Main_Class {
	
	
	
	
	public static String DecodeQR(BufferedImage QRImage) { //function to take an image of a QR code and return its value as a string
		
		String NoQRFound = "Empty";
		
		SwiftBotAPI API = SwiftbotAPI_Util.getAPIInstance();
		
		try{ 
			  String decodedText = API.decodeQRImage(QRImage); //attempt to decode text from the previous qr image
			  if(!decodedText.isEmpty()){
			     
			     return decodedText;  //if text is found, print it to the console and then return its value
			  }
			  else{
				  return NoQRFound; //otherwise, return a string containing 'Invalid'.
			  }
			}catch(IllegalArgumentException e){ //catch statement incase an invalid input is recieved (eg a non-string)
			  e.printStackTrace();
			  return NoQRFound; //return 'Invalid';
			}
	}
	public static boolean CheckValidInput(String decodedText) { //function to check if the Decoded QR code string fits the required spec.
		
		String[] valueSeparate = decodedText.split(":"); //split the two values into a list, separated by the : 
		
		if (valueSeparate.length != 2) {
			return false; //if invalid length, return false
		}
		try {
			double Value1 = Double.parseDouble(valueSeparate[0]);
			if (Value1 <= 0 || Value1 > 100) {
				return false; //if outside of range, return false
			}
			//Value1 checks complete
			String Value2 = valueSeparate[1].toUpperCase();
			if (CheckValidColour(Value2) == false) {
				return false;
			}
			
		
			
		} catch(NumberFormatException e) {
			return false; //return false if an invalid number format is recieved
		}
	return true;	//otherwise, return true.									

	}
	public static boolean CheckValidColour(String Value2) {
		String[] CorrectColours = {"RED","GREEN","BLUE","WHITE"};
		//list containing all valid colours.
		
		for (String Value : CorrectColours) {
			if(Value.equals(Value2)) {
				return true;
				//iterate through list, if a valid colour is matched to the input then true is returned
			}
		}
		return false;
		
		
		}
	public static String GetColour(String DecodedText) {
		String[] valueSeparate = DecodedText.split(":");
		String SelectedColour = valueSeparate[1].toUpperCase();
		return SelectedColour; //simply split the list by the colon, and fetch the value of the chosen colour and return it.
		
		
		
	}
	}


