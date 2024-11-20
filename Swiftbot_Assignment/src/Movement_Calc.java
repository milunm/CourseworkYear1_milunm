
public class Movement_Calc extends Main_Class {
	
	public static int SpeedCalc(int OctalNum){
		if (OctalNum >= 100) {
			return 100; //if bigger than 100, set to max speed of swiftbot
		}
		if (OctalNum < 50) {
			return OctalNum + 50; //if less than 50, add 50 to speed
		}
		else {
			return OctalNum; //anything else, just return the original num	
		} //simple calculation to return the speed when taking the octal number as an input
		
		
	
	    
	}
	public static int MovementDuration(String HexInput) {
		int DigitLength = HexInput.length();
		int MovementTime;
		if (DigitLength == 1) { //if hex value is one digit long
			MovementTime = 1000;
			return MovementTime; //return 1 second movement time
			
		}
		if (DigitLength == 2) {
			MovementTime = 2000; //if 2 digits, then return 2 seconds
			return MovementTime;
		}
		else {
			System.out.println("ERROR: HEX VALUE IS NOT VALID");
			return -1; //error code 
		}
	}
	
	

}
