package grupa2.hotel.data.IO;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
 *        user CRUD klasa
 */
public class UserDao implements IuserDao {

	@Override
	public <T> void dodajKorisnika(T korisnik) throws SQLException {
		Connection con = Datebase.getConnection().connected();
		Statement st = con.createStatement();

		if (korisnik instanceof Korisnik) {

			String sikul = "INSERT INTO  users (name,surname,idCardNumber,timeIn,timeOut,age,username,password,isAdmin) VALUES "
					+ "('" + ((Korisnik) korisnik).getIme() + "','" + ((Korisnik) korisnik).getPrezime() + "','"
					+ ((Korisnik) korisnik).getBrojLicne() + "','" + ((Korisnik) korisnik).getDatumDolaska() + "','"
					+ ((Korisnik) korisnik).getDatumOdlaska() + "','" + ((Korisnik) korisnik).getGodine() + "','"
					+ ((Korisnik) korisnik).getUsername() + "','" + ((Korisnik) korisnik).getPassword() + "','"

					+ 0 + "')";

			st.executeUpdate(sikul);

		} else {
			String sikulAdmin = "INSERT INTO  users (name,surname,age,username,password,isAdmin) VALUES " + "('"
					+ ((Administrator) korisnik).getIme() + "','" + ((Administrator) korisnik).getPrezime() + "','"
					+ ((Administrator) korisnik).getGodine() + "','" + ((Administrator) korisnik).getUsername() + "','"
					+ ((Administrator) korisnik).getPassword() + "','" + 1 + "')";

			st.executeUpdate(sikulAdmin);
		}

	}

	@Override
	public Korisnik getKorisnik(Korisnik korisnik) throws SQLException {
		Connection con = Datebase.getConnection().connected();

		PreparedStatement statement = con
				.prepareStatement("SELECT * FROM users WHERE idCardNumber = '" + korisnik.getBrojLicne() + "'");
		ResultSet result = statement.executeQuery();
		Korisnik koris = null;

		while (result.next()) {
			koris = new Korisnik(result.getInt(1), result.getString(8), result.getString(9), result.getString(2),
					result.getString(3), result.getByte(4), result.getDate(6), result.getDate(7));
			koris.setBrojLicne(result.getString(5));
		}

		return koris;

	}

	/**
	 * procitaj korisnika iz baze sa id kljucem
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Korisnik getKorisnik(int id) throws SQLException {
		Connection con = Datebase.getConnection().connected();

		PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE userid = '" + id + "'");
		ResultSet result = statement.executeQuery();
		Korisnik koris = null;

		while (result.next()) {
			koris = new Korisnik(result.getInt(1), result.getString(8), result.getString(9), result.getString(2),
					result.getString(3), result.getByte(4), result.getDate(6), result.getDate(7));
			koris.setBrojLicne(result.getString(5));
		}

		return koris;

	}

	/* provjeri skupo */
	public User getKorisnik(String usern, String pass) throws SQLException {
		Connection con = Datebase.getConnection().connected();

		PreparedStatement statement = con.prepareStatement(
				"SELECT * FROM users WHERE username = '" + usern + "'" + "and password ='" + pass + "'");

		ResultSet result = statement.executeQuery();
		User koris = null;

		while (result.next()) {
			// isAdmin
			if (result.getBoolean("isAdmin")) {
				koris = new Administrator(result.getInt(1), result.getString(8), result.getString(9),
						result.getString(2), result.getString(3), result.getByte(4));
			} else {
				koris = new Korisnik(result.getInt(1), result.getString(8), result.getString(9), result.getString(2),
						result.getString(3), result.getByte(4), result.getDate(6), result.getDate(7));

			}
		}

		return koris;
	}

	// pretraga korisnika po korisnickom imenu
	public User getKorisnik(String username) throws SQLException {
		Connection con = Datebase.getConnection().connected();

		PreparedStatement statement = con.prepareStatement("SELECT * FROM users WHERE username = '" + username);

		ResultSet result = statement.executeQuery();
		User koris = null;

		while (result.next()) {
			// isAdmin
			if (result.getInt(10)==1) {
				koris = new Administrator(result.getInt(1), result.getString(8), result.getString(9),
						result.getString(2), result.getString(3), result.getByte(4));
			} else {
				koris = new Korisnik(result.getInt(1), result.getString(8), result.getString(9), result.getString(2),
						result.getString(3), result.getByte(4), result.getDate(6), result.getDate(7));

			}
		}

		return koris;
	}

	/**
	 * Ovo get svi korisnici nebi trebalo bit ovako ali u hotelu nebi trebalo
	 * bit previse korisnika pa necemo imat dosta toga u heapu
	 * 
	 * @throws SQLException
	 */
	@Override
	public Set<User> getSviKorisnici() throws SQLException {
		HashSet<User> setUsera = new HashSet<>();
		Connection con = Datebase.getConnection().connected();

		Statement st = con.createStatement();
		ResultSet rezultat = st.executeQuery("select * from users");

		while (rezultat.next()) {
			if (rezultat.getBoolean("isAdmin")) {
				setUsera.add(new Administrator(rezultat.getInt("userid"), rezultat.getString("username"),
						rezultat.getString("password"), rezultat.getString("name"), rezultat.getString("surname"),
						(byte) rezultat.getInt("age")));
			} else {
				Korisnik koris = new Korisnik(rezultat.getInt("userid"), rezultat.getString("username"),
						rezultat.getString("password"), rezultat.getString("name"), rezultat.getString("surname"),
						(byte) rezultat.getInt("age"), rezultat.getDate("timeIn"), rezultat.getDate("timeOut"));
				koris.setBrojLicne(rezultat.getString("idCardNumber"));
				setUsera.add(koris);
			}

		}
		return setUsera;
	}

