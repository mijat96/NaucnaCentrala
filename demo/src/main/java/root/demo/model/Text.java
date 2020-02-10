package root.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "text_table")
public class Text {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;
	
	@Column(name = "naslov", unique = false, nullable = false)
	String naslov;
	
	@Column(name = "tekst", unique = false, nullable = false)
	String tekst;
	
	@Column(name = "naucnaOblast", unique = false, nullable = false)
	String naucnaOblast;
	
	@Column(name = "glavniUrednik", unique = false, nullable = false)
	String glavniUrednik;
	
	@Column(name = "aktivan", unique = false, nullable = false)
	String aktivan;
	
	@Column(name = "recenzent1", unique = false, nullable = true)
	String recenzent1;
	
	@Column(name = "recenzent2", unique = false, nullable = true)
	String recenzent2;
	
	@Column(name = "magazine", unique = false, nullable = true)
	String magazine;
	
	@Column(name = "autor", unique = false, nullable = true)
	String autor;
	
	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Text() {}
	
	public Text(String naslov, String naucnaOblast, String tekst, String magazine) {
		this.naslov = naslov;
		this.naucnaOblast = naucnaOblast;
		this.tekst = tekst;
		this.magazine = magazine;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public String getNaucnaOblast() {
		return naucnaOblast;
	}

	public void setNaucnaOblast(String naucnaOblast) {
		this.naucnaOblast = naucnaOblast;
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
	
	
	public String getMagazine() {
		return magazine;
	}

	public void setMagazine(String magazine) {
		this.magazine = magazine;
	}

}
