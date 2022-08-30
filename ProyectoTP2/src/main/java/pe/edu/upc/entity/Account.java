package pe.edu.upc.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Entity
@Table (name="Account")
public class Account implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int idAccount;
	@Column (name="nameAccount", nullable=false, length=45)
	private String nameAccount;	
	@Column (name="lastNameAccount", nullable=false, length=45)	
	private String lastNameAccount;
	
	
	@Column (name="correoAccount", nullable=false, length=45)
	private String correoAccount;
	
	@Positive(message = "Solo numeros positivos.")
	@NotNull(message="La cantidad es obligatoria")
	@Column (name="dniAccount", nullable=false, length=8)
	private int dniAccount;
	@Column (name="passwordAccount", nullable=false, length = 200)
	private String passwordAccount;
	
	
	
	
	@ManyToOne
	@JoinColumn(name = "idRole", nullable = false)
	private Role roleAccount;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(int idAccount, String nameAccount, String lastNameAccount, String correoAccount, int dniAccount,
			String passwordAccount, Role roleAccount) {
		super();
		this.idAccount = idAccount;
		this.nameAccount = nameAccount;
		this.lastNameAccount = lastNameAccount;
		this.correoAccount = correoAccount;
		this.dniAccount = dniAccount;
		this.passwordAccount = passwordAccount;
		this.roleAccount = roleAccount;
	}

	public int getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(int idAccount) {
		this.idAccount = idAccount;
	}

	public String getNameAccount() {
		return nameAccount;
	}

	public void setNameAccount(String nameAccount) {
		this.nameAccount = nameAccount;
	}

	public String getLastNameAccount() {
		return lastNameAccount;
	}

	public void setLastNameAccount(String lastNameAccount) {
		this.lastNameAccount = lastNameAccount;
	}

	public String getCorreoAccount() {
		return correoAccount;
	}

	public void setCorreoAccount(String correoAccount) {
		this.correoAccount = correoAccount;
	}

	public int getDniAccount() {
		return dniAccount;
	}

	public void setDniAccount(int dniAccount) {
		this.dniAccount = dniAccount;
	}

	public String getPasswordAccount() {
		return passwordAccount;
	}

	public void setPasswordAccount(String passwordAccount) {
		this.passwordAccount = passwordAccount;
	}

	public Role getRoleAccount() {
		return roleAccount;
	}

	public void setRoleAccount(Role roleAccount) {
		this.roleAccount = roleAccount;
	}
	
	
	
	
	
	
	
}