	@Override
	public void updateKorisnik(Korisnik korisnik) throws SQLException {
		// create connection
		Connection con = Datebase.getConnection().connected();
		// create query for editing user
		PreparedStatement statement = con.prepareStatement(
				"UPDATE `users` SET `name` = ?, `surname` = ?, `age` = ?, `timeIn` = ?, `timeOut` = ?, `username` = ?, `password` = ? WHERE `users`.`userid` = ?;");
		// change user info in database
		statement.setString(1, korisnik.getIme());
		statement.setString(2, korisnik.getPrezime());
		statement.setByte(3, korisnik.getGodine());
		statement.setDate(4, korisnik.getDatumDolaska());
		statement.setDate(5, korisnik.getDatumOdlaska());
		statement.setString(6, korisnik.getUsername());
		statement.setString(7, korisnik.getPassword());
		statement.setInt(8, korisnik.getId());

		statement.executeUpdate();
		con.close();

	}

	/**
	 * Brise korisnika po id-eu
	 */
	@Override
	public void izbrisiKorisnika(int id) throws SQLException {
		Connection con = Datebase.getConnection().connected();
		Statement st = con.createStatement();
		String sikul = "delete from users where id='" + id + "'";
		st.executeQuery(sikul);

	}

	/**
	 * Brise korisnika po broju licne karte
	 */
	public void izbrisiKorisnika(Korisnik korisnik) throws SQLException {
		Connection con = Datebase.getConnection().connected();
		String idKorinik = korisnik.getBrojLicne();
		Statement st = con.createStatement();
		String sikul = "delete from users where idCardNumber='" + idKorinik + "'";
		st.executeQuery(sikul);

	}

	/**
	 * prijavi korisnika
	 */
	public static void logIn(Korisnik korisnik) throws SQLException {

		Connection con = Datebase.getConnection().connected();
		Statement state = con.createStatement();
		ResultSet rezultat = state
				.executeQuery("select * from users where idCardNumber ='" + korisnik.getBrojLicne() + "'");

		if (rezultat.getBoolean("isLoggedIn") == false) {
			Statement st = con.createStatement();
			st.executeQuery(
					"update 'users' set 'isLoggedIn' = " + 1 + "where idCardNumber ='" + korisnik.getBrojLicne() + "'");
		}

	}

	/**
	 * odjavi korisnika
	 */
	public static void logOut(Korisnik korisnik) throws SQLException {
		Connection con = Datebase.getConnection().connected();
		Statement state = con.createStatement();
		ResultSet rezultat = state
				.executeQuery("select * from users where idCardNumber ='" + korisnik.getBrojLicne() + "'");

		if (rezultat.getBoolean("isLoggedIn") == true) {
			Statement st = con.createStatement();
			st.executeQuery(
					"update 'users' set 'isLoggedIn' = " + 0 + "where idCardNumber ='" + korisnik.getBrojLicne() + "'");
		}
	}

	public void dodajKorisnikaUArhivu(Korisnik korisnik) throws SQLException {
		Connection con = Datebase.getConnection().connected();
		Statement st = con.createStatement();

		String kve = "INSERT INTO  usersArhiva (name,surname,idCardNumber,timeIn,timeOut,age,username,password,isAdmin) VALUES "
				+ "('" + korisnik.getIme() + "','" + korisnik.getPrezime() + "','" + korisnik.getBrojLicne() + "','"
				+ korisnik.getDatumDolaska() + "','" + korisnik.getDatumOdlaska() + "','" + korisnik.getGodine() + "','"
				+ korisnik.getUsername() + "','" + korisnik.getPassword() + "','"

				+ 0 + "')";

		st.executeUpdate(kve);
	}

	/**
	 * napravi novog korisnika iz arhive
	 */
	public Korisnik getKorisnikIzArhive(String brojLicne) throws SQLException {
		Connection con = Datebase.getConnection().connected();

		PreparedStatement statement = con
				.prepareStatement("SELECT * FROM users WHERE idCardNumber = '" + brojLicne + "'");
		ResultSet result = statement.executeQuery();
		Korisnik koris = null;

		while (result.next()) {
			koris = new Korisnik(result.getInt(1), result.getString(8), result.getString(9), result.getString(2),
					result.getString(3), result.getByte(4), result.getDate(6), result.getDate(7));
			koris.setBrojLicne(result.getString(5));
		}

		return koris;
	}

	public static boolean postojiLicna(String brojLicne) throws SQLException {
		// Connection con = Datebase.getConnection().connected();
		return false;
	}

}