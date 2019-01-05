import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller {

	public static ArrayList<Book> books = new ArrayList<Book>();
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int choice;
		
		try {
			loadBooks();
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		while(true) {
			
			StartMenu();
			choice = sc.nextInt();
			if(choice ==1) {

				try {
					User u = login();
					if(u!=null) {
						LibMenu(u);
					}
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Invalid username/password !");
					continue;
				}
			}
			if(choice == 2) {
				try {
					register();
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(choice == 3) {
				try {
					saveBooks();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}

	}

	public static void StartMenu() {
		System.out.println(" ENTER 1 TO LOG IN: ");
		System.out.println(" ENTER 2 TO REGISTER USER: ");
		System.out.println(" ENTER 3 TO EXIT: ");
	}
	
	public static void LibMenu(User u) {
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("ENTER 1 TO TAKE BOOK: ");
			System.out.println("ENTER 2 TO RETURN BOOK: ");
			System.out.println("ENTER 3 TO SEARCH BOOK: ");
			System.out.println("ENTER 4 TO SEE BOOKS FOR RETURN: ");
			System.out.println("ENTER 5 TO ADD BOOK TO THE LIBRARY: ");
			System.out.println("ENTER 6 TO RETURN TO START MENU: ");
			int choice = sc.nextInt();
			if(choice == 1) {
				System.out.println("Enter title of book: ");
				String title = sc.next();
				try {
					Book temp = searchBook(title);
					temp.takeBook(u);
					u.addBook(temp);
				}
				catch(IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(choice == 2) {
				System.out.println("Enter title of book to return: ");
				String title = sc.next();
				try {
					Book temp = searchBook(title);
					temp.returnBook();
					u.removeBook(temp);
					System.out.println("Book successfuly returned!");
				}
				catch(IOException | ClassNotFoundException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(choice == 3) {
				System.out.println("ENTER 1 TO SEARCH BY TITLE: ");
				System.out.println("ENTER 2 TO SEARCH TITLE , AUTHOR AND DATE OF RELEASE: ");
				choice = sc.nextInt();
				if(choice == 1) {
					System.out.println("Enter book title to search: ");
					String title = sc.next();
					try {
						Book temp = searchBook(title);
						temp.isAvailable();
					}
					catch(IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
					continue;
				}
				if(choice == 2) {
					System.out.println("Enter book title to search: ");
					String title = sc.next();
					System.out.println("Enter book author: ");
					String author = sc.next();
					System.out.println("Enter date of release(dd/MM/yyyy): ");
					String date = sc.next();
					try {
						Book temp = searchBook(title , author , date);
						temp.isAvailable();
					}
					catch(IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
				}
				continue;
			}
			if(choice == 4) {
				ArrayList<Book> b = u.bookForReturn();
				if(b == null) {
					System.out.println("There are no books to return at that moment.");
				}
				else {
					for(Book book : b) {
						System.out.println(book.getTitle() + " " + book.getAuthor());
					}
				}
				continue;
			}
			if(choice == 5) {
				try {
					addBook();
				} catch (ClassNotFoundException | IOException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(choice == 6) {
				return;
			}
		}
		
	}
	
	
	
	public static void register() throws IOException {
		
		FileOutputStream fos = new FileOutputStream("users.txt" , true);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER USERRNAME: ");
		String name = sc.next();
		System.out.println("ENTER PASSWORD");
		String password = sc.next();
		
		User user = new User(name , password);
		User.addUser(user , oos);
	}

	public static User login() throws ClassNotFoundException, IOException {
		FileInputStream fis =new FileInputStream("users.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER USERNAME: ");
		String username = sc.next();
		System.out.println("ENTER PASSWORD");
		String password = sc.next();
		while(true) {
			
			Object obj = ois.readObject();
			
			
			if(obj instanceof User) {
				User u = (User)obj;
				if(u.getName().equals(username)&&u.getPassword().equals(password)) {
					System.out.println("Login successful");
					return u;
				}
			}
			
		}
		
	}
	
	public static void addBook() throws ClassNotFoundException, IOException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter book title: ");
		String title = sc.next();
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
	}
	
	public static Book searchBook(String title , String author , String dateOfRelease) throws IOException, ClassNotFoundException {
		
		int i = 0;
		
		while( books.size() > i) {
			
				if(books.get(i).getTitle().equals(title) && books.get(i).getAuthor().equals(author) && books.get(i).getDateOfRelease().equals(dateOfRelease)) {
					Book temp = books.get(i);
					return temp;
				}
				i++;
		}
		return null;
	}
	
	public static Book searchBook(String title) throws IOException, ClassNotFoundException {
		
		int i = 0;

		while( books.size() > i) {
			
				if(books.get(i).getTitle().equals(title)) {
					Book temp = books.get(i);
					return temp;
				}
				i++;
		}
		return null;
		
	}
}
