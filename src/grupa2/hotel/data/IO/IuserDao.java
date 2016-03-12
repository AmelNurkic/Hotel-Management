package grupa2.hotel.data.IO;

import grupa2.hotel.data.Korisnik;
import grupa2.hotel.data.User;

import java.sql.SQLException;
import java.util.Set;


/**
 * @author Ensar
 * @since 8.3.2016
 */
public interface IuserDao {

	public <T> void dodajKorisnika(T korisnik)     throws SQLException;
	public User getKorisnik(Korisnik korisnik)     throws SQLException;
	public Set<User> getSviKorisnici()             throws SQLException;
	public void updateKorisnik(Korisnik korisnik)  throws SQLException;
	public void izbrisiKorisnika(int id)           throws SQLException;
	
}