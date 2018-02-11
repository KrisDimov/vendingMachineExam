package services;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import enums.Coins;
import validation.NumberValidation;
import validation.TwoOptiosValidation;

public class Bank {

	private Bank() {

	}

	private static int remainingChange = 0;

	public static int getRemainingChange() {
		return remainingChange;
	}

	private static String source = "src/source/Coins.list";

	private static Map<Integer, Integer> cash = new HashMap<>();

	private static Map<Integer, Integer> insertedAmount = new HashMap<>();

	public static void clearPayment() {

		insertedAmount.clear();

	}

	public static void change() {

		int insertedAmount = insertedAmount();
		
		int getBasketAmount = (int)(Basket.getBasketAmount()*100);
		
		int change = insertedAmount - getBasketAmount;
		
		remainingChange = 0;
		
		if (change > 0) {

			Coins[] coin = Coins.values();

			int coinValue;

			for (int i = coin.length - 1; i >= 0; i--) {

				if (change != 0) {

					if (cash.get(coin[i].getCoinValue()) > 0) {

						coinValue = coin[i].getCoinValue();

						while (coinValue <= change) {

							change -= coinValue;

							System.out.println(coin[i].getCoinString());
						}
					} else {
						continue;
					}
				} else {
					continue;
				}
			}
		} else {
			System.out.println("There's no change.");
		}
		if (change > 0) {
			remainingChange = change;
			System.out.println("Not enough coins to give you your change. ");
		}
	}

	public static void proceedPayment() {

		for (Integer key : insertedAmount.keySet()) {

			cash.put(key, (cash.get(key) + insertedAmount.get(key)));

		}

	}

	public static int insertedAmount() {

		int totalAmount = 0;

		for (Integer key : insertedAmount.keySet()) {

			totalAmount += (key * insertedAmount.get(key));

		}

		return remainingChange + totalAmount;
		
	}

	public static void insertCoins(Scanner input) {

		acceptedCoins();

		Coins coin = Coins.fromString(input);

		int coinValue = coin.getCoinValue();

		System.out.println("How many coins are you inserting?");

		int inserterCoinNumber = NumberValidation.check(input);

		if (insertedAmount.containsKey(coinValue)) {
			
			insertedAmount.put(coinValue, insertedAmount.get(coinValue) + inserterCoinNumber);
			
		} else {
			
			insertedAmount.put(coinValue, inserterCoinNumber);
			
		}
		
		System.out.printf("You have inserted %d %s coin%s.%n", inserterCoinNumber, coin.getCoinString(),
				(inserterCoinNumber > 1 ? "s" : ""));

		System.out.println("Do you want to insert some more? 1: Yes | 2: No");

		if (TwoOptiosValidation.check(input) == 1) {
			insertCoins(input);
		}

	}
	
	private static void acceptedCoins() {

		for (Coins coin : Coins.values()) {
			System.out.printf("| %d: %s ", coin.getId(), coin.getCoinString());
		}
		System.out.printf("|%n");
	}

	public static void loadCash() {

		try {

			BufferedReader br = new BufferedReader(new FileReader(new File(source)));

			String line;

			while ((line = br.readLine()) != null) {

				String[] arr = line.split(";");

				cash.put(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]));

			}

			br.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}

}
