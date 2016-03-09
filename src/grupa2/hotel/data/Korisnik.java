package grupa2.hotel.data;

import java.sql.Date;
import java.util.List;

/**
 * @author Ensar
 * @since 8.3.2016
 */
public class Korisnik extends User{
	
	private Date datumDolaska;
	private Date datumOdlaska;
	private List<Usluga> usluga;
	private String brojLicne;
	
	public Korisnik(int id, String username, String password, String ime, String prezime, byte godine) {
		super(id, username, password, ime, prezime, godine);	
	}
	
	public Korisnik(int id, String username, String password, String ime, String prezime, byte godine,Date dolazak,Date odlazak) {
		super(id, username, password, ime, prezime, godine);
		this.datumDolaska = dolazak;
		this.datumOdlaska = odlazak;
	}
	
	public Date getDatumDolaska() {
		return datumDolaska;
	}

	public Date getDatumOdlaska() {
		return datumOdlaska;
	}

	public void setDatumDolaska(Date datumDolaska) {
		this.datumDolaska = datumDolaska;
	}

	public void setDatumOdlaska(Date datumOdlaska) {
		this.datumOdlaska = datumOdlaska;
	}

	
	public List<Usluga> getUsluge() {
		return usluga;
	}

	public void setUsluga(Usluga usluga) {
		this.usluga.add(usluga);
	}

	public String getBrojLicne() {
		return brojLicne;
	}

	public void setBrojLicne(String brojLicne) {
		this.brojLicne = brojLicne;
	}
	
	
	
	
	

	
	
	
	
	
	
	
}
