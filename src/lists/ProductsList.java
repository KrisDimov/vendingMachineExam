package lists;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import models.Product;

public class ProductsList {

	private ProductsList() {

	}

	private static String source = "src/source/Products.list";

	private static Map<Integer, Product> productsList = new HashMap<>();
	
	public static Map<Integer, Product> getMap() {
		return productsList;
	}
	
	public static void cancelOrder(int key, int quantity) {
		
		productsList.get(key).setQuantity(productsList.get(key).getQuantity() + quantity);
		
	}
	
	public static void proceedOrder(int key, int quantity) {
		
			productsList.get(key).setQuantity(productsList.get(key).getQuantity() - quantity);
			
	}
	
	public static void showProductsList() {

		int length = 0;

		for (int i = 1; i <= productsList.size(); i++) {
			if (productsList.get(i).getName().length() > length) {
				length = productsList.get(i).getName().length();
			}
		}

		StringBuilder sb = new StringBuilder();

		for (int i = 1; i <= productsList.size(); i++) {

			if (productsList.get(i).getQuantity() < 1) {
				continue;
			}
			
			if (productsList.get(i).getName().length() < length) {
				for (int k = 0; k < length - (productsList.get(i).getName().length()); k++) {
					sb.append(" ");
				}
			}

			System.out.printf("%2d | %s%s | $ %.2f | %2d pcs%n", productsList.get(i).getId(), productsList.get(i).getName(),
					sb.toString(), productsList.get(i).getPrice(), productsList.get(i).getQuantity());

			sb.setLength(0);
		}

		System.out.println();
		
	}

	public static void loadProductsList() {

		try {

			Scanner input = new Scanner(new File(source));

			String line;

			while (input.hasNextLine()) {

				line = input.nextLine();

				String[] arr = line.split(";");

				Product product = new Product();

				product.setId(Integer.parseInt(arr[0]));

				product.setName(arr[1]);

				product.setPrice(Float.parseFloat(arr[2]));

				product.setQuantity(Integer.parseInt(arr[3]));

				productsList.put(product.getId(), product);

			}
			
			input.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
