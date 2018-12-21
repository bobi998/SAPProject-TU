import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable{

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
	public static void addUser(User user , ObjectOutputStream oos) {
		try {
			oos.writeObject(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void addBook(Book book) {
		
	}
	
	public void bookForReturn() {
		
	}
	

}
