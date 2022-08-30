package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import pe.edu.upc.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Integer> {
	@Query("select count(a.nameBook) from Book a where upper(a.nameBook)=upper(:nameBook)")
	public int searchBook(@Param("nameBook") String nombre);
	
	@Query("from Book a where lower(a.nameBook) like lower(concat('%', :parametro,'%')) ")
	List<Book> findBynameBook(@Param("parametro")String nameBook);
	
	@Query(value = "SELECT b.name_book,sum(ide.quantity_books) from public.loan l join loan_details ide on ide.id_loan = l.id_loan join exemplary ex on ide.id_exemplary = ex.id_exemplary join book b on b.id_book= ex.id_book group by b.name_book ORDER BY COUNT(ide.id_exemplary) DESC ",
			nativeQuery = true)
	public List<String[]> BookTop();
}
