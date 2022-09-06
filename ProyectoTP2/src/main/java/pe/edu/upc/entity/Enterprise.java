package pe.edu.upc.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;

@Entity
@Table(name = "Empresa")
public class Enterprise implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idEnterprise;

	// @NotEmpty(message = "El nombre es obligatorio")
    //@Pattern(regexp = "[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+(\\s[a-zA-ZÀ-ÿ\\u00f1\\u00d1])*[a-zA-ZÀ-ÿ\\u00f1\\u00d1]+$", message = "El nombre solo puede tener letras")
	
	@Column(name = "nameEnterprise", nullable = false, length = 45)
	private String nameEnterprise;

    @Positive(message = "Solo numeros positivos.")
	@NotNull(message="La cantidad es obligatoria")
    @Column(name = "rucEnterprise", nullable = false, length = 11)
	private int rucEnterprise;

	public Enterprise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Enterprise(int idEnterprise, String nameEnterprise, int rucEnterprise) {
		super();
		this.idEnterprise = idEnterprise;
		this.nameEnterprise = nameEnterprise;
		this.rucEnterprise = rucEnterprise;
	}

	public int getIdEnterprise() {
		return idEnterprise;
	}

	public void setIdEnterprise(int idEnterprise) {
		this.idEnterprise = idEnterprise;
	}

	public String getNameEnterprise() {
		return nameEnterprise;
	}

	public void setNameEnterprise(String nameEnterprise) {
		this.nameEnterprise = nameEnterprise;
	}

	public int getRucEnterprise() {
		return rucEnterprise;
	}

	public void setRucEnterprise(int rucEnterprise) {
		this.rucEnterprise = rucEnterprise;
	}

}
