package application;

import java.util.ArrayList;
import java.util.List;

public class PassValues {
	private static List<String> bookCartPublisher = new ArrayList<String>();
	private static List<String> bookCartTitle = new ArrayList<String>();
	private static List<String> bookCartCategory = new ArrayList<String>();
	private static List<Integer> bookCartPublicationYear = new ArrayList<Integer>();
	private static List<Integer> bookCartISBN = new ArrayList<Integer>();
	private static List<Integer> bookCartSellingPrice = new ArrayList<Integer>();
	private static List<ArrayList<String>> bookCartAuthers = new ArrayList<ArrayList<String>>();
	private static String Title = "";
	private static List<String> Auther = new ArrayList<String>();
	private static String Publisher = "";
	static int PublicationYear = 0;
	static int SellingPrice = 0;
	private static String Category;
	private static String WhichBtn;// false add true delete

	public static void setTitle(String Title_set) {
		Title = Title_set;
	}

	public static String getTitele() {
		return Title;
	}

	public static void setAuthers(String Auther_set) {
		Auther.add(Auther_set);
	}

	public static List<String> getAuthers() {
		return Auther;
	}

	public static void setPublisher(String Publisher_set) {
		Publisher = Publisher_set;
	}

	public static String getPublisher() {
		return Publisher;
	}

	public static void setPublicationYear(int PublicationYear_set) {
		PublicationYear = PublicationYear_set;
	}

	public static int getPublicationYear() {
		return PublicationYear;
	}

	public static void setSellingPrice(int SellingPrice_set) {
		SellingPrice = SellingPrice_set;
	}

	public static int getSellingPrice() {
		return SellingPrice;
	}

	public static void setCategory(String Category_set) {
		Category = Category_set;
	}

	public static String getCategory() {
		return Category;
	}

	public static void setWhichBtb(String button_set) {
		WhichBtn = button_set;
	}

	public static String getWhichBtn() {
		return WhichBtn;
	}

	////////////////////////////////////////////////////////
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

	public static void setBookCartISBN(Object bookCartISBN_set, boolean addRemove) {
		if (addRemove) {
			bookCartISBN.add((Integer) bookCartISBN_set);
		} else {
			bookCartISBN.remove(bookCartISBN_set);
		}
	}

	public static List<Integer> getBookCartISBN() {
		return bookCartISBN;
	}

	public static void setBookCartSellingPrice(Object bookCartSellingPrice_set, boolean addRemove) {
		if (addRemove) {
			bookCartSellingPrice.add((Integer) bookCartSellingPrice_set);
		} else {
			bookCartSellingPrice.remove(bookCartSellingPrice_set);
		}
	}

	public static List<Integer> getBookCarttSellingPrice() {
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
}
