package pe.edu.upc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.entity.LoanDetails;

@Repository
public interface ILoanDetailsRepository extends JpaRepository <LoanDetails, Integer> {

}
