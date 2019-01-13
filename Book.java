import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Book implements Serializable{

	
	private static final long serialVersionUID = 1L;
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
		System.out.println("Book taken!");
	}
	
	public void returnBook() {
		this.taken = false;
		this.user = null;
		this.dateToReturn = "";
		System.out.println("Book returned!");
	}
	
	
	public void isAvailable() {
		
		if(this.taken == false) {
			System.out.println("Book " + this.title + " by " + this.author + " is available.");
		}
		else {
			System.out.println("Book " + this.title + " by " + this.author + " is taken by " + user.getName());
		}
	}
}
