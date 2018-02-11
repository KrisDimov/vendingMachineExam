package enums;

import java.util.Scanner;

public enum Coins {

	TEN(1, 10, "10¢"), TWENTY(2, 20, "20¢"), FIFTY(3, 50, "50¢"), HUNDRED(4, 100, "$1"), TWO_HUNDRED(5, 200, "$2");
	
	private int id;
	private int value;
	private String coinString;
	
	private Coins(int id, int value, String coinString) {
		this.id = id;
		this.value = value;
		this.coinString = coinString;
	}
	
	
	public static Coins fromString(Scanner input) {
		
		System.out.println("Enter the ID of your coin.");
		
		String source = input.nextLine();
		
		for (Coins coin : Coins.values()) {
			
			if (source.equals("" + coin.getId())) {
				return coin;
			}
			
		}
		System.out.println("There's no such coin.");
		return fromString(input);
		
	}
	
	public int getId() {
		return id;
	}
	
	public int getCoinValue() {
		return value;
	}
	
	public String getCoinString() {
		return coinString;
	}
	
//	public static int getEnumSize() {
//		
//		int size = 0;
//		
//		for (Coins coin : Coins.values()) {
//			size++;
//		}
//		
//		return size;
//	}
	
}
