package pe.edu.upc.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Account;
import pe.edu.upc.entity.Book;
import pe.edu.upc.entity.Loan;

@Repository
public interface ILoanRepository extends JpaRepository <Loan, Integer>{
	
	@Query("select i from Loan i join fetch i.loanDetails ide join fetch ide.exemplary where i.idLoan=?1")
	Optional<Loan> fetchByImportIdWhithImportDetailsWithProduct(int id);
	
	//@Query("from Loan i where i.account=:parametro")
	@Query(value="SELECT id_loan,dev_loan,loan_date,id_account FROM public.loan where id_account=:parametro",nativeQuery=true)
	List<Loan> findByUser(@Param("parametro")int idAccount);
	
}

