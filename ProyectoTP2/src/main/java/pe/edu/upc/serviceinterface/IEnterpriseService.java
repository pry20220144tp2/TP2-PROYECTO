package pe.edu.upc.serviceinterface;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.entity.Enterprise;

public interface IEnterpriseService {

	public int insert(Enterprise enterprise);

	List<Enterprise> list();

	public void delete(int idEnterprise);// Eliminar

	Optional<Enterprise> searchId(int idEnterprise);// Modificar

	List<Enterprise> findNameEnterpriseFull(String nameEnterprise);// Buscar
	
}
