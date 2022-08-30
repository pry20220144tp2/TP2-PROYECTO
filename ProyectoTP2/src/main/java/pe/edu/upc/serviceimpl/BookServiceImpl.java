package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Book;
import pe.edu.upc.repository.IBookRepository;
import pe.edu.upc.serviceinterface.IBookService;

@Service
public class BookServiceImpl implements Serializable, IBookService {
	private static final long serialVersionUID = 1L;

	/* Para que traiga el metodo que se va a utilizar */
	@Autowired
	private IBookRepository cR;

	@Override
	public List<Book> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public void insert(Book book) {
		cR.save(book);
	}
	@Override
	public void delete(int idBook) {
		cR.deleteById(idBook);
	}

	@Override
	public Optional<Book> searchId(int idBook) {
		return cR.findById(idBook);
	}
	
	@Override
	public List<Book> findNameBookFull(String nameBook) {
		return cR.findBynameBook(nameBook);
	}

	@Override
	public List<String[]> booktop() {
		return cR.BookTop();
	}
}
