package grupa2.hotel.data;

/**
 * @author Ensar
 * @since 8.3.2016
 */
public abstract class User {
	
	private int id;
	private String username;
	private String password;
	private String ime;
	private String prezime;
	private byte godine;
	
	public User(int id, String username, String password, String ime, String prezime, byte godine) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.ime = ime;
		this.prezime = prezime;
		this.godine = godine;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public void setGodine(byte godine) {
		this.godine = godine;
	}

	public int getId(){
		return id;
	}
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getIme() {
		return ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public byte getGodine() {
		return godine;
	}
	
}