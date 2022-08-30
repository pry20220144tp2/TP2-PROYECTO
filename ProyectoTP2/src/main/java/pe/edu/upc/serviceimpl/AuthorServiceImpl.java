package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Author;
import pe.edu.upc.repository.IAuthorRepository;
import pe.edu.upc.serviceinterface.IAuthorService;

@Service
public class AuthorServiceImpl implements Serializable, IAuthorService {

	private static final long serialVersionUID = 1L;
	/* Para que traiga el metodo que se va a utilizar */

	@Autowired
	private IAuthorRepository cR;

	@Override
	public List<Author> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public int insert(Author author) {
		int rpta = cR.searchAuthor(author.getNameAuthor(), author.getLastnameAuthor());
		if (rpta == 0) {
			cR.save(author);
		}
		return rpta;
	}

	@Override
	public void delete(int idAuthor) {
		cR.deleteById(idAuthor);
	}

	@Override
	public Optional<Author> searchId(int idAuthor) {
		return cR.findById(idAuthor);
	}

	@Override
	public List<Author> findNameAuthorFull(String nameAuthor) {
		return cR.findBynameAuthor(nameAuthor);
	}

	@Override
	public List<String[]> authortop() {
		// TODO Auto-generated method stub
		return cR.AuthorTop();
	}
	
	@Override
	public List<String[]> authorbooktop() {
		// TODO Auto-generated method stub
		return cR.AuthorBookTop();
	}
}
