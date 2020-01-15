package root.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "magazine_table")
public class Magazine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "naziv", unique = false, nullable = false)
	String naziv;
	
	@Column(name = "issn", unique = false, nullable = false)
	Long issn;
	
	@Column(name = "naucneOblasti", unique = false, nullable = false)
	String naucneOblasti;
	
	@Column(name = "nacinPlacanja", unique = false, nullable = false)
	String nacinPlacanja;
	
	@Column(name = "glavniUrednik", unique = false, nullable = false)
	String glavniUrednik;
	
	@Column(name = "aktivan", unique = false, nullable = false)
	String aktivan;
	
	@Column(name = "recenzent1", unique = false, nullable = true)
	String recenzent1;
	
	@Column(name = "recenzent2", unique = false, nullable = true)
	String recenzent2;
	
	@Column(name = "urednik1", unique = false, nullable = true)
	String urednik1;
	
	@Column(name = "urednik2", unique = false, nullable = true)
	String urednik2;
	
	public Magazine() {}
	
	public Magazine(String naziv, Long issn, String naucneOblasti, String nacinPlacanja) {
		this.naziv = naziv;
		this.issn = issn;
		this.naucneOblasti = naucneOblasti;
		this.nacinPlacanja = nacinPlacanja;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Long getIssn() {
		return issn;
	}

	public void setIssn(Long issn) {
		this.issn = issn;
	}

	public String getNaucneOblasti() {
		return naucneOblasti;
	}

	public void setNaucneOblasti(String naucneOblasti) {
		this.naucneOblasti = naucneOblasti;
	}

	public String getNacinPlacanja() {
		return nacinPlacanja;
	}

	public void setNacinPlacanja(String nacinPlacanja) {
		this.nacinPlacanja = nacinPlacanja;
	}

	public String getGlavniUrednik() {
		return glavniUrednik;
	}

	public void setGlavniUrednik(String glavniUrednik) {
		this.glavniUrednik = glavniUrednik;
	}

	public String getAktivan() {
		return aktivan;
	}

	public void setAktivan(String aktivan) {
		this.aktivan = aktivan;
	}

	public String getRecenzent1() {
		return recenzent1;
	}

	public void setRecenzent1(String recenzent1) {
		this.recenzent1 = recenzent1;
	}

	public String getRecenzent2() {
		return recenzent2;
	}

	public void setRecenzent2(String recenzent2) {
		this.recenzent2 = recenzent2;
	}

	public String getUrednik1() {
		return urednik1;
	}

	public void setUrednik1(String urednik1) {
		this.urednik1 = urednik1;
	}

	public String getUrednik2() {
		return urednik2;
	}

	public void setUrednik2(String urednik2) {
		this.urednik2 = urednik2;
	}
	
}
