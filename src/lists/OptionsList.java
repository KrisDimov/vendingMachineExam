package lists;

import enums.Options;

public class OptionsList {

	private static int optionsLength() {

		int optionLength = 0;

		for (Options option : Options.values()) {
			if (option.toString().length() > optionLength) {
				optionLength = option.toString().length();
			}
		}

		return optionLength;
	}

	public static void printOptionsList() {

		StringBuilder sb = new StringBuilder();
		;

		int optionsLength = optionsLength();

		int optionCount = 1;

		for (Options option : Options.values()) {

			if (optionCount == Options.values().length) {

				System.out.printf("%n%s", option.toString());
				System.out.println();
				
			} else {

				if (option.toString().length() < optionsLength) {
					for (int i = 0; i < (optionsLength - option.toString().length()); i++) {
						sb.append(" ");
					}
				}
				System.out.printf("|%2d - %s%s ", Integer.parseInt(option.getOptionNumber()), option.toString(),
						sb.toString());

				if (optionCount % 3 == 0) {
					System.out.printf("|%n");
				}

				optionCount++;
				sb.setLength(0);

			}

		}

	}
}
