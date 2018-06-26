package service;

import java.util.List;

import vo.Book;

public class DiscountProcess {

	private static List<Book> listBookToBuy;

	public DiscountProcess(List<Book> listBookToBuy) {
		this.listBookToBuy = listBookToBuy;
	}

	public enum DISCOUNT_TYPE {
		PERCENT_25, PERCENT_20, PERCENT_10, PERCENT_5, PERCENT_NO_DISCOUNT
	}
	
	private static void removeCalculatedBook(int counter) {
		//for(Book element : listBookToBuy) {
		Book element;
		int countDeleteTime = 0;
		for(int i=0; i<listBookToBuy.size(); i++)
		{
			element = listBookToBuy.get(i);
			if(element.getAmount()>0)
			{
				listBookToBuy.get(i).setAmount(element.getAmount()-1);
				countDeleteTime++;
				
				if(countDeleteTime == counter)
					break;
			}
		}
	}

	private static void reviseBookAmountBaseOnDiscountType(DISCOUNT_TYPE discountType) {
		switch (discountType) {
			case PERCENT_25:
				removeCalculatedBook(5);
			
				break;
			case PERCENT_20:
				removeCalculatedBook(4);
				break;
			case PERCENT_10:
				removeCalculatedBook(3);
				break;
			case PERCENT_5:
				removeCalculatedBook(2);
				break;
			case PERCENT_NO_DISCOUNT:
				removeCalculatedBook(1);
				break;
		}
	}

	private static double calPriceBaseOnDiscountType(DISCOUNT_TYPE discountType) {
		double priceForCurrentLoop = 0.0;
		switch (discountType) {
			case PERCENT_25:
				priceForCurrentLoop = 30.00; //5 x (8 - 2.00) == 5 x 6.00 == 30.00
				break;
			case PERCENT_20:
				priceForCurrentLoop = 25.60; //4 x (8 - 1.60) == 4 x 6.40 == 25.60
				break;
			case PERCENT_10:
				priceForCurrentLoop = 21.60; //3 x (8 - 0.80) == 3 x 7.20 == 21.60
				break;
			case PERCENT_5:
				priceForCurrentLoop = 6.40; //2 x (8 - 0.40) == 2 x 3.20 == 6.40
				break;
			case PERCENT_NO_DISCOUNT:
				priceForCurrentLoop = 8.00; //1 x 8.00 == 8.00
				break;
		}
		return priceForCurrentLoop;
	}

	private static DISCOUNT_TYPE getPossibleDiscountTypeBaseOnBookAmount() {
		int hasBook1 = 0, hasBook2 = 0, hasBook3 = 0, hasBook4 = 0, hasBook5 = 0;
		for (Book book : listBookToBuy) {
			if (book.getId() == 1 && book.getAmount()>0) {
				hasBook1 = 1;
				continue;
			}
			if (book.getId() == 2  && book.getAmount()>0) {
				hasBook2 = 1;
				continue;
			}
			if (book.getId() == 3  && book.getAmount()>0) {
				hasBook3 = 1;
				continue;
			}
			if (book.getId() == 4  && book.getAmount()>0) {
				hasBook4 = 1;
				continue;
			}
			if (book.getId() == 5  && book.getAmount()>0) {
				hasBook5 = 1;
				continue;
			}

		}

		DISCOUNT_TYPE disCountType = DISCOUNT_TYPE.PERCENT_NO_DISCOUNT;
		// case 25%
		if (hasBook1 + hasBook2 + hasBook3 + hasBook4 + hasBook5 == 5)
			disCountType = DISCOUNT_TYPE.PERCENT_25;

		// case 20%
		else if (hasBook1 + hasBook2 + hasBook3 + hasBook4 + hasBook5 == 4)
			disCountType = DISCOUNT_TYPE.PERCENT_20;

		// case 10%
		else if (hasBook1 + hasBook2 + hasBook3 + hasBook4 + hasBook5 == 3)
			disCountType = DISCOUNT_TYPE.PERCENT_10;

		// case 5%
		else if (hasBook1 + hasBook2 + hasBook3 + hasBook4 + hasBook5 == 2)
			disCountType = DISCOUNT_TYPE.PERCENT_5;

		return disCountType;

	}

	private static boolean isRemainNoCalculateBook() {
		for(Book book : listBookToBuy) {
			if(book.getAmount()>0) {
				return true;
			}
		}
		return false;
	}

	public static double start(){
		double totalPriceToPay = 0.0;
		while (isRemainNoCalculateBook()) {
			DISCOUNT_TYPE possibleDiscountType = getPossibleDiscountTypeBaseOnBookAmount();
			totalPriceToPay += calPriceBaseOnDiscountType(possibleDiscountType);
			reviseBookAmountBaseOnDiscountType(possibleDiscountType);
		}

		return totalPriceToPay;
	}

}
