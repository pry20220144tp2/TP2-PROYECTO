package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Exemplary;
import pe.edu.upc.repository.IExemplaryRepository;
import pe.edu.upc.serviceinterface.IExemplaryService;

@Service
public class ExemplaryServiceImpl implements Serializable, IExemplaryService {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IExemplaryRepository cR;

	@Override
	public void insert(Exemplary _exemplary) {
		// TODO Auto-generated method stub
		/*
		 * int contador =_exemplary.getCountExemplary(); do{
		 * 
		 * contador--; }while (contador>0);
		 */
		cR.save(_exemplary);
	}

	@Override
	public List<Exemplary> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}
}
