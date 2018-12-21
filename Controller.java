import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Controller {

	static User u;
	
	public static void main(String[] args) throws IOException {
		
		
		
		FileOutputStream fos = new FileOutputStream("users.txt" , true);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		FileInputStream fis =new FileInputStream("users.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		Scanner sc = new Scanner(System.in);
		int choice;
		
		while(true) {
			
			StartMenu();
			choice = sc.nextInt();
			if(choice ==1) {
				if(login(ois)) {
					LibMenu();
				}
			}
			if(choice == 2) {
				register(oos);
				continue;
			}
			if(choice == 3) {
				break;
			}
		}

	}

	public static void StartMenu() {
		System.out.println(" ENTER 1 TO LOG IN: ");
		System.out.println(" ENTER 2 TO REGISTER USER: ");
		System.out.println(" ENTER 3 TO EXIT: ");
	}
	
	public static void LibMenu() {
		System.out.println("ENTER 1 TO TAKE BOOK: ");
		System.out.println("ENTER 2 TO RETURN BOOK: ");
		System.out.println("ENTER 3 TO SEARCH BOOK: ");
		System.out.println("ENTER 4 TO SEE BOOKS FOR RETURN: ");
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			int choice = sc.nextInt();
			if(choice == 1) {
				
			}
		}
		
	}
	
	public static boolean login(ObjectInputStream ois) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER USERNAME: ");
		String name = sc.next();
		System.out.println("ENTER PASSWORD");
		String password = sc.next();
		boolean log = false;
		try {
		log = login(name, password , ois);
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return log;
	}
	
	public static void register(ObjectOutputStream oos) {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("ENTER USERRNAME: ");
		String name = sc.next();
		System.out.println("ENTER PASSWORD");
		String password = sc.next();
		
		User user = new User(name , password);
		User.addUser(user , oos);
	}

	public static boolean login(String username , String password , ObjectInputStream ois) throws ClassNotFoundException, IOException {
		
		while(true) {
			Object obj = ois.readObject();
			
			
			if(obj instanceof User) {
				u = (User)obj;
				if(u.getName().equals(username)&&u.getPassword().equals(password)) {
					System.out.println("login success");
					return true;
				}
			}
			
		}
		
	}
}
