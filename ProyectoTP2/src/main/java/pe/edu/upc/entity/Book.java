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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "Book")
public class Book implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBook;

	@NotEmpty(message = "El nombre del libro es obligatorio")
	@Pattern(regexp = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s[a-zA-ZÀ-ÿ\\u00f1\\u00d1])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$", message = "El nombre solo puede tener letras")
	@Column(name = "nameBook", nullable = false, length = 45)
	private String nameBook;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateOfAdmissionBook;

	//@Positive(message = "Solo numeros positivos.")
	//@NotNull(message="La cantidad es obligatoria")
	@Column(name = "editionBook", nullable = false, length = 2)
	private int editionBook;

	
	//@Positive(message = "Solo numeros positivos.")
	//@NotNull(message="La cantidad es obligatoria")
	@Column(name = "serieBook", nullable = false, length = 2)
	private int serieBook;

	
	//@NotEmpty(message = "El nombre del libro es obligatorio")
	@Pattern(regexp = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s[a-zA-ZÀ-ÿ\\u00f1\\u00d1])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$", message = "Solo puede tener letras")
	@Column(name = "languageBook", nullable = false, length = 45)
	private String languageBook;

	@ManyToOne
	@JoinColumn(name = "idAuthor", nullable = false)
	private Author authorBook;
	
	private String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(int idBook, String nameBook, Date dateOfAdmissionBook, int editionBook, int serieBook,
			String languageBook, Author author, String foto) {
		super();
		this.idBook = idBook;
		this.nameBook = nameBook;
		this.dateOfAdmissionBook = dateOfAdmissionBook;
		this.editionBook = editionBook;
		this.serieBook = serieBook;
		this.languageBook = languageBook;
		this.authorBook = author;
		this.foto = foto;
	}

	public int getIdBook() {
		return idBook;
	}

	public void setIdBook(int idBook) {
		this.idBook = idBook;
	}

	public String getNameBook() {
		return nameBook;
	}

	public void setNameBook(String nameBook) {
		this.nameBook = nameBook;
	}

	public Date getDateOfAdmissionBook() {
		return dateOfAdmissionBook;
	}

	public void setDateOfAdmissionBook(Date dateOfAdmissionBook) {
		this.dateOfAdmissionBook = dateOfAdmissionBook;
	}

	public int getEditionBook() {
		return editionBook;
	}

	public void setEditionBook(int editionBook) {
		this.editionBook = editionBook;
	}

	public int getSerieBook() {
		return serieBook;
	}

	public void setSerieBook(int serieBook) {
		this.serieBook = serieBook;
	}

	public String getLanguageBook() {
		return languageBook;
	}

	public void setLanguageBook(String languageBook) {
		this.languageBook = languageBook;
	}

	public Author getAuthor() {
		return authorBook;
	}

	public void setAuthor(Author author) {
		this.authorBook = author;
	}

}
