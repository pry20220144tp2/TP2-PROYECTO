package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Autor")
public class Author implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAuthor;

	// @NotEmpty(message = "El nombre es obligatorio")
    @Pattern(regexp = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s[a-zA-ZÀ-ÿ\\u00f1\\u00d1])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$", message = "El nombre solo puede tener letras")
	
	@Column(name = "nameAuthor", nullable = false, length = 45)
	private String nameAuthor;

	// @NotEmpty(message = "El apellido es obligatorio")
	@Pattern(regexp = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s[a-zA-ZÀ-ÿ\\u00f1\\u00d1])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$", message = "El apellido solo puede tener letras")
	@Column(name = "lastnameAuthor", nullable = false, length = 45)
	private String lastnameAuthor;

	private String foto;

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Author() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Author(int idAuthor, String nameAuthor, String lastnameAuthor, String foto) {
		super();
		this.idAuthor = idAuthor;
		this.nameAuthor = nameAuthor;
		this.lastnameAuthor = lastnameAuthor;
		this.foto = foto;
	}

	public int getIdAuthor() {
		return idAuthor;
	}

	public void setIdAuthor(int idAuthor) {
		this.idAuthor = idAuthor;
	}

	public String getNameAuthor() {
		return nameAuthor;
	}

	public void setNameAuthor(String nameAuthor) {
		this.nameAuthor = nameAuthor;
	}

	public String getLastnameAuthor() {
		return lastnameAuthor;
	}

	public void setLastnameAuthor(String lastnameAuthor) {
		this.lastnameAuthor = lastnameAuthor;
	}

}
