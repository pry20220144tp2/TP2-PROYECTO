package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Loan")
public class Loan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLoan;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "loanDate", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date loanDate;

	
	@Column(name = "devLoan", nullable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Future(message = "La fecha debe ser futura")
	private Date devLoan;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "idLoan", nullable = true)
	private List<LoanDetails> loanDetails;
	
	/**/
	@ManyToOne
	@JoinColumn(name = "idAccount")
	private Account account;
	
	
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	@PrePersist
    public void prePersist() {
        this.loanDate= new Date();
    }
	public Loan() {
		super();
	
	}

	

	public Loan(int idLoan, Date loanDate, @Future(message = "La fecha debe estar en el futuro") Date devLoan,
			List<LoanDetails> loanDetails) {
		super();
		this.idLoan = idLoan;
		this.loanDate = loanDate;
		this.devLoan = devLoan;
		this.loanDetails = loanDetails;
		this.account=new Account();
	}



	public int getIdLoan() {
		return idLoan;
	}

	public void addDetailImportation(LoanDetails item) {
		this.loanDetails.add(item);
	}

	public void setIdLoan(int idLoan) {
		this.idLoan = idLoan;
	}

	public Date getLoanDate() {
		return loanDate;
	}

	public void setLoanDate(Date loanDate) {
		this.loanDate = loanDate;
	}

	public Date getDevLoan() {
		return devLoan;
	}

	public void setDevLoan(Date devLoan) {
		this.devLoan = devLoan;
	}

	public List<LoanDetails> getLoanDetails() {
		return loanDetails;
	}

	public void setLoanDetails(List<LoanDetails> loanDetails) {
		this.loanDetails = loanDetails;
	}
	
	public int getTotal() {
		return loanDetails.stream().collect(Collectors.summingInt(LoanDetails::QuantitySubTotal));
	}

}
