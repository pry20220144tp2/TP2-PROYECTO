package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.LoanDetails;


public interface ILoanDetailsService {
	public void insert (LoanDetails lD);
	List <LoanDetails> list();
	
}
