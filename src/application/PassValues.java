package application;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PassValues {
	private static List<String> bookCartPublisher = new ArrayList<String>();
	private static List<String> bookCartTitle = new ArrayList<String>();
	private static List<String> bookCartCategory = new ArrayList<String>();
	private static List<Integer> bookCartPublicationYear = new ArrayList<Integer>();
	private static List<String> bookCartISBN = new ArrayList<String>();
	private static List<Double> bookCartSellingPrice = new ArrayList<Double>();
	private static List<ArrayList<String>> bookCartAuthers = new ArrayList<ArrayList<String>>();
	///////////////////////////////////////////////////////////////////////////////////////////
	private static List<String> Title = new ArrayList<String>();
	private static List<ArrayList<String>> Authers = new ArrayList<ArrayList<String>>();
	private static List<String> Publisher = new ArrayList<String>();
	private static List<Integer> PublicationYear = new ArrayList<Integer>();
	private static List<Double> SellingPrice = new ArrayList<Double>();
	private static List<String> ISBN = new ArrayList<String>();
	private static List<String> Category = new ArrayList<String>();
	private static List<Integer> AvaliableCopies = new ArrayList<Integer>();
	///////////////////////////////////////////////////////////////////////////////////////////
	private static List<Integer> orderId = new ArrayList<Integer>();
	private static List<String> orderDate = new ArrayList<String>();
	private static List<Integer> orderQuantity = new ArrayList<Integer>();
	private static List<String> orderISBN = new ArrayList<String>();
	///////////////////////////////////////////////////////////////////////////////////////////////
	private static String WhichBtn;// add to or delete from cart

	public static void setTitle(String Title_set) {// add true delete false
		Title.add(Title_set);
	}

	public static List<String> getTitle() {
		return Title;
	}

	public static void setAuthers(ArrayList<String> Auther_set) {
		Authers.add(Auther_set);

	}

	public static List<ArrayList<String>> getAuthers() {
		return Authers;
	}

	public static void setPublisher(String Publisher_set) {
		Publisher.add(Publisher_set);
	}

	public static List<String> getPublisher() {
		return Publisher;
	}

	public static void setPublicationYear(Object PublicationYear_set) {
		PublicationYear.add((Integer) PublicationYear_set);

	}

	public static List<Integer> getPublicationYear() {
		return PublicationYear;
	}

	public static void setISBN(String ISBN_set) {
		ISBN.add(ISBN_set);
	}

	public static List<String> getISBN() {
		return ISBN;
	}

	public static void setSellingPrice(Object SellingPrice_set) {
		SellingPrice.add((Double) SellingPrice_set);
	}

	public static List<Double> getSellingPrice() {
		return SellingPrice;
	}

	public static void setCategory(String Category_set) {
		Category.add(Category_set);

	}

	public static List<String> getCategory() {
		return Category;
	}

	public static void setAvailableCopies(int AvailableCopies_set) {
		AvaliableCopies.add(AvailableCopies_set);
	}

	public static List<Integer> getAvailableCopies() {
		return AvaliableCopies;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void setWhichBtb(String button_set) {
		WhichBtn = button_set;
	}

	public static String getWhichBtn() {
		return WhichBtn;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void setBookCartTitle(String bookCartTitle_set, boolean addRemove) {// add true delete false
		if (addRemove) {
			bookCartTitle.add(bookCartTitle_set);
		} else {
			bookCartTitle.remove(bookCartTitle_set);
		}
	}

	public static List<String> getBookCartTitele() {
		return bookCartTitle;
	}

	public static void setBookCartAuthers(ArrayList<String> bookCartAuther_set, boolean addRemove) {
		if (addRemove) {
			bookCartAuthers.add(bookCartAuther_set);
		} else {
			bookCartAuthers.remove(bookCartAuther_set);
		}
	}

	public static List<ArrayList<String>> getBookCartAuthers() {
		return bookCartAuthers;
	}

	public static void setBookCartPublisher(String bookCartPublisher_set, boolean addRemove) {
		if (addRemove) {
			bookCartPublisher.add(bookCartPublisher_set);
		} else {
			bookCartPublisher.remove(bookCartPublisher_set);
		}
	}

	public static List<String> getBookCartPublisher() {
		return bookCartPublisher;
	}

	public static void setBookCartPublicationYear(Object bookCartPublicationYear_set, boolean addRemove) {
		if (addRemove) {
			bookCartPublicationYear.add((Integer) bookCartPublicationYear_set);
		} else {
			bookCartPublicationYear.remove(bookCartPublicationYear_set);
		}
	}

	public static List<Integer> getBookCartPublicationYear() {
		return bookCartPublicationYear;
	}

	public static void setBookCartISBN(String bookCartISBN_set, boolean addRemove) {
		if (addRemove) {
			bookCartISBN.add(bookCartISBN_set);
		} else {
			bookCartISBN.remove(bookCartISBN_set);
		}
	}

	public static List<String> getBookCartISBN() {
		return bookCartISBN;
	}

	public static void setBookCartSellingPrice(Object bookCartSellingPrice_set, boolean addRemove) {
		if (addRemove) {
			bookCartSellingPrice.add((Double) bookCartSellingPrice_set);
		} else {
			bookCartSellingPrice.remove(bookCartSellingPrice_set);
		}
	}

	public static List<Double> getBookCarttSellingPrice() {
		return bookCartSellingPrice;
	}

	public static void setBookCartCategory(String bookCartCategory_set, boolean addRemove) {
		if (addRemove) {
			bookCartCategory.add(bookCartCategory_set);
		} else {
			bookCartCategory.remove(bookCartCategory_set);
		}
	}

	public static List<String> getBookCartCategory() {
		return bookCartCategory;
	}

	public static void clearAll() {
		bookCartPublisher.clear();
		bookCartTitle.clear();
		bookCartCategory.clear();
		bookCartPublicationYear.clear();
		bookCartISBN.clear();
		bookCartSellingPrice.clear();
		bookCartAuthers.clear();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static void setOrderID(int orderId_set) {
		orderId.add(orderId_set);

	}

	public static List<Integer> getOrderID() {
		return orderId;
	}

	public static void setOrderISBN(String orderISBN_set) {
		orderISBN.add(orderISBN_set);
	}

	public static List<String> getOrderISBN() {
		return orderISBN;
	}

	public static void setOrderDate(Timestamp orderDate_set) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		String string = dateFormat.format(orderDate_set);
		orderDate.add(string);

	}

	public static List<String> getOrderDate() {
		return orderDate;
	}

	public static void setOrderQuantity(int orderQuantity_set) {
		orderQuantity.add(orderQuantity_set);
	}

	public static List<Integer> getOrderQuantity() {
		return orderQuantity;
	}

}
