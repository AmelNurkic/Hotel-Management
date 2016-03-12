package grupa2.hotel.data;

/**
 * 
 * @author Ensar
 * @since 8.3.2016
 */
public class Usluga {
	
    /*ovo moze bit enum  -imena usluga*/
	public static final String JEDNOKREVETNA_SOBA = "Jednokrevetna soba";
	public static final String DVOKREVETNA_SOBA   = "Dvokrevetna soba";
	public static final String APARTMAN           = "Apartman";
	public static final String TERETANA           = "Teretana ";
	public static final String KINO               = "Kino";
	public static final String RESTORAN           = "Restoran";
	public static final String BAZEN              = "Bazen";
	public static final String SAUNA              = "Sauna";
	/* -cijene usluga*/
	public static final int CIJENA_JEDNOKREVETNE_SOBE = 30;
	public static final int CIJENA_DVOKREVETNE_SOBE = 60;
	public static final int CIJENA_APARTMANA = 120;
	public static final int CIJENA_TERETANA = 10;
	public static final int CIJENA_KINO = 10;
	public static final int CIJENA_RESTORAN = 10;
	public static final int CIJENA_BAZEN = 10;
	public static final int CIJENA_SAUNA = 10;
	
	
	
	
	private String imeUsluge;
	//znam da su danas cijene 2.99 , 0.85  al' jebes ga
	private int cijenaUsluge;
	
	
	public Usluga() { }

	public Usluga(String imeUsluge, int cijenaUsluge) {
		this.imeUsluge = imeUsluge;
		this.cijenaUsluge = cijenaUsluge;
	}
	
	public String getImeUsluge() {
		return imeUsluge;
	}
	
	public void setImeUsluge(String imeUsluge) {
		this.imeUsluge = imeUsluge;
	}
	
	public int getCijenaUsluge() {
		return cijenaUsluge;
	}
	public void setCijenaUsluge(int cijenaUsluge) {
		this.cijenaUsluge = cijenaUsluge;
	}
	
	
	
}