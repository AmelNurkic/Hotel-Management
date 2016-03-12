package grupa2.hotel.data.IO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import grupa2.hotel.data.Korisnik;
import grupa2.hotel.data.Usluga;


/**
 * @author Ensar
 * @since 10.3.2016
 */
public class UslugaDao {

	
	/**
	 * Get usluga za korisnika 
	 * @param kor
	 * @return
	 * @throws SQLException 
	 */
	public List<Usluga> getUsluga(Korisnik kor) throws SQLException{
		ArrayList<Usluga> listaUsluga = new ArrayList<>();
		Connection con = Datebase.getConnection().connected();
		Statement statement = con.createStatement();
		ResultSet res = statement.executeQuery("select * from usluge where brojLicneKorisnika = '"+kor.getBrojLicne()+"'");	
		while(res.next()){
			listaUsluga.add(new Usluga(res.getString("imeUsluge"), res.getInt("cijenaUsluge")));
		}
		
		return listaUsluga;	
	}
	
	/**
	 * upis usluge u bazu
	 * @param usluga
	 * @throws SQLException 
	 */
	public void createUsluga(Usluga usluga,Korisnik korisnikUsluge) throws SQLException{
		Connection conect = Datebase.getConnection().connected();
		Statement statem = conect.createStatement();
		
		String sikul = "insert into  usluge (brojLicneKorisnika,imeUsluge,cijenaUsluge) VALUES"+
		                                     "('"+korisnikUsluge.getBrojLicne()+"','"
				                                 +usluga.getImeUsluge()+"','"
		                                         +usluga.getCijenaUsluge()+"')";
		
		statem.executeUpdate(sikul);
		
		
	}
	
}