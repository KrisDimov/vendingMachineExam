package startVME;

import java.util.Scanner;

import enums.Options;
import lists.OptionsList;
import lists.ProductsList;
import services.Bank;
import services.Basket;
import services.OrderProcessing;

public class StartVME {
	public static void main(String[] args) {

		ProductsList.loadProductsList();
		Bank.loadCash();

		showProducts();

		Scanner input = new Scanner(System.in);

		while (true) {

			OptionsList.printOptionsList();

			System.out.println("Enter your command please.");

			Options option = Options.fromString(input);

			switch (option) {

			case ADD_PRODUCT:

				addProduct(input);

				break;

			case REMOVE_PRODUCT:

				removeProduct(input);

				break;

			case SHOW_PRODUCTS:

				showProducts();

				break;

			case INSERT_COINS:

				insertCoins(input);

				break;

			case INSERTED_AMOUNT:

				insertedAmount();

				break;

			case SHOW_BASKET:

				showBasket();

				break;
			case BASKET_AMOUNT:

				showBasketAmount();

				break;

			case PLACE_ORDER:

				placeOrder();

				break;

			case CANCEL_ORDER:

				cancelOrder();

				break;

			case EXIT:

				System.out.printf("%nThank you for using our Vending Machine.%n%nHave a nice day!");

				return;

			}

		}

	}

	static void addProduct(Scanner input) {

		Basket.addProduct(input);

	}

	static void removeProduct(Scanner input) {

		Basket.removeProduct(input);

	}

	static void showProducts() {

		ProductsList.showProductsList();

	}

	static void insertCoins(Scanner input) {

		Bank.insertCoins(input);

		System.out.println();
		
	}

	static void insertedAmount() {

		float totalAmount = (Bank.insertedAmount())/100f;

		if (totalAmount < 0.1) {
			System.out.println("You haven't inserted any coins yet.");
		} else {
			System.out.printf("$ %.2f are inserted.%n", totalAmount);
		}

		System.out.println();

	}

	static void showBasket() {

		Basket.showBasket();

	}

	static void showBasketAmount() {

		Basket.showBasketAmount();

	}

	static void placeOrder() {

		OrderProcessing.placeOrder();

	}

	static void cancelOrder() {

		if (Basket.getBasket().isEmpty()) {

			System.out.println("No products in you basket yet.");

		}

		if ((Bank.insertedAmount() - Bank.getRemainingChange()) > 0) {
			System.out.printf("Please take your $ %.2f from the change slot.%n",
					((Bank.insertedAmount() - Bank.getRemainingChange()))/100f);
		}

		Basket.cancelOrder();

		System.out.println("Done!");

		Bank.clearPayment();

	}

}
