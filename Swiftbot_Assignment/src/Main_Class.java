import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import swiftbot.*;


public class Main_Class {
	public static String filepath = "/home/pi/Documents/FINAL/SwiftbotReport.txt"; //set filename of the output file
	public static int[] TotalRunsCount = {1}; //flag used to check if the start button needs to be
	//pressed depending if it is the first runthrough or not, and also to track total runthroughs.
	
	
	
	public static void main(String[] args) {
        
        BufferedWriter writer = null; //define the filewriter outside of the loop
		boolean[] IsProgramRunning = {true};
		try {
			 writer = new BufferedWriter(new FileWriter(filepath)); //create the text file at location 
		while (IsProgramRunning[0]) {
			String DecodedText = null;
			SwiftBotAPI API = SwiftbotAPI_Util.getAPIInstance(); //utility used to re-open the API if it is open in another instance without running multiple instances and causing an error
			API.disableButton(Button.X);
			API.disableButton(Button.Y); //disable buttons that may have been already created from previous movements.
// 			
			
			final boolean[] WasButtonPressed = {false}; //flag if button is pressed yet
			
			API.enableButton(Button.A, () -> {
				System.out.println("///////Button A has been pressed. Now beginning execution!//////////////");
				WasButtonPressed[0] = true; //button enabled, if it is pressed, it changes the bool value
				
			});
			if (TotalRunsCount[0] > 1) {
				WasButtonPressed[0] = true; //skips pressing A to start if not first run
			}
			while (!WasButtonPressed[0]) { //while button hasnt been pressed, simply loop with the text 'press button a to start'
				System.out.println("/////////////////Press button A to start!////////////////////////////////"); 
				try {
					Thread.sleep(5000); //only re-prompt the user after 5 seconds have passed.
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
			API.disableButton(Button.A); //after the loop of waiting for the button to be pressed, it can now be disabled.
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Please scan a QR code to start!"); //prompt to scan QR
			boolean WasImageDetected = false; //boolean val to loop until a qr is found
			
			while (!WasImageDetected) {
				BufferedImage ScanQR = API.getQRImage(); //take a picture
			    DecodedText = QR_Code_Detection.DecodeQR(ScanQR); //details of any found qr code stored here 
				boolean IsTextValid = QR_Code_Detection.CheckValidInput(DecodedText); //check if the input is valid
				if (IsTextValid == (true)) {
	 				System.out.println("Success! Your QR code contained: " + DecodedText );
					//if valid, break loop and print out the QR code contents to console
					break;
					
				}
				else {
					WasImageDetected = false;
					
					if (!DecodedText.equals("Empty")) { //conditional statements for diff errors
					System.out.println("Your QR code was invalid, and contained: " + DecodedText );
					} //informs user that they had an invalid qr code
					else if (DecodedText.equals("Empty")) {
						System.out.println("No QR code found."); //informs user no text found
					}
					
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace(); //pause 2 seconds before taking another pic.
					}
					
				}
				
				
				
				
			}
			//storing of all required number variables.
			int NumberInput = Numerical_Conversion.GetNumber(DecodedText); //denary input
			int OctalOutput = Numerical_Conversion.decimalToOctal(NumberInput); //octal equivalent
			String HexadecimalOutput = Numerical_Conversion.decimalToHex(NumberInput); //hex equivalent
			String BinaryOutput = Numerical_Conversion.decimalToBinary(NumberInput); //binary equivalent
			
            int Speed = Movement_Calc.SpeedCalc(OctalOutput);
			int MovementTime = Movement_Calc.MovementDuration(HexadecimalOutput); //calculates movement time
			String SelectedColour = QR_Code_Detection.GetColour(DecodedText); //stores chosen colour	
			Underlight_Control.BlinkUnderlights(SelectedColour); //blinks underlight in the selected colour
		
			String StartTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")); //gets start time
			System.out.println("Time at start of execution: " + StartTime); //print to console
			HandleMovement.MoveRobot(Speed, MovementTime, BinaryOutput); //complete the movement
			Underlight_Control.BlinkUnderlights(SelectedColour); //blink underlights at end
			String EndTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")); //get end time
			
			System.out.println("Time at end of execution: " +  EndTime); //print to console
			
			//get details of duration to be written to text file
			int startMinutes = Integer.parseInt(StartTime.substring(3, 5)); //get only the minute val at the start
	        int startSeconds = Integer.parseInt(StartTime.substring(6, 8)); //get seconds val at start 
	        int endMinutes = Integer.parseInt(EndTime.substring(3, 5)); //get minutes total at end
	        int endSeconds = Integer.parseInt(EndTime.substring(6, 8)); //get seconds total at end
	        int TotalSeconds = (((endMinutes-startMinutes) * 60)) + (endSeconds - startSeconds);
	        //calculate duration in seconds using substrings
	       
	        try { //write each line of information into the text file
	        	writer.write("Run Number: " + TotalRunsCount[0]);
	        	writer.newLine();
				writer.write("QR Code Content: " + DecodedText);
				writer.newLine();
				writer.write("Octal Number Conversion: " + OctalOutput);
				writer.newLine();
				writer.write("Hexadecimal number conversion: " + HexadecimalOutput);
				writer.newLine();
				writer.write("Binary number Conversion: " + BinaryOutput);
				writer.newLine();
				writer.write("Time of duration in seconds: " + TotalSeconds);
				writer.newLine();
				writer.write("Calculated Speed: " + Speed);
				writer.newLine();
				writer.write("Calculated movement time: " + MovementTime);
				
				writer.newLine(); //extra newline so each movement is separated by a space
				writer.newLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
	        writer.flush(); //flush the writer to ensure everything is written
	        
			
			
			
			
			System.out.println("Press 'Y' on the swiftbot to scan another QR code? Or press 'X' to quit"); //prompt to quit /continue
			boolean[] HasUserSelected = {false}; //bool to create a while loop that runs until a button is pressed
			API.enableButton(Button.X, () -> {
				System.out.println("You Pressed: Button X. Thanks for playing!"); 
				System.out.println("Your file is saved at: " + filepath);
				TotalRunsCount[0] ++;
				IsProgramRunning[0] = false;
				System.exit(0); //this button prints a goodbye message, prints the filepath then ends the program., , 
				
				
				
			});		//button X function, prints a notice that the program will terminate, and prints the filepath the user selected.
		
		    API.enableButton(Button.Y, () -> {
			System.out.println("You pressed: button Y! Reloading program...");
			HasUserSelected[0] = true;
			TotalRunsCount[0] ++;
		});
		    //button y function just writes  a notice to command line that the program will restart.
			
			while (!HasUserSelected[0]) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}				
			} //program cant progress until a button has been pressed.
										
		}
		
		} catch (IOException e) {
	            e.printStackTrace(); //catch any ioexception while the file is open.
		}				
}
}




