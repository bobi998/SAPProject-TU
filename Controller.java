import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Controller implements AuthenticationService,BookPersistancy,BookQuery {

	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		try {
			BookPersistancy.loadBooks();
		} catch (ClassNotFoundException e1) {
			
		} catch (IOException e) {
		}
		while(true) {
			
			StartMenu();
			int choice = sc.nextInt();
			if(choice ==1) {

				try {
					User u = AuthenticationService.login();
					if(u!=null) {
						LibMenu(u);
					}
				} catch (ClassNotFoundException | IOException e) {
					System.out.println("Invalid username/password !");
					e.printStackTrace();
					continue;
				}
			}
			if(choice == 2) {
				try {
					AuthenticationService.register();
				} catch (IOException e) {
					e.printStackTrace();
				}
				continue;
			}
			if(choice == 3) {
				try {
					BookPersistancy.saveBooks();
				} catch (IOException e) {
					e.printStackTrace();
				}
				sc.close();
				AuthenticationService.closeService();
				BookPersistancy.closeService();
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
				String title = sc.nextLine();
				title = sc.nextLine();
				try {
					Book temp = BookQuery.searchBook(title);
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
				String title = sc.nextLine();
				title = sc.nextLine();
				try {
					Book temp = BookQuery.searchBook(title);
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
					String title = sc.nextLine();
					title = sc.nextLine();
					try {
						Book temp = BookQuery.searchBook(title);
						temp.isAvailable();
					}
					catch(IOException | ClassNotFoundException e) {
						e.printStackTrace();
					}
					continue;
				}
				if(choice == 2) {
					System.out.println("Enter book title to search: ");
					String title = sc.nextLine();
					title = sc.nextLine();
					System.out.println("Enter book author: ");
					String author = sc.nextLine();
					sc.nextLine();
					System.out.println("Enter date of release(dd/MM/yyyy): ");
					String date = sc.next();
					try {
						Book temp = BookQuery.searchBook(title , author , date);
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
					BookPersistancy.addBook();
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
}
