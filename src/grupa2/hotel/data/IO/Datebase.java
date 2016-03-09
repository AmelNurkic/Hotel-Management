package grupa2.hotel.data.IO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * @author Ensar
 * @since 8.3.2016
 */
public class Datebase {

	private static Datebase konektuj = null;
	
	private String DB_USER = "root";
	private String DB_PASS = "";
	private String DB_LINK = "jdbc:mysql://localhost/hotel";	
	private Connection conection;
	
	private Datebase() throws SQLException{
		conection = DriverManager.getConnection(DB_LINK,DB_USER,DB_PASS);
	}
	
	public static Datebase getConnection() throws SQLException{
		if(konektuj == null){
			konektuj = new Datebase();
		}
		return konektuj;
	}

	public Connection connected() {
		return this.conection;
	}

	public void dissConnect(){
		if(konektuj != null){
			try {
				konektuj.conection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
