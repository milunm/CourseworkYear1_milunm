 import swiftbot.*;
public class SwiftbotAPI_Util {
	private static SwiftBotAPI API_Instance;
	static { //calls this each time the class is loaded.
		initialiseAPI(); //function to check if the API is already running or not. allows for use across multiple classes without multiple instances.
	}
	private static void initialiseAPI() {
		if (API_Instance == null) { //check if API is already running, if not, create a new instance
			try {
				API_Instance = new SwiftBotAPI();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static SwiftBotAPI getAPIInstance() {
		return API_Instance; //used to ensure that only 1 instance of SwiftbotAPI is running at any time
	}
	
	

}
