import java.util.ArrayList;
import java.util.Scanner;

public class Assignment2 {
	private static short state = 0;
	private static short backupState = 0;
	private static String options = "- Looking for items \n- Show business hours and location \n- Tracking or cancel orders \n- Rate and leave comments";
	// A 2D array of responses for each states
	private static String[][] responses = {
			{ "How else may I help you today?", "Is there anything else I can help you with?",
					"How else may I be of assitance?", "Do you want me to help you with other things?",
					"Is there anywhere else I can help?", "Do you still want me to do something for you?" },
			{ "Sure, what would you like me to find for you?", "What kind of item do you want me to get for you?",
					"Could you give me a name of what you need?" },
			{ "We do have the item in stock right now, do you want to order online or to reserve in in store?",
					"We have the item available, would you like to purchase online or us to reserve it in store?",
					"We currently have this item here, do you wish to buy it online right now or do you want us to keep it for you in store?" },
			{ "Please give us your credit card number for us to complete this purchase.",
					"Could you give us your credit card number to finish the transaction?",
					"Please provide us your credit card number to pay for the item." },
			{ "Are you sure your card number is ", "Is your card number ", "Do you confirm that your card number is " },
			{ "Sorry, your input is not a legimitate card number, please type your card number as a 16 digit number without spaces.",
					"Your number is not in the correct format, please type your card number as a 16 digit number without spaces." },
			{ "Please leave us your name and we will sell you the item we reserved for you.",
					"Give us your name so that we know it is you when you walk in.",
					"Could you leave us your name for us to know it is you once you come in store?" },
			{ "Just to be sure, your name is ", "Please confirm that your name is " },
			{ "We currently do not have the item in stock, but we can send you an email once we have the item again. Please give us your email.",
					"We do not have the item right now, but we can send you an email if you leave us one down below once we got them restocked.",
					"Sorry, this item is out of stock, but we will give you a notification if you leave us an email right now when we have it again." },
			{ "To be sure, your email is ", "Please confirm that your email is ", "So your email is " },
			{ "Sorry, we do not have the item you requested. ",
					"Sorry, we do not have this type of item in our stores. ",
					"We believe that we do not have the item you have mentioned, sorry. " },
			{ "Our business hours and location is as following: " },
			{ "Please give us your order number.", "Could you give us your order number?",
					"What is your order number?" },
			{ "We have received information of your order, would you like to track your order or cancel your order?",
					"We have confirmed your order, do you wish to track your order or cancel your order?",
					"We have found your order, do you want us to track your order or cancel your order?" },
			{ "Could you give us your email so that we can send tracking information to you?",
					"Please give us your email for us to give any information regarding the package to you.",
					"We can give you tracking information if you could type in your email below." },
			{ "Do you really wish to cancel your order?", "Are you sure to cancel your order?",
					"Do you want to have your order removed?" },
			{ "Sorry, we cannot find your item order. ", "Sorry, there is no record of your order. ",
					"Sorry, I do not think your order number is correct. " },
			{ "Please leave us your comment below, you can leave anything here.",
					"You could leave your comment below, we accept any appraisals and criticisms.",
					"Type in your comments below, and we will be sure to make improvements." },
			{ "Got it. ", "No problem. ", "Understood. ", "Okay. ", "Alright. ", "Done. " },
			{ "Sorry, I do not understand what you mean, can you try again?", "My bad, could you say it once more?",
					"Sorry, I do not understand what you want me to do. Could you come again?",
					"Sorry, I do not know what you want me to do, could you repeat to me one more time?" },
			{ "Thank you for using our customer service, goodbye.", "Thank you for using our service, see you soon.",
					"Thank you for contacting us, we look forward to your visit again.",
					"Thanks for using our online services, don't hesitate to ask us if you have more questions." },
			{ "Please enter your credit card number again." }, { "Please enter your name again." },
			{ "Please enter your email again." } };
	// A 2D array of keywords/phrases to detect actions for each states.
	private static String[][] keyword = { { "search", "get", "item", "looking for" }, // Goes from 0 to 1
			{ "buy", "purchase", "online", "order" }, // Goes from 2 to 3
			{ "reserve", "keep", "stay", "store" }, // Goes from 2 to 6
			{ "check", "hours", "location", "business", "time", "address" }, // Goes from 0 to 11
			{ "order", "cancel", "track", "locat" }, // Goes from 0 to 12
			{ "track", "follow", "find", "locat", "search", "find" }, // Goes from 13 to 14
			{ "cancel", "drop", "remove" }, // Goes from 13 to 15
			{ "comment", "leave", "feedback", "rat" }, // Goes from 0 to 17
			{ "correct", "right", "yes", "sure", "confirm" }, // Generic correct keywords
			{ "don't", "do not", "wrong", "not correct", "incorrect", "no" }, // Generic wrong keywords
			{ "return", "go back", "not what", "not this", "something else", "not looking", "wrong", "no thanks",
					"no, thanks", "no need"}, // Goes to state = 0; overrides every other conditions except state = 20
			{ "goodbye", "that's all", "is all", "done", "everything", "good" } };// Goes to state = 20; overrides every
																					// other conditions
	// An array storing generic clothing items
	private static String[] clothingItems = { "t-shirt", "pants", "jeans", "jacket", "gloves", "shirt", "socks",
			"sneakers", "boots", "cap", "sweater", "belt", "coat" };
	// Business times and location
	private static String info = "\nLocation: 123 J.Doe Street, Kelowna\n" + "Sun: Closed\n" + "Mon: 10 AM to 8 PM\n"
			+ "Tue: 10 AM to 8 PM\n" + "Wed: 10 AM to 8 PM\n" + "Thu: 10 AM to 8 PM\n" + "Fri: 10 AM to 9 PM\n"
			+ "Sat: 12 PM to 9 PM\n";
	// state == 0 is default state
	// Action group 1: Finding item and subsequent actions on the item
	// state == 1 is looking for item
	// state == 2 is choosing to buy an item online or reserve an item
	// state == 3 is purchase online by card no.
	// state == 4 is acknowledge card no.
	// state == 18; back to 0
	// state == 21 if no; back to 4
	// state == 5 is unacknowledge card no.; back to 3
	// state == 6 is reserve item
	// state == 7 is acknowledge name
	// state == 18; back to 0
	// state == 22 if no; back to 7
	// state == 8 is notify by email when restock
	// state == 9 is acknowledge email
	// state == 18; back to 0
	// state == 23 if no; back to 9
	// state == 10 is wrong item type; back to 0
	// Action group 2: Getting business hours and location
	// state == 11 is checking hours and location
	// Action group 3: Check order and subsequent actions on the order
	// state == 12 is checking order by order no.
	// state == 13 is track order or cancel order
	// state == 14 is track order by email
	// state == 9 is acknowledge email
	// state == 18; back to 0
	// state == 23 if no; back to 9
	// state == 15 is cancel order confirm
	// state == 16 is order not found
	// Action group 4: Leave comment
	// state == 17 is leave comment
	// state == 18; back to 0
	// state == 18 is generic confirm
	// state == 19 is unacknowledged action
	// state == 20 puts you out of the loop (end)

