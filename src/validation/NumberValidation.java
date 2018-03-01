package validation;

import java.util.Scanner;

public class NumberValidation {

	private NumberValidation() {

	}

	public static int check(Scanner input) {

		String str = input.nextLine();

		boolean check = true;

		if (str.length() < 1) {
			return check(input);
		}

		for (int i = 0; i < str.length(); i++) {
			if (!((int) str.charAt(i) > 47 && (int) str.charAt(i) < 58)) {
				check = false;
			}
		}

		if (check) {
			return Integer.parseInt(str);
		} else {
			System.out.println("Machine is accepting numbers only.");
			return check(input);
		}
		
	}

}
