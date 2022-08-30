package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Author;

@Repository
public interface IAuthorRepository extends JpaRepository<Author, Integer> {
	@Query("select count(a.nameAuthor) from Author a where upper(a.nameAuthor)=upper(:nameAuthor) and upper(a.lastnameAuthor)=upper(:lastnameAuthor)")
	public int searchAuthor(@Param("nameAuthor") String nombre, @Param("lastnameAuthor") String apellido);
	
	
	@Query("from Author a where lower(a.nameAuthor) like lower(concat('%', :parametro,'%')) or lower(a.lastnameAuthor) like lower(concat('%', :parametro,'%'))")
	List<Author> findBynameAuthor(@Param("parametro")String nameAuthor);
	
	
	@Query(value = "SELECT a.name_author,sum(ide.quantity_books) from public.loan l join loan_details ide on  ide.id_loan = l.id_loan join exemplary ex on ide.id_exemplary = ex.id_exemplary join book b on b.id_book= ex.id_book join autor a on a.id_author = b.id_author group by a.name_author ORDER BY COUNT(ide.id_exemplary) DESC",
			nativeQuery = true)
	public List<String[]> AuthorTop();
	
	@Query(value = "SELECT CONCAT(a.name_author, ' ', a.lastname_author),count(ide.name_book) from public.autor a join book ide on  ide.id_author = a.id_author group by a.name_author, a.lastname_author ORDER BY COUNT(ide.name_book) DESC",
			nativeQuery = true)
	public List<String[]> AuthorBookTop();
}
