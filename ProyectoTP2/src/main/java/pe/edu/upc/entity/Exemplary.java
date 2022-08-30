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
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table (name="Exemplary")
public class Exemplary  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idExemplary;
	
	
	@ManyToOne
	@JoinColumn(name = "idBook", nullable = false)
	private Book exemplaryBook;
	
	@Positive(message = "Solo numeros positivos.")
	@NotNull(message="La cantidad es obligatoria")
	@Column (name="countExemplary", nullable=false, length=45)
	private int countExemplary;
	
	
	@Past(message = "La fecha debe ser pasada")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfExemplary;
	
	public Exemplary() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Exemplary(int idExemplary, Book exemplaryBook,
			@Positive(message = "Solo numeros positivos.") @NotNull(message = "La cantidad es obligatoria") int countExemplary,
			Date dateOfExemplary) {
		super();
		this.idExemplary = idExemplary;
		this.exemplaryBook = exemplaryBook;
		this.countExemplary = countExemplary;
		this.dateOfExemplary = dateOfExemplary;
	}



	public int getIdExemplary() {
		return idExemplary;
	}

	public void setIdExemplary(int idExemplary) {
		this.idExemplary = idExemplary;
	}

	public int getCountExemplary() {
		return countExemplary;
	}

	public void setCountExemplary(int countExemplary) {
		this.countExemplary = countExemplary;
	}

	public Book getExemplaryBook() {
		return exemplaryBook;
	}

	public void setExemplaryBook(Book exemplaryBook) {
		this.exemplaryBook = exemplaryBook;
	}


	public Date getDateOfExemplary() {
		return dateOfExemplary;
	}


	public void setDateOfExemplary(Date dateOfExemplary) {
		this.dateOfExemplary = dateOfExemplary;
	}
	
}
