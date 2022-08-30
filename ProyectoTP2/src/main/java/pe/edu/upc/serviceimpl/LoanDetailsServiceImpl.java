package pe.edu.upc.serviceimpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.LoanDetails;
import pe.edu.upc.repository.ILoanDetailsRepository;
import pe.edu.upc.serviceinterface.ILoanDetailsService;

@Service
public class LoanDetailsServiceImpl implements ILoanDetailsService{

	@Autowired
	private ILoanDetailsRepository ideR;
	
	@Override
	public void insert(LoanDetails lD) {
		ideR.save(lD);
		
	}

	@Override
	public List<LoanDetails> list() {
		// TODO Auto-generated method stub
		return ideR.findAll();
	}

}
