import java.io.IOException;

import swiftbot.SwiftBotAPI;

public class Underlight_Control {
	
	
	public static void BlinkUnderlights(String ChosenColour){
		SwiftBotAPI API = SwiftbotAPI_Util.getAPIInstance();
		int[] ColourToLightUp = {0,0,0}; //pre define the variable containing RGB information
		
		String[] CorrectValuesList = {"RED","GREEN","BLUE","WHITE"}; //list of all valid colours
		if (ChosenColour.toUpperCase().equals(CorrectValuesList[0])) {
			 ColourToLightUp = new int[] {255,0,0};			//if colour = red, light up red						
		}
		if (ChosenColour.toUpperCase().equals(CorrectValuesList[2])) {
			ColourToLightUp = new int[]{0,255,0};  //if colour = green, light up green
		}
		if (ChosenColour.toUpperCase().equals(CorrectValuesList[1])) { 
			ColourToLightUp = new int[]{0,0,255}; //if colour = blue , light up blue 
		}
		if (ChosenColour.toUpperCase().equals(CorrectValuesList[3])) {
			 ColourToLightUp = new int[]{255,255,255}; //if colour = white, light up white
			 
		//note: on my robot rgb the green and blue were switched, it was taking inputs as {red,green,blue}
		}
		try {
			API.fillUnderlights(ColourToLightUp); //light up the underlights
		}catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace(); //wait for 2 seconds to create 'blink effect'
		}
		API.disableUnderlights(); //then disable underlights
		try {
			Thread.sleep(1000);
		} catch (InterruptedException  e) { //wait 1 second before loading next phase.
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
