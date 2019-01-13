import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public interface AuthenticationService {
	
	
	Scanner sc = new Scanner(System.in);

	public static void register() throws IOException {
		
		FileOutputStream fos = new FileOutputStream("users.txt" , true);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		System.out.println("ENTER USERRNAME: ");
		String name = sc.next();
		System.out.println("ENTER PASSWORD");
		String password = sc.next();
		
		User user = new User(name , password);
		addUser(user , oos);
		
	}

	public static User login() throws ClassNotFoundException, IOException {
		FileInputStream fis =new FileInputStream("users.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);
		System.out.println("ENTER USERNAME: ");
		String username = sc.next();
		System.out.println("ENTER PASSWORD");
		String password = sc.next();
		while(true) {
			
			Object obj = ois.readObject(); 
			if(obj instanceof User) {
				User u = (User)obj;
				if(u.getName().equals(username) && u.getPassword().equals(password)) {
					System.out.println("Login successful");
					ois.close();
					return u;
				}
			}
			
		}
		
	}

	public static void addUser(User user , ObjectOutputStream oos) {
		try {
			oos.writeObject(user);
			oos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void closeService() {
		sc.close();
	}
}
