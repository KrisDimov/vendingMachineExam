package enums;

import java.util.Scanner;

public enum Options {
	ADD_PRODUCT("1", "Add product"), REMOVE_PRODUCT("2", "Rem. product"), SHOW_PRODUCTS("3", "Show products"), INSERT_COINS("4",
			"Insert coins"), INSERTED_AMOUNT("5", "Ins. amount"), BASKET_AMOUNT("6", "Basket amount"), SHOW_BASKET("7",
					"Show basket"), PLACE_ORDER("8", "Place order"), CANCEL_ORDER("9", "Cancel order"), EXIT("Exit", "Exit");

	private String optionNumber;
	private String optionStr;

//	private static final int SIZE = Options.values().length;
	
	private Options(String optionNumber, String optionStr) {
		this.optionNumber = optionNumber;
		this.optionStr = optionStr;
	}
	
	public static Options fromString(Scanner input) {
		
		String source = input.nextLine();
		
		for (Options option : Options.values()) {
			if (source.equalsIgnoreCase(option.getOptionNumber())) {
				return option;
			}
		}
		System.out.println("Unrecognized command!");
		return fromString(input);
	}
	
	public String getOptionNumber() {
		return optionNumber;
	}
	
	public String toString() {
		return optionStr;
	}
	
//	public int getEnumSize() {
//		
//		int num = 0;
//		
//		for (Options option : Options.values()) {
//			num++;
//		}
//		
//		return num;
//	}
	
}
