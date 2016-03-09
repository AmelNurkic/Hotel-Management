package grupa2.hotel;

import java.sql.SQLException;

import grupa2.hotel.data.Administrator;
import grupa2.hotel.data.IO.UserDao;

public class HotelApplication {

	public static void main(String[] args) {
		UserDao dao = new UserDao();
		Administrator ds = new Administrator(12, "Ensar", "Bavrk", "ensar", "bavrk", (byte) 19);
		
		
		try {
			
			
			dao.dodajKorisnika(ds);
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
