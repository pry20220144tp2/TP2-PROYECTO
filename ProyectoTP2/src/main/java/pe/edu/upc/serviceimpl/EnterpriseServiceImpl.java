package pe.edu.upc.serviceimpl;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.upc.entity.Enterprise;
import pe.edu.upc.repository.IEnterpriseRepository;
import pe.edu.upc.serviceinterface.IEnterpriseService;

@Service
public class EnterpriseServiceImpl implements Serializable, IEnterpriseService {

	private static final long serialVersionUID = 1L;
	/* Para que traiga el metodo que se va a utilizar */

	@Autowired
	private IEnterpriseRepository cR;

	@Override
	public List<Enterprise> list() {
		// TODO Auto-generated method stub
		return cR.findAll();
	}

	@Override
	public int insert(Enterprise enterprise) {
		int rpta = cR.searchEnterprise(enterprise.getNameEnterprise(), enterprise.getRucEnterprise());
		if (rpta == 0) {
			cR.save(enterprise);
		}
		return rpta;
	}

	@Override
	public void delete(int idEnterprise) {
		cR.deleteById(idEnterprise);
	}

	@Override
	public Optional<Enterprise> searchId(int idEnterprise) {
		return cR.findById(idEnterprise);
	}

	@Override
	public List<Enterprise> findNameEnterpriseFull(String nameEnterprise) {
		return cR.findBynameEnterprise(nameEnterprise);
	}
}
