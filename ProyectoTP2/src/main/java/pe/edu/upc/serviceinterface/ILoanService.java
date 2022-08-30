package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Loan;

public interface ILoanService {
	public void insert (Loan l);
	List <Loan> list();
	
	Loan listarId(int idLoan);

    Optional<Loan> fetchByLoanIdWithLoanDetailsWithBooks(int idLoan);
    
    List<Loan> listarIdUsuario(int idUsuario);
}
