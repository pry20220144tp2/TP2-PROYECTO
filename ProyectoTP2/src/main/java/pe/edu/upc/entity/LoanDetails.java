package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "LoanDetails")
public class LoanDetails implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLoandetails;

	@Positive(message = "Solo numeros positivos.")
	@NotNull(message = "La cantidad es obligatoria")
	private int quantityBooks;

	@Column(name = "observation", nullable = false, length = 45)
	private String observation;

	@ManyToOne
	@JoinColumn(name = "idExemplary", nullable = false)
	private Exemplary exemplary;

	@ManyToOne
	@JoinColumn(name = "idAccount", nullable = false)
	private Account account;

	@ManyToOne
	@JoinColumn(name = "idLoan", nullable = false)
	private Loan loan;

	public LoanDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoanDetails(int idLoandetails,
			@Positive(message = "Solo numeros positivos.") @NotNull(message = "La cantidad es obligatoria") int quantityBooks,
			String observation, Exemplary exemplary, Account account, Loan loan) {
		super();
		this.idLoandetails = idLoandetails;
		this.quantityBooks = quantityBooks;
		this.observation = observation;
		this.exemplary = exemplary;
		this.account = account;
		this.loan = loan;
	}

	public int getIdLoandetails() {
		return idLoandetails;
	}

	public void setIdLoandetails(int idLoandetails) {
		this.idLoandetails = idLoandetails;
	}

	public int getQuantityBooks() {
		return quantityBooks;
	}

	public void setQuantityBooks(int quantityBooks) {
		this.quantityBooks = quantityBooks;
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}

	public Exemplary getExemplary() {
		return exemplary;
	}

	public void setExemplary(Exemplary exemplary) {
		this.exemplary = exemplary;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}
	
	public int QuantitySubTotal() {
		return quantityBooks;
	}
}
