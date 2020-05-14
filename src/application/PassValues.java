package application;

import java.util.ArrayList;
import java.util.List;

public class PassValues {
	private static List<String> bookCartPublisher = new ArrayList<String>();
	private static List<String> bookCartTitle = new ArrayList<String>();
	private static List<String> bookCartCategory = new ArrayList<String>();
	private static List<Integer> bookCartPublicationYear = new ArrayList<Integer>();
	private static List<Integer> bookCartISBN = new ArrayList<Integer>();
	private static List<Double> bookCartSellingPrice = new ArrayList<Double>();
	private static List<ArrayList<String>> bookCartAuthers = new ArrayList<ArrayList<String>>();
	private static List<String> Title = new ArrayList<String>();
	private static List<ArrayList<String>> Authers = new ArrayList<ArrayList<String>>();
	private static List<String> Publisher = new ArrayList<String>();
	private static List<Integer> PublicationYear = new ArrayList<Integer>();
	private static List<Double> SellingPrice = new ArrayList<Double>();
	private static List<Integer> ISBN = new ArrayList<Integer>();
	private static List<String> Category = new ArrayList<String>();
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

	public static void setISBN(Object ISBN_set) {
		ISBN.add((Integer) ISBN_set);
	}

	public static List<Integer> getISBN() {
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

//////////////////////////////////////////////////////
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
}
