package validation;

import java.util.Scanner;

public class TwoOptiosValidation {

	private TwoOptiosValidation() {
		
	}
	
	public static int check(Scanner input) {
		
		int choice = 0;
		
		do {
			choice = NumberValidation.check(input);
		} while (choice != 1 && choice != 2);
		
		return choice;
		
	}
	
}
