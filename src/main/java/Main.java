
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor.DiscardOldestPolicy;

import service.DiscountProcess;
import vo.Book;

public class Main {

	public static void main(String[] args) {
		List<Book> listBookToBuy = new ArrayList<Book>();
		listBookToBuy.add(new Book(1,2));
		listBookToBuy.add(new Book(2,2));
		listBookToBuy.add(new Book(3,2));
		listBookToBuy.add(new Book(4,1));
		listBookToBuy.add(new Book(5,1));
		
		DiscountProcess discountProcess = new DiscountProcess(listBookToBuy);
		double totalPrice = discountProcess.start();
		System.out.println("Total price to pay is :"+totalPrice);
	}

}
