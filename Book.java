import java.io.Serializable;

public class Book implements Serializable{

	public String title;
	public String author;
	public String dateOfRelease;
	public boolean taken = true;
	public String dateToReturn = "";
	public User user = null;
	
	public Book (String title , String author , String dateOfRelease) {
		this.title = title;
		this.author = author;
		this.dateOfRelease = dateOfRelease;
	}
	
	public void addBook(Book book) {
		
	}
	
	public void deleteBook(Book book) {
		
	}
	
	public void takeBook(Book book , User user) {
		
	}
	
	public void returnBook(Book book) {
		
	}
	
	public void searchBook(String name) {
		
	}
	
	public void searchBook(String name , String author , String dateOfRelease) {
		
	}
	
	public void isAvailable(String name , String author , String dateOfRelease) {
		
	}
}
