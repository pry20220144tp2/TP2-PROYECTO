package pe.edu.upc.serviceinterface;

import java.util.List;

import pe.edu.upc.entity.Exemplary;

public interface IExemplaryService {
	
	public void insert (Exemplary _exemplary);
	
	List <Exemplary> list();
}