	public static void main(String[] args) {
		System.out.println("Hello there, welcome to SuperWet online customer service. How may I help you today?");
		System.out.println(options);
		do {
			respond();
		} while (state != 20);
	}

	public static void respond() {
		Scanner in = new Scanner(System.in);
		String input = in.nextLine();
		state = findState(input);
		String output = findResponse(input);
		System.out.println(output);
			
	}

	public static short findState(String input) {
			// To state = 20
			for (int i = 0; i < keyword[11].length; i++) {
				if (input.contains(keyword[11][i]))
					return 20;
			}
			// To state = 0
			for (int i = 0; i < keyword[10].length; i++) {
				if (input.contains(keyword[10][i]))
					return 0;
			}
			// Generic correct (for state = 4, 7, 9, 15)
			for (int i = 0; i < keyword[8].length; i++) {
				if (input.contains(keyword[8][i]))
					if (state == 4 || state == 7 || state == 9 || state == 15)
						return 18;
			}
			// Generic wrong (for state = 4, 7, 9, 15)
			for (int i = 0; i < keyword[9].length; i++) {
				if (input.contains(keyword[9][i])) {
					if (state == 4)
						return 21;
					if (state == 7)
						return 22;
					if (state == 9)
						return 23;
					if (state == 15)
						return 0;
				}
			}
			// From 0 to 1
			for (int i = 0; i < keyword[0].length; i++) {
				if (state == 0 && input.contains(keyword[0][i]))
					return 1;
			}
			// From 1 to 2(has item), 8(not in stock), 10(no item)
			for (String s : clothingItems) {
				if (state == 1 && (input.contains(s) || input.equals(s))) {
					if (Math.random() > 0.5)
						return 2;
					else
						return 8;
				}
			}
			if (state == 1)
				return 10;
			// From 2 to 3
			for (int i = 0; i < keyword[1].length; i++) {
				if (state == 2 && input.contains(keyword[1][i]))
					return 3;
			}
			// From 3, 21 to 4(valid), 5(invalid)
			if (state == 3 || state == 21) {
				for (char c : input.toCharArray()) {
					if (input.length() != 16 || !Character.isDigit(c)) {
						return 5;
					}
					return 4;
				}
			}
			// From 2 to 6
			for (int i = 0; i < keyword[2].length; i++) {
				if (state == 2 && input.contains(keyword[2][i]))
					return 6;
			}
			// From 6 to 7
			if (state == 6)
				return 7;
			// From 8, 14 to 9
			if (state == 8 || state == 14)
				return 9;
			// From 0 to 11
			for (int i = 0; i < keyword[3].length; i++) {
				if (state == 0 && input.contains(keyword[3][i]))
					return 11;
			}
			// From 0 to 12
			for (int i = 0; i < keyword[4].length; i++) {
				if (state == 0 && input.contains(keyword[4][i]))
					return 12;
			}
			// From 12 to 13(has order), 16(no order)
			if (state == 12) {
				if (Math.random() > 0.5)
					return 13;
				else
					return 16;
			}
			// From 13 to 14
			for (int i = 0; i < keyword[5].length; i++) {
				if (state == 13 && input.contains(keyword[5][i]))
					return 14;
			}
			// From 13 to 15
			for (int i = 0; i < keyword[6].length; i++) {
				if (state == 13 && input.contains(keyword[6][i]))
					return 15;
			}
			// From 0 to 17
			for (int i = 0; i < keyword[7].length; i++) {
				if (state == 0 && input.contains(keyword[7][i]))
					return 17;
			}
			// From 17 to 18
			if (state == 17)
				return 18;
			// From 22 to 7
			if (state == 22)
				return 7;
			// From 23 to 9
			if (state == 23)
				return 9;
			backupState = state;
			return 19;
	}

	public static String findResponse(String input) {
		String response = "";
		response = getResponse();
		if (state == 10 || state == 18 || state == 16) {
			state = 0;
			response += getResponse();
		}
		if (state == 4)
			response += input + "?";
		if (state == 7 || state == 9)
			response += input + ".";
		if (state == 5)
			state = 3;
		if (state == 11) {
			response += info;
			state = 0;
			response += getResponse();
		}
		if (state == 19)
			state = backupState;
		return response;
	}

	public static String getResponse() {
		return responses[state][(int) (Math.random() * responses[state].length)];
	}
	
}
