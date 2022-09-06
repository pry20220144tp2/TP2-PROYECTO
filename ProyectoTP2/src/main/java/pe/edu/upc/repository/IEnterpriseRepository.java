package pe.edu.upc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Enterprise;

@Repository
public interface IEnterpriseRepository extends JpaRepository<Enterprise, Integer> {
	@Query("select count(a.nameEnterprise) from Enterprise a where upper(a.nameEnterprise)=upper(:nameEnterprise)")
	public int searchEnterprise(@Param("nameEnterprise") String nombre);
	
	
	@Query("from Enterprise a where lower(a.nameEnterprise) like lower(concat('%', :parametro,'%'))")
	List<Enterprise> findBynameEnterprise(@Param("parametro")String nameEnterprise);
}
