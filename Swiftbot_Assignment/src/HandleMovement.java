  import swiftbot.SwiftBotAPI;





public class HandleMovement {
	
	public static void MoveRobot(int speed,int MovementTime,String BinaryOutput) {
		int AntiClockwiseCount = 0;
		int ClockwiseCount = 0;
		for (int i = BinaryOutput.length() - 1; i >= 0; i--) { //for each binary digit
			char CurrentDigit = BinaryOutput.charAt(i); //fetch the value of the current digit
			//System.out.println("Current Digit" + CurrentDigit);
			MoveForward(speed,MovementTime); //call function to perform the forward movement
			if (CurrentDigit == '0') { //if digit is a 0
				System.out.println("Turning anticlockwise...");
				AntiClockwise(); //perform anticlockwise turn
				AntiClockwiseCount ++;
				
				
			}
			else if (CurrentDigit == '1') { //if digit is a 1
				System.out.println("Turning clockwise...");
				Clockwise(); //perform clockwise turn
				ClockwiseCount ++;
				
				
			}			
		}
		System.out.println("This movement had : " + ClockwiseCount + " clockwise turns and " + AntiClockwiseCount + " anticlockwise turns");
	} //additional feature to track number of clockwise and anticlockwise turns.
	
	
	public static void MoveForward(int speed, int MovementTime) {
		SwiftBotAPI API = SwiftbotAPI_Util.getAPIInstance();
		System.out.println("Moving forward!");
		try {
			API.move(speed, speed, MovementTime);
		} catch (IllegalArgumentException e) { //when given a speed and movement time, this function performs the forward movement
			//with these values
			e.printStackTrace();
		}
	}
	
	public static void AntiClockwise() {
		SwiftBotAPI API = SwiftbotAPI_Util.getAPIInstance();
		int TimeNeeded = 580;
		int LeftWheel = -80;
		int RightWheel = 80; //values required for an anticlockwise turn. after testing found these values perform it with best accuracy
		
		
		try {
			API.move(LeftWheel,RightWheel,TimeNeeded);
		} catch (IllegalArgumentException  e) {
			e.printStackTrace(); //perform turn
			
		}
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); // stop for a short time before continuing to the next digit
		}
	}
	
	public static void Clockwise() {
		SwiftBotAPI API = SwiftbotAPI_Util.getAPIInstance();
		int TimeNeeded = 580;
		int LeftWheel = 80;
		int RightWheel = -80; //same as anticlockwise function except diff values.
		
		
		try {
			API.move(LeftWheel,RightWheel,TimeNeeded);
		} catch (IllegalArgumentException  e) {
			e.printStackTrace();
			
		}
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
