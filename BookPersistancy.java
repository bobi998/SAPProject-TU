import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public interface BookPersistancy {
	
	public static ArrayList<Book> books = new ArrayList<Book>();
	Scanner sc = new Scanner(System.in);
	
	public static void addBook() throws ClassNotFoundException, IOException {

		System.out.println("Enter book title: ");
		String title = sc.nextLine();
		sc.nextLine();
		System.out.println("Enter book author: ");
		String author = sc.nextLine();
		System.out.println("Enter book date of release (dd/MM/yyyy format): ");
		String dateOfRelease = sc.next();
		Book temp = new Book (title , author , dateOfRelease);
		books.add(temp);
		System.out.println("Book successfuly added!");
		
	}
	
	public static void loadBooks() throws IOException, ClassNotFoundException {
		
		if(Files.exists(Paths.get("books.txt"))) {
		FileInputStream fis =new FileInputStream("books.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		while(true) {
			Object obj = ois.readObject();
			if(obj instanceof Book) {
				books.add((Book)obj);
			}
			else if(obj == null) {
				ois.close();
				break;
			}
		}
		}
		else {
			return;
		}
	}
	
	public static void saveBooks() throws IOException {
		FileOutputStream fos = new FileOutputStream("books.txt");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		int i = 0;
		while(books.size() > i) {
			
			oos.writeObject(books.get(i));
			i++;
		}
		oos.close();
	}
	
	public static void closeService() {
		sc.close();
	}
}
