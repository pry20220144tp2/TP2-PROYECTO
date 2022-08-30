package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Book;

public interface IBookService {
	
	public void insert(Book book);

	List<Book> list();
	
	public void delete(int idBook);// Eliminar

	Optional<Book> searchId(int idBook);// Modificar

	List<Book> findNameBookFull(String nameBook);// Buscar
	
	public List<String[]> booktop();
}
