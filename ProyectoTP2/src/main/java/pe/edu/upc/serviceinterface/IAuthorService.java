package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Author;

public interface IAuthorService {

	public int insert(Author author);

	List<Author> list();

	public void delete(int idAuthor);// Eliminar

	Optional<Author> searchId(int idAuthor);// Modificar

	List<Author> findNameAuthorFull(String nameAuthor);// Buscar
	
	
	public List<String[]> authortop();
	
	public List<String[]> authorbooktop();
}
