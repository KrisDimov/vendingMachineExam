package services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import lists.ProductsList;
import models.Product;
import validation.NumberValidation;
import validation.TwoOptiosValidation;

public class Basket {

	private Basket() {

	}

	private static Map<Integer, Product> basket = new LinkedHashMap<>();

	public static Map<Integer, Product> getBasket() {
		return basket;
	}

	public static void cancelOrder() {

		for (Integer key : basket.keySet()) {
			ProductsList.cancelOrder(key, basket.get(key).getQuantity());
		}

		clearBasket();

	}

	public static void clearBasket() {
		basket.clear();
	}

	public static void showBasketAmount() {

		if (getBasketAmount() != 0) {

			Map<Float, Integer> basketAmountSorting = new TreeMap<>(Collections.reverseOrder());

			float positionCost;

			int length = 0;
			
			for (Integer position : basket.keySet()) {

				positionCost = basket.get(position).getQuantity() * basket.get(position).getPrice();

				basketAmountSorting.put(positionCost, position);
				
				if (basket.get(position).getName().length() > length) {
					length = basket.get(position).getName().length();
				}

			}

			StringBuilder sb = new StringBuilder();
			
			for (Float cost : basketAmountSorting.keySet()) {

				if (basket.get(basketAmountSorting.get(cost)).getName().length() < length) {
					for (int i = 0; i < (length - basket.get(basketAmountSorting.get(cost)).getName().length()); i++) {
						sb.append(" ");
					}
				}
				
				System.out.printf("%2d x %s%s x $ %.2f = $ %.2f%n", basket.get(basketAmountSorting.get(cost)).getQuantity(),
						basket.get(basketAmountSorting.get(cost)).getName(), sb.toString(), basket.get(basketAmountSorting.get(cost)).getPrice(), cost);

				sb.setLength(0);
				
			}

			for (int i = 0; i < length; i++) {
				sb.append(" ");
			}
			
			System.out.printf("%s%sTotal: $ %.2f%n", "         ", sb.toString(), getBasketAmount());

		} else {

			System.out.println("No products in you basket yet.");

		}

		System.out.println();
	}

	public static float getBasketAmount() {

		float totalCost = 0;

		for (Integer key : basket.keySet()) {

			totalCost += (basket.get(key).getPrice() * basket.get(key).getQuantity());

		}

		return totalCost;

	}

	public static void showBasket() {

		if (!basket.isEmpty()) {

			StringBuilder sb = new StringBuilder();
			
			for (Integer position : basket.keySet()) {
				
				System.out.printf("%2d - %2d x %s%n", basket.get(position).getId(), basket.get(position).getQuantity(),
						basket.get(position).getName());
			}

		} else {

			System.out.println("No products in you basket yet");

		}

		System.out.println();

	}

	public static void removeProduct(Scanner input) {

		/**
		 * A lot of if's in this method
		 */

		if (basket.isEmpty()) {
			System.out.println("No products in you basket yet.");
			return;
		} else {

			System.out.println("In your basket you have:");

			showBasket();

			System.out.println("Enter the ID of the product you don't want.");

			int productId = productValidation(input, basket);

			int productQuantity = 0;

			if (basket.get(productId).getQuantity() > 1) {

				System.out.printf("You have more than one %s.%n", basket.get(productId).getName());
				System.out.println("Press 1 to remove all or 2 to remove some.");

				if (TwoOptiosValidation.check(input) == 1) {
					basket.remove(productId);
				} else {
					System.out.println("How many would you like to remove?");
					productQuantity = quantityValidation(input, productId, basket);
					if (productQuantity == basket.get(productId).getQuantity()) {
						basket.remove(productId);
					} else {
						basket.get(productId).setQuantity(basket.get(productId).getQuantity() - productQuantity);
					}
				}
			} else {
				basket.remove(productId);
			}

			ProductsList.cancelOrder(productId, productQuantity);

			System.out.println("Done");

		}

		System.out.println();

	}

	public static void addProduct(Scanner input) {

		ProductsList.showProductsList();

		System.out.println("Enter the ID of your chosen product: ");

		int productId = productValidation(input, ProductsList.getMap());

		System.out.printf("How many %s would you like?%n", ProductsList.getMap().get(productId).getName());

		int productQuantity = quantityValidation(input, productId, ProductsList.getMap());
		;

		if (basket.containsKey(productId)) {

			System.out.println();

			basket.get(productId).setQuantity(basket.get(productId).getQuantity() + productQuantity);

		} else {

			Product product = new Product();

			product.setId(productId);

			product.setName(ProductsList.getMap().get(productId).getName());

			product.setPrice(ProductsList.getMap().get(productId).getPrice());

			product.setQuantity(productQuantity);

			basket.put(productId, product);

		}

		ProductsList.proceedOrder(productId, productQuantity);

		System.out.printf("%d %s %s added to your basket.%n", productQuantity,
				ProductsList.getMap().get(productId).getName(), (productQuantity > 1 ? "were" : "has been"));

		System.out.println();

	}

	private static int quantityValidation(Scanner input, int productId, Map<Integer, Product> localMap) {

		int productQuantity = NumberValidation.check(input);

		if (localMap.get(productId).getQuantity() >= productQuantity) {
			return productQuantity;
		}

		System.out.printf("Sorry, not enough quantity of %s.%n", localMap.get(productId).getName());
		System.out.printf("How many %s would you like?%n", localMap.get(productId).getName());
		return quantityValidation(input, productId, localMap);
	}

	private static int productValidation(Scanner input, Map<Integer, Product> localMap) {

		int productAvailability = NumberValidation.check(input);

		for (Integer id : localMap.keySet()) {
			if (productAvailability == id && localMap.get(id).getQuantity() > 0) {
				return id;
			}
		}
		System.out.println("Unavailable product.");
		System.out.print("Enter the ID of your chosen product: ");
		return productValidation(input, localMap);
	}

}
