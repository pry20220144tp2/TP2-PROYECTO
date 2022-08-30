package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entity.Exemplary;

@Repository
public interface IExemplaryRepository extends JpaRepository <Exemplary, Integer>{
	
}

