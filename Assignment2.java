 import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Assignment2 {
	private static short state = 0;
	private ArrayList item;
	//state == -1 puts you out of the loop
	//state == 0 is default state
	//state == 1 is buying an item online or reserving an item
	//state == 2 is notify by email
	//state == 3 is purchase online
	public static void main(String[] args) {
		System.out.println("Hello, this is *** shopping center customer service, how are you today?");
		do {
			System.out.println(respond());
		}while(state != -1);
	}
	public static String respond() {
		Scanner input = new Scanner(System.in);
		String output = search(input.nextLine());
		return output;
	}
	public static String search(String input) {
		if(input.toLowerCase().contains("hello")) {
			return "So, what do you want me to help you with?";
		}
		if(input.toLowerCase().contains("how are you") || input.toLowerCase().contains("good, and you") || input.toLowerCase().contains("and you")) {
			return "I'm good, thank you for asking.";
		}
		if(input.toLowerCase().contains("good") || input.toLowerCase().contains("fine")) {
			return "Glad to hear. Want me to do something for you?";
		}
		if(input.toLowerCase().contains("find item") || input.toLowerCase().contains("is there any") || input.toLowerCase().contains("do you have") || input.toLowerCase().contains("in stock")) {
			int rand = (int) (Math.random()*3);
			switch(rand) {
			case 1: {
				state = 1;
				return "Yes, we currently have this item in stock. Do you want to purchase online, or do you want me to reserve the item for you in store?";
			}
			case 2: {
				state = 2;
				return "Sorry, I believe the item is out of stock. Would you want me to notify you once we have it in stock again?";
			}
			default: {
				return "Sorry, we do not have this item in store. Is there anything else I can help you with?";
			}
			}
		}
		if(input.toLowerCase().contains("that's all") || input.toLowerCase().contains("that is all") || input.toLowerCase().contains("done") || input.toLowerCase().contains("goodbye") || input.toLowerCase().contains("bye")) {
			state = -1;
			return "Thank you for using *** shopping center customer service, goodbye!";
		}
		if(state == 1) {
			if(input.toLowerCase().contains("purchase") || input.toLowerCase().contains("online")) {
				state = 3;
				return "No problem, please type in your credit card number for me. (Don't actually)";
			}
			if(input.toLowerCase().contains("reserve") || input.toLowerCase().contains("in person")) {
				state = 0;
				return "Alright, I have reserved the item for you. Anything else I can help you with?";
			}
			if(input.toLowerCase().contains("no")) {
				state = 0;
				return "Okay, anything else I can help you with?";
			}
			else {
				return "Sorry, I do not understand what you mean, can you try again?";
			}
		}
		if(state == 2) {
			if(input.toLowerCase().contains("yes") || input.toLowerCase().contains("ok")) {
				state = 4;
				return "Sure, can you please type in your e-mail? (Please don't actually)";
			}
			if(input.toLowerCase().contains("no")) {
				state = 0;
				return "Got it, is there anything else I can help you with?";
			}
		}
		if(state == 4) {
			state = 0;
			return "We will send a message to your email " + input + " once we have new ones coming in. Anything else I can help?";
		}
		if(state == 3) {
			state = 5;
			return "We will now bill $" + ((double) ((int) (Math.random() * 100))+ 0.99) + " from your credit card " + input + ". Where do you want us to deliver to? (NO)";
		}
		if(state == 5) {
			state = 0;
			return "Your address is " + input + ", the item will arrive in 3 to 5 business days. Anything else I can do for you?";
		}
		return "Sorry, I do not understand what you want me to do. Can you say it once more?";
	}
}
