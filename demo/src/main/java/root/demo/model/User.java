package root.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_table")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "ime", unique = false, nullable = false)
	String ime;
	@Column(name = "prezime", unique = false, nullable = false)
	String prezime;
	@Column(name = "grad", unique = false, nullable = false)
	String grad;
	@Column(name = "drzava", unique = false, nullable = false)
	String drzava;
	@Column(name = "titula", unique = false, nullable = true)
	String titula;
	@Column(name = "email", unique = false, nullable = false)
	String email;
	@Column(name = "naucne_oblasti", unique = false, nullable = true)
	String naucneOblasti;
	@Column(name = "username", unique = true, nullable = false)
	String username;
	@Column(name = "password", unique = false, nullable = false)
	String password;
	@Column(name = "uloga", unique = false, nullable = true)
	String uloga;
	@Column(name = "hashCode", unique = false, nullable = true)
	String hashCode;


	public String getHashCode() {
		return hashCode;
	}

	public void setHashCode(String hashCode) {
		this.hashCode = hashCode;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	public User() {}

	public User(String ime,	String prezime,	String grad, String drzava, String titula, String email, String username, String password, String oblasti) {
		this.ime = ime;
		this.prezime = prezime;
		this.grad = grad;
		this.drzava = drzava;
		this.titula = titula;
		this.email = email;
		this.username = username;
		this.password = password;
		this.naucneOblasti = oblasti;
	}
	
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}

	public String getDrzava() {
		return drzava;
	}

	public void setDrzava(String drzava) {
		this.drzava = drzava;
	}

	public String getTitula() {
		return titula;
	}

	public void setTitula(String titula) {
		this.titula = titula;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNaucneOblasti() {
		return naucneOblasti;
	}

	public void setNaucneOblasti(String naucneOblasti) {
		this.naucneOblasti = naucneOblasti;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
}
