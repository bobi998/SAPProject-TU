import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Book implements Serializable{

	public String title;
	public String author;
	public String dateOfRelease;
	public boolean taken = false;
	public String dateToReturn = "";
	public User user = null;
	
	public Book (String title , String author , String dateOfRelease) {
		this.title = title;
		this.author = author;
		this.dateOfRelease = dateOfRelease;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getAuthor() {
		return this.author;
	}
	
	public String getDateOfRelease() {
		return this.dateOfRelease;
	}
	
	public String dateToReturn() {
		return this.dateToReturn;
	}
	
	public void takeBook(User user) {
		this.taken = true;
		this.user = user;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, 1);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		this.dateToReturn = sdf.format(c.getTime());
	}
	
	public void returnBook() {
		this.taken = false;
		this.user = null;
		this.dateToReturn = "";
	}
	
	
	public void isAvailable() {
		
		if(this.taken == false) {
			System.out.println("Book " + this.title + ", " + this.author + "is available.");
		}
		else {
			System.out.println("Book " + this.title + ", " + this.author + "is taken.");
		}
	}
}
