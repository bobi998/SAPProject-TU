import java.io.IOException;
import java.util.ArrayList;

public interface BookQuery {
	
	public static ArrayList<Book> books = BookPersistancy.books;

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
