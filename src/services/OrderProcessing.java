package services;

public class OrderProcessing {

	private OrderProcessing() {
		
	}

	public static void placeOrder() {
		
		if (!Basket.getBasket().isEmpty()) {
			
			if (Bank.insertedAmount() < (int)(Basket.getBasketAmount()*100)) {
				System.out.println("Please insert more coins.");
			} else {
				
				Bank.proceedPayment();
				
				Bank.change();
				
				Basket.clearBasket();
				
				Bank.clearPayment();
				
			}
			
		} else {
			
			System.out.println("No products in you basket yet");
			
		}
		
		System.out.println();
		
	}
	
}
