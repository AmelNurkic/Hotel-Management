package grupa2.hotel.data.IO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;


import grupa2.hotel.data.Administrator;
import grupa2.hotel.data.Korisnik;
import grupa2.hotel.data.User;


/**
 * @author Ensar 
 * @since 8.3.2016
 *
 * user CRUD klasa
 */
public class UserDao implements IuserDao{
	
	@Override
	public <T> void dodajKorisnika(T korisnik)throws SQLException{
		Connection con = Datebase.getConnection().connected();
		Statement st = con.createStatement();
	    
		if(korisnik instanceof Korisnik){
			
		   String sikul = "INSERT INTO  users (name,surname,idCardNumber,timeIn,timeOut,age,username,password,isAdmin) VALUES "+"("
				+((Korisnik) korisnik).getIme()+","
				+((Korisnik) korisnik).getPrezime()+","
				+((Korisnik) korisnik).getBrojLicne()+","
				+((Korisnik) korisnik).getDatumDolaska()+","
				+((Korisnik) korisnik).getDatumOdlaska()+","
                +((Korisnik) korisnik).getGodine()+","
				+((Korisnik) korisnik).getUsername()+","
                +((Korisnik) korisnik).getPassword()+","
                
                +false+")";
		
		    st.executeUpdate(sikul);
		    con.close();
		}else{
			  String sikulAdmin = "INSERT INTO  users (name,surname,age,username,password,isAdmin) VALUES "+"('"
						+((Administrator) korisnik).getIme()+"','"
						+((Administrator) korisnik).getPrezime()+"','"				
		                +((Administrator) korisnik).getGodine()+"','"
						+((Administrator) korisnik).getUsername()+"','"
		                +((Administrator) korisnik).getPassword()+"','"
		                +1+"')";
			  
			  st.executeUpdate(sikulAdmin);
			    con.close();
		}
		
		
	}
	@Override
	public User getKorisnik(int id){
		return null;
		//Connection con = Datebase.getConnection().connected();
	}
	/**
	 * Ovo get svi korisnici nebi trebalo bit ovako ali u hotelu nebi trebalo bit 
	 * previse korisnika
	 * @throws SQLException 
	 */
	@Override
	public Set<User> getSviKorisnici() throws SQLException{
		HashSet<User> setUsera = new HashSet<>();
		Connection con = Datebase.getConnection().connected();
		
		try(Statement st = con.createStatement()){
			ResultSet rezultat = st.executeQuery("select * from users");		
			
			while(rezultat.next()){
				if(rezultat.getBoolean("isAdmin")){
					setUsera.add(new Administrator(rezultat.getInt("userid"),
							rezultat.getString("username"),
							rezultat.getString("password"),
							rezultat.getString("name"),
							rezultat.getString("surname"),
							(byte)rezultat.getInt("age")));
				}else{
					
					setUsera.add(new Korisnik(rezultat.getInt("userid"),
							rezultat.getString("username"),
							rezultat.getString("password"),
							rezultat.getString("name"),
							rezultat.getString("surname"),
							(byte)rezultat.getInt("age"),
							rezultat.getDate("timeIn"),
							rezultat.getDate("timeOut")));
				}
				
			}
			
			return setUsera;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void updateKorisnik(Korisnik korisnik){
		
	}
	
	@Override
	public void izbrisiKorisnika(int id) throws SQLException{
		Connection con = Datebase.getConnection().connected();
		try(Statement st = con.createStatement()){
			String sikul ="delete from users where id='"+id+"'";
			st.executeQuery(sikul);
		}

	}
}