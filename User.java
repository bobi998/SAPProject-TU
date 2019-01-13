import java.io.Serializable;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class User implements Serializable{

	
	private static final long serialVersionUID = 1L;
	public String name;
	public String password;
	public ArrayList<Book> books = new ArrayList<Book>();
	
	public User(String name , String password) {
		this.name = name;
		this.password = password;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void addBook(Book book) {
		this.books.add(book);
	}
	
	public ArrayList<Book> bookForReturn() {
		ArrayList<Book> booksToReturn = new ArrayList<Book>();
		int i = 0;
		Calendar c = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String currdate = sdf.format(c.getTime());
		Date d1 = sdf.parse(currdate, new ParsePosition(0));
		while(this.books.size() > i) {
			Date d2 = sdf.parse(this.books.get(i).dateToReturn, new ParsePosition(0));
			if(d1.compareTo(d2) > 0) {
				booksToReturn.add(this.books.get(i));
			}
			i++;
		}
		return booksToReturn;
	}
	
	public void removeBook(Book book) {
		
		if(this.books.remove(book)) {
			System.out.println("Book removed successfuly");
		}
	}
}
