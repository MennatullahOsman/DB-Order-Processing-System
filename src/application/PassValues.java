package application;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PassValues {

	private static Queue<Integer> cartBooks = new LinkedList<Integer>();
	private static String Title = "";
	private static List<String> Authers = new ArrayList<String>();
	private static String Publisher = "";
	static int PublicationYear = 0;
	static int SellingPrice = 0;
	private static String Category;

	public static void setTitle(String Title_set) {
		Title = Title_set;
	}

	public static String getTitele() {
		return Title;
	}

	public static void setAuthers(String Auther) {
		Authers.add(Auther);
	}

	public static List<String> getAuthers() {
		return Authers;
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

	public static void setCartBooks(int cartBooks_set) {
		cartBooks.add(cartBooks_set);
	}

	public static Queue<Integer> getCartBooks() {
		return cartBooks;
	}

	public static void setCategory(String Category_set) {
		Category = Category_set;
	}

	public static String getCategory() {
		return Category;
	}
}
